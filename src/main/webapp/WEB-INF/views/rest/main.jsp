<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" ng-app="restApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>RESTful service test</title>
</head>
<body ng-controller="RestController">
<form>
	communityId: <input ng-model="communityId" /> {{communityId}}<br/>
	communityName: <input ng-model="communityName" /> {{communityName}}<br/>
	<button ng-click="onPostFormSubmit()">등록</button>
<script type="text/javascript" src="/static/js/lib/angularjs/angular.min.js"></script>
<script type="text/javascript" src="/static/js/rest/restApp.js"></script>
<script type="text/javascript" src="/static/js/rest/restService.js"></script>
</body>
</html>