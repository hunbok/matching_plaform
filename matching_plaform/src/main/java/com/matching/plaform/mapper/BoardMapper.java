package com.matching.plaform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matching.plaform.domain.Board;

@Mapper
public interface BoardMapper {

	public List<Board> boardList(int categoryCode);
}
