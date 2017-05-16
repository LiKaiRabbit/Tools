package com.likairabbit.tool;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	static final String algorithmStr = "AES/ECB/PKCS5Padding";

	@SuppressWarnings("unused")
	private static final Object TAG = "AES";

	static private KeyGenerator keyGen;

	static private Cipher cipher;

	static boolean isInited = false;
      
      private static  void init() {
		try { 
		  
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		keyGen.init(128); 
		try {    

			cipher = Cipher.getInstance(algorithmStr);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		isInited = true;
	}

	@SuppressWarnings("unused")
	private static byte[] genKey() {
		if (!isInited) {
			init();  
		}
 
		return keyGen.generateKey().getEncoded();
	}

	@SuppressWarnings("unused")
	private static byte[] encrypt(byte[] content, byte[] keyBytes) {
		byte[] encryptedText = null;
		if (!isInited) { 
			init();
		}

		Key key = new SecretKeySpec(keyBytes, "AES");
		try {

			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		try {

			encryptedText = cipher.doFinal(content);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedText;
	}

	private static byte[] encrypt(String content, String password) {
		try {
			byte[] keyStr = getKey(password);
			SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
			Cipher cipher = Cipher.getInstance(algorithmStr);//algorithmStr          
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);//   ʼ  
			byte[] result = cipher.doFinal(byteContent);
			return result; //     
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
 
	private static byte[] decrypt(byte[] content, String password) {
		try {
			byte[] keyStr = getKey(password);
			SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
			Cipher cipher = Cipher.getInstance(algorithmStr);//algorithmStr           
			cipher.init(Cipher.DECRYPT_MODE, key);//   ʼ  
			byte[] result = cipher.doFinal(content);
			return result; //     
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static byte[] getKey(String password) {
		byte[] rByte = null;
		if (password!=null) {
			rByte = password.getBytes();
		}else{
			rByte = new byte[24];
		}
		return rByte;
	}


	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}


	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null; 
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	


	
	
	public static String encode(String content,String password){

		return parseByte2HexStr(encrypt(content, password));
	}
	
	
	public static String decode(String content,String password){

		byte[] b = decrypt(parseHexStr2Byte(content), password);
		return new String(b);
	}
	

	
	
}