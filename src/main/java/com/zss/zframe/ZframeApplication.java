package com.zss.zframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZframeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZframeApplication.class, args);
		AppConst.init();
	}
	
}
