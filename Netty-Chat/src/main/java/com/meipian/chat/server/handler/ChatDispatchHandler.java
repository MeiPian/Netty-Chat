package com.meipian.chat.server.handler;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.server.core.Session;
import com.meipian.chat.server.core.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatDispatchHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ChatMessage message = (ChatMessage) msg;

		Session session = SessionManager.getInstance().getSessionByUid(message.getOid());
		session.sendResponse(message);
	}

}
