package com.meipian.chat.protocol;

public class MessageTypeConstant {
	// 消息类型：0x00表示心跳 
	public static final byte HEARTBEAT = 0x00;
	//0x02 表示用户上线(连接成功) 
	public static final byte USER_ONLINE = 0x02;
	//0x03 表示用户下线(连接失败) 
	public static final byte USER_OFFLINE = 0x03;
	// 操作成功 0x04,比如连接确认
	public  static  final  byte ACK_SUCCESS=0x04;
	// 表示正常消息 0x10
	public static final byte NORMAL_MESSAGE = 0x10;

}
