package com.aurelius.util.populator;

import org.apache.commons.text.RandomStringGenerator;

public class DefaultStringGenerator implements StringGenerator{
	private int characterCount;
	
	DefaultStringGenerator(int characterCount) {
		this.characterCount = characterCount;
	}
	
	@Override
	public String getRandomString() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
				.withinRange('a', 'z')
				.build();
		return generator.generate(characterCount);
	}
}
