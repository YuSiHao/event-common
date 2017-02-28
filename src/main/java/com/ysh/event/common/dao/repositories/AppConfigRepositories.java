package com.ysh.event.common.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ysh.event.common.model.AppConfig;

public interface AppConfigRepositories extends MongoRepository<AppConfig, String>{

}
