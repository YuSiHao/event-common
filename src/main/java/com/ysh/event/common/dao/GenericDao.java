package com.ysh.event.common.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.CommandResult;
import com.ysh.event.common.model.BaseModel;

public interface GenericDao<T extends BaseModel> {
	/**
	 * @param query
	 * @param entityClass
	 * @return
	 */
	List<T> query(Query query, Class<T> entityClass);

	/**
	 * @param query
	 * @param entityClass
	 * @return
	 */
	T querySingle(Query query, Class<T> entityClass);

	/**
	 * @param id
	 * @param update
	 * @param entityClass
	 */
	void saveFields(String id, Update update, Class<T> entityClass);

	/**
	 * @param query
	 * @param update
	 * @param entityClass
	 * @return
	 */
	T findAndModify(Query query, Update update, Class<T> entityClass);

	/**
	 * @param query
	 * @param entityClass
	 * @return
	 */
	long count(Query query, Class<T> entityClass);

	boolean exists(Query query, Class<T> entityClass);

	CommandResult executeCommand(String jsonCommand);
}
