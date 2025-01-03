package com.matching.plaform.service;

import java.util.HashMap;
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

    public Map<String, Object> boardbyCategory(int categoryCode, String type, String keyword) {
    	boolean searchOption = (type.equals("null") || keyword.equals("null")) ? false : true;
 
        int listCount = boardMapper.getBoardView(type, keyword);
        
        List<Board> boardList = boardMapper.boardbyCategory(categoryCode, type , keyword);
       
        Map<String, Object> modelMap = new HashMap<String, Object>();
        
        modelMap.put("bList", boardList);
        modelMap.put("listCount", listCount);
        modelMap.put("searchOption", searchOption);
        
        if(searchOption) {
        	modelMap.put("type", type);
        	modelMap.put("keyword", keyword);
        }  
    	return modelMap;
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
    
    public Map<String, Integer> boardLike(int boardNo, String boardLike){
    		
    		boardMapper.updateEmpathy(boardNo, boardLike);
    		Board board = boardMapper.getEmpathy(boardNo);
    		
		Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("boardLike", board.getBoardLike());
    	map.put("boardDislike", board.getBoardDislike());
    	
    	return map;
    }
    
    
    public List<Reply> replyList(int boardNo) {
        return boardMapper.replyList(boardNo);
    }

    public void saveReply(Reply reply) {
    	boardMapper.saveReply(reply);
    }
    public void updateReply(Reply reply) {
    	boardMapper.updateReply(reply);
    }

    public void deleteReply(int replyNo) {
        boardMapper.deleteReply(replyNo);
    }
}
