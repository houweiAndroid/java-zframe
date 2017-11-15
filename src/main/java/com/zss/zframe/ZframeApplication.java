package com.zss.zframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zss.zframe.utils.Constants;

@SpringBootApplication
public class ZframeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZframeApplication.class, args);
		Constants.init();
	}
	
}
