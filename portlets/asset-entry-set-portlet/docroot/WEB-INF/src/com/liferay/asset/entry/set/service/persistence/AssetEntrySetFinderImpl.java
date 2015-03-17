/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.asset.entry.set.service.persistence;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetImpl;
import com.liferay.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Calvin Keum
 * @author Sherry Yang
 */
public class AssetEntrySetFinderImpl
	extends BasePersistenceImpl<AssetEntrySet>
	implements AssetEntrySetFinder {

	public static final String FIND_BY_CT_PAESI_CNI =
		AssetEntrySetFinder.class.getName() + ".findByCT_PAESI_CNI";

	public static final String JOIN_BY_ASSET_SHARING_ENTRY =
		AssetEntrySetFinder.class.getName() + ".joinByAssetSharingEntry";

	public static final String JOIN_BY_ASSET_TAG =
		AssetEntrySetFinder.class.getName() + ".joinByAssetTag";

	public List<AssetEntrySet> findByCT_PAESI_CNI(
			long classNameId, long classPK, long createTime,
			boolean gtCreateTime, long parentAssetEntrySetId,
			JSONArray sharedToJSONArray, String[] assetTagNames, int start,
			int end)
		throws SystemException {

		if (((sharedToJSONArray == null) ||
			 (sharedToJSONArray.length() == 0)) &&
			ArrayUtil.isEmpty(assetTagNames)) {

			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CT_PAESI_CNI);

			sql = StringUtil.replace(
				sql, "[$JOIN_BY$]",
				getJoinBy(sharedToJSONArray, assetTagNames));

			if (gtCreateTime) {
				sql = StringUtil.replace(
					sql, "[$CREATE_TIME_COMPARATOR$]", ">");
			}
			else {
				sql = StringUtil.replace(
					sql, "[$CREATE_TIME_COMPARATOR$]", "<=");
			}

			sql = StringUtil.replace(
				sql, "[$SHARED_TO$]",
				getSharedTo(classNameId, classPK, sharedToJSONArray));
			sql = StringUtil.replace(
				sql, "[$ASSET_TAG_NAMES$]",
				getAssetTagNames(
					classNameId, classPK, sharedToJSONArray, assetTagNames));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetEntrySet", AssetEntrySetImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createTime);
			qPos.add(parentAssetEntrySetId);
			qPos.add(_ASSET_ENTRY_SET_CLASS_NAME_ID);

			setAssetTagNames(qPos, assetTagNames);

			return (List<AssetEntrySet>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getAssetTagNames(
		long classNameId, long classPK, JSONArray sharedToJSONArray,
		String[] assetTagNames) {

		if (ArrayUtil.isEmpty(assetTagNames)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(assetTagNames.length * 4);

		if ((sharedToJSONArray != null) && (sharedToJSONArray.length() > 0)) {
			sb.append(" OR ");
		}

		for (int i = 0; i < assetTagNames.length; i++) {
			sb.append("((AssetTag.name = ?) AND ");
			sb.append(getViewable(classNameId, classPK));
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected String getJoinBy(
		JSONArray sharedToJSONArray, String[] assetTagNames) {

		String joinBy = StringPool.BLANK;

		if ((sharedToJSONArray != null) && (sharedToJSONArray.length() > 0)) {
			joinBy = CustomSQLUtil.get(JOIN_BY_ASSET_SHARING_ENTRY);
		}

		if (ArrayUtil.isNotEmpty(assetTagNames)) {
			joinBy = joinBy + CustomSQLUtil.get(JOIN_BY_ASSET_TAG);
		}

		return joinBy;
	}

	protected String getSharedTo(
		long classNameId, long classPK, JSONArray sharedToJSONArray) {

		if ((sharedToJSONArray == null) || (sharedToJSONArray.length() == 0)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(
			(sharedToJSONArray.length() * 2) + 2);

		sb.append(StringPool.OPEN_PARENTHESIS);

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject jsonObject = sharedToJSONArray.getJSONObject(i);

			long sharedToClassNameId = jsonObject.getLong("classNameId");
			long sharedToClassPK = jsonObject.getLong("classPK");

			sb.append(
				getSharedTo(
					classNameId, classPK, sharedToClassNameId,
					sharedToClassPK));
			sb.append(" OR ");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getSharedTo(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		StringBundler sb = new StringBundler(8);

		sb.append("((AssetSharingEntry2.sharedToClassNameId = ");
		sb.append(sharedToClassNameId);
		sb.append(") AND (AssetSharingEntry2.sharedToClassPK = ");
		sb.append(sharedToClassPK);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		if (!AssetEntrySetParticipantInfoUtil.isMember(
				classNameId, classPK, sharedToClassNameId, sharedToClassPK)) {

			sb.append(" AND ");
			sb.append(getViewable(classNameId, classPK));
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected String getViewable(long classNameId, long classPK) {
		StringBundler sb = new StringBundler(6);

		sb.append("((AssetEntrySet.privateAssetEntrySet = [$FALSE$])");
		sb.append(" OR ((AssetSharingEntry1.sharedToClassNameId = ");
		sb.append(classNameId);
		sb.append(") AND (AssetSharingEntry1.sharedToClassPK = ");
		sb.append(classPK);
		sb.append(")))");

		return sb.toString();
	}

	protected void setAssetTagNames(QueryPos qPos, String[] assetTagNames) {
		if (ArrayUtil.isEmpty(assetTagNames)) {
			return;
		}

		for (String assetTagName : assetTagNames) {
			qPos.add(StringUtil.toLowerCase(assetTagName));
		}
	}

	private static final long _ASSET_ENTRY_SET_CLASS_NAME_ID =
		ClassNameLocalServiceUtil.getClassNameId(AssetEntrySet.class);

}