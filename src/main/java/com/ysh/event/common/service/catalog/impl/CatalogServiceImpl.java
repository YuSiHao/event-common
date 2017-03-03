package com.ysh.event.common.service.catalog.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ysh.event.common.exception.business.InvalidInputException;
import com.ysh.event.common.service.catalog.CatalogService;
import com.ysh.event.common.service.impl.AbstractGenericServiceImpl;
import com.ysh.event.common.utils.Constants;
import com.ysh.event.common.utils.DataValidationUtils;

@Component
public class CatalogServiceImpl extends AbstractGenericServiceImpl implements CatalogService {

	public String eventListener(Map<String, Object> inputPayload) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start execute method eventListener with inputPayload {}", inputPayload);
		}
		String result = "FAILURE";
		try {
			DataValidationUtils.validateDataEmpty("inputPayload", inputPayload);
			Map<String, Object> output = this.process(inputPayload);
			if (logger.isDebugEnabled()) {
				logger.debug("The output is {}", output);
			}
			result = (String) output.get(Constants.OUTPUT_RESULT);
		} catch (InvalidInputException e) {
			// the error has already been printed
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End exectuet method eventListener with result {}", result);
		}
		return result;

	}

	@Override
	protected Map<String, Object> doProcess(Map<String, Object> input, Map<String, Object> payload) throws Exception {
		// generate payload for next event if required
		// TODO do your business here

		// generate output
		Map<String, Object> output = new HashMap<String, Object>();

		output.put("OUTPUT_RESULT", "Finished CatalogServiceImpl!");
		return output;
	}

}
