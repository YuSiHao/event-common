package com.ysh.event.common.dao;


import java.util.List;

import com.ysh.event.common.model.AppError;


public interface AppErrorDao extends GenericDao<AppError> {
	AppError save(AppError appError);

	List<AppError> findAll();

	void delete(AppError appError);

	AppError findById(String id);
}
