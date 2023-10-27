package com.example.demo.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.exception.BaseException;
import com.example.demo.configuration.http.BaseResponse;
import com.example.demo.configuration.http.BaseResponseCode;
import com.example.demo.mvc.domain.Board;
import com.example.demo.mvc.parameter.BoardParameter;
import com.example.demo.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



/**
 * 게시판 controller
 * @author 박지영
 */

@RestController
@RequestMapping("/api/v1/board")
@Api(tags = "게시판 API")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 등록 처리
	 * @param board
	 */
	@PostMapping("/save")
	@ApiOperation(value = "저장 처리", notes = "게시물을 저장합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "title", value = "제목", example = "spring"),
		@ApiImplicitParam(name = "contents", value = "내용", example = "spring")
	})
	public ResponseEntity<String> save(BoardParameter parameter) {
		// 제목 및 내용 필수 체크
		checkRequired(parameter);
		// 게시물 저장
		boardService.save(parameter);
		System.out.println("SUCCESS_SAVE_BOARD");
		return new ResponseEntity<String>("SUCCESS_SAVE_BOARD", setHttpHeaders("save"), HttpStatus.OK);
	}
	
	/** 
	 * 전체 조회 처리
	 * @return
	 */
	@GetMapping("/getList")
	@ApiOperation(value = "전체 조회", notes = "전체 게시물을 조회합니다.")
	public ResponseEntity<List<Board>> getList() {
		List<Board> list = boardService.getList();
		System.out.println("SUCCESS_GET_BOARD_LIST");
		return new ResponseEntity<List<Board>>(list, setHttpHeaders("getList"), HttpStatus.OK);
	}
	
	/**
	 * 조회 처리
	 * @param boardId
	 * @return
	 */
	@GetMapping("/get/{boardId}")
	@ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardId", value = "게시물 번호", example = "1")
	})
	public BaseResponse<Board> get(@PathVariable int boardId) {
		Board board = boardService.get(boardId);
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
		}
		System.out.println("SUCCESS_GET_BOARD"+board.getBoardId());
		return new BaseResponse<Board>(board);
	}
	
	/**
	 * 업데이트 처리
	 * @param board
	 */
	@PutMapping("/update")
	@ApiOperation(value = "수정 처리", notes = "게시물 번호에 해당하는 정보를 수정합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardId", value = "게시물 번호", example = "1"),
		@ApiImplicitParam(name = "title", value = "제목", example = "spring_up"),
		@ApiImplicitParam(name = "contents", value = "내용", example = "spring_up")
	})
	public ResponseEntity<String> update(BoardParameter parameter) {
		int updatedId = boardService.save(parameter);
		System.out.println("SUCCESS_UPDATE_BOARD_ID : " + updatedId);
		return new ResponseEntity<String>("UPDATED_ID : " + updatedId, setHttpHeaders("update"), HttpStatus.OK);
	}
	
	/**
	 * 삭제 처리
	 * @param boardId
	 * @return integer
	 */
	@DeleteMapping("/delete/{boardId}")
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	public ResponseEntity<String> delete(@PathVariable int boardId) {
		Board board = boardService.get(boardId);
		if(board != null) {
			boardService.delete(boardId);
		}
		System.out.println("SUCCESS_DELETE_BOARD");
		return new ResponseEntity<String>("DELETED_ID : " + boardId, setHttpHeaders("delete"), HttpStatus.NO_CONTENT);
	}
	
	public void checkRequired(BoardParameter parameter) {
		// 제목 필수 체크
		if(StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{ "title", "제목" });
		}
		// 내용 필수 체크
		if(StringUtils.isEmpty(parameter.getContents())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{ "contents", "내용" });
		}
	}
	
	public HttpHeaders setHttpHeaders(String status) {
		HttpHeaders headers = new HttpHeaders();
		
		switch(status) {
			case "save" : 
				headers.set("save", "board");
				break;
			case "update" : 
				headers.set("update", "board");
				break;
			case "delete" : 
				headers.set("delete", "board");
				break;
			case "get" : 
				headers.set("get", "board");
				break;
			case "getList" : 
				headers.set("getList", "board");
				break;
		}
		return headers;
	}

}
