package com.ysh.event.common.model;

import java.util.Date;


public class SubError {
	private String serviceHandler;
	private String source;
	private Date generatedTime;
	private String errorInfo;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
		builder.append("serviceHandler=");
		builder.append(serviceHandler);
		builder.append("source=");
		builder.append(source);
		builder.append(", generatedTime=");
		builder.append(generatedTime);
		builder.append(", errorInfo=");
		builder.append(errorInfo);
		builder.append("]");
		return builder.toString();
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
