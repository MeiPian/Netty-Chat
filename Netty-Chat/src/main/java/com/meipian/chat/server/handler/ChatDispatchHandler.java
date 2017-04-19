package com.meipian.chat.server.handler;

import org.apache.log4j.Logger;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.server.core.MessageHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatDispatchHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = Logger.getLogger(ChatDispatchHandler.class);
	private static final MessageHandler handler = new MessageHandler();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ChatMessage message = (ChatMessage) msg;

		handler.dispatch(ctx, message);

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("连接异常关闭", cause);
		ctx.close();

	}
}
