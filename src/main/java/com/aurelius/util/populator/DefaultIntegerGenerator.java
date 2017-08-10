package com.aurelius.util.populator;

import org.apache.commons.lang3.RandomUtils;

public class DefaultIntegerGenerator implements IntegerGenerator {
	@Override
	public byte getRandomByte() {
		return RandomUtils.nextBytes(20)[0];
	}

	@Override
	public byte[] getRandomBytes() {
		return RandomUtils.nextBytes(20);
	}
	
	@Override
	public short getRandomShort() {
		return (short) RandomUtils.nextInt();
	}

	@Override
	public int getRandomInteger() {
		return RandomUtils.nextInt();
	}

	@Override
	public long getRandomLong() {
		return RandomUtils.nextLong();
	}
}
