package com.example.demo.configuration.http;

import lombok.Data;

/** 
 * 공통으로 사용할 응답 클래스
 * @author 박지영
 * @param <T>
 */
@Data
public class BaseResponse<T> {
	private BaseResponseCode code;
	private String message;
	private T data;
	
	public BaseResponse(T data) {
		this.code = BaseResponseCode.SUCCESS;
		this.data = data;
	}
	
	// 예외처리 case
	public BaseResponse(BaseResponseCode code, String message) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.message = message;
	}

	// getter&setter
	public BaseResponseCode getCode() {
		return code;
	}

	public void setCode(BaseResponseCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
