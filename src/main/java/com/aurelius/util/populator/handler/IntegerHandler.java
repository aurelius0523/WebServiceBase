package com.aurelius.util.populator.handler;

import org.apache.commons.lang3.RandomUtils;

public class IntegerHandler implements DataTypeHandler<Integer> {

	@Override
	public Integer getRandom() {
		return RandomUtils.nextInt();
	}

}
