package com.meipian.chat.server.utils;

public class ByteUtils {

	public static byte[] short2Byte(short a) {
		byte[] b = new byte[2];
		b[0] = (byte) (a >> 8);
		b[1] = (byte) (a);
		return b;

	}

	public static void short2Byte(short a, byte[] b, int offset) {
		b[offset] = (byte) (a >> 8);
		b[offset + 1] = (byte) (a);
	}

	public static byte[] int2Byte(int a) {
		byte[] b = new byte[4];
		b[0] = (byte) (a >> 24);
		b[1] = (byte) (a >> 16);
		b[2] = (byte) (a >> 8);
		b[3] = (byte) (a);

		return b;
	}

	/**
	 * int转byte数组
	 * 
	 * @param a
	 * @param b
	 * @param offset
	 * @return
	 */
	public static void int2Byte(int a, byte[] b, int offset) {
		b[offset++] = (byte) (a >> 24);
		b[offset++] = (byte) (a >> 16);
		b[offset++] = (byte) (a >> 8);
		b[offset++] = (byte) (a);
	}

	public static void long2Byte(long a, byte[] b, int offset) {
		b[offset + 0] = (byte) (a >> 56);
		b[offset + 1] = (byte) (a >> 48);
		b[offset + 2] = (byte) (a >> 40);
		b[offset + 3] = (byte) (a >> 32);

		b[offset + 4] = (byte) (a >> 24);
		b[offset + 5] = (byte) (a >> 16);
		b[offset + 6] = (byte) (a >> 8);
		b[offset + 7] = (byte) (a);
	}

	public static byte[] long2Byte(long a) {
		byte[] b = new byte[8];
		b[0] = (byte) (a >> 56);
		b[1] = (byte) (a >> 48);
		b[2] = (byte) (a >> 40);
		b[3] = (byte) (a >> 32);
		b[4] = (byte) (a >> 24);
		b[5] = (byte) (a >> 16);
		b[6] = (byte) (a >> 8);
		b[7] = (byte) (a);
		return b;
	}

	/**
	 * byte[8]转long
	 * 
	 * @param b
	 * @param offset
	 *            b的偏移量
	 * @return
	 */
	public static long byte2Long(byte[] b, int offset) {
		return ((((long) b[offset + 0] & 0xff) << 56) | (((long) b[offset + 1] & 0xff) << 48)
				| (((long) b[offset + 2] & 0xff) << 40) | (((long) b[offset + 3] & 0xff) << 32)

		| (((long) b[offset + 4] & 0xff) << 24) | (((long) b[offset + 5] & 0xff) << 16)
				| (((long) b[offset + 6] & 0xff) << 8) | (((long) b[offset + 7] & 0xff) << 0));
	}

	// 浮点到字节转换

	public static byte[] double2Bytes(double d) {
		long value = Double.doubleToRawLongBits(d);
		byte[] byteRet = new byte[8];
		for (int i = 0; i < 8; i++) {
			byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
		}
		return byteRet;
	
	}

	// 字节到浮点转换

	public static double byteToDouble(byte[] b) {
		long l;
		l = b[0];
		l &= 0xff;
		l |= ((long) b[1] << 8);
		l &= 0xffff;
		l |= ((long) b[2] << 16);
		l &= 0xffffff;
		l |= ((long) b[3] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[6] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[7] << 56);
		return Double.longBitsToDouble(l);

	}
}