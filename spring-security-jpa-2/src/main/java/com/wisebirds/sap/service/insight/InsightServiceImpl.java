package com.wisebirds.sap.service.insight;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InsightServiceImpl implements InsightService {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsightServiceImpl.class);

	@PostConstruct
	private void init() {
		LOGGER.info("init");
		
	}
}
