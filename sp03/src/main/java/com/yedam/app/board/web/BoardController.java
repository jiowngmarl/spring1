package com.yedam.app.board.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller // (URI+METHOD) + Service + Response(View or Data)
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
		
		//전체조회 : GET - boardList
		@GetMapping("boardList") // 1) URI+METHOD
		public String boardList(Model model) {
		// 2) Service
			List<BoardVO> list = boardService.findAll();
			// 2-1) View에 전달할 데이터 담기 : Model
			model.addAttribute("boards", list);
			return "board/list"; // 3) Response(View)
			// classpath:/templates/board/list.html
		}
		
		// 등록 - 페이지
		@GetMapping("boardInsert")
		public String boardInsertForm() {
			return "/board/insert";
			//classpath:/templates/board/insert.html
		}
		
		// 등록 - 처리
		@PostMapping("boardInsert")
		public String boardINsertProcess(BoardVO boardVO) {
			boardService.createInfo(boardVO);
			return "redirect:boardList";
		}
}
