package cn.com.didi.message.shortmessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
	public static void main(String[] args) {
		String str="java.io.temp";
		System.out.println(Pattern.quote(str));
		System.out.println( Matcher.quoteReplacement("123"));
		System.out.println(str.replaceFirst(Pattern.quote(str), Matcher.quoteReplacement("1323")));
	}
}
