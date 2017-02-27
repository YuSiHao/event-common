package com.ysh.event.common.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/forEventCommonPage")
public class EventCommonController {

	protected Logger log = LoggerFactory.getLogger(EventCommonController.class);

	@RequestMapping(value = { "", "/" }, produces = "text/html; charset=utf-8")
	public ModelAndView visitEventCommonPage(@RequestParam(defaultValue = "") String code) throws IOException {
		String test = "hello";
		log.info("EventCommonPage:{}", test);
		log.debug("EventCommonPage:{}", test);
		ModelAndView candidatePage = new ModelAndView("index");
		return candidatePage;
	}
}
