package com.meipian.chat.server.core;

import org.springframework.stereotype.Service;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessageType;
import com.meipian.chat.session.Session;

@Service
public class MessageHandler {

	private SessionManager sessionManager = SessionManager.getInstance();

	public void dispatch(ChatMessage message) {
		byte type = message.getType();
		if (MessageType.ACK_SUCCESS == message.getType()) {
			Session session = sessionManager.getSessionByUid(message.getUid());
			session.sendResponse(message);
		}
		Session session = sessionManager.getSessionByUid(message.getUid());
		if (MessageType.USER_OFFLINE == type) {
			session.destory();
		}
		if (MessageType.NORMAL_MESSAGE == type)
			session = sessionManager.getSessionByUid(message.getOid());
		session.sendResponse(message);
	}

}
