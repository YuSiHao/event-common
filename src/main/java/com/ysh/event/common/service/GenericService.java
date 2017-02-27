package com.ysh.event.common.service;

import java.util.Map;

import com.ysh.event.common.model.AppEvent;

public interface GenericService {
	
	Map<String, Object> process(Map<String, Object> input) throws Exception;

	Map<String, Object> process(AppEvent appEvent) throws Exception;
}
