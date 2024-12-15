package com.matching.plaform.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.matching.plaform.domain.Board;
import com.matching.plaform.domain.Reply;

@Mapper
public interface BoardMapper {

    List<Board> boardbyCategory(@Param("categoryCode")int categoryCode,@Param("type")String type,@Param("keyword")String keyword);
    
    int getBoardView(@Param("type")String type, @Param("keyword")String keyword);

    Board getDetail(int boardNo);

    void writeBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(int boardNo);

    // reply
    void updateEmpathy(@Param("boardNo") int boardNo, @Param("boardLike") String boardLike);

    Board getEmpathy(int boardNo);
    
    List<Reply> replyList(int boardNo);
    
    void saveReply(Reply reply);
    
    void updateReply(Reply reply);

    void deleteReply(int replyNo);
}

