package com.example.demo.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.service.BoardService;


/**
 * 게시판 controller
 * @author 박지영
 */

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 등록/수정 처리
	 * @param board
	 */
	@PostMapping("/save")
	public void save(Board board) {
		boardService.save(board);
		System.out.println("SUCCESS_SAVE_BOARD");
	}
	
	/** 
	 * 전체 조회 처리
	 * @return
	 */
	@GetMapping("/getList")
	public List<Board> getList() {
		System.out.println("SUCCESS_GET_BOARD_LIST");
		return boardService.getList();
	}
	
	/**
	 * 조회 처리
	 * @param boardId
	 * @return
	 */
	@GetMapping("/get/{boardId}")
	public Board get(@PathVariable int boardId) {
		System.out.println("SUCCESS_GET_BOARD");
		return boardService.get(boardId);
	}
	
	/**
	 * 업데이트 처리
	 * @param board
	 */
	@PutMapping("/update/{boardId}")
	public void update(@PathVariable int boardId, Board board) {
		boardService.update(boardId, board);
		System.out.println("SUCCESS_UPDATE_BOARD");
	}
	
	/**
	 * 삭제 처리
	 * @param boardId
	 */
	@DeleteMapping("/delete/{boardId}")
	public void delete(@PathVariable int boardId) {
		boardService.delete(boardId);
		System.out.println("SUCCESS_DELETE_BOARD");
	}

}
