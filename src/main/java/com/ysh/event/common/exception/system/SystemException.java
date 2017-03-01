package com.ysh.event.common.exception.system;

import com.ysh.event.common.exception.AbstractGenericException;

public class SystemException extends AbstractGenericException {
	
	private static final long serialVersionUID = 369701165726195643L;

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
