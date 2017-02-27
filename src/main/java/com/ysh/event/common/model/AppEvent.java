package com.ysh.event.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppEvent extends BaseModel {
	private String type;
	private String status;
	private String description;
	// determine which service will handle event
	private String serviceHandler;
	// determine which is next service to handle event, define in config
	private String nextServiceHandler;
	// determine event priority, need config to generate
	private Long priority;
	// determine when need execute event, need config to generate
	private Date executeTime;
	private String uniqueId;
	private Map<String, Object> input = new HashMap<String, Object>();
	private Map<String, Object> output = new HashMap<String, Object>();
	private Map<String, Object> payload = new HashMap<String, Object>();

	// save the event process workflow
	private List<SubEvent> eventTrackList = new ArrayList<SubEvent>();

	private String notifyStatus;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
		builder.append("type=");
		builder.append(type);
		builder.append(", status=");
		builder.append(status);
		builder.append(", description=");
		builder.append(description);
		builder.append(", serviceHandler=");
		builder.append(serviceHandler);
		builder.append(", nextServiceHandler=");
		builder.append(nextServiceHandler);
		builder.append(", executeTime=");
		builder.append(executeTime);
		builder.append(", uniqueId=");
		builder.append(uniqueId);
		builder.append(", input=");
		builder.append(input);
		builder.append(", output=");
		builder.append(output);
		builder.append(", payload=");
		builder.append(payload);
		builder.append(", notifyStatus=");
		builder.append(notifyStatus);
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public Map<String, Object> getInput() {
		return input;
	}

	public void setInput(Map<String, Object> input) {
		this.input = input;
	}

	public Map<String, Object> getOutput() {
		return output;
	}

	public void setOutput(Map<String, Object> output) {
		this.output = output;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}

	public List<SubEvent> getEventTrackList() {
		return eventTrackList;
	}

	public void setEventTrackList(List<SubEvent> eventTrackList) {
		this.eventTrackList = eventTrackList;
	}

	public String getNotifyStatus() {
		return notifyStatus;
	}

	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
}
