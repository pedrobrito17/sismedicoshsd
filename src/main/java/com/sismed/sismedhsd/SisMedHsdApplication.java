package com.sismed.sismedhsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SisMedHsdApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
         return application.sources(SisMedHsdApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(SisMedHsdApplication.class, args);
	}

}

	