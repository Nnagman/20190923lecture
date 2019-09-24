package com.lecture.practice.service;

import java.util.List;

import com.lecture.practice.domain.BoardVO;

public interface BoardService {
	List<BoardVO> getBoardList() throws Exception;
	void board_write(BoardVO boardVO) throws Exception;
	void board_file(BoardVO boardVO) throws Exception;
	BoardVO board_detail(BoardVO boardVO) throws Exception;
	void board_delete(BoardVO boardVO) throws Exception;
	void board_modify(BoardVO boardVO) throws Exception;
	void board_file_modify(BoardVO boardVO) throws Exception;
}
