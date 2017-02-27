package com.ysh.event.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.ysh.event.common.model.AppConfig;

public interface AppConfigService {
	
	public Object saveAppConfig(@Context HttpServletRequest request, AppConfig appConfig) throws Exception;

}
