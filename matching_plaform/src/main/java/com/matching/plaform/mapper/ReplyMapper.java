package com.matching.plaform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.matching.plaform.domain.Reply;

@Mapper
public interface ReplyMapper {

	public List<Reply> ReplyList(int boardNo);
}
