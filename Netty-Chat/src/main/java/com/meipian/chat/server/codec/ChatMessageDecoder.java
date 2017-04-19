package com.meipian.chat.server.codec;

import org.apache.log4j.Logger;

import com.meipian.chat.protocol.ChatMessage;
import com.meipian.chat.protocol.MessageTypeConstant;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ChatMessageDecoder extends LengthFieldBasedFrameDecoder {
	private static Logger logger = Logger.getLogger(ChatMessageDecoder.class);
	/**
	 * |length|content| length指的是实际内容的长度
	 * http://blog.csdn.net/educast/article/details/47706599
	 */
	private static final int MAX_FRAME_LENGTH = 1024;
	private static final int LENGTH_FIELD_OFFSET = 0; // 由于没有header，所以第一个字节就是length占用的字段
	private static final int LENGTH_FIELD_LENGTH = 4; // length所占字段的长度，表示length本身占用了4个字节
	private static final int LENGTH_ADJUSTMENT = 0;
	private static final int INITIAL_BYTES_TO_STRIP = 0; // 不截取前面的length

	public ChatMessageDecoder() {
		this(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP);
	}

	public ChatMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

	/**
	 * 如果返回为null会一直循环。直到非空
	 */
	@Override
	protected Object decode(ChannelHandlerContext arg0, ByteBuf arg1) throws Exception {
		System.out.println(arg1.readableBytes());
		if (arg1.readableBytes() <5) {
				return null;
		}
			int contentLength = arg1.readInt();
			if (contentLength == 0) {
				return null;
			}
			ChatMessage message = new ChatMessage();
			byte type = arg1.readByte();
			message.setType(type);
			/**
			 * type 一个字节的类型+uid的四个字节。就是5个字节
			 */	
			if (contentLength == 5 && type == MessageTypeConstant.HEARTBEAT) {
				return message;
			}
			int uid = arg1.readInt();
			message.setUid(uid);
			int oid = arg1.readInt();
			message.setOid(oid);
			double longitude = arg1.readDouble();
			message.setLatitude(longitude);
			double latitude = arg1.readDouble();
			message.setLatitude(latitude);
			int action = arg1.readInt();
			message.setAction(action);
			return message;
}}
