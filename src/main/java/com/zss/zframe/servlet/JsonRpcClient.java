package com.zss.zframe.servlet;

import java.net.URL;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class JsonRpcClient {

	public static void main(String[] args) throws Throwable {
		try {
			JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://172.16.0.29:8081/rpc"));
			String[] msg = new String[] { "zhangmiao" };
			String response = client.invoke("getString", msg, String.class);
			System.out.println("-------rpc = " + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}