package kr.web.dev.common.util.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Cipher {
	
	private static volatile AES256Cipher	INSTANCE;
	
	final static String						secretKey	= "infosec.co.kr";
	static byte[]							IV			= new byte[16];
	
	public static AES256Cipher getInstance() {
		if (INSTANCE == null) {
			synchronized (AES256Cipher.class) {
				if (INSTANCE == null) {
					System.out.println("create AES");
					INSTANCE = new AES256Cipher();
				}
			}
		}
		
		return INSTANCE;
	}
	
	private AES256Cipher() {
		Arrays.fill(IV, Byte.parseByte("0"));
	}
	
	public byte[] encrypt(byte[] plainText) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] keyData = secretKey.getBytes();
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		
		SecretKey secureKey = new SecretKeySpec(messageDigest.digest(keyData), "AES");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV));
		
		byte[] doFinal = c.doFinal(plainText);
		
		return doFinal;
	}
	
	public byte[] decrypt(byte[] encryptedText) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] keyData = secretKey.getBytes();
		
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		
		SecretKey secureKey = new SecretKeySpec(messageDigest.digest(keyData), "AES");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV));
		
		byte[] doFinal = c.doFinal(encryptedText);
		
		return doFinal;
	}
	
}
