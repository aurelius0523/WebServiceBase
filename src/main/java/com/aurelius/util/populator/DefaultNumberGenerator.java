package com.aurelius.util.populator;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.RandomUtils;

public class DefaultNumberGenerator implements NumberGenerator {

	@Override
	public BigDecimal getRandomBigDecimal() {
		return BigDecimal.valueOf(RandomUtils.nextDouble());
	}

	@Override
	public BigInteger getRandomBigInteger() {
		return BigInteger.valueOf(RandomUtils.nextLong());
	}
}
