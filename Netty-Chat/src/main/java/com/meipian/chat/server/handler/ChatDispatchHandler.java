package com.meipian.chat.server.handler;

import org.apache.log4j.Logger;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessageType;
import com.meipian.chat.server.core.MessageHandler;
import com.meipian.chat.server.core.SessionManager;
import com.meipian.chat.session.Session;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatDispatchHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = Logger.getLogger(ChatDispatchHandler.class);
	private static final MessageHandler handler = new MessageHandler();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ChatMessage message = (ChatMessage) msg;
		if (MessageType.USER_ONLINE == message.getType()) {
			Session session = SessionManager.getInstance().addSession(ctx, message.getUid());
			message.setType(MessageType.ACK_SUCCESS);
			session.sendResponse(message);
		} else {
			handler.dispatch(message);
		}
	}

}
