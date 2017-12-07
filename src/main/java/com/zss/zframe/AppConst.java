package com.zss.zframe;

import java.util.HashMap;


public class AppConst {

	private static HashMap<Integer, String> ERROR_MAP = new HashMap<>();
	
	public static void init()
	{
		ERROR_MAP.put(1001, "%s param is null");
		ERROR_MAP.put(1002, "密码不正确");
		ERROR_MAP.put(1003, "用户不存在");
		ERROR_MAP.put(1004, "会话超时");
	}
	
	public static String getError(Integer code){
		return ERROR_MAP.get(code);
	}
	
	public static void put(Integer code, String error) {
		ERROR_MAP.put(code, error);
	}
}
