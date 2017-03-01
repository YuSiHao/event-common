package com.ysh.event.common.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;

import com.ysh.event.common.dao.AppEventDao;
import com.ysh.event.common.exception.business.InvalidInputException;
import com.ysh.event.common.exception.system.SystemException;
import com.ysh.event.common.manager.AppEventManager;
import com.ysh.event.common.model.AppEvent;
import com.ysh.event.common.model.EventConfig;
import com.ysh.event.common.model.SubEvent;
import com.ysh.event.common.utils.ConfigUtils;
import com.ysh.event.common.utils.Constants;
import com.ysh.event.common.utils.FreemarkerUtils;

public class AppEventManagerImpl extends AbstractGenericManagerImpl implements AppEventManager {

	@Autowired
	private AppEventDao appEventDao;

	@Override
	public AppEvent generateNewEvent(String serviceHandler, Map<String, Object> input) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method generateNewEvent with serviceHandler ", serviceHandler);
			logger.debug("Input is ", input);
		}
		EventConfig eventConfig = ConfigUtils.getEventConfigByServiceHandler(serviceHandler);
		AppEvent appEvent = new AppEvent();
		appEvent.setInput(input);
		appEvent.setOutput(null);
		appEvent.setNextServiceHandler(eventConfig.getNextServiceHandler());
		appEvent.setNotifyStatus(Constants.EVENT_STATUS_NEW);
		appEvent.setServiceHandler(serviceHandler);
		appEvent.setStatus(Constants.EVENT_STATUS_PROCESSING);
		appEvent.setType(eventConfig.getType());
		generateDynamicEventProperties(appEvent, eventConfig);
		boolean isSaveToDb = eventConfig.isSaveToDb();

		if (isSaveToDb) {
			appEvent = appEventDao.save(appEvent);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("End exectuet method generateNewEvent with appEvent [" + appEvent + "]");
		}
		return appEvent;
	}

	private void generateDynamicEventProperties(AppEvent appEvent, EventConfig eventConfig) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method generateDynamicEeventProperties with appEvent [" + appEvent
					+ "], eventConfig [" + eventConfig + "]");
		}
		Map<String, Object> input = appEvent.getInput();

		// combine input with pre defined input
		Map<String, Object> preDefinedInput = eventConfig.getPreDefinedInput();
		if (!CollectionUtils.isEmpty(preDefinedInput)) {
			if (CollectionUtils.isEmpty(input)) {
				input = preDefinedInput;
			} else {
				input.putAll(preDefinedInput);
			}
			appEvent.setInput(input);
		}

		Date executeTime = null;
		String executeTimeGenerator = null;
		try {
			executeTimeGenerator = eventConfig.getExecuteTimeGenerator();
			String executeTimeString = FreemarkerUtils.processTemplate(input, executeTimeGenerator);
			if (StringUtils.isNotEmpty(executeTimeString)) {
				// expect a long value to generate date
				executeTime = new Date(new Long(executeTimeString));
			}
		} catch (Exception e) {
			throw new SystemException("Can't generate executeTime with input [" + input + "], executeTimeGenerator ["
					+ executeTimeGenerator + "]", e);
		}
		appEvent.setExecuteTime(executeTime);

		Long priotiry = null;
		String priorityGenerator = null;
		try {
			priorityGenerator = eventConfig.getPriorityGenerator();
			String priotiryString = FreemarkerUtils.processTemplate(input, priorityGenerator);
			if (StringUtils.isNotEmpty(priotiryString)) {
				priotiry = new Long(priotiryString);
			}
		} catch (Exception e) {
			throw new SystemException(
					"Can't generate priotiry with input [" + input + "], priorityGenerator [" + priorityGenerator + "]", // priority优先权
					e);
		}
		appEvent.setPriority(priotiry);

		boolean isNeedPreventDuplicated = eventConfig.isNeedPreventDuplicated();
		if (isNeedPreventDuplicated) {
			String uniqueId = null;
			String uniqueIdGenerator = null;
			try {
				uniqueIdGenerator = eventConfig.getUniqueIdGenerator();
				uniqueId = FreemarkerUtils.processTemplate(input, uniqueIdGenerator);
			} catch (Exception e) {
				throw new SystemException("Can't generate uniqueId with input [" + input + "], uniqueIdGenerator ["
						+ uniqueIdGenerator + "]", e);
			}
			appEvent.setUniqueId(uniqueId);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("End exectuet method generateDynamicEeventProperties with appEvent {}", appEvent);
		}
	}

	@Override
	public AppEvent startProcessingEvent(String serviceHandler, AppEvent appEvent) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method startProcessingEvent with serviceHandler [" + serviceHandler
					+ "], appEvent [" + appEvent + "]");
		}

		EventConfig eventConfig = ConfigUtils.getEventConfigByServiceHandler(serviceHandler);

		boolean isSaveToDb = eventConfig.isSaveToDb();

		if (StringUtils.equals(serviceHandler, appEvent.getServiceHandler())) {
			// consider as retry event, just set status
			appEvent.setStatus(Constants.EVENT_STATUS_PROCESSING);

			if (isSaveToDb) {
				Query query = Query.query(Criteria.where("id").is(appEvent.getId()));
				Update update = new Update();
				update.set("status", appEvent.getStatus());
				appEvent = appEventDao.findAndModify(query, update, AppEvent.class);
			}

		} else if (StringUtils.equals(serviceHandler, appEvent.getNextServiceHandler())
				|| Constants.EVENT_END_FLAG_PENDING.equals(appEvent.getNextServiceHandler())) {
			// the event is from previous service
			appEvent.setInput(appEvent.getOutput());
			appEvent.setOutput(null);
			appEvent.setNextServiceHandler(eventConfig.getNextServiceHandler());
			appEvent.setNotifyStatus(Constants.EVENT_STATUS_NEW);
			appEvent.setServiceHandler(serviceHandler);
			appEvent.setStatus(Constants.EVENT_STATUS_PROCESSING);
			appEvent.setType(eventConfig.getType());

			// set up event properties by using freemarker
			generateDynamicEventProperties(appEvent, eventConfig);

			if (isSaveToDb) {
				Query query = Query.query(Criteria.where("id").is(appEvent.getId()));
				Update update = new Update();
				update.set("input", appEvent.getInput());
				update.set("output", null);
				update.set("nextServiceHandler", appEvent.getNextServiceHandler());
				update.set("notifyStatus", appEvent.getNotifyStatus());
				update.set("serviceHandler", appEvent.getServiceHandler());
				update.set("status", appEvent.getStatus());
				update.set("type", appEvent.getType());
				update.set("executeTime", appEvent.getExecuteTime());
				update.set("priority", appEvent.getPriority());
				update.set("uniqueId", appEvent.getUniqueId());
				appEvent = appEventDao.findAndModify(query, update, AppEvent.class);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End exectuet method startProcessingEvent with appEvent [" + appEvent + "]");
		}
		return appEvent;
	}

	@Override
	public void validEventInput(String serviceHandler, AppEvent appEvent) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method validEventInput with serviceHandler [" + serviceHandler
					+ "], appEvent [" + appEvent + "]");
		}
		EventConfig eventConfig = ConfigUtils.getEventConfigByServiceHandler(serviceHandler);
		Map<String, Object> input = appEvent.getInput();
		String inputValidator = null;
		String validResult = null;
		try {
			inputValidator = eventConfig.getInputValidator();
			if (StringUtils.isNotEmpty(inputValidator)) {
				validResult = FreemarkerUtils.processTemplate(input, inputValidator);
				if (logger.isDebugEnabled()) {
					logger.debug("The validResult is [" + validResult + "]");
				}
				if (validResult.equals("failed")) {
					if (logger.isDebugEnabled()) {
						logger.debug("input is not valid");
					}
					throw new InvalidInputException("input is not valid");
				}

			}
		} catch (InvalidInputException e) {
			throw e;
		} catch (Exception e) {
			throw new SystemException(
					"Can't perform valid with input [" + input + "], inputValidator [" + inputValidator + "]", e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("End exectuet method validEventInput with validResult [" + validResult + "], appEvent ["
					+ appEvent + "]");
		}
	}

	@Override
	public boolean isDuplicatedEvent(String serviceHandler, AppEvent appEvent) throws Exception {
		boolean isDuplicated = false;
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method isDuplicatedEvent with serviceHandler [" + serviceHandler
					+ "], appEvent [" + appEvent + "]");
		}

		EventConfig eventConfig = ConfigUtils.getEventConfigByServiceHandler(serviceHandler);

		if (eventConfig.isNeedPreventDuplicated()) {
			String eventId = appEvent.getId();
			String uniqueId = appEvent.getUniqueId();

			Query query = Query.query(Criteria.where("id").ne(eventId));
			query.addCriteria(Criteria.where("uniqueId").is(uniqueId));
			// TODO consider if need add status condition
			query.addCriteria(
					Criteria.where("status").in(Constants.EVENT_STATUS_PENDING, Constants.EVENT_STATUS_PROCESSING));

			isDuplicated = appEventDao.exists(query, AppEvent.class);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("End exectuet method isDuplicatedEvent with isDuplicated [" + isDuplicated + "]");
		}
		return isDuplicated;
	}

	@Override
	public boolean setEventEndStatus(String serviceHandler, AppEvent appEvent, boolean isDuplicated, boolean isFailed,
			String description, Map<String, Object> output) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"Start exectuet method setEventEndStatus with serviceHandler [" + serviceHandler + "], appEvent ["
							+ appEvent + "], isDuplicated [" + isDuplicated + "], isFailed [" + isFailed + "]");
		}

		boolean isContinue = false;

		appEvent.setDescription(description);
		appEvent.setOutput(output);
		if (isDuplicated) {
			appEvent.setStatus(Constants.EVENT_STATUS_DUPLICATED);
		} else if (isFailed) {
			appEvent.setStatus(Constants.EVENT_STATUS_FALIURE);
		} else {// finished successfully
			if (Constants.EVENT_END_FLAG_PENDING.equals(appEvent.getNextServiceHandler())) {
				appEvent.setStatus(Constants.EVENT_STATUS_PENDING);
			} else if (Constants.EVENT_END_FLAG_FINISHED.equals(appEvent.getNextServiceHandler())) {
				// last event
				appEvent.setStatus(Constants.EVENT_STATUS_PROCESSED);
			} else {
				// has next event
				appEvent.setStatus(Constants.EVENT_STATUS_PROCESSED);
				isContinue = true;
			}
		}

		// set up event track list, add current event status to list
		List<SubEvent> eventTrackList = appEvent.getEventTrackList();
		SubEvent subEvent = new SubEvent();
		subEvent.setDescription(appEvent.getDescription());
		subEvent.setExecutedTime(new Date());
		subEvent.setServiceHandler(serviceHandler);
		subEvent.setStatus(appEvent.getStatus());
		eventTrackList.add(subEvent);
		appEvent.setEventTrackList(eventTrackList);

		EventConfig eventConfig = ConfigUtils.getEventConfigByServiceHandler(serviceHandler);
		boolean isSaveToDb = eventConfig.isSaveToDb();
		if (isSaveToDb) {
			Query query = Query.query(Criteria.where("id").is(appEvent.getId()));
			Update update = new Update();
			update.set("status", appEvent.getStatus());
			update.set("description", appEvent.getDescription());
			update.set("output", appEvent.getOutput());
			update.set("eventTrackList", appEvent.getEventTrackList());
			appEvent = appEventDao.findAndModify(query, update, AppEvent.class);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("End exectuet method setEventEndStatus with isContinue [" + isContinue + "]");
		}
		return isContinue;
	}

	@Override
	public AppEvent save(AppEvent appEvent) {
		return appEventDao.save(appEvent);
	}

	@Override
	public boolean hasNextJob(String taskName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AppEvent getNextExecuteableEvent(String taskName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regenerateEventDynamicProperties(Map<String, String[]> paramMap) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> launchTask(String taskName, boolean isForce) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
