package com.ysh.event.common.basement;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasementTest {
	
	private final static Logger log = LoggerFactory.getLogger(BasementTest.class);
	
	@Test
	public void testLog(){
		String testok = "ok";
		log.info("test log.info,{}",testok);
	}
}
