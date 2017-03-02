package com.ysh.event.common.utils;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public class CommonUtils {
	
	protected static Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	
	final static ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof Object[]) {
			Object[] objectArray = (Object[]) object;
			if (objectArray.length == 0) {
				return true;
			} else {
				return false;
			}
		}

		if (object instanceof String) {
			String objectString = (String) object;
			return StringUtils.isEmpty(objectString);
		}

		if (object instanceof Collection) {
			Collection<?> objectCollection = (Collection<?>) object;
			return CollectionUtils.isEmpty(objectCollection);
		}

		if (object instanceof Map) {
			Map objectCollection = (Map) object;
			return CollectionUtils.isEmpty(objectCollection);
		}

		if (logger.isWarnEnabled()) {
			logger.warn("The object [" + object + "] can't be determined if empty.");
		}

		return false;
	}
	
	public static <T> T convertMapToPojo(Map<String, Object> map, Class<T> pojoClass) {
		T pojo = mapper.convertValue(map, pojoClass);
		return pojo;
	}
	
	public static <T> T generatePojoFromString(String content, Class<T> pojoClass) throws Exception {
		T pojo = mapper.readValue(content, pojoClass);
		return pojo;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertPojoToMap(Object pojo) {
		Map<String, Object> map = mapper.convertValue(pojo, Map.class);
		return map;
	}
}
