$(function() {

	$("#detailUpdate").on("click", function() {	
		
		$("#checkForm").attr("action", "updateForm");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	$("#detailDelete").on("click", function() {

		$("#checkForm").attr("action", "delete");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
	$("#writeForm").on("submit", function() {
	    if($("#name").val().length <= 0) {
	        alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
	        $("#writer").focus();            
	        return false;
	    }
	    if($("#boardTitle").val().length <= 0) { 
	        alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
	        $("#boardTitle").focus(); 
	        return false;
	    }
	    if($("#boardContent").val().length <= 0) { 
	        alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
	        $("#boardContent").focus();
	        return false;
	    }
	});


	$("#updateForm").on("submit", function() {
		if($("#name").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#name").focus();			
			return false;
		}
		if($("#boardTitle").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#boardTitle").focus();
			return false;
		}
		if($("#memberPass").val().length <= 0) {
			alert("비밀번호가 입력되지 않았습니다.\n비밀번호를 입력해주세요");
			$("#memberPass").focus();
			return false;
		}
		if($("#boardContent").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#boardContent").focus();
			return false;
		}
	});
});