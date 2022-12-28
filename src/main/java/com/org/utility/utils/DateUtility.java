package com.org.utility.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtility
{

	public static List<LocalDate> getCurrentMonth()
	{
		LocalDate start = YearMonth.now().atDay(1);
		LocalDate end   = YearMonth.now().atEndOfMonth();
		return Arrays.asList(start, end);
	}

	public static LocalDate getCurrentDate()
	{
		return LocalDate.now();
	}

	public static LocalDate getPreviousDate(LocalDate localDate)
	{
		return localDate.minusDays(1);
	}

	public static LocalDate getNextDate(LocalDate localDate)
	{
		return localDate.plusDays(1);
	}

	public static List<LocalDate> getCurrentWeek()
	{
		final DayOfWeek  firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
		final DayOfWeek  lastDayOfWeek  = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

		LocalDate start = LocalDate.now().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));// first day
		LocalDate end = LocalDate.now().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));// last day

//		LocalDate start = LocalDate.now().with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());
//		LocalDate end = start.plusDays(6);

		/*Date     date = new Date();
		Calendar c    = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		System.out.println(start + " - " + end);

		LocalDate now = LocalDate.now();
		int       value = now.getDayOfWeek().getValue();

		TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
		System.out.println(now.with(fieldUS, 1)); // 2015-02-08 (Sunday)*/

		return Arrays.asList(start, end);
	}

	public static List<LocalDate> getCurrentYear()
	{
		YearMonth yearMonth  = YearMonth.now().withYear(YearMonth.now().getYear());
		int       daysInYear = yearMonth.lengthOfYear();
		LocalDate start      = Year.now(ZoneId.systemDefault()).atDay(1);
		LocalDate end        = start.plusDays(daysInYear - 1);
		return Arrays.asList(start, end);
	}

	// get current week
	// get prev week
	// get next week

	// get current month
	// get prev month
	// get next month

	// get current day
	// get prev day
	// get next day

	// get current year
	// get prev year
	// get next year

	public static void main(String[] args)
	{
//		List<LocalDate> currentMonth = getCurrentMonth();
//		System.out.println(currentMonth);

//		List<LocalDate> currentWeek = getCurrentWeek();
//		System.out.println(currentWeek.get(0) + " to " + currentWeek.get(1));

//		List<LocalDate> currentYear = getCurrentYear();
//		System.out.println(currentYear.get(0) + " to " + currentYear.get(1));

		LocalDate currentDate = getCurrentDate();
		LocalDate previousDate = getPreviousDate(currentDate);
		LocalDate nextDate = getNextDate(previousDate);
		System.out.println("Current Date : " + currentDate + "\n" +
				"Previous Date : " + previousDate + "\n" +
				"Next Date : " + nextDate);
	}
}
