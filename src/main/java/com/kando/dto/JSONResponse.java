package com.kando.dto;

import lombok.Data;

@Data
public class JSONResponse {
	private Boolean success;
	private String code;
	private String message;
	private Object data;
	
	public static JSONResponse ok() {
		return new JSONResponse(true,"200",null,null);
	}
	public static JSONResponse ok(Object data) {
		return new JSONResponse(true,"200",null,data);
	}
	public static JSONResponse fail(String code, String message) {
		return new JSONResponse(false,code,message,null);
	}
	public static JSONResponse fail(String message) {
		return new JSONResponse(false,"401",message,null);
	}


	public JSONResponse(Boolean success,String code, String message, Object data) {
		this.success=success;
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
