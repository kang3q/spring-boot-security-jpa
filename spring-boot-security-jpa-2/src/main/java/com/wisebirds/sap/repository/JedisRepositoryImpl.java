package com.wisebirds.sap.repository;

import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wisebirds.sap.util.AppContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisRepositoryImpl implements JedisRepository {
	@Autowired
	private JedisPool jedisPool;

	@PostConstruct
	private void init() {
		// TODO 각 애드 카테고리 초기화
	}

	@PreDestroy
	private void destroy() {
		jedisPool.destroy();
	}

	@Override
	public String get(String key) {
		try (Jedis jedis = borrow()) {
			return jedis.get(key);
		}
	}

	@Override
	public <T> T get(String key, Type t) {
		try (Jedis jedis = borrow()) {
			String json = jedis.get(key);
			T obj = AppContext.GSON.fromJson(json, t);
			return obj;
		}
	}

	@Override
	public <T> T get(String key, Class<T> t) {
		try (Jedis jedis = borrow()) {
			String json = jedis.get(key);
			T obj = AppContext.GSON.fromJson(json, t);
			return obj;
		}
	}

	@Override
	public boolean put(String key, String value) {
		try (Jedis jedis = borrow()) {
			return jedis.set(key, value).equals("OK");
			// return jedis.append(key, value);
		}
	}

	@Override
	public boolean putObject(String key, Object o) {
		try (Jedis jedis = borrow()) {
			return jedis.set(key, AppContext.GSON.toJson(o)).equals("OK");
		}
	}

	@Override
	public Long delete(String key) {
		try (Jedis jedis = borrow()) {
			return jedis.del(key);
		}
	}

	public void destory() {
		jedisPool.destroy();
	}

	public Jedis borrow() {
		return jedisPool.getResource();
	}
}
