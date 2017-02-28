package com.ysh.event.common.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ysh.event.common.dao.AppConfigDao;
import com.ysh.event.common.model.AppConfig;

@RestController
@RequestMapping(value = "/forEventCommonTest")
public class EventCommonController {

	protected Logger log = LoggerFactory.getLogger(EventCommonController.class);

	@Autowired
	protected AppConfigDao appConfigDao;

	@RequestMapping(value = { "", "/" }, produces = "text/html; charset=utf-8")
	public ModelAndView visitEventCommonPage(@RequestParam(defaultValue = "") String code) throws IOException {
		String EventCommonPage = "is coming";
		log.info("EventCommonPage:{}", EventCommonPage);
		log.debug("EventCommonPage:{}", EventCommonPage);
		ModelAndView candidatePage = new ModelAndView("index");
		return candidatePage;
	}

	@RequestMapping(value = "/AppConfigDao", produces = "application/xml; charset=utf-8", method = RequestMethod.GET)
	public List<AppConfig> visitEventCommonPage() throws IOException {
		String AppConfigDao = "AppConfigDao";
		log.info("test for:{}", AppConfigDao);
		log.debug("test for:{}", AppConfigDao);
		List<AppConfig> AppConfigs = appConfigDao.findAll();
		log.info("AppConfigs are,{}", AppConfigs);
		return AppConfigs;
	}

}
