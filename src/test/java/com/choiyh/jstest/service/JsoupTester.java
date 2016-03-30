package com.choiyh.jstest.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class JsoupTester {
	protected static Logger logger = LoggerFactory.getLogger(JsoupTester.class);	

	@Test
	public void test() {
		String url = "http://www.stylenanda.com/product/detail.html?product_no=206794";
//		String selector = "DIV:nth-child(1) DIV:nth-child(7) DIV:nth-child(9) DIV:nth-child(2) DIV:nth-child(1) H2:nth-child(1)";
		String selector = ".path > ol > li:nth-child(2) > a";
		String html;
		String fieldValue = null;
		
		// html 조회
		RestTemplate restTemplate = new RestTemplate();
		html = restTemplate.getForObject(url, String.class);
		logger.debug(html);

		// jsoup 적용
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(selector);
		// 상품페이지에서 실렉터별로 데이터값을 조회한다.
		if (elements != null && elements.first() != null) {
			fieldValue = elements.first().html();
			logger.debug(fieldValue);
		}

		assertThat(fieldValue,  is(notNullValue()));

	}

}
