package com.zss.zframe.base;

import com.zss.zframe.AppConst;

public class DataRes{
	
	public Integer code = 0;
	public String error = ""; 
	public Object data = "";

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public void setCodeAndError(Integer code) {
		this.code = code;
		String errorStr = AppConst.getError(code);
		setError(errorStr);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
