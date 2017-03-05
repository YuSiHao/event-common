package com.ysh.event.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

	protected static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	final static ObjectMapper mapper = new ObjectMapper();

	private static String projectName;

	private static String HOST_NAME;

	public static String getHostName() {
		if (StringUtils.isEmpty(HOST_NAME)) {
			InetAddress ip = null;
			String hostName = null;
			try {
				ip = InetAddress.getLocalHost();
				hostName = ip.getHostName();
				if (StringUtils.isEmpty(hostName)) {
					hostName = ip.getHostAddress().toString();
				}
			} catch (UnknownHostException e) {
				hostName = "UnknownHost";
			}
			if (logger.isDebugEnabled()) {
				logger.debug("The hostName is {}", hostName);
			}
			HOST_NAME = hostName;
		}
		return HOST_NAME;
	}

	public static String generateUUID(String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("urn:ysh:");
		sb.append(projectName);
		sb.append(":");
		sb.append(className.toLowerCase());
		// sb.append(":");
		// sb.append(new Date().getTime());
		sb.append(":");
		sb.append(UUID.randomUUID().toString().replace("-", "").toUpperCase());

		String uuid = sb.toString();
		if (logger.isDebugEnabled()) {
			logger.debug("The uuid is {}", uuid);
		}
		return uuid;
	}

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

	public static String getProjectName() {
		return projectName;
	}

	public static void setProjectName(String projectName) {
		CommonUtils.projectName = projectName;
	}

	public static void setHostName(String hostName) {
		CommonUtils.HOST_NAME = hostName;
	}
}
