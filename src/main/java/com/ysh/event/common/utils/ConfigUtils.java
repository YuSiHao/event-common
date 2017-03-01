package com.ysh.event.common.utils;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ysh.event.common.domain.TaskConfig;
import com.ysh.event.common.manager.AppConfigManager;
import com.ysh.event.common.model.EventConfig;


public class ConfigUtils {
	
	@Autowired
	private AppConfigManager appConfigManager;
	
	protected static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
	
	public static Map<String, List<Map<String, Object>>> appConfigMap;
	public static Map<String, EventConfig> eventConfigMap;
	public static Map<String, TaskConfig> taskConfigMap;
	
	//execute when servlet start and only execute one time
	@PostConstruct
	public void loadConfig() throws Exception {
		appConfigManager.loadConfigToMemory();
	}
	
	public static EventConfig getEventConfigByServiceHandler(String serviceHandler) throws Exception {
		EventConfig eventConfig = eventConfigMap.get(serviceHandler);
		DataValidationUtils.validateDataEmpty("eventConfig with serviceHandler [" + serviceHandler + "]", eventConfig);
		return eventConfig;
	}
	
}
