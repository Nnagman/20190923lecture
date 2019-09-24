package com.lecture.practice.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lecture.practice.domain.BoardVO;
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
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String board() {
		return "board";
	}
	
	@RequestMapping(value = "/board_detail", method = RequestMethod.GET)
	public String board_detail() {
		return "board_detail";
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.GET)
	public String board_writeGET() {
		return "board_write";
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.POST)
	public String board_writePOST(BoardVO boardVO) throws Exception {
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));

        boardVO.setBoard_id(today);
        
        logger.info(boardVO.toString());
		
		if(boardVO.getFile_name() == null) {
			
			logger.info("NO FILE");
			boardService.board_write(boardVO);
			
			return "board_write";
		}else {
			
			logger.info("FILE EXIST");
			boardService.board_write(boardVO);
			boardService.board_file(boardVO);
			
			return "board_write";
		}
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
