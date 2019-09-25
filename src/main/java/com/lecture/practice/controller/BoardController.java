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
		return board_list(model);
	}
	
	@RequestMapping(value = "/board_detail", method = RequestMethod.GET)
	public ModelAndView board_detail(Model model, BoardVO boardVO) throws Exception {
		logger.info(boardVO.toString());
		
		if(boardVO.getId().equals("")) { return is_login(model); }
		
		return board_detail_function(model, boardVO);
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
		
		if(boardVO.getFile_name() == null) {
			boardService.board_write(boardVO);
		}else {
			boardService.board_write(boardVO);
			boardService.board_file(boardVO);
		}
				
		return board_detail_function(model, boardVO);
	}
	
	@RequestMapping(value = "/comment_write", method = RequestMethod.POST)
	public ModelAndView comment_writePOST(CommentVO commentVO) throws Exception {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        
        BoardVO boardVO = new BoardVO();
        boardVO.setBoard_id(commentVO.getBoard_id());
        
        logger.info(commentVO.toString());

        commentVO.setComment_id(today + "/" + commentVO.getId());
        boardService.comment_write(commentVO);
        
		BoardVO board_detail = boardService.board_detail(boardVO);
		BoardVO board_file_detail = boardService.board_file_detail(boardVO);
		
		if(board_file_detail != null) {
			board_detail.setFile_name(board_file_detail.getFile_name());
		}
		
		List<CommentVO> list = boardService.getCommentList(boardVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_detail");
		mav.addObject("board_detail", board_detail);
		mav.addObject("list", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/board_edit", method = RequestMethod.GET)
	public ModelAndView board_editGET(Model model, BoardVO boardVO) throws Exception {
		BoardVO board_detail = boardService.board_detail(boardVO);
		BoardVO board_file_detail = boardService.board_file_detail(boardVO);
		
		if(board_file_detail != null) {
			board_detail.setFile_name(board_file_detail.getFile_name());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_write");
		mav.addObject("board_detail", board_detail);
		mav.addObject("msg", "edit");
		
		return mav;
	}
	
	@RequestMapping(value = "/board_edit", method = RequestMethod.POST)
	public ModelAndView board_editPOST(Model model, BoardVO boardVO) throws Exception {
		if(boardVO.getFile_name() == null) {
			boardService.board_modify(boardVO);
		}else {
			boardService.board_modify(boardVO);
			boardService.board_file_modify(boardVO);
		}
		
		return board_detail_function(model, boardVO);
	}
	
	@RequestMapping(value = "/board_delete", method = RequestMethod.GET)
	public ModelAndView board_deleteGET(Model model, BoardVO boardVO) throws Exception {
		boardService.board_delete(boardVO);
		
		return board_list(model);
	}
	
	private ModelAndView board_detail_function(Model model, BoardVO boardVO) throws Exception {
		BoardVO board_count = boardService.getCountList(boardVO);
		
		if(board_count == null) { boardService.board_count(boardVO); }
		
		BoardVO board_detail = boardService.board_detail(boardVO);
		BoardVO board_file_detail = boardService.board_file_detail(boardVO);
		
		if(board_file_detail != null) {
			board_detail.setFile_name(board_file_detail.getFile_name());
		}
		
		List<CommentVO> list = boardService.getCommentList(boardVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_detail");
		mav.addObject("board_detail", board_detail);
		mav.addObject("list", list);
		
		return mav;
	}
	
	private ModelAndView is_login(Model model) throws Exception {
		List<BoardVO> list = boardService.getBoardList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board");
		mav.addObject("list", list);
		mav.addObject("msg", "로그인을 해주세요.");
		
		return mav;
	}
	
	private ModelAndView board_list(Model model) throws Exception {
		List<BoardVO> list = boardService.getBoardList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board");
		mav.addObject("list", list);
		
		return mav;
	}
}
