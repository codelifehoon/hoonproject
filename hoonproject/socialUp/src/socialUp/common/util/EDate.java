package socialUp.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EDate {

	/* 월 이름 리스트 */
	public static final String [] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	/* 날짜형식 정의 */
	public static final String yyyyMM 				= "yyyyMM";
	public static final String yyMMdd 				= "yyMMdd";
	public static final String yyyyMMdd 			= "yyyyMMdd";
	public static final String yyyyMMddHHmmss 		= "yyyyMMddHHmmss";
	public static final String yyyyMMddHHmmssSSS	= "yyyyMMddHHmmssSSS";
	public static final String HHmmss				= "HHmmss";

	/* 요일 정의 */
	public static final int	SUNDAY		= 0;
	public static final int	MONDAY		= 1;
	public static final int	TUESDAY		= 2;
	public static final int	WEDNESDAY	= 3;
	public static final int	THURSDAY	= 4;
	public static final int	FRIDAY		= 5;
	public static final int	SATURDAY	= 6;
	
	/**
	 * 문자열을 날짜로 변환한다.
	 * @param inDate
	 * @return
	 */
	public static Date convert(Object inDate) {
		return convert((String) inDate);
	}

	public static Date convert(String inDate) {
		if (inDate == null || inDate.length() < 8)
			return null;

		int Y 	= Integer.parseInt(inDate.substring(0, 4));
		int M 	= Integer.parseInt(inDate.substring(4, 6));
		int D 	= Integer.parseInt(inDate.substring(6, 8));
		int h 	= 0;
		int m 	= 0;
		int s 	= 0;
		
		if (inDate.length() >= 10) {
			h 	= Integer.parseInt(inDate.substring(8, 10));
		}
		
		if (inDate.length() >= 12) {
			m 	= Integer.parseInt(inDate.substring(10, 12));
		}
		
		if (inDate.length() >= 14) {
			s 	= Integer.parseInt(inDate.substring(12, 14));
		}

		return convert(Y,M,D,h,m,s);
	}

	@SuppressWarnings("deprecation")
	public static Date convert(int Y, int M, int D, int h, int m, int s) {
		return new Date(Y-1900, M-1, D, h, m, s);
	}

	public static Date convert(String Y, String M, String D, String h, String m, String s) {
		if (Y == null || Y.length() <= 0)	Y = "1900";
		if (M == null || M.length() <= 0)	M = "1";
		if (D == null || D.length() <= 0)	D = "0";
		if (h == null || h.length() <= 0)	h = "0";
		if (m == null || m.length() <= 0)	m = "0";
		if (s == null || s.length() <= 0)	s = "0";
			
		return convert(Integer.parseInt(Y), Integer.parseInt(M), Integer.parseInt(D), Integer.parseInt(h), Integer.parseInt(m), Integer.parseInt(s));
	}

	public static Date convert(int Y, int M, int D) {
		return convert(Y,M,D,0,0,0);
	}

	public static Date convert(String Y, String M, String D) {
		return convert(Y,M,D,"0","0","0");
	}

	/**
	 * 년도를 구한다 (예:2005)
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getYear(Date inDate) {
		return (inDate.getYear()+1900);
	}

	/**
	 * 월을 구한다 (1 ~ 12)
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getMonth(Date inDate) {
		return (inDate.getMonth()+1);
	}

	/**
	 * 일을 구한다 (1 ~ 31)
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getDay(Date inDate) {
		return inDate.getDate();
	}

	/**
	 * 지정된 년,월,일 전후의 날짜를 얻는다. (양수는 이후, 음수는 이전날짜)
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date offset(Date inDate, int year, int month, int day) {
		if (inDate == null)
			return null;

		Date outDate = new Date(inDate.getTime());

		if (year != 0) {
			outDate.setYear(outDate.getYear() + year);
		}
		
		if (month != 0) {
			outDate.setMonth(outDate.getMonth() + month);
		}

		if (day != 0) {
			long offset = (long)day*24*60*60*1000;
			outDate.setTime(outDate.getTime() + offset);
		}
		
		return outDate;
	}

	/**
	 * 지정된 년,월,일 전후의 날짜를 얻는다. (양수는 이후, 음수는 이전날짜)
	 * @param inDate
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date offset(String inDate, int year, int month, int day) {
		return offset(convert(inDate), year, month, day);
	}

	/**
	 * 오늘 날짜를 얻는다.
	 * @return
	 */
	public static Date today() {
		return new Date();
	}

	/**
	 * 오늘 날짜를 지정된 포맷으로 변환한다.
	 * @param format
	 * @return
	 */
	public static String today(String format) {
		return format(new Date(), format);
	}

	/**
	 * 오늘에서 이동된 날짜를 얻은다.
	 * @param days
	 * @return
	 */
	public static Date offsetToday(int days) {
		Date dt = offset(new Date(), 0, 0, days);
		return dt;
	}

	/**
	 * 오늘에서 이동된 날짜를 얻은다.
	 * @param days
	 * @return
	 */
	public static String offsetToday(int days, String format) {
		Date dt = offset(new Date(), 0, 0, days);
		return format(dt, format);
	}

	/**
	 * 지정된 날짜에서 이동된 날짜를 얻은다.
	 * @param day, days
	 * @return
	 */
	public static String offsetToday(String day, int days, String format) {
		Date dt = offset(day, 0, 0, days);
		return format(dt, format);
	}
	
	/**
	 * 날짜를 지정된 포맷으로 변환한다. 
	 * @param inDate
	 * @param format
	 * @return
	 */
	public static String format(Date inDate, String format) {
		if (inDate == null)
			return "";
		
		if (format == null || format.length() <= 0) {
			format = yyyyMMddHHmmss;
		}
		
		SimpleDateFormat SDF = new SimpleDateFormat(format);
		return SDF.format(inDate);
	}

	/**
	 * 날짜를 지정된 포맷으로 변환한다. 
	 * @param inDate
	 * @param format
	 * @return
	 */
	public static String format(String inDate, String format) {
		return format(convert(inDate), format);
	}

	public static String format(Object inDate, String format) {
		return format(convert(inDate), format);
	}

	/**
	 * 해당 날짜의 요일을 얻는다.
	 * @param date
	 * @return 요일
	 */
	@SuppressWarnings("deprecation")
	public static int getDayOfWeek(Date inDate) {
		return inDate.getDay();
	}
	
	public static int getDayOfWeek(String inDate) {
		return getDayOfWeek(convert(inDate));
	}

	public static int getDayOfWeek(int year, int month, int day) {
		return getDayOfWeek(convert(year,month,day));
	}

	public static int getDayOfWeek(String year, String month, String day) {
		return getDayOfWeek(convert(year,month,day));
	}

	/**
	 * 지정된 기간의 날짜수를 얻는다.
	 * @param dtStart
	 * @param dtEnd
	 * @return
	 */
	public static int getDays(Date dtStart, Date dtEnd) {
		if (dtStart == null || dtEnd == null)
			return 0;
		
		long time = dtEnd.getTime() - dtStart.getTime();
		
		return (int)(time/(24*60*60*1000));
	}

	/**
	 * 지정된 기간의 날짜수를 얻는다.
	 * @param dtStart
	 * @param dtEnd
	 * @return
	 */
	public static int getDays(String dtStart, String dtEnd) {
		return getDays(convert(dtStart), convert(dtEnd));
	}

	public static int getDays(Object dtStart, Object dtEnd) {
		return getDays(convert(dtStart), convert(dtEnd));
	}

	/**
	 * 지정한 달의 날짜수를 구한다.
	 * @param inDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getDaysOfMonth(Date inDate) {
		Date dt1 = new Date(inDate.getTime());
		dt1.setDate(1);
		Date dt2 = offset(dt1, 0, 1, 0);
		
		return getDays(dt1, dt2);
	}

	public static int getDaysOfMonth(int year, int month) {
		Date dt1 = convert(year, month, 1);
		Date dt2 = offset(dt1, 0, 1, 0);
		
		return getDays(dt1, dt2);
	}

	public static int getDaysOfMonth(String year, String month) {
		if (year == null || year.length() <= 0)
			year = "1900";
		
		if (month == null || month.length() <= 0)
			month = "1";

		return getDaysOfMonth(Integer.parseInt(year),Integer.parseInt(month));
	}
	
	/**
	 * 지정한 달의 주수를 구한다.
	 * @param inDate
	 * @return
	 */
	public static int getWeeksOfMonth(int year, int month) {
		Date dt1 = convert(year, month, 1);
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, getDaysOfMonth(dt1));
		return cal.get(Calendar.WEEK_OF_MONTH);
	}

	public static int getWeeksOfMonth(String year, String month) {
		if (year == null || year.length() <= 0)
			year = "0";
		
		if (month == null || month.length() <= 0)
			month = "1";

		return getWeeksOfMonth(Integer.parseInt(year),Integer.parseInt(month));
	}

	/**
	 * 오늘이 해당 범위날짜 안에 포함되는지 여부.
	 * @param dtStart 시작일시
	 * @param dtEnd 종료일시
	 * @return
	 */
	public static boolean checkToday(String dtStart, String dtEnd) {
		
		if (dtStart != null && dtStart.length() >= 8) {
			String dtToday = today(yyyyMMddHHmmss).substring(0,dtStart.length());
			
			if (convert(dtStart).getTime() > convert(dtToday).getTime()) {
				return false;
			}
		}

		if (dtEnd != null && dtEnd.length() >= 8) {
			String dtToday = today(yyyyMMddHHmmss).substring(0,dtEnd.length());
			
			if (convert(dtEnd).getTime() < convert(dtToday).getTime()) {
				return false;
			}
		}
		
		return true;
	}
} // end of class
