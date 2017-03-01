package com.ysh.event.common.exception;


public abstract class AbstractGenericException extends Exception {
	private static final long serialVersionUID = -5592716928202468849L;
	
	private boolean isSavedToDb = false;

	public AbstractGenericException(String message) {
		super(message);
	}

	public AbstractGenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public boolean isSavedToDb() {
		return isSavedToDb;
	}

	public void setSavedToDb(boolean isSavedToDb) {
		this.isSavedToDb = isSavedToDb;
	}

}
