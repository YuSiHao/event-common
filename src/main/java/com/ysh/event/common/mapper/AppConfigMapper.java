package com.ysh.event.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ysh.event.common.domain.AppConfig;

public interface AppConfigMapper {
	
	final String FIND_ALL_APPCONFIGS = "SELECT * FROM appconfig";
	
	@Select(FIND_ALL_APPCONFIGS)   
	List<AppConfig> findAll();
	
}
