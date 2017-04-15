package com.meipian.chat.protocol;

import java.util.Map;

public class ChatMessage {
	
	private byte type; // 消息类型：0x00表示心跳 0x02 表示用户上线(连接成功) 0x03 表示用户下线(连接失败) 0x10
						// 表示正常消息

	private long uid; // 当前我的ID

	private long oid;// 对方id(opposite side user id)

	private double longitude;

	private double latitude;
	private int action; // 动作类型，由客户端自己定义

	private Map<String, String> body; // 自定义消息内容。如果大于1k直接丢弃不处理
	private int bodyByteSize; // body消息的长度
	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}



	public Map<String, String> getBody() {
		return body;
	}

	public void setBody(Map<String, String> body) {
		this.body = body;
	}

	public int getBodyByteSize() {
		return bodyByteSize;
	}

	public void setBodyByteSize(int bodyByteSize) {
		this.bodyByteSize = bodyByteSize;
	}
   
}
