package com.aurelius.util.populator;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

public class DefaultDateGenerator implements DateGenerator {

	@Override
	public Date getRandomUtilDate() {
		return new java.util.Date();
	}

	@Override
	public Date getRandomSqlDate() {
		return new java.sql.Date(RandomUtils.nextLong());
	}
	
}
