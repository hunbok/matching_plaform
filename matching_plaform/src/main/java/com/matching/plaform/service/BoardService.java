package com.matching.plaform.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.plaform.domain.Board;
import com.matching.plaform.domain.Reply;
import com.matching.plaform.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public List<Board> boardbyCategory(int categoryCode) {
        return boardMapper.boardbyCategory(categoryCode);
    }

    public Board getDetail(int boardNo) {
        log.info("BoardService: getDetail(int boardNo)");
        return boardMapper.getDetail(boardNo);
    }

    public void writeBoard(Board board) {
        boardMapper.writeBoard(board);
    }
    
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }
    
    public void deleteBoard(int boardNo) {
        boardMapper.deleteBoard(boardNo);
    }
    
    public Map<String, Integer> getEmpathy(int boardNo) {
        return boardMapper.getEmpathy(boardNo);
    }

    public Map<String, Integer> updateEmpathy(int boardNo, String empathy) {
        boardMapper.updateEmpathy(empathy, boardNo);
        return boardMapper.getEmpathy(boardNo);
    }
    
    public List<Reply> getReplies(int boardNo) {
        return boardMapper.replyList(boardNo);
    }

    public void saveReply(String replyContent, int boardNo, String memberId) {
        boardMapper.saveReply(replyContent, boardNo, memberId);
    }

    public void modifyReply(int replyNo, String replyContent) {
        boardMapper.modifyReply(replyNo, replyContent);
    }

    public void deleteReply(int replyNo) {
        boardMapper.deleteReply(replyNo);
    }
}
