package com.ysh.event.common.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TaskConfig {
	private String taskName;

	// 2 types: PENDING_EVENT_PICKUP, NEW_EVENT_GENERATE
	private String taskType;

	// PERIOD, CRON
	// this can't be changed during running
	private String triggerType;
	// only for PERIOD trigger, this can be changed during running  
	private Long triggerPeriod;
	// only for CRON trigger, this can be changed during running
	private String cronExpression;
	private boolean isEagerEnabled = true;

	// how many thread used to execute task
	private Integer concurrentThreadNo = 1;

	// determine which PENDING serviceHandler will be picked up
	private String serviceHandler;
	// determine which serviceHandler will be responsible to process event
	private String nextServiceHandler;

	// pre defined input
	private Map<String, Object> preDefinedInput;

	// this won't stop scheduler, only skip the business logic
	private boolean isEnabled = false;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
		builder.append("taskName=");
		builder.append(taskName);
		builder.append("taskType=");
		builder.append(taskType);
		builder.append(", triggerType=");
		builder.append(triggerType);
		builder.append(", triggerPeriod=");
		builder.append(triggerPeriod);
		builder.append(", cronExpression=");
		builder.append(cronExpression);
		builder.append(", concurrentThreadNo=");
		builder.append(concurrentThreadNo);
		builder.append(", serviceHandler=");
		builder.append(serviceHandler);
		builder.append(", nextServiceHandler=");
		builder.append(nextServiceHandler);
		builder.append(", preDefinedInput=");
		builder.append(preDefinedInput);
		builder.append(", isEnabled=");
		builder.append(isEnabled);
		builder.append(", isEagerEnabled=");
		builder.append(isEagerEnabled);
		builder.append("]");
		return builder.toString();
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public Long getTriggerPeriod() {
		return triggerPeriod;
	}

	public void setTriggerPeriod(Long triggerPeriod) {
		this.triggerPeriod = triggerPeriod;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public boolean isEagerEnabled() {
		return isEagerEnabled;
	}

	public void setEagerEnabled(boolean isEagerEnabled) {
		this.isEagerEnabled = isEagerEnabled;
	}

	public Integer getConcurrentThreadNo() {
		return concurrentThreadNo;
	}

	public void setConcurrentThreadNo(Integer concurrentThreadNo) {
		this.concurrentThreadNo = concurrentThreadNo;
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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Map<String, Object> getPreDefinedInput() {
		return preDefinedInput;
	}

	public void setPreDefinedInput(Map<String, Object> preDefinedInput) {
		this.preDefinedInput = preDefinedInput;
	}

}
