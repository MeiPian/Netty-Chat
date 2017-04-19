package com.meipian.chat.session;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.server.core.ChatConnection;
import com.meipian.chat.server.core.SessionOperationListener;

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
