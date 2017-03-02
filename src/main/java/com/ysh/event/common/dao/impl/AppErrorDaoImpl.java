package com.ysh.event.common.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysh.event.common.dao.AppErrorDao;
import com.ysh.event.common.dao.repositories.AppErrorRepository;
import com.ysh.event.common.model.AppError;



@Component
public class AppErrorDaoImpl extends AbstractGenericDaoImpl<AppError>implements AppErrorDao {
	@Autowired
	AppErrorRepository appErrorRepository;

	
	@Override
	public AppError save(AppError appError) {
		return appErrorRepository.save(appError);
	}

	
	@Override
	public List<AppError> findAll() {
		return appErrorRepository.findAll();
	}

	
	@Override
	public void delete(AppError appError) {
		appErrorRepository.delete(appError);
	}

	@Override
	public AppError findById(String id) {
		return appErrorRepository.findById(id);
	}
}
