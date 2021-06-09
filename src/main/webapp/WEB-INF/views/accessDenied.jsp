<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<br>
		<h1>403</h1>
		<p>접근 권한이 없습니다.</p>
		
		<button class="btn btn-info" onclick="location.href='/album/list'">목록</button>
	</div>
	<!------ content -------->
	<div class="sidebar"></div>
</div>
<%@ include file="layout/footer.jsp"%>