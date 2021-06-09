<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>muIzik</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/layout.css?after" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

<div id="wrapper">
	<div id="header">
		<div class="sidebar"></div>
		<div class="header-content">
			<div id="title">
				<a href="/album/list" id="title_text">muIzik</a>

			</div>
			<div id="loginform">
				<sec:authentication property="principal" var="pinfo" />
				
				<sec:authorize access="isAnonymous()">
					<button class="login" onclick="location.href='/login'">login</button>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
					<c:out value="${pinfo.member.nickname}"/>님, 안녕하세요!
					<form id="logoutForm" action="/logout" method="POST" style="display:inline">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button id="logoutButton" class="login" type="submit">logout</button>
					</form>
				</sec:authorize>
			</div>
		</div>
		<div class="sidebar"></div>
	</div>

<script type="text/javascript">
$(document).ready(function() {
	var logoutForm = $("#logoutForm");
	$("#logoutButton").on("click", function(e){	
		e.preventDefault();
		
		if (confirm("로그아웃하시겠습니까?") == true) {
			logoutForm.submit();
		} else { //취소
			return false;
		}
	});
})
</script>