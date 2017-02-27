package com.ysh.event.common.manager;

import java.util.Map;

public interface GenericManager {
	Map<String, Object> executeCommands(String[] commands) throws Exception;
}
