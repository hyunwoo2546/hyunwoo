package com.hyunwoo.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyunwoo.service.BoardService;
import com.hyunwoo.vo.BoardVO;
import com.hyunwoo.vo.Criteria;
import com.hyunwoo.vo.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	//*** 게시판 글 작성 화면 ***
	@RequestMapping(value="/board/writeView", method={RequestMethod.GET, RequestMethod.POST})
	public void writeView() throws Exception {
		logger.info("writeView");
	}
	
	//*** 게시판 글 작성 ***
	@RequestMapping(value="/board/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write (BoardVO boardVO) throws Exception {
		logger.info("write");
		
		service.write(boardVO);
		
		return "redirect:/board/list";
	}
	
	//*** 게시판 목록 조회 ***
	@RequestMapping(value="/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model,Criteria cri) throws Exception {
		logger.info("list");
		
		model.addAttribute("list",service.list(cri));	// service.list()를 "list"라는 이름으로 담는 것.
		
		PageMaker pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(service.listCount());
		
		model.addAttribute("pageMaker",pagemaker);
		
		return "board/list";		// jsp 페이지 리턴
	}
	
	//*** 게시물 조회 ***
		@RequestMapping(value="/readView", method = {RequestMethod.GET,RequestMethod.POST})
		public String read(BoardVO boardVO,Model model) throws Exception {
			logger.info("read");
			
			model.addAttribute("read",service.read(boardVO.getBno()));	// service.read()를 "read"라는 이름으로 담는 것.
			
			return "board/readView";		// jsp 페이지 리턴
		}
	
	/*** 게시판 수정 VIEW ***/
	@RequestMapping(value="/updateView" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String updateView(BoardVO boardVO, Model model) throws Exception {
		logger.info("updateView");
		
		model.addAttribute("update", service.read(boardVO.getBno()));
		
		return "board/updateView";
		
	}
	
	/*** 게시글 수정 ***/
	@RequestMapping(value="/update", method = {RequestMethod.GET,RequestMethod.POST})
	public String update(BoardVO boardVO) throws Exception {
		logger.info("update");
		
		service.update(boardVO);
		
		return "redirect:/board/list";
	}
	
	/*** 게시글 삭제 ***/
	@RequestMapping(value="/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(BoardVO boardVO) throws Exception {
		logger.info("delete");
		
		service.delete(boardVO.getBno());
		
		return "redirect:/board/list";
	}
	
}
