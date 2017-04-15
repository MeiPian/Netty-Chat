package com.meipian.chat.server.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.meipian" })
@EnableScheduling
@ImportResource({ "classpath:mybatis.xml", "classpath:config/redis.xml" })
@PropertySource(value = { "classpath:price.properties" })
public class Start {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext ctx = SpringApplication.run(Start.class);
		int port = Integer.valueOf(ctx.getEnvironment().getProperty("port"));
		run(port);

	}

	public static void run(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class).childHandler(new ChatServerInitializer());
			bootstrap.bind(port).sync().channel().closeFuture().sync();

		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
