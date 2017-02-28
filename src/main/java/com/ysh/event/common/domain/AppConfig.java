package com.ysh.event.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppConfig extends BaseModel implements Serializable {

	private static final long serialVersionUID = -728911735634977070L;
	private String key;
	private List<Map<String, Object>> value = new ArrayList<Map<String, Object>>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [");
		builder.append("key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Map<String, Object>> getValue() {
		return value;
	}

	public void setValue(List<Map<String, Object>> value) {
		this.value = value;
	}

}
