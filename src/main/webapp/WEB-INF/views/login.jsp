<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>

<style type="text/css">
	.loginBlock{
		width:400px; height:280px;
		border: 1px solid lightgray;
		margin-top:100px;
		display: inline-block;
	}
	.loginBlock form{
		margin-top:70px;
	}
	#loginButton,#joinButton{
		width:90px; height:50px;
		margin:10px;
		border-radius:10px;
		border:0;
	}
	#loginButton:hover {color:white; background-color:salmon;}
	#joinButton:hover{color:white; background-color:skyblue;}
</style>	

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<div class="loginBlock">
		<form action="/login" method="post"> 
			<p>아이디: <input type="text" name="username" autofocus><br></p>
			<p>비밀번호: <input type="password" name="password"><br></p>
			<p><input name="remember-me" type="checkbox">자동 로그인</p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input id="loginButton" type="submit" value="로그인">
		<button id="joinButton" onclick="location.href='/join'">회원가입</button>
		</form>
		</div>
	</div>
	<!------ content -------->
	<div class="sidebar"></div>
</div>
<%@ include file="layout/footer.jsp"%>