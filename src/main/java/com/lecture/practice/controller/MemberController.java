package com.lecture.practice.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lecture.practice.domain.BoardVO;
import com.lecture.practice.domain.CommentVO;
import com.lecture.practice.domain.MemberVO;
import com.lecture.practice.service.BoardService;
import com.lecture.practice.service.MemberService;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService memberService;
	private BoardService boardService;

	@Inject
	public MemberController(MemberService memberService, BoardService boardService) {
		this.memberService = memberService;
		this.boardService = boardService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPOST(Model model, HttpServletRequest request, MemberVO memberVO) throws Exception {
		HttpSession httpSession = request.getSession();
		
		MemberVO member = memberService.login(memberVO);
		
		if(member != null) {
			httpSession.setAttribute("member", member);
			return board_list(model);
		}else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			return mav;
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(Model model, HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession();
		
		httpSession.removeAttribute("member");
		
		return "main";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET() {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(Model model, MemberVO memberVO) throws Exception {
		memberService.register(memberVO);
		
		return "main";
	}
	
	private ModelAndView board_list(Model model) throws Exception {
		List<BoardVO> list = boardService.getBoardList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board");
		mav.addObject("list", list);
		
		return mav;
	}
}
