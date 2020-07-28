package com.flower.youth.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {

	private static final String SECRET_KEY = "bf@1dw6v";
	//密钥
	private static Key key = getKey(SECRET_KEY.getBytes());
	//加密工具     
    private static Cipher encryptCipher = null;    
    //解密工具     
    private static Cipher decryptCipher = null; 
    
    static{
    	try {
			encryptCipher = Cipher.getInstance("DES");
	        decryptCipher = Cipher.getInstance("DES");    
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    	
    	try {
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private DESUtil(){}
    
	/**
	 * 加密
	 * @param plaintext 明文
	 * */
	public static String encrypt(String plaintext) throws Exception{
		return byteArr2HexStr(encryptCipher.doFinal(plaintext.getBytes()));
	}
	
	/**  
     * 将byte数组转换为表示16进制值的字符串  
     * 
     * @param arrB  需要转换的byte数组  
     * @return 转换后的字符串  
     */    
    private static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;    
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍    
        StringBuffer sb = new StringBuffer(iLen * 2);    
        for (int i = 0; i < iLen; i++) {    
            int intTmp = arrB[i];    
            // 把负数转换为正数    
            while (intTmp < 0) {    
                intTmp = intTmp + 256;    
            }    
            // 小于0F的数需要在前面补0    
            if (intTmp < 16) {    
                sb.append("0");    
            }    
            sb.append(Integer.toString(intTmp, 16));    
        }    
        return sb.toString();    
    } 
	
	/**
	 * 解密
	 * @param ciphertext 密文
	 * */
	public static String decrypt(String ciphertext) throws Exception {
		return new String(decryptCipher.doFinal(hexStr2ByteArr(ciphertext)));
	}
	
	/**  
     * 将表示16进制值的字符串转换为byte数组
     * 
     * @param strIn 需要转换的字符串  
     * @return 转换后的byte数组  
     */    
    private static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();    
        int iLen = arrB.length;    
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2    
        byte[] arrOut = new byte[iLen / 2];    
        for (int i = 0; i < iLen; i = i + 2) {    
            String strTmp = new String(arrB, i, 2);    
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);    
        }    
        return arrOut;    
    }
	
	/**  
     * 从指定字符串生成密钥
     * 密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位  
     * 
     * @param bytes  构成该字符串的字节数组  
     * @return 生成的密钥  
     */  
	private static Key getKey(byte[] bytes){
		Key key = null;
		
		// 创建一个空的8位字节数组（默认值为0）    
        byte[] arrB = new byte[8]; 
        // 将原始字节数组转换为8位    
        for (int i = 0; i < bytes.length && i < arrB.length; i++) {    
            arrB[i] = bytes[i];    
        }  
        // 生成密钥    
        key = new SecretKeySpec(arrB, "DES");
        
		return key;
	}
}
