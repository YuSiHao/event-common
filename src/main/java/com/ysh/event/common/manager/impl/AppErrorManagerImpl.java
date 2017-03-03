package com.ysh.event.common.manager.impl;

import java.util.Date;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ysh.event.common.exception.AbstractGenericException;
import com.ysh.event.common.manager.AppErrorManager;
import com.ysh.event.common.model.AppError;
import com.ysh.event.common.model.AppEvent;
import com.ysh.event.common.model.SubError;
import com.ysh.event.common.utils.Constants;

@Component
public class AppErrorManagerImpl extends AbstractGenericManagerImpl implements AppErrorManager {

	@Override
	public void generateError(AppEvent appEvent, Exception e) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Start exectuet method generateError with appEvent [" + appEvent + "], Exception [" + e + "]");
		}
		boolean isSaveToDb = false;
		AbstractGenericException age = null;
		if (e instanceof AbstractGenericException) {
			age = (AbstractGenericException) e;
			isSaveToDb = age.isSavedToDb();
		}
		if (isSaveToDb) { // default is false
			AppError appError = null;
			if (appEvent != null) {
				// check if can find existing error with same event
				String eventId = appEvent.getId();
				appError = findErrorByEventId(eventId);
			}
			if (appError == null) {
				// can't find error then generate new error
				appError = new AppError();
			}
			appError.setErrorInfo(e.getMessage());
			appError.setNotifyStatus(Constants.EVENT_STATUS_NEW);
			appError.setStatus(Constants.EVENT_STATUS_NEW);
			appError.setSource(e.getClass().getSimpleName());
			if (appEvent != null) {
				appError.setEventId(appEvent.getId());
				appError.setServiceHandler(appEvent.getServiceHandler());

				// setup errorTrackList
				List<SubError> errorTrackList = appError.getErrorTrackList();
				SubError subError = new SubError();
				subError.setSource(appError.getSource());
				subError.setServiceHandler(appError.getServiceHandler());
				subError.setGeneratedTime(new Date());
				subError.setErrorInfo(appError.getErrorInfo());
				errorTrackList.add(subError);
				appError.setErrorTrackList(errorTrackList);
			}

			appErrorDao.save(appError);
			if (logger.isDebugEnabled()) {
				logger.debug("End exectuet method generateError with age {}", age);
			}

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("It is not required to save db agian.");
			}
		}
		throw age;

	}

	private AppError findErrorByEventId(String eventId) {
		AppError appError = null;
		if (StringUtils.isNotEmpty(eventId)) {
			// try to get error with event id
			Query query = Query.query(Criteria.where("eventId").is(eventId));
			appError = appErrorDao.querySingle(query, AppError.class);
		}
		return appError;
	}

	@Override
	public void retryErrorById(String errorId) throws Exception {
		// TODO Auto-generated method stub

	}

}
