package com.hbo.woodmark.common.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ysh.event.common.model.AppConfig;


@Repository
public interface AppConfigRepository extends MongoRepository<AppConfig, String> {//AppConfig类，String主键的类型

}
