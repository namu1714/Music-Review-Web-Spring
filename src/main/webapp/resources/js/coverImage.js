$(document).ready(function(e) {
	var maxSize = 10000000; //5MB

	function checkExtension(fileSize) {
		if (fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}
		return true;
	}

	function showUploadedImage(uploadResult) {
		if (!uploadResult) { return; }
		var uploadDiv = $(".uploadResult");

		var fileCallPath = encodeURIComponent(uploadResult);
		var str = "<img src='/display?fileName=" + fileCallPath + "'";
		str += " style='width:100%'>"

		uploadDiv.append(str);
	}

	function registerCoverName(uploadResult) {
		var albumForm = $("#albumForm");
		albumForm.find("input[name='coverImage']").val(uploadResult);
	}

	$("input[type='file']").change(function(e) {
		var formData = new FormData();
		var inputFile = $("input[name='imageFile']");
		var file = inputFile[0].files;

		if (!checkExtension(file[0].name, file[0].size)) {
			return false;
		}

		formData.append("file", file[0]);
		
		// 이미 선택된 파일이 있으면 삭제
		var albumForm = $("#albumForm");
		var deleteImage = albumForm.find("input[name='coverImage']").val();
		console.log(deleteImage);
		
		if(deleteImage.length != 0 || deleteImage != ''){
			
			console.log("delete file");
			targetImg = $('.uploadResult').find('img');
		
			$.ajax({
				type: 'POST',
				url: '/deleteFile',
				data: {fileName : deleteImage},
				dataType: 'text'
			}).done(function(result){
				targetImg.remove();
			});
		}

		$.ajax({
			type: 'POST',
			url: '/uploadAjax',
			processData: false,
			contentType: false,
			data: formData
		}).done(function(result) {
			console.log(result);
			registerCoverName(result);
			showUploadedImage(result);
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	}); //delete & upload
});