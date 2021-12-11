package com.cesararaujostudio.api.util;

import java.time.LocalDate;

public class Converter {
	
	public static LocalDate convertJodaLocalDateToLocalDateJava(org.joda.time.LocalDate date) {
		return LocalDate.of(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());
	}
}
