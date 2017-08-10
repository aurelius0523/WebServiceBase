package com.aurelius.util.populator;

import org.apache.commons.lang3.RandomUtils;

public class DefaultFloatingPointGenerator implements FloatingPointGenerator {

	@Override
	public float getRandomFloat() {
		return RandomUtils.nextFloat();
	}

	@Override
	public double getRandomDouble() {
		return RandomUtils.nextDouble();
	}

}
