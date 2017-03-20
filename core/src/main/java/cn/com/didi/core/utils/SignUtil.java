package cn.com.didi.core.utils;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.lang.StringUtils;

/**
 * @author xlm
 *
 */
public class SignUtil {

	public static byte[] sign(String alg, PrivateKey priKey, byte[] source)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		java.security.Signature signature = java.security.Signature.getInstance(alg);

		signature.initSign(priKey);
		signature.update(source);

		byte[] signed = signature.sign();

		return signed;
	}

	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, byte[] source) throws Exception {
		if (source == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(source));
	}
	
	public static PublicKey getPublickKeyFromX509(String algorithm, byte[] source) throws Exception {
		if (source == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}
	    KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

   

        return keyFactory.generatePublic(new X509EncodedKeySpec(source));
	}
	
	public static boolean verify(String alg, PublicKey publickey, byte[] source,byte[] signed) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException{
		java.security.Signature signature = java.security.Signature.getInstance(alg);
		signature.initVerify(publickey);
		signature.update(source);
		return signature.verify(signed);
	}
}
