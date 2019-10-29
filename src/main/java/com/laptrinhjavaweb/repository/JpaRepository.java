package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.paging.Pageable;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface JpaRepository<T> {
	List<T> findAll(Map<String,Object> properties,Pageable pageable, Object... objects);
	List<T> finAll(Map<String,Object> properties,Object... objects);
	List<T> findAll(Pageable pageable);
	List<T> findAll(String sql,Pageable pageable,Object... objects);
	List<T> findAll(String sql,Object... objects);
	List<T> findAll();
	T findById(Long id);
	void deleteById(Long id,Object... objects);
	Long insert(Object t);
	void update(Object t);
}
