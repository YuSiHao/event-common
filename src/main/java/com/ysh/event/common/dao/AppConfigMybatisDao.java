package com.ysh.event.common.dao;

import java.util.List;

import com.ysh.event.common.domain.AppConfig;


public interface AppConfigMybatisDao extends GenericMybatisDao<AppConfig>{
	
	AppConfig save(AppConfig appConfig);

	List<AppConfig> findAll();

	void delete(AppConfig appConfig);
	
}
