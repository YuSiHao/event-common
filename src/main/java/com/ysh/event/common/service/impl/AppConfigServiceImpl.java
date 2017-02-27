package com.ysh.event.common.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.core.Context;
import com.ysh.event.common.model.AppConfig;
import com.ysh.event.common.service.AppConfigService;

@RestController
@RequestMapping(value = "/forAppConfig")
public class AppConfigServiceImpl implements AppConfigService{
	
	@RequestMapping(value = "/save", produces = "application/xml; charset=utf-8", method = RequestMethod.POST)
	public Object saveAppConfig(@Context HttpServletRequest request, @RequestBody AppConfig appConfig) throws IOException {
		System.out.println("To do saveAppConfig");
		Object appConfigTest = "appconfig";
		return appConfigTest;
	}
	
}