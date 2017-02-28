package com.ysh.event.common.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class EventConfig {
	
	private String type;
	private String serviceHandler;
	// could be contants EVENT_PENDING, EVENT_FINISHED, actual serviceImpl
	// name
	private String nextServiceHandler;
	// freemarker template to generate priority 
	private String priorityGenerator;
	// freemarker template to generate executeTime
	private String executeTimeGenerator;
	// freemarker template to do input validation
	private String inputValidator;
	// freemarker template to do generate unique id which is used to prevent
	// duplicated
	private String uniqueIdGenerator;

	// pre defined input
	private Map<String, Object> preDefinedInput;

	private boolean isSaveToDb = false;
	private boolean isNeedPreventDuplicated = false;
	private boolean canRetry = false;
	private boolean isNeedNotify = false;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
		builder.append("type=");
		builder.append(type);
		builder.append(", serviceHandler=");
		builder.append(serviceHandler);
		builder.append(", nextServiceHandler=");
		builder.append(nextServiceHandler);
		builder.append(", priorityGenerator=");
		builder.append(priorityGenerator);
		builder.append(", executeTimeGenerator=");
		builder.append(executeTimeGenerator);
		builder.append(", inputValidator=");
		builder.append(inputValidator);
		builder.append(", uniqueIdGenerator=");
		builder.append(uniqueIdGenerator);
		builder.append(", preDefinedInput=");
		builder.append(preDefinedInput);
		builder.append(", isSaveToDb=");
		builder.append(isSaveToDb);
		builder.append(", isNeedPreventDuplicated=");
		builder.append(isNeedPreventDuplicated);
		builder.append(", canRetry=");
		builder.append(canRetry);
		builder.append(", isNeedNotify=");
		builder.append(isNeedNotify);
		builder.append("]");
		return builder.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

	public String getNextServiceHandler() {
		return nextServiceHandler;
	}

	public void setNextServiceHandler(String nextServiceHandler) {
		this.nextServiceHandler = nextServiceHandler;
	}

	public String getPriorityGenerator() {
		return priorityGenerator;
	}

	public void setPriorityGenerator(String priorityGenerator) {
		this.priorityGenerator = priorityGenerator;
	}

	public String getExecuteTimeGenerator() {
		return executeTimeGenerator;
	}

	public void setExecuteTimeGenerator(String executeTimeGenerator) {
		this.executeTimeGenerator = executeTimeGenerator;
	}

	public boolean isSaveToDb() {
		return isSaveToDb;
	}

	public void setSaveToDb(boolean isSaveToDb) {
		this.isSaveToDb = isSaveToDb;
	}

	public boolean isCanRetry() {
		return canRetry;
	}

	public void setCanRetry(boolean canRetry) {
		this.canRetry = canRetry;
	}

	public boolean isNeedNotify() {
		return isNeedNotify;
	}

	public void setNeedNotify(boolean isNeedNotify) {
		this.isNeedNotify = isNeedNotify;
	}

	public String getInputValidator() {
		return inputValidator;
	}

	public void setInputValidator(String inputValidator) {
		this.inputValidator = inputValidator;
	}

	public String getUniqueIdGenerator() {
		return uniqueIdGenerator;
	}

	public void setUniqueIdGenerator(String uniqueIdGenerator) {
		this.uniqueIdGenerator = uniqueIdGenerator;
	}

	public boolean isNeedPreventDuplicated() {
		return isNeedPreventDuplicated;
	}

	public void setNeedPreventDuplicated(boolean isNeedPreventDuplicated) {
		this.isNeedPreventDuplicated = isNeedPreventDuplicated;
	}

	public Map<String, Object> getPreDefinedInput() {
		return preDefinedInput;
	}

	public void setPreDefinedInput(Map<String, Object> preDefinedInput) {
		this.preDefinedInput = preDefinedInput;
	}
}
