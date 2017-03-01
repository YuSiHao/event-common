package com.ysh.event.common.exception.business;



public class InvalidConfigException extends BusinessException {
	private static final long serialVersionUID = 5806793242452371148L;

	public InvalidConfigException(String message) {
		super(message);
	}

	public InvalidConfigException(String configName, String configValue, String invalidReason) {
		super("The value [" + configValue + "] of config [" + configName + "] is not valid because [" + invalidReason
				+ "]");
	}

}
