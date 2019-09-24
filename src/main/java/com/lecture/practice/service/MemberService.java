package com.lecture.practice.service;

import java.util.List;

import com.lecture.practice.domain.MemberVO;

public interface MemberService {
	List<MemberVO> getMemberList() throws Exception;
	void register(MemberVO memberVO) throws Exception;
	MemberVO login(MemberVO memberVO) throws Exception;
}
