package com.choiyh.jstest.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.choiyh.jstest.beans.RestBean;

@Controller
public class RestTestController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/viewRestMain")
	public String viewRestMain() {
		return "rest/main";
	}
	
	@RequestMapping(value = "/communities", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getPathParamTest2() {
		logger.debug("************* getPathParamTest2 ***************");
		return new ResponseEntity<String>("communityId: 없음", HttpStatus.OK);
	}

	@RequestMapping(value = "/communities//posts", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getPathParamTest3() {
		logger.debug("************* getPathParamTest2 ***************");
		return new ResponseEntity<String>("communityId: 없음2", HttpStatus.OK);
	}

	@RequestMapping(value = "/communities/{communityId}/posts/{postsId}", method = RequestMethod.GET)
	public ResponseEntity<String> getPathParamTest4(@PathVariable String communityId, @PathVariable String postsId) {
		logger.debug("************* getPathParamTest ***************");
		return new ResponseEntity<String>("communityId: " + communityId + " postsId: " + postsId, HttpStatus.OK);
	}

	@RequestMapping(value = "/communities/{communityId}/posts/{postsId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getPathParamTest5(@PathVariable String communityId, @PathVariable String postsId
		,@RequestBody RestBean restBean) {
		logger.debug("************* getPathParamTest ***************");
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> header = new HashMap<String,Object>();
		header.put("isSuccessful", true);
		header.put("resultCode", 0); // 
		header.put("resultMessage", ""); 
		result.put("header", header);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/communities/{communityId}/posts", method = RequestMethod.GET)
	public ResponseEntity<String> getPathParamTest6(@PathVariable String communityId
		, @RequestParam(value="searchType") String searchType
		, @RequestParam(value="searchKeyword") String searchKeyword) {
		logger.debug("************* getPathParamTest ***************");
		return new ResponseEntity<String>("communityId: " + communityId + " searchType: " + searchType + " searchKeyword: " + searchKeyword, HttpStatus.OK);
	}

	@RequestMapping(value = "/communities/{communityId}/posts/search", method = RequestMethod.GET)
	public ResponseEntity<String> getPathParamTest7(@PathVariable String communityId
		, RestBean restBean) {
		logger.debug("************* getPathParamTest ***************");
		return new ResponseEntity<String>("communityId: " + communityId + " searchType: " + restBean.getSearchType() + " searchKeyword: " + restBean.getSearchKeyword(), HttpStatus.OK);
	}

}
