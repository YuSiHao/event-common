package com.ysh.event.common.manager;

import com.ysh.event.common.model.AppEvent;

public interface AppErrorManager extends GenericManager {
	
	void generateError(AppEvent appEvent, Exception e) throws Exception;

	void retryErrorById(String errorId) throws Exception;
}
