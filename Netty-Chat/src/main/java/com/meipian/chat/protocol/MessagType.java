package com.meipian.chat.protocol;

public class MessagType {
	
	
	private  byte  type;  //消息类型：0x00表示心跳    0x02 表示用户上线(连接成功) 0x03 表示用户下线(连接失败) 0x10 表示正常消息
	
	
	
	public  static  final   byte  HEARTBEAT=0x00;

}
