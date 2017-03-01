package com.ysh.event.common.dao;


import java.util.List;

import com.ysh.event.common.model.AppEvent;


public interface AppEventDao extends GenericDao<AppEvent> {
	
	AppEvent save(AppEvent appEvent);

	List<AppEvent> findAll();

	void delete(AppEvent appEvent);
	
	AppEvent findById(String id);
}
