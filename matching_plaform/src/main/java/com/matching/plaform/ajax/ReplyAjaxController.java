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

    @PostMapping("/boardLike.ajax")
    public Map<String, Integer> boardLike(@RequestParam("boardNo") int boardNo, 
                                      @RequestParam("boardLike")String boardLike) {
    
        System.out.println("boardNo: " + boardNo); 
        System.out.println("boardLike: " + boardLike);

        return boardService.boardLike(boardNo, boardLike);
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

