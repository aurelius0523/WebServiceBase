package com.aurelius.util.populator;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface NumberGenerator {
	public BigDecimal getRandomBigDecimal();
	public BigInteger getRandomBigInteger();
}
