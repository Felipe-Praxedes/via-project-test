package com.via;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.via.data.FiliaisUtil;

@SpringBootApplication
public class ViaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViaApiApplication.class, args);
		FiliaisUtil.postFiliais();
		
	}

}
