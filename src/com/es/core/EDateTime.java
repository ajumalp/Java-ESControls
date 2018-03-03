package com.es.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EDateTime {

	private Calendar FCalendar;
	private String FFormat;

	public EDateTime() {
		// TODO Auto-generated constructor stub
		FCalendar = Calendar.getInstance();
	}

	public EDateTime(int aYear, int aMonth, int aDate) {
		this();
		getCalendar().set(aYear, aMonth, aDate);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new SimpleDateFormat(FFormat).format(getCalendar().getTime());
	}

	public static Date CreateDate(int aYear, int aMonth, int aDate) {
		Calendar varCal = Calendar.getInstance();
		varCal.set(aYear, aMonth - 1, aDate);
		return varCal.getTime();
	}

	public Date getDateTime() {
		return getCalendar().getTime();
	}

	public void setDateTime(Date aValue) {
		getCalendar().setTime(aValue);
	}

	public Calendar getCalendar() {
		return FCalendar;
	}

	public int getYear() {
		return getCalendar().get(Calendar.YEAR);
	}

	public void setYear(int aValue) {
		getCalendar().set(Calendar.YEAR, aValue);
	}

	public int getMonth() {
		return getCalendar().get(Calendar.MONTH);
	}

	public void setMonth(int aValue) {
		getCalendar().set(Calendar.MONTH, aValue);
	}

	public int getDate() {
		return getCalendar().get(Calendar.DATE);
	}

	public void setDate(int aValue) {
		getCalendar().set(Calendar.DATE, aValue);
	}

	public String getFormat() {
		return FFormat;
	}

	public void setFormat(String aFormat) {
		FFormat = aFormat;
	}

	public int getFirstDayOfMonth() {
		Calendar varCal = Calendar.getInstance();
		varCal.setTime(getDateTime());
		varCal.set(Calendar.DATE, 1);
		return varCal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public int getMaxDaysInMonth() {
		return getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public boolean isThisYear() {
		return Calendar.getInstance().get(Calendar.YEAR) == getYear();
	}

	public boolean isThisMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) == getMonth();
	}

	public boolean isToday() {
		return isSameDate(new EDateTime());
	}

	public boolean isSameDate(EDateTime aDateTime) {
		return (aDateTime.getYear() == getYear()) && (aDateTime.getMonth() == getMonth())
				&& (aDateTime.getDate() == getDate());
	}

	public static Date Now() {
		// TODO Auto-generated method stub
		return Calendar.getInstance().getTime();
	}
}
