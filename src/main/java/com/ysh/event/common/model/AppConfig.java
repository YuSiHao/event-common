package com.ysh.event.common.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

@Document(collection = "appconfig")
public class AppConfig extends BaseModel {
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
