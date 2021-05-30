var commentService = (function(){

	function add(comment, callback, error){
		console.log("add comment.................");
		
		$.ajax({
			type : 'post',
			url : '/comment/new',
			data : JSON.stringify(comment),
			contentType : "application/json; charset=utf-8"
		}).done(function(result, status, xhr) {
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, er) {
			if(er) {
				error(er);
			}
		});
	}
	
	function getList(param, callback, error){
		var albumNo = param.albumNo;
		var page = param.page || 1;
		
		$.getJSON("/comment/pages/" + albumNo + "/" + page + ".json",
			function(data) {
				if(callback){
					callback(data.commentCnt, data.list);
				}
			}).fail(function(xhr, status, err) {
			if(error){
				error();
			}
		});
	}
	
	function remove(no, writer, callback, error){
		$.ajax({
			type : 'delete',
			url : '/comment/' + no,
			data: JSON.stringify({no:no, writer:writer}),
			contentType: "application/json; charset=utf-8"
		}).done(function(deleteResult, status, xhr) {
			if(callback) {
				callback(deleteResult);
			}
		}).fail(function(xhr, status, er) {
			if(error) {
				error(er);
			}
		});
	}
	
	function update(no, content, callback, error){
		console.log("comment no: " + no);
		
		$.ajax({
			type : 'put',
			url : '/comment/' + no,
			data : JSON.stringify({commentNo:no, content:content}),
			contentType : "application/json; charset=utf-8"
		}).done(function(result, status, xhr) {
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, er) {
			if(error) {
				error(er);
			}
		});
	}
	
	function get(no, callback, error){
		$.get("/comment/" + albumNo + ".json", function(result){
		
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	
	function displayTime(timeValue){
		var today = new Date();
		
		var gap = today.getTime() - timeValue;
		
		var dateObj = new Date(timeValue);
		
		if(gap < (1000 * 60 * 60 * 24)) {
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [ (hh > 9 ? '' : '0' ) + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss ].join('');
		}
		else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return [ yy, '/', (mm > 9 ? '' : '0' ) + mm, '/', (dd > 9 ? '' : '0' ) + dd ].join('');
		}
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
	};
})();