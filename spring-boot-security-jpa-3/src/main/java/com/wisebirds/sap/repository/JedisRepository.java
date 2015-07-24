package com.wisebirds.sap.repository;

import java.lang.reflect.Type;

public interface JedisRepository {
	String get(String key);

	<T> T get(String key, Type t);

	<T> T get(String key, Class<T> t);

	boolean put(String key, String value);

	boolean putObject(String key, Object o);

	Long delete(String key);
}
