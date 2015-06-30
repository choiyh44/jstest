package com.choiyh.jstest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.scribe.model.Response;
import org.scribe.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.choiyh.jstest.beans.Twitter;
import com.choiyh.jstest.common.TwitterUtils;
import com.choiyh.jstest.service.TwitterService;

/**
 * 참조 사이트: http://java.dzone.com/articles/spring-mvc-and-scribe-simple
 * @author mOer
 *
 */
@Controller
@RequestMapping("twitter")
@SessionAttributes({"requestToken", "accessToken"})
public class TwitterController {
	@Autowired TwitterService twitterService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/index.nhn")
	public String list(Model model, HttpSession session) {
		Token requestToken = (Token) session.getAttribute("requestToken");
		Token accessToken = (Token) session.getAttribute("accessToken");
		
		if (requestToken == null || accessToken == null) 
			return "redirect:/twitter/oauth.nhn";
		
		List<Twitter> twitter = twitterService.getTimeline(accessToken);
		model.addAttribute("result", twitter);
		return "twitter/main";
	}
	
	@RequestMapping(value="/form.nhn", method=RequestMethod.GET)
	public String form() {
		return "twitter/form";
	}
	
	@RequestMapping(value="/form.nhn", method=RequestMethod.POST)
	public String twiteeing(Twitter twitter, @ModelAttribute("accessToken") Token accessToken) {
		logger.info(twitter.toString());
		twitterService.tweeting(twitter, accessToken);
		return "redirect:/twitter/index.nhn";
	}
	
	@RequestMapping("/oauth.nhn")
	public String startOAuth(Model model) {
		Token requestToken = twitterService.getRequestToken();
		model.addAttribute("requestToken", requestToken);
		logger.info("request token: " + requestToken);
		return "redirect:" + twitterService.requestOAuth(requestToken);
	}
	
	@RequestMapping("/oauth_callback.nhn")
	public String oauthCallback(@RequestParam("oauth_verifier") String oauthVerifier, @ModelAttribute("requestToken") Token requestToken, Model model) throws Exception {
		logger.info("OAuth Verifier: " + oauthVerifier);
		logger.info("OAuth request token: " + requestToken);
		
		Token accessToken = twitterService.getAccessToken(requestToken, oauthVerifier);
		model.addAttribute("accessToken", accessToken);
		logger.info("user_id: " + TwitterUtils.getTwitterUserId(accessToken.getRawResponse()));
		logger.info("screen_name: " + TwitterUtils.getTwitterScreenName(accessToken.getRawResponse()));
		logger.info("accessToken: " + accessToken);
		return "redirect:/twitter/index.nhn";
	}
	
	@RequestMapping("/anywhere.nhn")
	public String anywhere() {
		return "twitter/anywhere.nhn";
	}
	
	@RequestMapping("/verify.nhn")
	public String verifyCredentials(@ModelAttribute("accessToken") Token accessToken) {
		Response response = twitterService.verifyCredentials(accessToken);
		logger.info(response.getHeaders().toString());
		logger.info(response.getBody());
		
		if (response.getHeader("Status").contains("Unauthorized")) {
			return "redirect:/twitter/oauth";
		}
		
		return "redirect:/twitter/index.nhn";
	}
	
}
