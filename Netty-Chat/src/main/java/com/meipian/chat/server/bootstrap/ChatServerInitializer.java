package com.meipian.chat.server.bootstrap;

import java.util.concurrent.TimeUnit;

import com.meipian.chat.server.handler.ChatDispatchHandler;
import com.meipian.chat.server.handler.ChatReadTimeoutHandler;
import com.meipian.chat.server.spring.support.ApplicationContext;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		int heartBeatTimeOut = Integer.valueOf(ApplicationContext.getInstance().getProperty("heartBeatTimeOut"));
		arg0.pipeline().addLast("chat_dispatch_handler", new ChatDispatchHandler());
		arg0.pipeline().addLast("heart_beat_handler", new ChatReadTimeoutHandler(heartBeatTimeOut, TimeUnit.SECONDS));
	}

}
