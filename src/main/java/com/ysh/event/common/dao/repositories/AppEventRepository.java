package com.ysh.event.common.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ysh.event.common.model.AppEvent;

@Repository
public interface AppEventRepository extends MongoRepository<AppEvent, String> {
	AppEvent findById(String id);
}
