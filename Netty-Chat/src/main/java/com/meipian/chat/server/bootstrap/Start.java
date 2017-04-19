package com.meipian.chat.server.bootstrap;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.meipian.chat.server.spring.support.ApplicationContext;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@EnableAutoConfiguration
public class Start {
	private static Logger logger = Logger.getLogger(Start.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(Start.class);
		int port = Integer.valueOf(ctx.getEnvironment().getProperty("port"));
		ApplicationContext.init(ctx);
		run(port);
	}

	public static void run(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
					.childHandler(new ChatServerInitializer()).option(ChannelOption.SO_BACKLOG, 1024)
					.childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_KEEPALIVE, true); // 保持长连接状态
			// 绑定端口，开始接收进来的连接
			ChannelFuture future = bootstrap.bind(port).sync();
			logger.info(" netty  server start in port:" + port);
			future.channel().closeFuture().sync();// 子线程开始监听
		} catch (Exception e) {
			logger.error(" netty  server start  error in port:" + port, e);
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
