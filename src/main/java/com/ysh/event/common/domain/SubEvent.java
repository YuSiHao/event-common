package com.ysh.event.common.domain;

import java.io.Serializable;
import java.util.Date;

public class SubEvent implements Serializable {

	private static final long serialVersionUID = -728911735634977070L;
	private String id;
	private String eventTrackId;
	private String serviceHandler;
	private Date executedTime;
	private String status;
	private String description;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
		builder.append("id=");
		builder.append(id);
		builder.append("eventTrackId=");
		builder.append(eventTrackId);
		builder.append("serviceHandler=");
		builder.append(serviceHandler);
		builder.append(", executedTime=");
		builder.append(executedTime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventTrackId() {
		return eventTrackId;
	}

	public void setEventTrackId(String eventTrackId) {
		this.eventTrackId = eventTrackId;
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

	public Date getExecutedTime() {
		return executedTime;
	}

	public void setExecutedTime(Date executedTime) {
		this.executedTime = executedTime;
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
}
