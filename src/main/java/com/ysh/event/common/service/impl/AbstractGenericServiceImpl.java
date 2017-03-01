package com.ysh.event.common.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ysh.event.common.manager.AppConfigManager;
import com.ysh.event.common.manager.AppErrorManager;
import com.ysh.event.common.manager.AppEventManager;
import com.ysh.event.common.model.AppEvent;
import com.ysh.event.common.service.GenericService;
import com.ysh.event.common.utils.AppContextUtils;
import com.ysh.event.common.utils.Constants;

public abstract class AbstractGenericServiceImpl implements GenericService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected AppEventManager appEventManager;

	@Autowired
	protected AppErrorManager appErrorManager;

	@Autowired
	protected AppConfigManager appConfigManager;

	@Override
	public Map<String, Object> process(Map<String, Object> input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> process(AppEvent appEvent) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected Map<String, Object> process(AppEvent appEvent, Map<String, Object> input, boolean isCreateNewEvent)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method process with appEvent [" + appEvent + "], input [" + input
					+ "], isCreateNewEvent [" + isCreateNewEvent + "]");
		}
		try {
			Map<String, Object> payload = null;
			Map<String, Object> output = null;
			boolean isDuplicated = false;
			boolean isFailed = false;

			String serviceHandler = this.getClass().getSimpleName();

			if (isCreateNewEvent) {
				// create new event
				appEvent = appEventManager.generateNewEvent(serviceHandler, input);
			} else {
				// set PROCESSING status to event
				appEvent = appEventManager.startProcessingEvent(serviceHandler, appEvent);
			}

			// get input and payload
			input = appEvent.getInput();
			payload = appEvent.getPayload();

			boolean isForce = false;
			if (input.containsKey(Constants.INPUT_IS_FORCE)) {// "INPUT_IS_FORCE"
				isForce = (Boolean) input.get(Constants.INPUT_IS_FORCE);
				if (logger.isDebugEnabled()) {
					logger.debug("The isForce is {}", isForce);
				}
			}

			try {
				// validate input
				appEventManager.validEventInput(serviceHandler, appEvent);

				// check if need prevent duplicated
				isDuplicated = appEventManager.isDuplicatedEvent(serviceHandler, appEvent);
				if (logger.isDebugEnabled()) {
					logger.debug("The isDuplicated is {}", isDuplicated);
				}
				if (isForce) {
					isDuplicated = false;
				}

				if (!isDuplicated) {
					output = doProcess(input, payload);
					if (logger.isDebugEnabled()) {
						logger.debug("The output is {}", output);
					}
				}

			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("Got exception in method process after doProcess method.", e);
				}
				// set FAILURE status to event
				isFailed = true;
				appEventManager.setEventEndStatus(serviceHandler, appEvent, isDuplicated, isFailed, e.getMessage(),
						output);

				throw e;// break logic
			}

			// set PROCESSED status to event
			boolean hasNextEvent = appEventManager.setEventEndStatus(serviceHandler, appEvent, isDuplicated, isFailed,
					null, output);

			if (hasNextEvent) {
				// TODO consider if need handle a list of output so one event
				// can have multiple output
				// forward to next event handler
				output = AppContextUtils.processEventByServiceHandler(appEvent.getNextServiceHandler(), appEvent);
			}

			if (logger.isDebugEnabled()) {
				logger.debug("End exectuet method process with output [" + output + "]");
			}
			return output;
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Got exception in method process.", e);
			}
			// error handler, insert error table
			appErrorManager.generateError(appEvent, e);

			return null;
		}
	}

	protected abstract Map<String, Object> doProcess(Map<String, Object> input, Map<String, Object> payload)
			throws Exception;

}
