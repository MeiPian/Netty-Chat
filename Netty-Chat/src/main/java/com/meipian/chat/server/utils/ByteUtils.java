package com.meipian.chat.server.utils;

public class ByteUtils {

	public static byte[] short2Byte(short a) {
		byte[] b = new byte[2];
		b[0] = (byte) (a >> 8);
		b[1] = (byte) (a);
		return b;

	}
	
	 public static void short2Byte(short a, byte[] b, int offset){  
	        b[offset] = (byte) (a >> 8);  
	        b[offset+1] = (byte) (a);  
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

}