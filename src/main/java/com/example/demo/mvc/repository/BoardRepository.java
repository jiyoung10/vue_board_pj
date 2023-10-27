package com.example.demo.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.parameter.BoardParameter;

/**
 * 게시판 repository
 * @author 박지영
 */

@Repository
public interface BoardRepository {
	
	List<Board> getList();
	
	Board get(int boardId);
	
	void save(BoardParameter parameter);
	
	void update(BoardParameter parameter);
	
	void delete(int boardId);

}
