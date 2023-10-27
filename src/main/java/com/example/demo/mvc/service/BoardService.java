package com.example.demo.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.parameter.BoardParameter;
import com.example.demo.mvc.repository.BoardRepository;

/**
 * 게시판 서비스
 * @author 박지영
 */

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	/**
	 * 등록/수정 처리
	 * @parameter board
	 */
	public int save(BoardParameter parameter) {
		Board getBoard = boardRepository.get(parameter.getBoardId());
		if(getBoard == null){
			boardRepository.save(parameter);
		}else {
			boardRepository.update(parameter);
		}
		return parameter.getBoardId();
	}
	
	/**
	 * 전체 조회 처리
	 * @return
	 */
	public List<Board> getList() {
		return boardRepository.getList();
	}
	
	/**
	 * 조회 처리
	 * @param boardId
	 * @return
	 */
	public Board get(int boardId) {
		return boardRepository.get(boardId);
	}
	
	/**
	 * 삭제 처리
	 * @param boardId
	 */
	public void delete(int boardId) {
		boardRepository.delete(boardId);
	}

}
