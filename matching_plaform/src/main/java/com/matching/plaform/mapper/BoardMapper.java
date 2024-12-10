package com.matching.plaform.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.matching.plaform.domain.Board;
import com.matching.plaform.domain.Reply;

@Mapper
public interface BoardMapper {

    List<Board> boardbyCategory(@Param("keyword")String keyword,@Param("type")String type ,@Param("categoryCode")int categoryCode);
    
    int getBoardView(@Param("type")String type, @Param("keyword")String keyword);

    Board getDetail(int boardNo);

    void writeBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(int boardNo);

    // reply
    void updateEmpathy(@Param("empathy") String empathy, @Param("boardNo") int boardNo);

    Map<String, Integer> getEmpathy(int boardNo);
    
    List<Reply> replyList(int boardNo);
    
    void saveReply(@Param("replyContent") String replyContent, 
            @Param("boardNo") int boardNo, 
            @Param("memberId") String memberId);
    
    void modifyReply(@Param("replyNo") int replyNo, 
            @Param("replyContent") String replyContent);
    
    void deleteReply(int replyNo);
}

