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

.review-panel {
	margin: 30px 50px 20px 50px;
	padding: 5px;
	background-color: #fad4e1;
	text-align:left;
}
.comment-header{
	padding:5px;
	border-bottom: 1px solid lightgray;
}
.comment-body{
	padding:5px;
	height:90px;
}

td {
	vertical-align: top;
	padding: 10px;
}

img {
	width: 200px;
	height: 200px
}

.writeComment {
	width: 100px;
}

.readComment {
	margin: 10px;
	padding: 5px;
	background-color: white;
	border: 0;
	border-radius: 15px;
}

.readComment:hover {
	background-color: whiteSmoke;
}

#commentForm {
	margin: 10px;
	padding: 5px;
}

#commentSubmit{
	float:right;
	width:90px; height:30px;
	border:0;
	border-radius:10px;
}
#commentSubmit:hover{
	background-color: lightgray;
	color: white;
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
		<br/>

		<!----------- 앨범 커멘트 ------------>
		<div class="review-panel">
			<div class="panel-header">
				<h2 style="color:white;margin-left: 10px;">COMMENT</h2>
			</div>
			<div class="panel-body contents">
				
			</div>
			<div class="panel-footer" style="text-align:center">
			
			</div>
			<form role="form" id="commentForm" action="/comment/new" method="post">
				<textarea name="content" placeholder="댓글을 입력해주세요" rows="4" style="width:100%" required></textarea>
				<input type="text" name="writerId" placeholder="아이디" required>
				<input type="text" name="writerName" placeholder="닉네임" required>
				<input type="hidden" name="albumNo" value="${album.albumNo}">
				<button id="commentSubmit" style="float:right">입력</button>
			</form>
		</div>
		
	</div>
	<!------ content -------->
	<div class="sidebar"></div>
</div>

<script type="text/javascript" src="/resources/js/comment.js"></script>

<script type="text/javascript">
$(document).ready(function(e) {
	//button
	var deleteForm = $("#deleteForm");

	$('#deleteButton').on("click", function(e) {
		if (confirm("정말 삭제하시겠습니까?") == true) {
			deleteForm.submit();
		} else { //취소
			return false;
		}
	});

	$('#modifyButton').on("click", function(e) {
			location.href = "/album/modify?no=" + "<c:out value='${album.albumNo}'/>";
	});
	
	
	//comment
	var pageNum = 1;
	var albumNo = "<c:out value='${album.albumNo}'/>";
	
	var commentBody = $(".contents");
	var commentFooter = $(".panel-footer");
	
	showCommentList(1);
	
	function showCommentList(page){
		console.log("show comment list: page" + page);
		
		commentService.getList({ albumNo : albumNo, page : page || 1 },
		function(commentCnt, list){
			console.log("commentCnt: " + commentCnt);
			console.log("list: " + list);
			
			if (list == null || list.length == 0) {
				return;
			}
			
			var str=""
			for (var i = 0; i < list.length; i++) {
				str += "<div class='readComment' data-no='" + list[i].commentNo + "'>"
				str += "	<div class='comment-header'><strong class='primary-font'>" + list[i].writerName + "</strong>";
				str += "		<small class='text-muted' style='float:right'>";
				str += "		최근작성시간:" + commentService.displayTime(list[i].regdate) + "</small></div>";
				str += "	<div class='comment-body'><p>" + list[i].content + "</p></div></div>";
			}
			
			commentBody.html(str);
			showCommentPaging(commentCnt);
		}); //end getList
	}
	
	function showCommentPaging(commentCnt){
		var endNum = Math.ceil(pageNum / 5.0) * 5;
		var startNum = endNum - 4;

		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 5 >= commentCnt){
			endNum = Math.ceil(commentCnt/5.0);
		}
		if(endNum * 5 < commentCnt){
			next = true;
		}
		
		var str = "<ul class='pagination justify-content-center'>";
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>";
		}
		for(var i = startNum; i<=endNum; i++){
			var active = (pageNum == i)? "active":"";	
			str += "<li class='page-item " + active + " '><a class='page-link' href='" + i + "'>" + i + "</a></li>";
		}
		if(next){
			str += "<li class='page-item'><a class='page-link' href='" + (endNum - 1) + "'>Next</a></li>";
		}
		str += "</ul></div>";
		
		console.log(str);
		commentFooter.html(str);
	}
	
	commentFooter.on("click", "li a", function(e){
		e.preventDefault();
		
		var targetPageNum = $(this).attr("href");
		
		console.log("targetPageNum: " + targetPageNum);
		
		pageNum = targetPageNum;
		showCommentList(pageNum);
	})
	
	$('#commentSubmit').on("click", function(e){
		e.preventDefault();
		 var formArr = $("form[id=commentForm]").serializeArray() ;
		 var obj = null;
         if (formArr) {
             obj = {};
             jQuery.each(formArr, function() {
                 obj[this.name] = this.value;
             });
         }//if ( arr ) {
		 
		commentService.add(obj, 
		function(result){
			 showCommentList(1);
			 $("form[id=commentForm]").find("input").val("");
			 $("form[id=commentForm]").find("textarea").val("");
		});
	})
});
</script>

<%@ include file="../layout/footer.jsp"%>