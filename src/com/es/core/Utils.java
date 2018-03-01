package com.es.core;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date getDateTime(int aYear, int aMonth, int aDay) {
		Calendar varCal = Calendar.getInstance();
		varCal.set(aYear, aMonth, aDay);
		return varCal.getTime();
	}
	
	public static int DayOfWeek(int aYear, int aMonth, int aDay) {
		Calendar varCal = Calendar.getInstance();
		varCal.set(aYear, aMonth, aDay);
		return varCal.get(Calendar.DAY_OF_WEEK);
	}
}
