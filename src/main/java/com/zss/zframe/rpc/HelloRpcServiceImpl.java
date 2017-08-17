package com.zss.zframe.rpc;

public class HelloRpcServiceImpl implements HelloRpcService {

	@Override
	public Integer getInt(Integer code) {
		return code;
	}

	@Override
	public String getString(String msg) {
		return " server update " + msg;
	}

	@Override
	public void doSomething() {
		System.out.println("-----doSomething-----");
	}

}
