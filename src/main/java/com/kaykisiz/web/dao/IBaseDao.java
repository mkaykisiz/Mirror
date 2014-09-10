package com.kaykisiz.web.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T, ID extends Serializable> {

	public T insert(T entity) throws Exception;
	public List<T> fetchRecordsAll() throws Exception;
	public List<T> fetchPostRecordsUserLike(String User) throws Exception;
	public void delete(String user) throws Exception ;
}