package com.lecture.practice.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lecture.practice.domain.MemberVO;

@Service
public class MemberServiceImplement implements MemberService {
	private static final String NAMESPACE = "com.lecture.practice.mapper.MemberMapper";
	private final SqlSession sqlSession;
	
	@Inject
	public MemberServiceImplement(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<MemberVO> getMemberList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getMemberList");
	}

	@Override
	public void register(MemberVO memberVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".register", memberVO);
		
	}

	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".login", memberVO);
	}
	
}
