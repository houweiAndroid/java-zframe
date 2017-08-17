package com.zss.zframe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.zss.zframe.rpc.HelloRpcService;
import com.zss.zframe.rpc.HelloRpcServiceImpl;

public class RpcServlet extends HttpServlet {  
   
  private static final long serialVersionUID = 1L;
  
  private JsonRpcServer rpcServer = null;  

  public RpcServlet() {  
      super(); 
      rpcServer = new JsonRpcServer(new ObjectMapper(), new  HelloRpcServiceImpl(), HelloRpcService.class);  
  }  

  @Override  
  protected void service(HttpServletRequest request,  
          HttpServletResponse response) throws ServletException, IOException {  
      System.out.println("-------------JsonRpcService service is call");  
      rpcServer.handleRequest(request.getInputStream(), response.getOutputStream());
  }  
}
