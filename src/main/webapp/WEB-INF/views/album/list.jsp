<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<style type="text/css">
img {
	width: 100%;
	height: auto;
}

.onealbum {
	width: 200px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.addAlbum {
	position: absolute;
	right: 250px;
	top: 50px;
	margin: 20px;
}
</style>
<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<form action="/album/list" method="GET">
			<input type="text" name="search" value="${inputText}"
				style="width: 450px"> <input type="submit" value="검색">
		</form>
		<button class="addAlbum" onclick="location.href='/album/upload'">앨범
			등록</button>

		<br>

		<!---------------앨범 이미지 정렬---------------->
		<div class="container text-center">
			<c:forEach var="i" begin="0" end="2">
				<div class="row">
					<c:forEach var="j" begin="0" end="2">
						<c:set value="${3*i+j}" var="index" />

						<div class="col-xs-12" style="float: none; margin: 0 auto;">
							<c:if test="${index lt albumList.size()}">
								<div class="onealbum">
									<img src='/display?fileName=${albumList[3*i+j].coverImage}' /><br />
									<a href="/album/read?no=${albumList[3*i+j].albumNo}"> <b><c:out
												value="${albumList[3*i+j].title}" /></b><br> <c:out
											value="${albumList[3*i+j].artist}" />
									</a>
								</div>
							</c:if>
						</div>

					</c:forEach>
				</div>
				<br />
			</c:forEach>
		</div>
		<!--------------- 정렬 테이블 ----------------->
		<br />
		<div class="container">
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev}">
					<li class="page-item previous"><a
						href="${pageMaker.startPage -1}">Previous</a></li>
				</c:if>

				<c:forEach var="num" begin="${pageMaker.startPage}"
					end="${pageMaker.endPage}">
					<li
						class="paginate_button page-item ${pageMaker.cri.pageNum == num ? 'active' : ''}">
						<a class="page-link" href="${num}">${num}</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next}">
					<li class="paginate_button next"><a
						href="${pageMaker.endPage +1}">Next</a></li>
				</c:if>
			</ul>
		</div>

		<form id='pageForm' action="/album/list" method='get'>
			<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
			<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
		</form>
		<!-- 페이지번호 -->
	</div>
	<!-- content -->
	<div class="sidebar"></div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		var pageForm = $("#pageForm");
		$(".paginate_button a").on("click", function(e) {
			e.preventDefault();

			console.log('click');

			pageForm.find("input[name='pageNum']").val($(this).attr("href"));
			pageForm.submit();
		});
	});
</script>

<%@ include file="../layout/footer.jsp"%>