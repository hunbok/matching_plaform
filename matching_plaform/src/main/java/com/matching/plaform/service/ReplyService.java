package com.matching.plaform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matching.plaform.domain.Reply;
import com.matching.plaform.mapper.ReplyMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyService {
	
	@Autowired
	private ReplyMapper replyMapper;
	
	public List<Reply> ReplyList(int boardNo){
		return replyMapper.ReplyList(boardNo);
	}
}
