package com.meipian.chat.protocol;

public class MessagType {

	private byte type; // 消息类型：0x00表示心跳 0x02 表示用户上线(连接成功) 0x03 表示用户下线(连接失败) 
						// 操作成功 0x04
	
						// 表示正常消息 0x10

	public static final byte HEARTBEAT = 0x00;

	public static final byte USER_OFFLINE = 0x03;
	
	

}
