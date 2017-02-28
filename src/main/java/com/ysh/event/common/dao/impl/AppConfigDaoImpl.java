package com.ysh.event.common.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysh.event.common.dao.AppConfigDao;
import com.ysh.event.common.dao.repositories.AppConfigRepositories;
import com.ysh.event.common.model.AppConfig;

@Component
public class AppConfigDaoImpl extends AbstractGenericDaoImpl<AppConfig> implements AppConfigDao {
	
	@Autowired
	AppConfigRepositories appConfigRepositories;
	
	@Override
	public AppConfig save(AppConfig appConfig) {
		return appConfigRepositories.save(appConfig);
	}

	@Override
	public List<AppConfig> findAll() {
		return appConfigRepositories.findAll();
	}

	@Override
	public void delete(AppConfig appConfig) {
		appConfigRepositories.delete(appConfig);
	}
	
	

}
