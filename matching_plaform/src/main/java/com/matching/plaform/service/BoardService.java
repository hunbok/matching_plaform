package com.matching.plaform.service;

import java.util.List;

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
		return boardMapper.getDetail(boardNo);
	}

	public void writeBoard(Board board) {
		boardMapper.writeBoard(board);
	}
}
