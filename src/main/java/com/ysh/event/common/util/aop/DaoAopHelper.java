package com.ysh.event.common.util.aop;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.ysh.event.common.domain.BaseModel;
import com.ysh.event.common.utils.CommonUtils;

@Aspect
@Component
public class DaoAopHelper {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution( * com.ysh.event.common.dao.impl.*.save(..))")
	public void savePointCut() {
	}

	@Before("savePointCut() &&" + "args(t,..)")
	public void beforeSave(BaseModel t) {
		setIdAndAuditInfoForPojo(t);
	}

	@Pointcut("execution(* com.hbo.event.common.dao.impl.*.findAndModify(..))")
	public void findAndModifyPointCut() {
	}

	@Before("findAndModifyPointCut() && " + "args(query,update,..)")
	public void beforeFindAndModify(Query query, Update update) {
		update.set("modifiedBy", CommonUtils.getHostName());
		update.set("modifiedDate", new Date());
	}

	private void setIdAndAuditInfoForPojo(BaseModel t) {
		String id = t.getId();
		if (StringUtils.isEmpty(id)) {
			// set id
			t.setId(CommonUtils.generateUUID(t.getClass().getSimpleName()));
			// consider as new record
			t.setCreatedBy(CommonUtils.getHostName());
			t.setCreatedDate(new Date());
			t.setModifiedBy(CommonUtils.getHostName());
			t.setModifiedDate(new Date());
		} else {
			// consider as update record
			t.setModifiedBy(CommonUtils.getHostName());
			t.setModifiedDate(new Date());
		}
	}
}
