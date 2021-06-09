<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<div class="row">
			<div class="col-lg-12" style="text-align:left">
				<h2>앨범 업로드</h2><br/>
			</div>
		</div>
		<form id="albumForm" role="form" method="post" action="/album/upload">
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">아티스트명</label>
				<div class="col-sm-10">
					<input class="form-control" name="artist" required>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">앨범 타이틀</label>
				<div class="col-sm-10">
					<input class="form-control" name="title" required>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">발매년도</label>
				<div class="col-sm-10">
					<input class="form-control" name="releaseYear" required>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">커버 이미지</label>
				<div class="col-sm-10">
					<input class="form-control" type="file" name="imageFile" required
					style="border:0px">
					<br/>
					<div class='uploadResult' style="width:150px">

					</div>
				</div>
			</div>
			
			<input type="hidden" name="coverImage" value="">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<input type="submit" value="확인" style="width:100px">
		</form>
	</div>
	<!----- content ------>

	<div class="sidebar"></div>
</div>

<script type="text/javascript">
$(document).ready(function(e) {
	var csrfHeaderName="${_csrf.headerName}";
	var csrfTokenValue="${_csrf.token}";
	
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);	
	});	
});
</script>

<script src="/resources/js/coverImage.js"></script>
<%@ include file="../layout/footer.jsp"%>