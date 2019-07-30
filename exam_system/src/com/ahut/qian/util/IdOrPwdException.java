package com.ahut.qian.util;

/**
 * ID 或　PWD　异常处理类(自定义)
 */
public class IdOrPwdException extends Exception{
	// 异常消息
	private String message;

	public IdOrPwdException() {

	}

	public IdOrPwdException(String mess) {
		super(mess);	// 调用Exception 类的有参构造器
		this.message = mess;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
