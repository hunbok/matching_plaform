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

    @PostMapping("/updateEmpathy.ajax")
    public Map<String, Integer> updateEmpathy(@RequestParam("boardNo") int boardNo, 
                                              @RequestParam("empathy") String empathy) {
        return boardService.updateEmpathy(boardNo, empathy);
    }

    @PostMapping("/replyWrite.ajax")
    public List<Reply> replyWrite(@RequestParam String replyContent, 
                                  @RequestParam int boardNo, 
                                  @RequestParam String memberId) {
        boardService.saveReply(replyContent, boardNo, memberId);
        return boardService.getReplies(boardNo);
    }

    @PostMapping("/modifyReply.ajax")
    public List<Reply> modifyReply(@RequestParam int replyNo, 
                                   @RequestParam String replyContent) {
        boardService.modifyReply(replyNo, replyContent);
        return boardService.getReplies(replyNo);
    }

    @PostMapping("/deleteReply.ajax")
    public List<Reply> deleteReply(@RequestParam int replyNo) {
        boardService.deleteReply(replyNo);
        return boardService.getReplies(replyNo);
    }
}

