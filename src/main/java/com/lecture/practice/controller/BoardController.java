package com.lecture.practice.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lecture.practice.util.UploadFileUtils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

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
	public String main() throws Exception {
		return "main";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView board(Model model) throws Exception {
		return board_list(model);
	}
	
	@RequestMapping(value = "/board_detail", method = RequestMethod.GET)
	public ModelAndView board_detail(Model model, BoardVO boardVO) throws Exception {
		logger.info("board_detail");
		logger.info(boardVO.toString());
		
		if(boardVO.getId().equals("")) { return is_login(model); }
		
		return board_detail_function(model, boardVO);
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.GET)
	public String board_writeGET() {
		return "board_write";
	}
	
	@RequestMapping(value = "/board_file", method = RequestMethod.POST)
	public String board_filePOST() {	
		return "board_write";
	}
	
	@RequestMapping(value = "/board_file_upload", method = RequestMethod.POST)
	public @ResponseBody String board_file_uploadPOST(MultipartHttpServletRequest multipartRequest, ServletRequest request) throws IOException, Exception {	
		logger.info("upload");
		Iterator<String> itr = multipartRequest.getFileNames();
		String str = new String();
		
		while (itr.hasNext()) {
			MultipartFile mpf = multipartRequest.getFile(itr.next());
	 
	        String originalFilename = mpf.getOriginalFilename();
	        
	        String uploadPath = request.getServletContext().getRealPath("/resources");
	        
	        if(str.equals("")) {
	        	str = UploadFileUtils.uploadFile(uploadPath, originalFilename, mpf.getBytes(), "/image");
	        	str = str.substring(str.indexOf("image/")-1);
	        }else {
	        	String temp = UploadFileUtils.uploadFile(uploadPath, originalFilename, mpf.getBytes(), "/image");
	        	temp = temp.substring(temp.indexOf("image/")-1);
	        	str += "&&";
	        	
	        	str += temp;
	        }
		}
		
		logger.info(str);
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value= "/board_file_delete", method = RequestMethod.POST)
	public JSON board_file_deletePOST(@RequestBody String file_name, ServletRequest request) throws Exception {
		BoardVO boardVO = new BoardVO();
		
		boardVO.setFile_name(file_name);
		
		logger.info(boardVO.toString());
		
		boardService.board_file_delete(boardVO);
		
		UploadFileUtils.deleteFile(file_name, request);
		
		JSONObject json = new JSONObject();
		json.put("message", "삭제 성공");
		return json;
	}
	
	@RequestMapping(value = "/board_write", method = RequestMethod.POST)
	public ModelAndView board_writePOST(Model model, BoardVO boardVO) throws Exception {
		
		
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));

        boardVO.setBoard_id(today + "/" + boardVO.getId());
        
        boardService.board_write(boardVO);
		
		if(!boardVO.getFile_name().equals("")) {
			String[] str_array = boardVO.getFile_name().split("&&");
			String board_id = boardVO.getBoard_id();
			
			for(String file_name : str_array) {
				logger.info(file_name);
				BoardVO board_fileVO = new BoardVO();
				board_fileVO.setFile_name(file_name);
				board_fileVO.setBoard_id(board_id);
				
				boardService.board_file(board_fileVO);
			}
		}
				
		return board_detail_function(model, boardVO);
	}
	
	@RequestMapping(value = "/comment_write", method = RequestMethod.POST)
	public ModelAndView comment_writePOST(Model model, CommentVO commentVO) throws Exception {	
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        
        BoardVO boardVO = new BoardVO();
        boardVO.setBoard_id(commentVO.getBoard_id());
        boardVO.setId(commentVO.getId());

        commentVO.setComment_id(today + "/" + commentVO.getId());
        
        boardService.comment_write(commentVO);
        
        return board_detail_function(model, boardVO);
	}
	
	@RequestMapping(value = "/comment_delete", method = RequestMethod.GET)
	public ModelAndView comment_deleteGET(Model model, CommentVO commentVO) throws Exception {
		boardService.comment_delete(commentVO);
		
		BoardVO boardVO = new BoardVO();
        boardVO.setBoard_id(commentVO.getBoard_id());
        boardVO.setId(commentVO.getId());
		
		return board_detail_function(model, boardVO);
	}
	
	@RequestMapping(value = "/comment_modify", method = RequestMethod.POST)
	public ModelAndView comment_modifyPOST(Model model, CommentVO commentVO) throws Exception {
		logger.info(commentVO.toString());
		
		boardService.comment_modify(commentVO);
		
		BoardVO boardVO = new BoardVO();
        boardVO.setBoard_id(commentVO.getBoard_id());
        boardVO.setId(commentVO.getId());
		
		return board_detail_function(model, boardVO);
	}
	
	@RequestMapping(value = "/board_edit", method = RequestMethod.GET)
	public ModelAndView board_editGET(Model model, BoardVO boardVO) throws Exception {
		BoardVO board_detail = boardService.board_detail(boardVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_write");
		mav.addObject("board_detail", board_detail);
		mav.addObject("file_list", boardService.board_file_detail(boardVO));
		mav.addObject("msg", "edit");
		
		return mav;
	}
	
	@RequestMapping(value = "/board_edit", method = RequestMethod.POST)
	public ModelAndView board_editPOST(Model model, BoardVO boardVO) throws Exception {
		boardService.board_modify(boardVO);
		
		if(!boardVO.getFile_name().equals("")) {
			String[] str_array = boardVO.getFile_name().split("&&");
			String board_id = boardVO.getBoard_id();
			
			for(String file_name : str_array) {
				logger.info(file_name);
				BoardVO board_fileVO = new BoardVO();
				board_fileVO.setFile_name(file_name);
				board_fileVO.setBoard_id(board_id);
				
				boardService.board_file(board_fileVO);
			}
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
		
		List<CommentVO> list = boardService.getCommentList(boardVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_detail");
		mav.addObject("board_detail", board_detail);
		mav.addObject("file_list", boardService.board_file_detail(boardVO));
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
