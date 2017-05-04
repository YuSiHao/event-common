package com.ysh.event.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ysh.event.common.dao.AppConfigDao;
import com.ysh.event.common.dao.AppConfigMybatisDao;
import com.ysh.event.common.dao.AppEventDao;
import com.ysh.event.common.model.AppConfig;
import com.ysh.event.common.model.AppEvent;

@RestController
@RequestMapping(value = "/forEventCommonTest")
public class EventCommonController {

	protected Logger log = LoggerFactory.getLogger(EventCommonController.class);

	@Autowired
	protected AppConfigDao appConfigDao;

	@Autowired
	private AppEventDao appEventDao;

	@Autowired
	protected AppConfigMybatisDao appConfigMybatisDao;

	@RequestMapping(value = { "", "/" }, produces = "text/html; charset=utf-8")
	public ModelAndView visitEventCommonPage(@RequestParam(defaultValue = "") String code) throws IOException {
		String EventCommonPage = "is coming";
		log.info("EventCommonPage:{}", EventCommonPage);
		log.debug("EventCommonPage:{}", EventCommonPage);
		ModelAndView candidatePage = new ModelAndView("index");
		return candidatePage;
	}

	@RequestMapping(value = "/AppConfigDao", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public List<AppConfig> findAllAppConfigs() throws IOException {
		String AppConfigDao = "AppConfigDao";
		log.info("test for:{}", AppConfigDao);
		log.debug("test for:{}", AppConfigDao);
		List<AppConfig> AppConfigs = appConfigDao.findAll();
		log.info("AppConfigs are,{}", AppConfigs);
		return AppConfigs;
	}

	@RequestMapping(value = "/AppConfigMybatisDao", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public List<com.ysh.event.common.domain.AppConfig> findAllAppMybatisConfigs() throws IOException {
		String AppConfigMybatisDao = "AppConfigMybatisDao";
		log.info("test for:{}", AppConfigMybatisDao);
		log.debug("test for:{}", AppConfigMybatisDao);
		List<com.ysh.event.common.domain.AppConfig> AppConfigs = appConfigMybatisDao.findAll();
		log.info("AppConfigs are,{}", AppConfigs);
		return AppConfigs;
	}

	@RequestMapping(value = "/testAop", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public void testAop() throws IOException {
		AppEvent appEvent = new AppEvent();
		Map<String, Object> testPayLoad = new HashMap<>();
		testPayLoad.put("test", "test");
		appEvent.setPayload(testPayLoad);
		appEventDao.save(appEvent);
		log.info("appEvent are,{}", appEvent);
	}

}
