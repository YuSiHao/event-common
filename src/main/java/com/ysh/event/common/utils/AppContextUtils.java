package com.ysh.event.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.ysh.event.common.exception.system.SystemException;
import com.ysh.event.common.model.AppEvent;
import com.ysh.event.common.service.GenericService;

public class AppContextUtils {
	protected static Logger logger = LoggerFactory.getLogger(AppContextUtils.class);

	@Autowired
	private List<GenericService> allServiceList;

	private static Map<String, GenericService> allServiceMap;

	@PostConstruct
	public void loadAllService() {
		if (logger.isDebugEnabled()) {
			logger.debug("The allServiceList is {}", allServiceList);
		}
		if (CollectionUtils.isEmpty(allServiceMap)) {
			if (!CollectionUtils.isEmpty(allServiceList)) {
				allServiceMap = new HashMap<String, GenericService>(allServiceList.size());
				for (GenericService service : allServiceList) {
					allServiceMap.put(service.getClass().getSimpleName(), service);
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("The allServiceMap is {}", allServiceMap);
		}
	}

	public static GenericService getServiceByName(String name) throws SystemException {
		if (!allServiceMap.containsKey(name)) {
			throw new SystemException("Can't find serviceHandler by name [" + name + "]");
		}
		GenericService service = allServiceMap.get(name);
		return service;
	}

	public static Map<String, Object> processEventByServiceHandler(String serviceHandler, AppEvent appEvent)
			throws Exception {
		GenericService nextService = getServiceByName(serviceHandler);
		Map<String, Object> output = nextService.process(appEvent);
		return output;
	}

	public static Map<String, Object> processEventByServiceHandler(String serviceHandler, Map<String, Object> input)
			throws Exception {
		GenericService nextService = getServiceByName(serviceHandler);
		Map<String, Object> output = nextService.process(input);
		return output;
	}

}
