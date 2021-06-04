<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<style type="text/css">
.col-form-label{
	text-align:left;
}
</style>

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<div class="row">
			<div class="col-lg-12" style="text-align:left">
				<h2>앨범 코멘트 수정</h2><br/>
			</div>
		</div>
		<div class="form-group row" >				
			<div class="col-sm-12" style="display:flex">
				<label class="col-sm-2 col-form-label">아티스트</label>
				<input class="form-control" name="artist" value='<c:out value="${album.artist}"/>' readonly>
				<label class="col-sm-2 col-form-label">앨범 타이틀</label>
				<input class="form-control" name="artist" value='<c:out value="${album.title}"/>' readonly>
			</div>
		</div>
		<form name="commentModify" role="form" action="/comment/modify" method="post">

			<div class="form-group row">
				<div class="col-sm-12" style="display:flex">
					<label class="col-sm-2 col-form-label">아이디</label>
					<input class="form-control" name="writerId" 
					   value='<c:out value="${comment.writerId}"/>' readonly>
					   
					<label class="col-sm-2 col-form-label">닉네임</label>
					<input class="form-control" name="writerId" 
					   value='<c:out value="${comment.writerName}"/>' readonly>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-12">
					<textarea class="form-control" name="content" rows="10"><c:out value="${comment.content}"/></textarea>
				</div>
			</div>
			
			<input type="hidden" name="commentNo" value="${comment.commentNo}">
			<input type="hidden" name="albumNo" value="${album.albumNo}">
			
			<input type="submit" value="확인" style="width:100px">
		</form>
	</div>
	<!----- content ------>

	<div class="sidebar"></div>
</div>
<script src="/resources/js/coverImage.js"></script>

<%@ include file="../layout/footer.jsp"%>