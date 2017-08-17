package com.zss.zframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.zss.zframe.servlet.RpcServlet;

@SpringBootApplication
public class ZframeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZframeApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new RpcServlet(), "/rpc");
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
	       return new EmbeddedServletContainerCustomizer() {
	           @Override
	           public void customize(ConfigurableEmbeddedServletContainer container) {
	        	   container.setSessionTimeout(60);//单位为秒
	          }
	    };
	}
	
}
