package com.meipian.chat.server.spring.support;

import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationContext {

	private  ConfigurableApplicationContext ctx = null;

	private static ApplicationContext instance;

	public String getProperty(String propertyName) {
		return ctx.getEnvironment().getProperty("port");

	}

	public  ConfigurableApplicationContext getCtx() {
		return ctx;
	}

	public  void setCtx(ConfigurableApplicationContext ctx) {
		this.ctx = ctx;
	}

	public static void init(ConfigurableApplicationContext ctx) {
		synchronized (ApplicationContext.class) {
			if (instance == null || ctx == null) {
				ApplicationContext applicationContext = new ApplicationContext();
				instance = applicationContext;
				instance.setCtx(ctx);
			}
		}
	}

	public static ApplicationContext getInstance() {
		return instance;
	}
}
