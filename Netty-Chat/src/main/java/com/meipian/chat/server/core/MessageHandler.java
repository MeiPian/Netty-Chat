package com.meipian.chat.server.core;

import org.springframework.stereotype.Service;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessageTypeConstant;
import com.meipian.chat.session.Session;

import io.netty.channel.ChannelHandlerContext;

@Service
public class MessageHandler {

	private SessionManager sessionManager = SessionManager.getInstance();

	public void dispatch(ChannelHandlerContext ctx, ChatMessage message) {
		Session session = SessionManager.getInstance().getSessionByUid(message.getUid());
		byte type = message.getType();
		if (MessageTypeConstant.USER_ONLINE == message.getType()) {
			if (session != null) {
				session.close();
			}
			message.setType(MessageTypeConstant.ACK_SUCCESS);
			session = SessionManager.getInstance().addSession(ctx, message.getUid());
			session.sendResponse(message);
			return;
		}
		
		if(session==null){
			message.setType(MessageTypeConstant.REFUSE);
			ctx.writeAndFlush(message);
			ctx.fireChannelRead(message);
			return;
			
		}
		if (MessageTypeConstant.ACK_SUCCESS == message.getType()) {
			session = sessionManager.getSessionByUid(message.getUid());
			session.sendResponse(message);
			return;
		}

		if (MessageTypeConstant.USER_OFFLINE == type) {
			if (session != null) {
				message.setType(MessageTypeConstant.USER_OFFLINE);
				session.sendResponse(message);
				session.close();

			}
			return;
		}
		if (MessageTypeConstant.NORMAL_MESSAGE == type)
			session = sessionManager.getSessionByUid(message.getOid());
		    session.sendResponse(message);
	      	return;
	}

}
