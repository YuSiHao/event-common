package com.ysh.event.common.manager.impl;



import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ysh.event.common.manager.AppConfigManager;
import com.ysh.event.common.model.AppConfig;
import com.ysh.event.common.utils.ConfigUtils;

public class AppConfigManagerImpl extends AbstractGenericManagerImpl implements AppConfigManager {
	
	

	@Override
	public void loadAppConfigToMemory() throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("Start exectuet method loadAppConfigToMemory");
		}
		if(CollectionUtils.isEmpty(ConfigUtils.appConfigMap)){
			List<AppConfig> appConfigList = appConfigDao.findAll();
			if (logger.isDebugEnabled()) {
				logger.debug("appConfigList is [" + appConfigList + "]");
			}
			
		}
	}

	@Override
	public void reloadConfigFromDb() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public AppConfig save(AppConfig appConfig) {
		// TODO Auto-generated method stub
		return null;
	}

}
