$(".btnCommend").click(function() {
	var com = $(this).attr("id");
	var boardNo = $("#boardNo").val(); 

	$.ajax({
		url: "/updateEmpathy.ajax", 
		type: "post",
		data: {	empathy: com, boardNo: $("#boardNo").val()},
		dataType: "json",
		success: function(data) {
			var msg = com === 'replyLike' ? "추천이" : "비추천이";
			alert(msg + " 반영되었습니다.");

			$("#replyLike > .recommend").text("(" + data.replyLike + ")");
			$("#replydisLike > .recommend").text("("+ data.replyDislike +")");
		},
		error: function(xhr, status, error) {
			alert("Error: "+ xhr.statusText + ", " + error);
		}
	});
});
$(document).on("submit", "#replyWriteForm", function(e) {
	e.preventDefault();

	var replyContent = $("#replyContent").val().trim();
	if (replyContent.length < 5) {
		alert("댓글은 5자 이상 입력해야 합니다.");
		return;
	}

	$.ajax({
		url: "/replyWrite.ajax",
		type: "post",
		data: $(this).serialize(),
		dataType: "json",
		success: function(data) {
		
			loadReplies(data);
		},
		error: function(xhr, status, error) {
			alert("댓글 작성 실패: " + error);
		}
	});
});

function loadReplies(replies) {
	var replyList = $("#rList");
	replyList.empty();

	$.each(replies, function(i, reply) {
		var date = new Date(reply.replyRegDate);
		var formattedDate = date.toISOString().slice(0, 19).replace("T", " ");

		var replyHtml = `
            '<div class="replyRow row border border-top-0">'
          	+ '    <div class="col">'
            + '      <div class="row bg-light p-2">'
            + '          <div class="col-4">'
            + '              <span>${reply.memberId}</span>'
            + '          </div>
            + '          <div class="col-8 text-end">'
            + '              <span class="me-3">${formattedDate}</span>'
            + '              <button class="modifyReply btn btn-outline-success btn-sm" data-no="${reply.replyNo}">'
            + '                  <i class="bi bi-journal-text">수정</i>'
            + '              </button>'
            + '              <button class="deleteReply btn btn-outline-warning btn-sm" data-no="${reply.replyNo}">'
            + '                  <i class="bi bi-trash">삭제</i>'
            + '              </button>'
            + '          </div>'
            + '      </div>'
            + '      <div class="row">'
            + '          <div class="col p-3">'
            + '              <pre>${reply.replyContent}</pre>'
            + '          </div>'
            + '      </div>'
              + '</div>'
            + '</div>'
        `;
		replyList.append(replyHtml);
	});
}
$(document).on("click", ".modifyReply", function() {
	var replyNo = $(this).data("no");
	var newContent = prompt("수정할 댓글을 입력하세요");

	if (newContent === null || newContent.trim() === "") {
		alert("댓글 내용은 비어 있을 수 없습니다.");
		return;
	}

	$.ajax({
		url: "/modifyReply.ajax", 
		type: "post",
		data: {
			replyNo: replyNo,
			replyContent: newContent
		},
		dataType: "json",
		success: function(data) {
			loadReplies(data); 
		},
		error: function(xhr, status, error) {
			alert("댓글 수정 실패: " + error);
		}
	});
});
$(document).on("click", ".deleteReply", function() {
	var replyNo = $(this).data("no");
	var confirmDelete = confirm("정말 삭제하시겠습니까?");

	if (!confirmDelete) return;

	$.ajax({
		url: "/deleteReply.ajax", 
		type: "post",
		data: { replyNo: replyNo },
		dataType: "json",
		success: function(data) {
			loadReplies(data); 
		},
		error: function(xhr, status, error) {
			alert("댓글 삭제 실패: " + error);
		}
	});
});
