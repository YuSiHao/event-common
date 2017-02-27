package com.ysh.event.common.manager;

import java.util.Map;

import com.ysh.event.common.model.AppEvent;


public interface AppEventManager extends GenericManager {
	
	AppEvent generateNewEvent(String serviceHandler, Map<String, Object> input) throws Exception;

	AppEvent startProcessingEvent(String serviceHandler, AppEvent appEvent) throws Exception;

	void validEventInput(String serviceHandler, AppEvent appEvent) throws Exception;

	boolean isDuplicatedEvent(String serviceHandler, AppEvent appEvent) throws Exception;

	boolean setEventEndStatus(String serviceHandler, AppEvent appEvent, boolean isDuplicated, boolean isFailed,
			String description, Map<String, Object> output) throws Exception;

	AppEvent save(AppEvent appEvent);

	void retryEventById(String eventId) throws Exception;

	boolean hasNextJob(String taskName) throws Exception;

	AppEvent getNextExecuteableEvent(String taskName) throws Exception;

	void regenerateEventDynamicProperties(Map<String, String[]> paramMap) throws Exception;

	Map<String, Object> launchTask(String taskName, boolean isForce) throws Exception;

}
