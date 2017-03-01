package com.ysh.event.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ysh.event.common.exception.business.InvalidInputException;


public class DataValidationUtils {
	protected static Logger logger = LoggerFactory.getLogger(DataValidationUtils.class);

	public static void validateDataEmpty(String dataName, Object data) throws Exception {
		if (CommonUtils.isEmpty(data)) {
			throw new InvalidInputException(dataName, null, dataName + " is required.");
		}
	}
}
