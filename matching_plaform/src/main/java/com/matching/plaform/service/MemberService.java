package com.matching.plaform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matching.plaform.domain.Member;
import com.matching.plaform.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public int login(String id, String pass) {
		int revalue = 0;
		Member m = memberMapper.getMember(id);
		
		if(m == null) {
			return revalue;
		}
		if(passwordEncoder.matches(pass,m.getPass())) {
			revalue= 1;
		}else {
			revalue = 2;
		}
		return revalue;		
	}
	public Member getMember(String id) {
		log.info("MamberMapper:" + memberMapper.getMember(id));
		return memberMapper.getMember(id);
	}
}
