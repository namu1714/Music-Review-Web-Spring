<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>

<style type="text/css">
.col-form-label{
	text-align:right;
}
.col-sm-10{
	text-align:left;
}
 .form-control{
 	display:inline;
 	width:70%;
 	margin:5px;
 }
</style>

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<div class="row">
			<div class="col-lg-12" style="text-align:left; margin:10px">
				<h2>회원가입</h2>
			</div>
		</div>
		<hr style="border: solid 1px lightgray;"><br/>
		<form id="joinForm" role="form" method="post" action="/join">
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">아이디</label>
				<div class="col-sm-10">
					<input class="form-control" name="memberid" required>
					<strong style="color:red">*</strong>
					&nbsp
					<button type="button" id="duplicateCheck">중복체크</button>
				</div>	
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password" 
					placeholder="비밀번호는 8-12자로 설정해주세요" required>
					<strong style="color:red">*</strong>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">비밀번호 확인</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="passwordConfirm" required>
					<strong style="color:red">*</strong>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">닉네임</label>
				<div class="col-sm-10">
					<input class="form-control" name="nickname" required>
					<strong style="color:red">*</strong>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<input class="form-control" name="email" required>
					<strong style="color:red">*</strong>
				</div>
			</div>

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input id="joinSubmit" type="submit" value="확인" style="width:100px">
		</form>
	</div>
	
	<!------ content -------->
	<div class="sidebar"></div>
	
	<div class="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">아이디 중복체크</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	var joinForm = $("#joinForm");
	var duplCheck = false;
	
	var csrfHeaderName="${_csrf.headerName}";
	var csrfTokenValue="${_csrf.token}";
	
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);	
	});
	
	function checkPw(){
		var pw = joinForm.find("[name='password']").val();
		var confirmPw = joinForm.find("[name='passwordConfirm']").val();
		
		console.log(pw);
		console.log(confirmPw);
		
		if (pw != confirmPw){
			return false;
		}
		return true;
	}
	
	$("#joinSubmit").on("click", function(e){
		e.preventDefault();
		
		if(duplCheck == false){
			alert("아이디 중복체크를 해주세요!");
			return false;
		}
		if(checkPw() == false){
			alert("패스워드 확인이 일치하지 않습니다.");
			return false;
		}
		
		joinForm.submit();
	});
	
	$("#duplicateCheck").on("click", function(e){
		var idInput = joinForm.find("[name='memberid']");
		var id = idInput.val();
		var str = "";
		console.log("id check: " + id);
		
		if(jQuery.trim(id) == ""){
			alert("아이디를 입력해주세요!");
			return false;
		}
		
		$.get("/member/" + id, function(result){
			if(result == "ok"){
				console.log("아이디 사용 가능");
				str += "사용할 수 있는 아이디입니다.";
				$(".modal-body p").html(str);
				
				idInput.attr('readonly', true);
				duplCheck = true;
			}
			else if(result == "exist"){
				console.log("중복 아이디");
				str += "이미 사용중인 아이디입니다.";
				$(".modal-body p").html(str);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
		
		$(".modal").modal("show");
	});
});
</script>
<%@ include file="layout/footer.jsp"%>