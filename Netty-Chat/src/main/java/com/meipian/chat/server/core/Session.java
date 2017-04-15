package com.meipian.chat.server.core;

import com.meipian.chat.protocol.ChatMessage;

public class Session {

	private ChatConnection connection;

	private SessionOperationListener listener;

	public void sendResponse(ChatMessage response) {

		connection.sendResponse(response);
	}

	public long getUid() {
		return connection.getId();
	}

	public ChatConnection getConnection() {
		return connection;
	}

	public void setConnection(ChatConnection connection) {
		this.connection = connection;
	}

	public void destory() {
		connection.close();
		listener.doClose(this);

	}

	public SessionOperationListener getListener() {
		return listener;
	}

	public void setListener(SessionOperationListener listener) {
		this.listener = listener;
	}

}
