package com.ysh.event.common.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "apperror")
public class AppError extends BaseModel {
	private String source;
	private String eventId;
	private String serviceHandler;
	private String errorInfo;
	private String status;
	private String notifyStatus;
	private List<SubError> errorTrackList = new ArrayList<SubError>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
		builder.append("source=");
		builder.append(source);
		builder.append(", eventId=");
		builder.append(eventId);
		builder.append(", serviceHandler=");
		builder.append(serviceHandler);
		builder.append(", errorInfo=");
		builder.append(errorInfo);
		builder.append(", status=");
		builder.append(status);
		builder.append(", notifyStatus=");
		builder.append(notifyStatus);
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotifyStatus() {
		return notifyStatus;
	}

	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	public List<SubError> getErrorTrackList() {
		return errorTrackList;
	}

	public void setErrorTrackList(List<SubError> errorTrackList) {
		this.errorTrackList = errorTrackList;
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

}
