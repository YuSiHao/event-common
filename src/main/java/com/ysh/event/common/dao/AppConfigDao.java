package com.ysh.event.common.dao;

import java.util.List;
import com.ysh.event.common.model.AppConfig;


public interface AppConfigDao extends GenericDao<AppConfig>{
	AppConfig save(AppConfig appConfig);

	List<AppConfig> findAll();

	void delete(AppConfig appConfig);
}
