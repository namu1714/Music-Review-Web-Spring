<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<div class="loginBlock">
			<div style="margin-top:20%">
				<h2>회원가입 성공</h2>
				<br/>
				<button class="btn btn-info" onclick="location.href='/album/list'">목록</button>
				<button class="btn btn-warning" onclick="location.href='/login'">로그인</button>
			</div>
		</div>
	</div>
	<!------ content -------->
	<div class="sidebar"></div>
</div>
<%@ include file="layout/footer.jsp"%>