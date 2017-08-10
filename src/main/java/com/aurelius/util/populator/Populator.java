package com.aurelius.util.populator;

import static org.mockito.ArgumentMatchers.anyMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class Populator {
	private static final Logger logger = Logger.getLogger(Populator.class);
	
	private int iteration;
	private final int circuitBreaker;
	private final StringGenerator stringGenerator;
	private final IntegerGenerator integerGenerator;
	private final FloatingPointGenerator floatingPointGenerator;
	private final NumberGenerator numberGenerator;
	private final DateGenerator dateGenerator;
	
	private Populator(StringGenerator stringGenerator, 
			IntegerGenerator integerGenerator,
			FloatingPointGenerator floatingPointGenerator,
			NumberGenerator numberGenerator,
			DateGenerator dateGenerator) {
		this.stringGenerator = stringGenerator;
		this.integerGenerator = integerGenerator;
		this.floatingPointGenerator = floatingPointGenerator;
		this.numberGenerator = numberGenerator;
		this.dateGenerator = dateGenerator;
		this.circuitBreaker = 2;
	}

	public static class Builder {
		private boolean includeNegativeNumbers;
		private int minDigit;
		private int maxDigit;
		private Date startDate;
		private Date endDate;
		private int stringCharacterCount;
		private StringGenerator stringGenerator;
		private CharacterGenerator characterGenerator;
		private FloatingPointGenerator floatingPointGenerator;
		private IntegerGenerator integerGenerator;
		private DateGenerator dateGenerator;
		private NumberGenerator numberGenerator;
		
		public Populator build() {
			if (minDigit <= 0) {
				minDigit = 0;
				
				if (minDigit <= Integer.MIN_VALUE) {
					throw new ArithmeticException("value underflow");
				}
			}
			
			if (maxDigit > Integer.MAX_VALUE) {
				throw new ArithmeticException("value overflow");
			}
			
			if (stringCharacterCount <= 0) {
				stringCharacterCount = 20;
			}
			
			if (stringGenerator == null) {
				stringGenerator = new DefaultStringGenerator(stringCharacterCount);
			}
			
			if (integerGenerator == null) {
				integerGenerator = new DefaultIntegerGenerator();
			}
			
			if (floatingPointGenerator == null) {
				floatingPointGenerator = new DefaultFloatingPointGenerator();
			}
			
			if (numberGenerator == null) {
				numberGenerator = new DefaultNumberGenerator();
			}
			
			if (dateGenerator == null) {
				dateGenerator = new DefaultDateGenerator();
			}
			
			return new Populator(stringGenerator, 
					integerGenerator,
					floatingPointGenerator,
					numberGenerator,
					dateGenerator);
		}

		public Builder withStringCharacterCount(int stringCharacterCount) {
			this.stringCharacterCount = stringCharacterCount;
			return this;
		}

		public Builder withStringGenerator(StringGenerator stringGenerator) {
			this.stringGenerator = stringGenerator;
			return this;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T populate (Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class must be provided");
		}
		
		try {
			Constructor<?> cons = clazz.getConstructor();
			Object o = cons.newInstance();
			iteration = 0;
			
			reflectFields(o);
			
			return (T) o;
			
		} catch (NoSuchMethodException 
				| SecurityException 
				| InstantiationException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			throw new IllegalArgumentException("No Argument Constructor must be provided");
		}
	}
	
	public <T> Collection<T> populate (Class<T> clazz, int size) {
		if (clazz == null || size <= 0) {
			throw new IllegalArgumentException("Class and size must be provided");
		}
		
		Collection<T> collections = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			collections.add(populate(clazz));
		}
		
		return collections;
	}
	
	//do not reflect if it's more than 5 layers deep
	private void reflectFields(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		
		if (fields.length > 0 && iteration < circuitBreaker) {
			for (Field field : fields) {
				field.setAccessible(true);
				
				try {
					randomizeByDataType(o, field);
				} catch (IllegalArgumentException e) {
					logger.error("illegal argument provided!");
				} catch (IllegalAccessException e) {
					logger.error("illegal access exception!");
				}
			}
		}
	}
	
	private void randomizeByDataTypeUsingDataHandler(Object o, Field field) throws IllegalAccessException {
		
	}
	
	private void randomizeByDataType(Object o, Field field) throws IllegalAccessException {
		if (String.class.isAssignableFrom(field.getType())) {
			field.set(o, stringGenerator.getRandomString());
			
		} else if (int.class.isAssignableFrom(field.getType()) 
				|| Integer.class.isAssignableFrom(field.getType())) {
			field.set(o, integerGenerator.getRandomInteger());
			
		} else if (byte.class.isAssignableFrom(field.getType())) {
			field.set(o, integerGenerator.getRandomByte());
			
		} else if (byte[].class.isAssignableFrom(field.getType())) {
			field.set(o, integerGenerator.getRandomBytes());
			
		} else if (short.class.isAssignableFrom(field.getType())) {
			field.set(o, integerGenerator.getRandomShort());
			
		} else if (long.class.isAssignableFrom(field.getType())
				|| Long.class.isAssignableFrom(field.getType())) {
			field.set(o, integerGenerator.getRandomLong());
			
		} else if (float.class.isAssignableFrom(field.getType())) {
			field.set(o, floatingPointGenerator.getRandomFloat());
			
		} else if (double.class.isAssignableFrom(field.getType())
				|| Double.class.isAssignableFrom(field.getType())) {
			field.set(o, floatingPointGenerator.getRandomDouble());
			
		} else if (BigInteger.class.isAssignableFrom(field.getType())) {
			field.set(o, numberGenerator.getRandomBigInteger());
			
		} else if (BigDecimal.class.isAssignableFrom(field.getType())) {
			field.set(o, numberGenerator.getRandomBigDecimal());
			
		} else if (java.util.Date.class.isAssignableFrom(field.getType())) {
			field.set(o, dateGenerator.getRandomUtilDate());
			
		} else if (java.sql.Date.class.isAssignableFrom(field.getType())) {
			field.set(o, dateGenerator.getRandomSqlDate());
			
		} else if (Map.class.isAssignableFrom(field.getType())) {
			field.set(o, anyMap());
			
		} else if (List.class.isAssignableFrom(field.getType())) {
			
		} else if (Set.class.isAssignableFrom(field.getType())) {
			
		} else {
//			Object object = field.get(o);
//			reflectFields(object);
//			iteration++;
		}
	}
}
