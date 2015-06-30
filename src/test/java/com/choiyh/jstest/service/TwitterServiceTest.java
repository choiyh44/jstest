package com.choiyh.jstest.service;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TwitterServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(TwitterServiceTest.class);

	@Autowired
	OAuthService twitterOAuthService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void clean() throws Exception {
	}


//	@Test
//	public void testGetRequestSign() throws Exception {
//		// Given
//		
//		// When
//		String message = twitterService.getRequestSign();
//		
//		// Then
//		assertNotNull("getRequestSign() returns null.", message);
//
//	}

	@Test
	public void testGetOAuthRequestToken() throws Exception {
		// Given
		
		// When
		Token token = twitterOAuthService.getRequestToken();
		logger.debug("token.getRawResponse()" + token.getRawResponse());
		logger.debug("token.getToken()" + token.getToken());
		logger.debug("token.getSecret()" + token.getSecret());
		
		// Then
		assertNotNull("getOAuthRequestToken() returns null.", token);

	}

	@Test
	/**
	 * Scribe API test
	 * @return
	 * @throws Exception
	 */
	public void testScribeApiGetOAuthRequestToken() throws Exception {
		// Given
		String consumerKey = "oERChCTjDtQr3GLvh9KPOEK5l"; // TODO 키설정 관리 필요
		String consumerSecret = "eD0k9P8c84yF4m2dLZDLvP2pylbdfXPwehkOt03rNXTEBuKZuF"; // TODO 키설정 관리 필요
		String callback = "http://jstest.toast.com/twitter/oauth_callback.nhn";
		
		// When
		OAuthService service = new ServiceBuilder().provider(org.scribe.builder.api.TwitterApi.Authenticate.class)
			.apiKey(consumerKey)
		    .apiSecret(consumerSecret)
		    .callback(callback)
		    .build();
		Token token = service.getRequestToken();
		logger.debug("token.getRawResponse()" + token.getRawResponse());
		logger.debug("token.getToken()" + token.getToken());
		logger.debug("token.getSecret()" + token.getSecret());

		
		// Then
		assertNotNull("getOAuthRequestToken() returns null.", token);
	}

}
