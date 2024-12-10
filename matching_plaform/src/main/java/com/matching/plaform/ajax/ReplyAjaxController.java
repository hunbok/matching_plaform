package com.matching.plaform.ajax;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matching.plaform.domain.Reply;
import com.matching.plaform.service.BoardService;

@RestController
public class ReplyAjaxController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/empathy.ajax")
    public Map<String, Integer> empathy(@RequestParam("boardNo") int boardNo, 
                                              @RequestParam("empathy") String empathy) {
        return boardService.empathy(boardNo, empathy);
    }

    @PostMapping("/replyWrite.ajax")
    public List<Reply> replyWrite(@RequestParam String replyContent, 
                                  @RequestParam int boardNo, 
                                  @RequestParam String memberId) {
        boardService.saveReply(replyContent, boardNo, memberId);
        return boardService.replyList(boardNo);
    }

    @PostMapping("/modifyReply.ajax")
    public List<Reply> modifyReply(@RequestParam int replyNo, 
                                   @RequestParam String replyContent) {
        boardService.modifyReply(replyNo, replyContent);
        return boardService.replyList(replyNo);
    }

    @PostMapping("/deleteReply.ajax")
    public List<Reply> deleteReply(@RequestParam int replyNo) {
        boardService.deleteReply(replyNo);
        return boardService.replyList(replyNo);
    }
}

