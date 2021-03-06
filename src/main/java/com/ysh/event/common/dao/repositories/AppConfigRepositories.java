package com.ysh.event.common.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ysh.event.common.model.AppConfig;

@Repository
public interface AppConfigRepositories extends MongoRepository<AppConfig, String>{

}
