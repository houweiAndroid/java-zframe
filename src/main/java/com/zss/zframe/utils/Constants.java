package com.zss.zframe.utils;

import java.util.HashMap;


public class Constants {

	private static HashMap<String, String> errMap = new HashMap<>();
	
	public final static String REQ_PARAM_NULL = "1001";
	public final static String PWD_ERROR = "1002";
	public final static String USER_NOT_EXIST = "1003";
	public final static String LOGIN_TIMEOUT = "1004";
	
	public static void init()
	{
		errMap.put(REQ_PARAM_NULL, "%s param is null");
		errMap.put(PWD_ERROR, "密码不正确");
		errMap.put(USER_NOT_EXIST, "用户不存在");
		errMap.put(LOGIN_TIMEOUT, "会话超时");
	}
	
	public static String getError(String code){
		return errMap.get(code);
	}
	
	public static void put(String code, String error) {
		errMap.put(code, error);
	}
}
