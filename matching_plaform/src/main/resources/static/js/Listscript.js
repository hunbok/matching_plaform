$(function() {
	$("#writeForm").on("submit", function() {
		if ($("#name").val().length <= 0) {
			alert("작성자를 입력해주세요");
			$("#name").focus();
			return false;
		}
		if ($("#title").val().length <= 0) {
			alert("제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if ($("#content").val().length <= 0) {
			alert("내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
	});

});