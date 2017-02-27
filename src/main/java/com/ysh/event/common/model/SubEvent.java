package com.ysh.event.common.model;

import java.util.Date;

public class SubEvent {
	private String serviceHandler;
	private Date executedTime;
	private String status;
	private String description;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
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
