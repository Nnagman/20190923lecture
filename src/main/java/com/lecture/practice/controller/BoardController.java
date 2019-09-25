package com.lecture.practice.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lecture.practice.domain.BoardVO;
import com.lecture.practice.domain.CommentVO;
import com.lecture.practice.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private BoardService boardService;
	
	@Inject
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView board(Model model) throws Exception {
		List<BoardVO> list = boardService.getBoardList();
		List<CommentVO> list2 = boardService.getCommentList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board");
		mav.addObject("list", list);
		mav.addObject("list2", list2);
		mav.addObject("msg", "로그인을 해주세요.");
		
		return mav;
	}
	
	@RequestMapping(value = "/board_detail", method = RequestMethod.GET)
	public ModelAndView board_detail(Model model, BoardVO boardVO) throws Exception {
		logger.info(boardVO.toString());
		
		if(boardVO.getId().equals("")) {
			List<BoardVO> list = boardService.getBoardList();
			List<CommentVO> list2 = boardService.getCommentList();
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("board");
			mav.addObject("list", list);
			mav.addObject("list2", list2);
			mav.addObject("msg", "로그인을 해주세요.");
			
			return mav;
		}
		
		boardService.board_count(boardVO);
		BoardVO board_detail = boardService.board_detail(boardVO);
		BoardVO board_file_detail = boardService.board_file_detail(boardVO);
		
		if(board_file_detail != null) {
			board_detail.setFile_name(board_file_detail.getFile_name());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_detail");
		mav.addObject("board_detail", board_detail);
		
		return mav;
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.GET)
	public String board_writeGET() {
		return "board_write";
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.POST)
	public ModelAndView board_writePOST(Model model, BoardVO boardVO) throws Exception {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));

        boardVO.setBoard_id(today + "/" + boardVO.getId());
        
        logger.info(boardVO.toString());
		
		if(boardVO.getFile_name() == null) {
			logger.info("NO FILE");
			boardService.board_write(boardVO);
		}else {
			logger.info("FILE EXIST");
			boardService.board_write(boardVO);
			boardService.board_file(boardVO);
		}
		
		List<BoardVO> list = boardService.getBoardList();
		List<CommentVO> list2 = boardService.getCommentList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board");
		mav.addObject("list", list);
		mav.addObject("list2", list2);
		mav.addObject("msg", "로그인을 해주세요.");
		
		return mav;
	}
	
	@RequestMapping(value = "/comment_write", method = RequestMethod.POST)
	public String comment_writePOST(CommentVO commentVO) throws Exception {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));

        commentVO.setComment_id(today + "/" + commentVO.getId());
		
		return "board_write";
	}
	
	@RequestMapping(value = "/board_edit", method = RequestMethod.GET)
	public String board_editGET() {
		return "board_edit";
	}
	
	@RequestMapping(value = "/board_edit", method = RequestMethod.POST)
	public String board_editPOST() {
		return "board_edit";
	}
	
}
