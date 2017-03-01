package com.ysh.event.common.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysh.event.common.dao.AppEventDao;
import com.ysh.event.common.dao.repositories.AppEventRepository;
import com.ysh.event.common.model.AppEvent;

@Component
public class AppEventDaoImpl extends AbstractGenericDaoImpl<AppEvent> implements AppEventDao {
	@Autowired
	AppEventRepository appEventRepository;

	/*
	 * Covered by aop to generate id, createdBy, createdDate, modifiedBy,
	 * modifiedDate for new generate modifiedBy, modifiedDate for update
	 * 
	 */
	@Override
	public AppEvent save(AppEvent appEvent) {
		return appEventRepository.save(appEvent);
	}

	@Override
	public List<AppEvent> findAll() {
		return appEventRepository.findAll();
	}

	@Override
	public void delete(AppEvent appEvent) {
		appEventRepository.delete(appEvent);
	}

	@Override
	public AppEvent findById(String id) {
		return appEventRepository.findById(id);
	}
}
