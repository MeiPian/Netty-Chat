package com.meipian.chat.server.handler;

import org.apache.commons.logging.LogFactory;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.server.core.MessageHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatDispatchHandler extends ChannelInboundHandlerAdapter {
	private static final org.apache.commons.logging.Log logger = LogFactory.getLog(ChatDispatchHandler.class);
	
	private  static  final MessageHandler  handler=new MessageHandler();
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ChatMessage message = (ChatMessage) msg;
		
	
		handler.dispatch( message);
	}

}
