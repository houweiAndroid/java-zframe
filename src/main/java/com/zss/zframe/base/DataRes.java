package com.zss.zframe.base;

import com.zss.zframe.utils.Constants;

public class DataRes{
	
	public String code = "0";
	public String error = ""; 
	public Object data = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public void setCodeAndError(String code) {
		this.code = code;
		String errorStr = Constants.getError(code);
		setError(errorStr);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
