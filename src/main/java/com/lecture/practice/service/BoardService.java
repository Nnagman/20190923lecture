package com.lecture.practice.service;

import java.util.List;

import com.lecture.practice.domain.BoardVO;
import com.lecture.practice.domain.CommentVO;

public interface BoardService {
	List<BoardVO> getBoardList() throws Exception;
	List<CommentVO> getCommentList(BoardVO boardVO) throws Exception;
	BoardVO getCountList(BoardVO boardVO) throws Exception;
	void board_write(BoardVO boardVO) throws Exception;
	void comment_write(CommentVO commentVO) throws Exception;
	void board_file(BoardVO boardVO) throws Exception;
	BoardVO board_detail(BoardVO boardVO) throws Exception;
	List<BoardVO> board_file_detail(BoardVO boardVO) throws Exception;
	void board_delete(BoardVO boardVO) throws Exception;
	void comment_delete(CommentVO commentVO) throws Exception;
	void board_file_delete(BoardVO boardVO) throws Exception;
	void board_modify(BoardVO boardVO) throws Exception;
	void board_file_modify(BoardVO boardVO) throws Exception;
	void comment_modify(CommentVO commentVO) throws Exception;
	void board_count(BoardVO boardVO) throws Exception;
}
