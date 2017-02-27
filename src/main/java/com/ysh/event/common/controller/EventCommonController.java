package com.ysh.event.common.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/forEventCommonPage")
public class EventCommonController {
	
	@RequestMapping(value = { "", "/" }, produces = "text/html; charset=utf-8")
	public ModelAndView visitEventCommonPage(@RequestParam(defaultValue = "") String code) throws IOException {
		System.out.println("code is " + code);
		ModelAndView candidatePage = new ModelAndView("index");
		return candidatePage;
	}
}
