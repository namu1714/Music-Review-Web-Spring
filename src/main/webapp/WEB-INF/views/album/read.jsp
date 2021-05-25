<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<style type="text/css">
table {
	margin: 30px 50px 20px 50px;
	text-align: left;
}

.albumInfo {
	border: solid 3px lightgray;
}

.comment {
	table-layout: fixed;
	background-color: #fad4e1
}

td {
	vertical-align: top;
	padding: 10px;
}

.reviewContent {
	height: 110px;
	text-overflow: ellipsis;
	overflow: hidden;
}

img {
	width: 200px;
	height: 200px
}

.writeComment {
	width: 100px;
}

.readComment {
	margin-top: 10px;
	padding: 5px;
	background-color: white;
	border: 0;
}

.readComment:hover {
	background-color: whiteSmoke;
}
</style>

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<!---------------앨범 정보---------------->
		<table class="albumInfo">
			<tr>
				<td><img src='/display?fileName=${album.coverImage}' /></td>
				<td style="width: 100%;"><font size="5"><b>${album.title}</b></font><br>
					${album.releaseYear}<br> <b>${album.artist}</b></td>
			</tr>
		</table>

		<form role="form" id="deleteForm" action="/album/remove" method="post">
			<input type="hidden" name="albumNo" value="${album.albumNo}">
		</form>
		
		<button class="btn btn-info" id="modifyButton">수정</button>
		<button class="btn btn-warning" id="deleteButton">삭제</button>

		<!---------------앨범 코멘트---------------
			<c:if test="${review.hasNoComments()}">게시글이 없습니다.</c:if>

			<c:forEach var="comment" items="${review.content}">
				<table class="comment">
					<tr>
						<td style="width:100%"><b>${comment.writer.name}</b></td>
						<td style="text-align:right;width:100px"><font color="red">♥${comment.likes}</font></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="reviewContent"><u:pre value='${comment.content}'/></div>
							<div style="text-align:right">
								<button class="readComment" onclick="location.href='read.do?no=${comment.number}'">전체 보기</button>
							</div>
						</td>
					</tr>
				</table>
			</c:forEach>
			
			
			<c:if test="${review.hasComments()}">
			<tr>
				<td colspan="4">
					<c:if test="${review.startPage > 5}">
						<a href="list.do?album=${review.album.number}&pageNo=${review.startPage - 5}">[이전]</a>
					</c:if>
					<c:forEach var="pNo" begin="${review.startPage}" end="${review.endPage}">
						<c:if test="${review.currentPage == pNo}">
							[${pNo}]
						</c:if>
						<c:if test="${review.currentPage != pNo}">
							<a href="list.do?album=${review.album.number}&pageNo=${pNo}">[${pNo}]</a>
						</c:if>
					</c:forEach>
					<c:if test="${review.endPage < review.totalPages}">
						<a href="list.do?album=${review.album.number}&pageNo=${review.startPage + 5}">[다음]</a>
					</c:if>
				</td>
			</tr>
			</c:if>
			------------------------------->
	</div>
	<!------ content -------->
	<div class="sidebar"></div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	var deleteForm = $("#deleteForm");
	
	$('#deleteButton').on("click", function(e){
		if (confirm("정말 삭제하시겠습니까?") == true){
			deleteForm.submit();
		}else{   //취소
			return false;
		}
	});
	
	$('#modifyButton').on("click", function(e){
		location.href = "/album/modify?no=" + "<c:out value='${album.albumNo}'/>";
	});
});
</script>