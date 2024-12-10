package com.matching.plaform.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.matching.plaform.domain.Member;

@Mapper
public interface MemberMapper {
	
	public Member getMember(String id);
	
}
