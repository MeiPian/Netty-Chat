package com.meipian.chat.server.spring.support;

import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationContext {

	private static ConfigurableApplicationContext ctx = null;

	private static ApplicationContext instance;

	public String getProperty(String propertyName) {
		return ctx.getEnvironment().getProperty("port");

	}

	public static void init(ConfigurableApplicationContext ctx) {
		ApplicationContext applicationContext = new ApplicationContext();
		applicationContext.ctx = ctx;

	}
	public   static ApplicationContext getInstance(){
		return  instance;
	}
}
