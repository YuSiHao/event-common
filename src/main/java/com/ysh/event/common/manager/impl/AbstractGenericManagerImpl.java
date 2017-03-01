package com.ysh.event.common.manager.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ysh.event.common.dao.AppConfigDao;
import com.ysh.event.common.manager.GenericManager;


public class AbstractGenericManagerImpl implements GenericManager{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected AppConfigDao appConfigDao;
	
	public Map<String, Object> executeCommands(String[] commands) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void retryEventById(String eventId) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
