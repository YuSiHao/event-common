package com.ysh.event.common.manager;

import com.ysh.event.common.model.AppConfig;

public interface AppConfigManager extends GenericManager {

	void loadAppConfigToMemory() throws Exception;

	void reloadConfigFromDb() throws Exception;

	AppConfig save(AppConfig appConfig);
}
