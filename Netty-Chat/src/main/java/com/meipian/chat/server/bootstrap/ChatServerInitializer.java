package com.meipian.chat.server.bootstrap;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.meipian.chat.server.codec.ChatMessageDecoder;
import com.meipian.chat.server.handler.ChatDispatchHandler;
import com.meipian.chat.server.handler.ChatReadTimeoutHandler;
import com.meipian.chat.server.spring.support.ApplicationContext;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {
	private static  Logger  logger=Logger.getLogger(ChatServerInitializer.class);

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		logger.debug("ChatServerInitializer 初始化");
		int heartBeatTimeOut = Integer.valueOf(ApplicationContext.getInstance().getProperty("heartBeatTimeOut"));
		arg0.pipeline().addLast(new ChatMessageDecoder());
		arg0.pipeline().addLast("heart_beat_handler", new ChatReadTimeoutHandler(heartBeatTimeOut, TimeUnit.SECONDS));
		arg0.pipeline().addLast("chat_dispatch_handler", new ChatDispatchHandler());
		
	}

}
