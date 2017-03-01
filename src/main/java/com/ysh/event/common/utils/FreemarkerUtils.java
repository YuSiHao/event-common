package com.ysh.event.common.utils;


import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ysh.event.common.exception.system.SystemException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtils {
	protected static Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

	@SuppressWarnings("deprecation")
	public static String processTemplate(Map<String, Object> inputMap, String templateString) throws Exception {
		String content = null;
		StringWriter contentOut = new StringWriter();
		Template template = null;
		try {
			template = new Template("template", new StringReader(templateString), new Configuration());
			template.process(inputMap, contentOut);
			content = contentOut.toString();
		} catch (Exception e) {
			if (logger.isWarnEnabled()) {
				logger.warn("Can't process template [" + templateString + "] with inputMap [" + inputMap + "]");
			}
			throw new SystemException(
					"Can't process template [" + templateString + "] with inputMap [" + inputMap + "]", e);
		}
		return content;
	}

	public static boolean returnBooleanAfterTemplateProcess(Map<String, Object> inputMap, String templateString)
			throws Exception {
		String result = processTemplate(inputMap, templateString);
		if ("true".equals(result)) {
			return true;
		} else {
			return false;
		}
	}

	public static String[] returnListAfterTemplateProcess(Map<String, Object> inputMap, String templateString)
			throws Exception {
		String result = processTemplate(inputMap, templateString);
		if (CommonUtils.isEmpty(result)) {
			return null;
		} else {
			return result.split(Constants.STRING_COMMA);
		}
	}
}
