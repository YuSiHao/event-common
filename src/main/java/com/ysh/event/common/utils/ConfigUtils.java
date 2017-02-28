package com.ysh.event.common.utils;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ysh.event.common.domain.TaskConfig;
import com.ysh.event.common.model.EventConfig;


public class ConfigUtils {
	
	protected static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
	
	public static Map<String, List<Map<String, Object>>> appConfigMap;
	public static Map<String, EventConfig> eventConfigMap;
	public static Map<String, TaskConfig> taskConfigMap;
}
