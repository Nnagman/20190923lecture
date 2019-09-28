package com.lecture.practice.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lecture.practice.domain.BoardVO;
import com.lecture.practice.domain.CommentVO;

@Service
public class BoardServiceImplement implements BoardService {
	
	private static final String NAMESPACE = "com.lecture.practice.mapper.BoardMapper";
	private final SqlSession sqlSession;
	
	@Inject
	public BoardServiceImplement(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BoardVO> getBoardList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getBoardList");
	}
	
	@Override
	public List<CommentVO> getCommentList(BoardVO boardVO) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getCommentList", boardVO);
	}
	
	@Override
	public BoardVO getCountList(BoardVO boardVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getCountList", boardVO);
	}

	@Override
	public void board_write(BoardVO boardVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".board_write", boardVO);
	}
	
	@Override
	public void comment_write(CommentVO commentVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".comment_write", commentVO);
	}

	@Override
	public void board_file(BoardVO boardVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".board_file", boardVO);
	}

	@Override
	public BoardVO board_detail(BoardVO boardVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".board_detail", boardVO);
	}
	
	@Override
	public List<BoardVO> board_file_detail(BoardVO boardVO) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".board_file_detail", boardVO);
	}

	@Override
	public void board_delete(BoardVO boardVO) throws Exception {
		sqlSession.delete(NAMESPACE + ".board_delete", boardVO);
	}
	
	@Override
	public void comment_delete(CommentVO commentVO) throws Exception {
		sqlSession.delete(NAMESPACE + ".comment_delete", commentVO);
	}
	
	@Override
	public void board_file_delete(BoardVO boardVO) throws Exception {
		sqlSession.delete(NAMESPACE + ".board_file_delete", boardVO);
	}
	
	@Override
	public void board_modify(BoardVO boardVO) throws Exception {
		sqlSession.update(NAMESPACE + ".board_modify", boardVO);
	}
	
	@Override
	public void comment_modify(CommentVO commentVO) throws Exception {
		sqlSession.update(NAMESPACE + ".comment_modify", commentVO);
	}

	@Override
	public void board_file_modify(BoardVO boardVO) throws Exception {
		sqlSession.update(NAMESPACE + ".board_file_modify", boardVO);
	}

	@Override
	public void board_count(BoardVO boardVO) {
		sqlSession.insert(NAMESPACE + ".board_count", boardVO);
		
	}
}
