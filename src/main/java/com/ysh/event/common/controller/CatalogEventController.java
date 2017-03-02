package com.ysh.event.common.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysh.event.common.service.catalog.CatalogService;
import com.ysh.event.common.utils.CommonUtils;

@RestController
@RequestMapping(value = "/forCatalogEventTest")
public class CatalogEventController {
	
	@Autowired
	private CatalogService catalogService;
	
	
	protected Logger log = LoggerFactory.getLogger(CatalogEventController.class);
	
	@RequestMapping(value = { "", "/" }, produces = "text/html; charset=utf-8")
	public String catalogEventTest(@RequestParam(defaultValue = "") String coreEvent) throws Exception {
		String catalogEventTest = "is coming";
		Map<String, Object> coreEventMap = null;
		log.info("catalogEventTest:{}", catalogEventTest);
		log.debug("catalogEventTest:{}", catalogEventTest);
		coreEventMap = CommonUtils.convertPojoToMap(coreEvent);
		String result = catalogService.eventListener(coreEventMap);
		return result;
	}
}
