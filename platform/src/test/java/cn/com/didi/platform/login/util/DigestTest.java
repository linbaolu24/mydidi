package cn.com.didi.platform.login.util;

import org.apache.commons.codec.digest.DigestUtils;

public class DigestTest {
	public static void main(String[] args) {
		System.out.println(DigestUtils.md2Hex("123456"));
	}
}
