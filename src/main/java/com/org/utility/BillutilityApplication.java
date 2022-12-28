package com.org.utility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SpringBootApplication
public class BillutilityApplication
{
	public static void getDatesFromWeekNumber()
	{
		//		SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
		//		Calendar         cal = Calendar.getInstance();
		//		cal.set(Calendar.WEEK_OF_YEAR, 23);
		//		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		//		System.out.println(sdf.format(cal.getTime()));
		LocalDate now = LocalDate.now();
		//		System.out.println(now);
		//		DayOfWeek  dayOfWeek  = now.getDayOfWeek();
		//		Month      month      = now.getMonth();
		int year = now.getYear();
		//		int        dayOfMonth = now.getDayOfMonth();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		int        weekId     = now.get(weekFields.weekOfYear());
		//		System.out.println("week Id : "+ weekId + " : dayOfMonth : "+ dayOfMonth + " : dayOfWeek : " + dayOfWeek + " : month : " + month + " : year : " +year);

		// Get calendar, clear it and set week number and year.
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.WEEK_OF_YEAR, weekId);
		calendar.set(Calendar.YEAR, year);

		// Now get the first day of week.
		Date            date            = calendar.getTime();
		LocalDate       startDateOfWeek = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate       lastDateOfWeek  = startDateOfWeek.plusDays(7);
		List<LocalDate> datesInWeek     = startDateOfWeek.datesUntil(lastDateOfWeek).collect(Collectors.toList());
		List<Integer>   dates           = datesInWeek.stream().map(LocalDate::getDayOfMonth)
				.collect(Collectors.toList());
		System.out.println("dates in a week : " + datesInWeek);
		System.out.println("dates : " + dates);
		System.out.println(
				"date :: " + date + "\n" + "First day of Week :: " + startDateOfWeek + " :: Last day of Week :: "
						+ lastDateOfWeek);
	}

	public static void main(String[] args) throws ParseException
	{
		// getDateFromFileName();
		//		getDatesFromWeekNumber();
		//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//		String format = formatter.format(date);

		SpringApplication.run(BillutilityApplication.class, args);
	}

	private static void getDateFromFileName()
	{
		String fileName = "Uk_Finance_Benne_20221027_150040";
		String date     = fileName.split("_")[3];
		System.out.println("date :: " + date);

		SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			String reformattedStr = myFormat.format(fromUser.parse("20220526"));
			System.out.println("formatted date : " + reformattedStr);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
	}
}
