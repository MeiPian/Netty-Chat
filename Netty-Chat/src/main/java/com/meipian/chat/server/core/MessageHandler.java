package com.meipian.chat.server.core;

import org.springframework.stereotype.Service;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessageTypeConstant;
import com.meipian.chat.session.Session;

@Service
public class MessageHandler {

	private SessionManager sessionManager = SessionManager.getInstance();

	public void dispatch(ChatMessage message) {
		byte type = message.getType();
		if (MessageTypeConstant.ACK_SUCCESS == message.getType()) {
			Session session = sessionManager.getSessionByUid(message.getUid());
			session.sendResponse(message);
		}
		Session session = sessionManager.getSessionByUid(message.getUid());
		if (MessageTypeConstant.USER_OFFLINE == type) {
			session.destory();
		}
		if (MessageTypeConstant.NORMAL_MESSAGE == type)
			session = sessionManager.getSessionByUid(message.getOid());
		session.sendResponse(message);
	}

}
