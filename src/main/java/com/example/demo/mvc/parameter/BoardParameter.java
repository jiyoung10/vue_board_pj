package com.example.demo.mvc.parameter;

import lombok.Data;


@Data
public class BoardParameter {
	
	private int boardId;
	private String title;
	private String contents;
	
	//getter&setter
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
