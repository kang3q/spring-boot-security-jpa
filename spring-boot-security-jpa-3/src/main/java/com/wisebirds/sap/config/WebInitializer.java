package com.wisebirds.sap.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.wisebirds.sap.Application;

public class WebInitializer extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
}
