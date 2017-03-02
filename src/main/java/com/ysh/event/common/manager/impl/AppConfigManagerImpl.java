package com.ysh.event.common.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ysh.event.common.domain.TaskConfig;
import com.ysh.event.common.manager.AppConfigManager;
import com.ysh.event.common.model.AppConfig;
import com.ysh.event.common.model.EventConfig;
import com.ysh.event.common.utils.CommonUtils;
import com.ysh.event.common.utils.ConfigUtils;
import com.ysh.event.common.utils.Constants;
import com.ysh.event.common.utils.DataValidationUtils;

@Component
public class AppConfigManagerImpl extends AbstractGenericManagerImpl implements AppConfigManager {

	@Override
	public void loadConfigToMemory() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method loadAppConfigToMemory");
		}

		// load all config
		if (CollectionUtils.isEmpty(ConfigUtils.appConfigMap)) {
			List<AppConfig> appConfigList = appConfigDao.findAll();
			if (logger.isDebugEnabled()) {
				logger.debug("appConfigList is {}", appConfigList);
			}
			DataValidationUtils.validateDataEmpty("appConfigList", appConfigList);
			ConfigUtils.appConfigMap = new HashMap<String, List<Map<String, Object>>>(appConfigList.size());
			for (AppConfig appConfig : appConfigList) {
				ConfigUtils.appConfigMap.put(appConfig.getKey(), appConfig.getValue());
			}
			if (logger.isDebugEnabled()) {
				logger.debug("ConfigUtils.appConfigMap is {}", ConfigUtils.appConfigMap);
			}
		}

		// load event config
		if (CollectionUtils.isEmpty(ConfigUtils.eventConfigMap)) {
			if (ConfigUtils.appConfigMap.containsKey(Constants.CFG_KEY_EVENT)) {
				List<Map<String, Object>> eventConfigList = ConfigUtils.appConfigMap.get(Constants.CFG_KEY_EVENT);

				DataValidationUtils.validateDataEmpty("eventConfigList", eventConfigList);
				ConfigUtils.eventConfigMap = new HashMap<String, EventConfig>(eventConfigList.size());
				EventConfig eventConfig = null;
				for (Map<String, Object> eventConfigMapOb : eventConfigList) {
					eventConfig = CommonUtils.convertMapToPojo(eventConfigMapOb, EventConfig.class);
					ConfigUtils.eventConfigMap.put(eventConfig.getServiceHandler(), eventConfig);
				}

				if (logger.isDebugEnabled()) {
					logger.debug("ConfigUtils.eventConfigMap is {}", ConfigUtils.eventConfigMap);
				}
			} else {
				if (logger.isWarnEnabled()) {
					logger.warn("No EventConfig in MongoDB.");
				}
				ConfigUtils.eventConfigMap = new HashMap<String, EventConfig>();
			}
		}

		// load task config
		if (CollectionUtils.isEmpty(ConfigUtils.taskConfigMap)) {
			if (ConfigUtils.appConfigMap.containsKey(Constants.CFG_KEY_TASK)) {

				List<Map<String, Object>> taskConfigList = ConfigUtils.appConfigMap.get(Constants.CFG_KEY_TASK);
				if (CollectionUtils.isEmpty(taskConfigList)) {
					if (logger.isWarnEnabled()) {
						logger.warn("No TaskConfig in MongoDB.");
					}
					ConfigUtils.taskConfigMap = new HashMap<String, TaskConfig>();
				} else {
					ConfigUtils.taskConfigMap = new HashMap<String, TaskConfig>(taskConfigList.size());
					TaskConfig taskConfig = null;
					for (Map<String, Object> taskConfigMapOb : taskConfigList) {
						taskConfig = CommonUtils.convertMapToPojo(taskConfigMapOb, TaskConfig.class);
						ConfigUtils.taskConfigMap.put(taskConfig.getTaskName(), taskConfig);
					}

					if (logger.isDebugEnabled()) {
						logger.debug("ConfigUtils.taskConfigMap is {}", ConfigUtils.taskConfigMap);
					}
				}

			} else {
				if (logger.isWarnEnabled()) {
					logger.warn("No TaskConfig in MongoDB.");
				}
				ConfigUtils.taskConfigMap = new HashMap<String, TaskConfig>();
			}

		}
	}

	@Override
	public void reloadConfigFromDb() throws Exception {
		ConfigUtils.appConfigMap = null;
		ConfigUtils.eventConfigMap = null;
		ConfigUtils.taskConfigMap = null;
		loadConfigToMemory();
	}

	@Override
	public AppConfig save(AppConfig appConfig) {
		return appConfigDao.save(appConfig);
	}

}
