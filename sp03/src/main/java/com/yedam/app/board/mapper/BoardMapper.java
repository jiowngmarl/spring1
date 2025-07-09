package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.service.BoardVO;

public interface BoardMapper {

	// 전체 조회
	public List<BoardVO> selectAll();
	
	// 등록
	public int insertInfo(BoardVO boardVO);
}
