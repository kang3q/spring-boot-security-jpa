package com.wisebirds.sap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
@PropertySource(value = "classpath:redis.properties")
public class Application extends SpringBootServletInitializer {
	public static Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static int getObjectType(String objectId) {
		int n = objectId.length() - OBJECT_LENGTH;
		if (n < 0) {
			throw new IllegalArgumentException("잘못된 오브젝트 아이디입니다.");
		} else {
			return Integer.parseInt(objectId.substring(0, n));
		}
	}

	@Value("${redis.host}")
	private String redisHost;
	@Value("${redis.auth}")
	private String auth;
	@Value("${redis.pool.max}")
	private int max;

	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(max);
		JedisPool jedisPool = new JedisPool(poolConfig, redisHost, 6379, 1000, auth);
		return jedisPool;
	}

	private static final int OBJECT_LENGTH = "000000000000000".length(); // 100조

	public static final int OBJECT_SAP_ACCOUNT = 1;
	public static final int OBJECT_ACCOUNT_1 = 2;
	public static final int OBJECT_ACCOUNT_2 = 3;
	public static final int OBJECT_ACCOUNT_3 = 4;
	public static final int OBJECT_ACCOUNT_4 = 5;
	public static final int OBJECT_ACCOUNT_5 = 6;
	public static final int OBJECT_ACCOUNT_6 = 7;
	public static final int OBJECT_ACCOUNT_7 = 8;
	public static final int OBJECT_ACCOUNT_8 = 9;
	public static final int OBJECT_ACCOUNT_9 = 10;
	public static final int OBJECT_ACCOUNT_10 = 11;
	public static final int OBJECT_ACCOUNT_11 = 12;
	public static final int OBJECT_ACCOUNT_12 = 13;
	public static final int OBJECT_ACCOUNT_13 = 14;
	public static final int OBJECT_ACCOUNT_14 = 15;
	public static final int OBJECT_ACCOUNT_15 = 16;
	public static final int OBJECT_ACCOUNT_16 = 17;
	public static final int OBJECT_ACCOUNT_17 = 18;
	public static final int OBJECT_ACCOUNT_18 = 19;

	public static final int OBJECT_AD_ACCOUNT = 20;
	public static final int OBJECT_AD_CAMPAIGN_GROUP = 21;
	public static final int OBJECT_AD_CAMPAIGN = 22;
	public static final int OBJECT_AD_ITEM = 23;
	public static final int OBJECT_AD_CREATIVE = 24;
	public static final int OBJECT_AD_TARGET = 25;
	public static final int OBJECT_AD_CUSTOM_AUDIENCE = 26;
	public static final int OBJECT_AD_CONVERSIOS_PIXEL = 27;
	public static final int OBJECT_AD_AD = 28;
	public static final int OBJECT_AD_9 = 29;
	public static final int OBJECT_AD_10 = 30;
	public static final int OBJECT_AD_11 = 31;
	public static final int OBJECT_AD_12 = 32;
	public static final int OBJECT_AD_13 = 33;
	public static final int OBJECT_AD_14 = 34;
	public static final int OBJECT_AD_15 = 35;
	public static final int OBJECT_AD_16 = 36;
	public static final int OBJECT_AD_17 = 37;
	public static final int OBJECT_AD_18 = 38;
	public static final int OBJECT_AD_19 = 39;
	public static final int OBJECT_AD_20 = 40;
	public static final int OBJECT_AD_21 = 41;
	public static final int OBJECT_AD_22 = 42;
	public static final int OBJECT_AD_23 = 43;
	public static final int OBJECT_AD_24 = 44;
	public static final int OBJECT_AD_25 = 45;
	public static final int OBJECT_AD_26 = 46;
	public static final int OBJECT_AD_27 = 47;
	public static final int OBJECT_AD_28 = 48;
	public static final int OBJECT_AD_29 = 49;
	public static final int OBJECT_AD_30 = 50;
	public static final int OBJECT_AD_31 = 51;
	public static final int OBJECT_AD_32 = 52;
	public static final int OBJECT_AD_33 = 53;
	public static final int OBJECT_AD_34 = 54;
	public static final int OBJECT_AD_35 = 55;
	public static final int OBJECT_AD_36 = 56;
	public static final int OBJECT_AD_37 = 57;
	public static final int OBJECT_AD_38 = 58;
	public static final int OBJECT_AD_39 = 59;
	public static final int OBJECT_AD_40 = 60;
	public static final int OBJECT_AD_41 = 61;
	public static final int OBJECT_AD_42 = 62;
	public static final int OBJECT_AD_43 = 63;
	public static final int OBJECT_AD_44 = 64;
	public static final int OBJECT_AD_45 = 65;
	public static final int OBJECT_AD_46 = 66;
	public static final int OBJECT_AD_47 = 67;
	public static final int OBJECT_AD_48 = 68;
	public static final int OBJECT_AD_49 = 69;
	public static final int OBJECT_AD_50 = 70;
}
