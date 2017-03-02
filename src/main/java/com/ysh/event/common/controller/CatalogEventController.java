package com.ysh.event.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ysh.event.common.service.catalog.CatalogService;
import com.ysh.event.common.utils.CommonUtils;

@RestController
@RequestMapping(value = "/forCatalogEventTest")
public class CatalogEventController {

	@Autowired
	private CatalogService catalogService;

	protected Logger log = LoggerFactory.getLogger(CatalogEventController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "", "/" }, produces = "text/html; charset=utf-8", method = RequestMethod.GET)
	public String catalogEventTest(@RequestParam(defaultValue = "") String coreEvent) throws Exception {
		String catalogEventTest = "is coming";
		Map<String, Object> coreEventMap = null;
		log.info("catalogEventTest:{}", catalogEventTest);
		log.debug("catalogEventTest:{}", catalogEventTest);
		if(coreEvent.equals("")){
			coreEvent = "{\"entityName\": \"video\"}";
		}
		coreEventMap = CommonUtils.generatePojoFromString(coreEvent, Map.class);
		String result = catalogService.eventListener(coreEventMap);
		return result;
	}
}
