<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" ng-app="restApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>RESTful service test</title>
	<script type="text/javascript" src="/static/js/lib/angularjs/angular.min.js"></script>
	<script type="text/javascript" src="/static/js/rest/restApp.js"></script>
	<script type="text/javascript" src="/static/js/rest/restService.js"></script>
</head>
<body ng-controller="RestController">

<div>
	<p>
		<a href="/communities">@RequestMapping(value = "/communities", method = RequestMethod.GET)</a><br/>
		<a href="/communities//posts">@RequestMapping(value = "/communities//posts", method = RequestMethod.GET)</a><br/>
		<a href="/communities/123/posts/456">@RequestMapping(value = "/communities/{communityId}/posts/{postsId}", method = RequestMethod.GET)</a><br/>
		<a href="/communities/123/posts?searchType=st&searchKeyword=sk">@RequestMapping(value = "/communities/{communityId}/posts", method = RequestMethod.GET) + searchType=st&searchKeyword=sk</a><br/>
		<a href="/communities/123/posts/search?searchType=st&searchKeyword=sk">@RequestMapping(value = "/communities/{communityId}/posts/search", method = RequestMethod.GET) + searchType=st&searchKeyword=sk</a><br/>
	</p>
</div>

<div>
	<form>
		communityId: <input ng-model="communityId" /> {{communityId}}<br/>
		communityName: <input ng-model="communityName" /> {{communityName}}<br/>
		<button ng-click="onPostFormSubmit()">등록</button>
	</form>
</div>

</body>
</html>
