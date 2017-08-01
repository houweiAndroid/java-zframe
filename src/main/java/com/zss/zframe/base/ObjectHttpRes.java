package com.zss.zframe.base;

public class ObjectHttpRes implements IHttpRes{
	
	public int ret_code = 1;
	public String ret_error = ""; 
	public Object result;
	
	public int getRet_code() {
		return ret_code;
	}
	public void setRet_code(int ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_error() {
		return ret_error;
	}
	public void setRet_error(String ret_error) {
		this.ret_error = ret_error;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}
