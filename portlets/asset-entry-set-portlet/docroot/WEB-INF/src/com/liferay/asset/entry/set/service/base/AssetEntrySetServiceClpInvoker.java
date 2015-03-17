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

package com.liferay.asset.entry.set.service.base;

import com.liferay.asset.entry.set.service.AssetEntrySetServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetServiceClpInvoker {
	public AssetEntrySetServiceClpInvoker() {
		_methodName46 = "getBeanIdentifier";

		_methodParameterTypes46 = new String[] {  };

		_methodName47 = "setBeanIdentifier";

		_methodParameterTypes47 = new String[] { "java.lang.String" };

		_methodName52 = "addAssetEntrySet";

		_methodParameterTypes52 = new String[] {
				"long", "long", "long", "java.lang.String", "boolean"
			};

		_methodName53 = "addFileAttachment";

		_methodParameterTypes53 = new String[] { "java.io.File" };

		_methodName54 = "deleteAssetEntrySet";

		_methodParameterTypes54 = new String[] { "long" };

		_methodName55 = "getAssetEntrySet";

		_methodParameterTypes55 = new String[] { "long", "int", "int" };

		_methodName56 = "getNewAssetEntrySets";

		_methodParameterTypes56 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String[][]",
				"int", "int", "int", "int"
			};

		_methodName57 = "getOldAssetEntrySets";

		_methodParameterTypes57 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String[][]",
				"int", "int", "int", "int"
			};

		_methodName58 = "likeAssetEntrySet";

		_methodParameterTypes58 = new String[] { "long" };

		_methodName59 = "unlikeAssetEntrySet";

		_methodParameterTypes59 = new String[] { "long" };

		_methodName60 = "updateAssetEntrySet";

		_methodParameterTypes60 = new String[] {
				"long", "com.liferay.portal.kernel.json.JSONObject", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return AssetEntrySetServiceUtil.getBeanIdentifier();
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			AssetEntrySetServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return AssetEntrySetServiceUtil.addAssetEntrySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return AssetEntrySetServiceUtil.addFileAttachment((java.io.File)arguments[0]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return AssetEntrySetServiceUtil.deleteAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return AssetEntrySetServiceUtil.getAssetEntrySet(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return AssetEntrySetServiceUtil.getNewAssetEntrySets(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.lang.String[])arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return AssetEntrySetServiceUtil.getOldAssetEntrySets(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.lang.String[])arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue());
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return AssetEntrySetServiceUtil.likeAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return AssetEntrySetServiceUtil.unlikeAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return AssetEntrySetServiceUtil.updateAssetEntrySet(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.json.JSONObject)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
}