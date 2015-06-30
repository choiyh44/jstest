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

import com.choiyh.jstest.beans.RestBean;

@Controller
public class RestTestController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/communities/{communityId}/posts", method = RequestMethod.GET)
	public ResponseEntity getPathParamTest(@PathVariable String communityId) {
		logger.debug("************* getPathParamTest ***************");
		return new ResponseEntity("communityId: " + communityId, HttpStatus.OK);
	}

	@RequestMapping("/viewRestMain")
	public String viewRestMain() {
		return "rest/main";
	}
	
	@RequestMapping(value = "/communities/{communityId}/posts/{postsId}", method = RequestMethod.POST)
	public ResponseEntity getPathParamTest5(@PathVariable String communityId, @PathVariable String postsId
		,@RequestBody RestBean restBean) {
		logger.debug("************* getPathParamTest ***************");
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> header = new HashMap<String,Object>();
		header.put("isSuccessful", true);
		header.put("resultCode", 0); // 
		header.put("resultMessage", ""); 
		result.put("header", header);
		return new ResponseEntity(result, HttpStatus.OK);
	}

//	@RequestMapping(value = "/communities/{communityId}/posts/{postsId}", method = RequestMethod.GET)
//	public ResponseEntity getPathParamTest4(@PathVariable Map<String,String> params) {
//		logger.debug("************* getPathParamTest ***************");
//		return new ResponseEntity("communityId: " + params.get("communityId") + " postsId: " + params.get("postsId"), HttpStatus.OK);
//	}

	@RequestMapping(value = "/communities/{communityId}/posts/{postsId}", method = RequestMethod.GET)
	public ResponseEntity getPathParamTest4(@PathVariable String communityId, @PathVariable String postsId) {
		logger.debug("************* getPathParamTest ***************");
		return new ResponseEntity("communityId: " + communityId + " postsId: " + postsId, HttpStatus.OK);
	}

	@RequestMapping(value = "/communities", method = RequestMethod.GET)
	public ResponseEntity getPathParamTest2() {
		logger.debug("************* getPathParamTest2 ***************");
		return new ResponseEntity("communityId: 없음", HttpStatus.OK);
	}

	@RequestMapping(value = "/communities//posts", method = RequestMethod.GET)
	public ResponseEntity getPathParamTest3() {
		logger.debug("************* getPathParamTest2 ***************");
		return new ResponseEntity("communityId: 없음2", HttpStatus.OK);
	}


}
