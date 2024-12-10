package com.matching.plaform.ajax;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
        return boardService.empathy(boardNo,empathy);
    }

    @PostMapping("/replyWrite.ajax")
    public List<Reply> replyWrite(Reply reply) {
        boardService.saveReply(reply);
        return boardService.replyList(reply.getBoardNo());
    }
    
    @PatchMapping("/replyUpdate.ajax")
    public List<Reply> updateReply(Reply reply) {
    	
    boardService.updateReply(reply);

    return boardService.replyList(reply.getBoardNo());
    }

    @DeleteMapping("/replyDelete.ajax")
    public List<Reply> deleteReply(@RequestParam ("replyNo")int replyNo,
    		@RequestParam("boardNo")int boardNo) {
        boardService.deleteReply(replyNo);
        return boardService.replyList(boardNo);
    }
}

