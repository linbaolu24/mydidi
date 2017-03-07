package cn.com.didi.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtils.class);

    private static Charset DEFAULT_CHARSET = Charset.forName("utf-8");
    /**
     * 
     */
    public static String CBC_128="AES/CBC/NoPadding";

    public static byte[] decrypt(byte[] sSrc, String sKey)
            throws UnsupportedEncodingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(sSrc);
    }
    public static byte[] decrypt(String mode,byte[] sSrc, String sKey)
            throws UnsupportedEncodingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException {
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(sKey.getBytes("ASCII"));
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,iv);
        return cipher.doFinal(sSrc);
    }

    // 判断Key是否正确
    public static byte[] encrypt(byte[] sSrc, String sKey)
            throws UnsupportedEncodingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(sSrc);
    }
    
    public static byte[] encrypt(String mode,byte[] sSrc, String sKey)
            throws UnsupportedEncodingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException {
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(sKey.getBytes("ASCII"));
        Cipher cipher = Cipher.getInstance(mode);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,iv);
        return cipher.doFinal(sSrc);
    }
    
    
    /**
     * 解密 
     * @param str  需要解密的密文
     * @param key 密钥
     * @return
     * @throws CodecException
     */
    public static String decrypt(String str, String key)   {
        if(str!=null)
        {
            byte[] decrypts;
            try {
                decrypts = AESUtils.decrypt(AESUtils.toBytes(str), key);
            } catch (Exception e) {
               return "";
            }
            if (decrypts == null || decrypts.length == 0) {
               return "";
            }
            return new String(decrypts, DEFAULT_CHARSET);
        }
        return "";
    }


    /**
     * 生成加密密文
     * @param str 需要加密的明文
     * @param key 密钥
     * @return
     * @throws Exception 
     * @throws CodecException
     */
    public static String encrypt(String str, String key) throws Exception {
        if(str!=null)
        {
            try {
                return AESUtils.toHex(AESUtils.encrypt(str.getBytes(DEFAULT_CHARSET), key));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            }
        }
        return "";
    }
 

    public static byte[] toBytes(String strhex) {
        if (strhex == null) {
            return new byte[0];
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return new byte[0];
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }

    public static String toHex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
}
