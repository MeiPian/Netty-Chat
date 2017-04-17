package com.meipian.chat.protocol;

import java.util.ArrayList;
import java.util.Map;

import com.meipian.chat.server.utils.ByteUtils;

public class ChatMessage {

	private byte type;
	private int uid; // 当前我的ID
	private int oid;// 对方id(opposite side user id)
	private double longitude;
	private double latitude;
	private int action; // 动作类型，由客户端自己定义
	private Map<String, String> body; // 自定义消息内容。如果大于1k直接丢弃不处理。此字段暂时不用。getBytes也不会发生转换

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
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

	public static class ByteBuilder {

		private ArrayList<Byte> bytesList = new ArrayList<>();

		public static  ByteBuilder newBuilder() {

			return new ByteBuilder();
		}
		public ByteBuilder setType(byte type) {
			bytesList.add(type);
			return this;
		}

		private ByteBuilder setUid(int uid) {

			byte[] uidByte = ByteUtils.int2Byte(uid);
			for (byte b : uidByte) {
				bytesList.add(b);
			}
			return this;
		}

		private ByteBuilder setOid(int oid) {
			byte[] oidByte = ByteUtils.int2Byte(oid);
			for (byte b : oidByte) {
				bytesList.add(b);
			}
			return this;
		}

		private ByteBuilder setLongitude(double longitude) {
			byte[] longitudeByte = ByteUtils.double2Bytes(longitude);
			;
			for (byte b : longitudeByte) {
				bytesList.add(b);
			}
			return this;
		}

		private ByteBuilder setLatitude(double latitude) {
			byte[] latitudeByte = ByteUtils.double2Bytes(latitude);
			;
			for (byte b : latitudeByte) {
				bytesList.add(b);
			}
			return this;
		}

		private ByteBuilder setAction(int action) {
			byte[] actionByte = ByteUtils.int2Byte(action);
			for (byte b : actionByte) {
				bytesList.add(b);
			}
			return this;
		}

		public byte[] build(ChatMessage message) {

			this.setType(message.getType()).setUid(message.getUid()).setOid(message.getOid())
					.setLongitude(message.getLongitude()).setLatitude(message.getLatitude())
					.setAction(message.getAction());
			int length = bytesList.size();
			byte[] bytes = new byte[length];
			for (int i = 0; i < length; i++) {
				bytes[i] = bytesList.get(i).byteValue();
			}
			return bytes;
		}

	}

}
