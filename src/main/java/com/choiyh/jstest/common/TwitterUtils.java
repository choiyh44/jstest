package com.choiyh.jstest.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.scribe.exceptions.OAuthException;
import org.scribe.utils.OAuthEncoder;

public class TwitterUtils {
	public static String getTwitterScreenName(String response) throws Exception {
		final Pattern pattern = Pattern.compile("screen_name=([^&]*)");
		
		return extractTwitterUserInfo(response, pattern);
	}
	
	public static String getTwitterUserId(String response) throws Exception {
		final Pattern pattern = Pattern.compile("user_id=([^&]*)");

		return extractTwitterUserInfo(response, pattern);
	}

	public static String extractTwitterUserInfo(String response, Pattern pattern) throws Exception {
		Matcher matcher = pattern.matcher(response);
		if (matcher.find() && matcher.groupCount() >= 1) {
			return OAuthEncoder.decode(matcher.group(1));
		}
		else {
			throw new OAuthException("Response body is incorrect. No user_id or screen_name: '" + response + "'", null);
		}
	}
	
}
