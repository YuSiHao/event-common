package com.ysh.event.common.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysh.event.common.dao.AppConfigMybatisDao;
import com.ysh.event.common.domain.AppConfig;
import com.ysh.event.common.mapper.AppConfigMapper;

@Component
public class AppConfigDaoMybatisImpl extends AbstractGenericDaoMybatisImpl<AppConfig> implements AppConfigMybatisDao {

	@Autowired
	private AppConfigMapper appConfigMapper;

	@Override
	public AppConfig save(AppConfig appConfig) {
		// To do
		return null;
	}

	@Override
	public List<AppConfig> findAll() {
		return appConfigMapper.findAll();
	}

	@Override
	public void delete(AppConfig appConfig) {
		// To do
	}

}
