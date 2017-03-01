package com.ysh.event.common.exception.business;


public class InvalidInputException extends BusinessException {
	private static final long serialVersionUID = 5806793242452371148L;

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(String paramName, String paramValue, String invalidReason) {
		super("The value [" + paramValue + "] of param [" + paramName + "] is not valid because [" + invalidReason
				+ "]");
	}

}
