package com.hbo.woodmark.common.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ysh.event.common.model.AppError;


@Repository
public interface AppErrorRepository extends MongoRepository<AppError, String> {
	AppError findById(String id);
}
