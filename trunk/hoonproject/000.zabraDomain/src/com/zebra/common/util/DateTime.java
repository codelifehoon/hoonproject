package com.zebra.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public final class DateTime {

    /**
     * Don't let anyone instantiate this class
     */
    private DateTime() {}

    /**
     * check date string validation with the default format "yyyyMMdd".
     * @param s date string you want to check with default format "yyyyMMdd".
     * @return date java.util.Date
     */
    public static java.util.Date check(String s) throws java.text.ParseException {
        return check(s, "yyyyMMdd");
    }

    /**
     * check date string validation with an user defined format.
     * @param s date string you want to check.
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return date java.util.Date
     */
    public static java.util.Date check(String s, String format) throws java.text.ParseException {
        if ( s == null )
            throw new java.text.ParseException("date string to check is null", 0);
        if ( format == null )
            throw new java.text.ParseException("format string to check date is null", 0);

        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = null;
        try {
            date = formatter.parse(s);
        }
        catch(java.text.ParseException e) {
            /*
            throw new java.text.ParseException(
                e.getMessage() + " with format \"" + format + "\"",
                e.getErrorOffset()
            );
            */
            throw new java.text.ParseException(" wrong date:\"" + s +
            "\" with format \"" + format + "\"", 0);
        }

        if ( ! formatter.format(date).equals(s) )
            throw new java.text.ParseException(
                "Out of bound date:\"" + s + "\" with format \"" + format + "\"",
                0
            );
        return date;
    }

    /**
     * check date string validation with the default format "yyyyMMdd".
     * @param s date string you want to check with default format "yyyyMMdd"
     * @return boolean true 날짜 형식이 맞고, 존재하는 날짜일 때
     *                 false 날짜 형식이 맞지 않거나, 존재하지 않는 날짜일 때
     */
    public static boolean isValid(String s) throws Exception {
        return DateTime.isValid(s, "yyyyMMdd");
    }

    /**
     * check date string validation with an user defined format.
     * @param s date string you want to check.
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return boolean true 날짜 형식이 맞고, 존재하는 날짜일 때
     *                 false 날짜 형식이 맞지 않거나, 존재하지 않는 날짜일 때
     */
    public static boolean isValid(String s, String format) {
/*
        if ( s == null )
            throw new NullPointerException("date string to check is null");
        if ( format == null )
            throw new NullPointerException("format string to check date is null");
*/
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = null;
        try {
            date = formatter.parse(s);
        }
        catch(java.text.ParseException e) {
            return false;
        }

        if ( ! formatter.format(date).equals(s) )
            return false;

        return true;
    }

    /**
     * @return formatted string representation of current day with  "yyyy-MM-dd".
     */
    public static String getDateString() {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getDay() {
        return getNumberByPattern("dd");
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getYear() {
        return getNumberByPattern("yyyy");
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getMonth() {
        return getNumberByPattern("MM");
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getNumberByPattern(String pattern) {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
        String dateString = formatter.format(new java.util.Date());
        return Integer.parseInt(dateString);
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static String getFormatString(String pattern) {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
        String dateString = formatter.format(new java.util.Date());
        return dateString;
    }

    /**
     * @return formatted string representation of current day with  "yyyyMMdd".
     */
    public static String getShortDateString() {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat ("yyyyMMdd", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     * @return formatted string representation of current time with  "HHmmss".
     */
    public static String getShortTimeString() {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat ("HHmmss", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     * @return formatted string representation of current time with  "yyyy-MM-dd-HH:mm:ss".
     */
    public static String getTimeStampString() {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat ("yyyy-MM-dd-HH:mm:ss:SSS", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     * @return formatted string representation of current time with  "HH:mm:ss".
     */
    public static String getTimeString() {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat ("HH:mm:ss", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }


    /**
     * return days between two date strings with default defined format.(yyyyMMdd)
     * @param s date string you want to check.
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 요일을 리턴
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     *          0: 일요일 (java.util.Calendar.SUNDAY 와 비교)
     *          1: 월요일 (java.util.Calendar.MONDAY 와 비교)
     *          2: 화요일 (java.util.Calendar.TUESDAY 와 비교)
     *          3: 수요일 (java.util.Calendar.WENDESDAY 와 비교)
     *          4: 목요일 (java.util.Calendar.THURSDAY 와 비교)
     *          5: 금요일 (java.util.Calendar.FRIDAY 와 비교)
     *          6: 토요일 (java.util.Calendar.SATURDAY 와 비교)
     * 예) String s = "20000529";
     *  int dayOfWeek = whichDay(s, format);
     *  if (dayOfWeek == java.util.Calendar.MONDAY)
     *      System.out.println(" 월요일: " + dayOfWeek);
     *  if (dayOfWeek == java.util.Calendar.TUESDAY)
     *      System.out.println(" 화요일: " + dayOfWeek);
     */
    public static int whichDay(String s) throws java.text.ParseException {
        return whichDay(s, "yyyyMMdd");
    }
    /**
     *
     * 시작일자와 종료일자사이에 월요일과 일요일을 List에 닫아 return한다.
     * <P/>
     * 시작일자와 종료일자사이에서 각 주간별 월요일과 일요일의 날짜를 List에 담아서 return합니다.
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List getMondayAndSundayDate(String startDate, String endDate) throws ParseException {


        int dayCount = daysBetween(startDate,endDate);
        String sDay = startDate;
        int whichDay = 0;
        //기간동안의 주 구하기
        int weekCnt = dayCount/7;
        int weekCnt1 = dayCount % 7;
        if(weekCnt1 > 0) weekCnt++;
        String[][] saveDay = new String[weekCnt][2];
        int saveDayPos = 0;
        boolean monday = false;
        boolean sunday = false;
        List al = new ArrayList();

        for(int i=0;i<dayCount;i++){
            //sDay가 월요일인지 확인한다.
            whichDay = whichDay(sDay);
            //out.println("saveDayPos"+saveDayPos);
            //out.println("sDay"+sDay);
            //out.println("whichDay"+whichDay+"<br>");
            if(whichDay == 2){

                //월요일이면 insertStartDate에 저장
                saveDay[saveDayPos][0] = sDay;
                //일요일을 저장할수 있도록 monday mode를 true로 변경
                monday = true;
                //out.println(sDay +"is monday <br>");

            }

            if(whichDay == 1 && i > 0 && monday == true){

                //일요일이면 insertStartDate에 저장
                saveDay[saveDayPos][1] = sDay;
                //일요일을 저장할수 있도록 monday mode를 true로 변경
                monday = false;
                HashMap hm = new HashMap();

                hm.put("sDate",saveDay[saveDayPos][0]);
                hm.put("eDate",saveDay[saveDayPos][1]);
                al.add( hm );

                //일요일을 찾았으므로 월요일로 저장하기 위해 증가한다.
                saveDayPos++;

            }

            sDay = addDays(sDay,1);
        }
        return al;
    }
    /**
     * return days between two date strings with user defined format.
     * @param s date string you want to check.
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 요일을 리턴
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     *          0: 일요일 (java.util.Calendar.SUNDAY 와 비교)
     *          1: 월요일 (java.util.Calendar.MONDAY 와 비교)
     *          2: 화요일 (java.util.Calendar.TUESDAY 와 비교)
     *          3: 수요일 (java.util.Calendar.WENDESDAY 와 비교)
     *          4: 목요일 (java.util.Calendar.THURSDAY 와 비교)
     *          5: 금요일 (java.util.Calendar.FRIDAY 와 비교)
     *          6: 토요일 (java.util.Calendar.SATURDAY 와 비교)
     * 예) String s = "2000-05-29";
     *  int dayOfWeek = whichDay(s, "yyyy-MM-dd");
     *  if (dayOfWeek == java.util.Calendar.MONDAY)
     *      System.out.println(" 월요일: " + dayOfWeek);
     *  if (dayOfWeek == java.util.Calendar.TUESDAY)
     *      System.out.println(" 화요일: " + dayOfWeek);
     */

    public static int whichDay(String s, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        java.util.Calendar calendar = formatter.getCalendar();
        calendar.setTime(date);
        return calendar.get(java.util.Calendar.DAY_OF_WEEK);
    }

    /**
     * return days between two date strings with default defined format.("yyyyMMdd")
     * @param String from date string
     * @param String to date string
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 나이 리턴
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static int daysBetween(String from, String to) throws java.text.ParseException {
        return daysBetween(from, to, "yyyyMMdd");
    }

    /**
     * return days between two date strings with user defined format.
     * @param String from date string
     * @param String to date string
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 일자 리턴
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static int daysBetween(String from, String to, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
        new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date d1 = check(from, format);
        java.util.Date d2 = check(to, format);

        long duration = d2.getTime() - d1.getTime();

        return (int)( duration/(1000 * 60 * 60 * 24) );
        // seconds in 1 day
    }

    /**
     * return age between two date strings with default defined format.("yyyyMMdd")
     * @param String from date string
     * @param String to date string
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 나이 리턴
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static int ageBetween(String from, String to) throws java.text.ParseException {
        return ageBetween(from, to, "yyyyMMdd");
    }

    /**
     * return age between two date strings with user defined format.
     * @param String from date string
     * @param String to date string
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 나이 리턴
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static int ageBetween(String from, String to, String format) throws java.text.ParseException {
        return (int)(daysBetween(from, to, format) / 365 );
    }

    /**
     * return add day to date strings
     * @param String date string
     * @param int 더할 일수
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 더하기
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static String addDays(String s, int day) throws java.text.ParseException {
        return addDays(s, day, "yyyyMMdd");
    }
    /**
     * return add day to date strings
     * @param String date string
     * @param int 더할 일수
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 차감하기
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static String minusDays(String s, int day) throws java.text.ParseException {
        return minusDays(s, day, "yyyyMMdd");
    }

    /**
     * return add day to date strings with user defined format.
     * @param String date string
     * @param int 더할 초
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 더하기
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static String addSeconds(String s, int day, String format) throws java.text.ParseException{
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        date.setTime(date.getTime() + ((long)day * 1000));
        return formatter.format(date);
    }

    /**
     * return add day to date strings with user defined format.
     * @param String date string
     * @param int 더할 일수
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 더하기
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static String addDays(String s, int day, String format) throws java.text.ParseException{
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        date.setTime(date.getTime() + ((long)day * 1000 * 60 * 60 * 24));
        return formatter.format(date);
    }
    /**
     * return add day to date strings with user defined format.
     * @param String date string
     * @param int 더할 일수
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 차감하기
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static String minusDays(String s, int day, String format) throws java.text.ParseException{
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        date.setTime(date.getTime() - ((long)day * 1000 * 60 * 60 * 24));
        return formatter.format(date);
    }

    /**
     * return add month to date strings
     * @param String date string
     * @param int 더할 월수
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 월수 더하기
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static String addMonths(String s, int month) throws Exception {
        return addMonths(s, month, "yyyyMMdd");
    }

    /**
     * return add month to date strings with user defined format.
     * @param String date string
     * @param int 더할 월수
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 월수 더하기
     *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
     */
    public static String addMonths(String s, int addMonth, String format) throws Exception {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        java.text.SimpleDateFormat yearFormat =
            new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
        java.text.SimpleDateFormat monthFormat =
            new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
        java.text.SimpleDateFormat dayFormat =
            new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);
        int year = Integer.parseInt(yearFormat.format(date));
        int month = Integer.parseInt(monthFormat.format(date));
        int day = Integer.parseInt(dayFormat.format(date));

        month += addMonth;
        if (addMonth > 0) {
            while (month > 12) {
                month -= 12;
                year += 1;
            }
        } else {
            while (month <= 0) {
                month += 12;
                year -= 1;
            }
        }
        java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
        java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
        String tempDate = String.valueOf(fourDf.format(year))
                         + String.valueOf(twoDf.format(month))
                         + String.valueOf(twoDf.format(day));
        java.util.Date targetDate = null;

        try {
            targetDate = check(tempDate, "yyyyMMdd");
        } catch(java.text.ParseException pe) {
            day = lastDay(year, month);
            tempDate = String.valueOf(fourDf.format(year))
                         + String.valueOf(twoDf.format(month))
                         + String.valueOf(twoDf.format(day));
            targetDate = check(tempDate, "yyyyMMdd");
        }

        return formatter.format(targetDate);
    }

    public static String addYears(String s, int year) throws java.text.ParseException {
        return addYears(s, year, "yyyyMMdd");
    }

    public static String addYears(String s, int year, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);
        date.setTime(date.getTime() + ((long)year * 1000 * 60 * 60 * 24 * (365 + 1)));
        return formatter.format(date);
    }

    public static int monthsBetween(String from, String to) throws java.text.ParseException {
        return monthsBetween(from, to, "yyyyMMdd");
    }

    public static int monthsBetween(String from, String to, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date fromDate = check(from, format);
        java.util.Date toDate = check(to, format);

        // if two date are same, return 0.
        if (fromDate.compareTo(toDate) == 0) return 0;

        java.text.SimpleDateFormat yearFormat =
            new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
        java.text.SimpleDateFormat monthFormat =
            new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
        java.text.SimpleDateFormat dayFormat =
            new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);

        int fromYear = Integer.parseInt(yearFormat.format(fromDate));
        int toYear = Integer.parseInt(yearFormat.format(toDate));
        int fromMonth = Integer.parseInt(monthFormat.format(fromDate));
        int toMonth = Integer.parseInt(monthFormat.format(toDate));
        int fromDay = Integer.parseInt(dayFormat.format(fromDate));
        int toDay = Integer.parseInt(dayFormat.format(toDate));

        int result = 0;
        result += ((toYear - fromYear) * 12);
        result += (toMonth - fromMonth);

//        if (((toDay - fromDay) < 0) ) result += fromDate.compareTo(toDate);
        // ceil과 floor의 효과
        if (((toDay - fromDay) > 0) ) result += toDate.compareTo(fromDate);

        return result;
    }

    public static String lastDayOfMonth(String src) throws java.text.ParseException {
        return lastDayOfMonth(src, "yyyyMMdd");
    }

    public static String lastDayOfMonth(String src, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(src, format);

        java.text.SimpleDateFormat yearFormat =
            new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
        java.text.SimpleDateFormat monthFormat =
            new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);

        int year = Integer.parseInt(yearFormat.format(date));
        int month = Integer.parseInt(monthFormat.format(date));
        int day = lastDay(year, month);

        java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
        java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
        String tempDate = String.valueOf(fourDf.format(year))
                         + String.valueOf(twoDf.format(month))
                         + String.valueOf(twoDf.format(day));
        date = check(tempDate, format);

        return formatter.format(date);
    }

    public static int lastDay(int year, int month) throws java.text.ParseException {
        int day = 0;
        switch(month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: day = 31;
                     break;
            case 2: if ((year % 4) == 0) {
                        if ((year % 100) == 0 && (year % 400) != 0) { day = 28; }
                        else { day = 29; }
                    } else { day = 28; }
                    break;
            default: day = 30;
        }
        return day;
    }
    /**
     * 문자열을 배열로 리턴
     * @param txt 배열로 리턴할 문자열 ex) a,b,c
     * @param deli 구분자 ex) ,
     * @return 배열 ex) a b c 각각 배열
     */
    public static String[] getArrayFromString(String txt, String deli) {
        String str = txt;
        StringTokenizer st = new StringTokenizer(str, deli);
        String arrayStr[] = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++)
            arrayStr[i] = st.nextToken();
        return arrayStr;
    }
    /**
     *
     * 금주에 해당하는 월요일 구하기
     * <P/>
     * 금주에 해당하는 월요일 구하기
     *
     * @return
     * @throws ParseException
     */
    public static String getThisWeekMondayDate() throws ParseException {
        //금일날짜구하기
        String curDate = getDateString();
        //금일날짜가 월요일이면 금일날짜를 리턴, 금일날짜가 화요일이면 -1, 수요일-2, 목-3, 금-4, 토-5, 일-6
        String mondayDate = curDate;
        //sDay가 월요일인지 확인한다.
        int whichDay = whichDay(curDate);
        //화요일이면
        if(whichDay == 3){
            mondayDate = minusDays(curDate,1);
        }
        //수요일이면
        if(whichDay == 4){
            mondayDate = minusDays(curDate,2);
        }
        //목요일이면
        if(whichDay == 5){
            mondayDate = minusDays(curDate,3);
        }
        //금요일이면
        if(whichDay == 6){
            mondayDate = minusDays(curDate,4);
        }
        //토요일이면
        if(whichDay == 7){
            mondayDate = minusDays(curDate,5);
        }
        //일요일이면
        if(whichDay == 1){
            mondayDate = minusDays(curDate,6);
        }
        //mondayDate = "20071224";
        return mondayDate;
    }
    /**
     *
     * 금주에 해당하는 월요일 구하기
     * <P/>
     * 금주에 해당하는 월요일 구하기
     *
     * @return
     * @throws ParseException
     */
    public static String getThisWeekSundayDate() throws ParseException {
        //금일날짜구하기
        String curDate = getDateString();
        //금일날짜가 일요일이면 금일날짜를 리턴, 금일날짜가 월요일이면 +6, 화+5,수+4, 목+3, 금+2, 토+1
        String returnDate = curDate;
        //sDay가 일요일인지 확인한다.
        int whichDay = whichDay(curDate);
        //System.out.println(whichDay);
        //월요일이면
        if(whichDay == 2){
            returnDate = addDays(curDate,6);
        }
        //화요일이면
        if(whichDay == 3){
            returnDate = addDays(curDate,5);
        }
        //수요일이면
        if(whichDay == 4){
            returnDate = addDays(curDate,4);
        }
        //목요일이면
        if(whichDay == 5){
            returnDate = addDays(curDate,3);
        }
        //금요일이면
        if(whichDay == 6){
            returnDate = addDays(curDate,2);
        }
        //토요일이면
        if(whichDay == 7){
            returnDate = addDays(curDate,1);
        }
        //returnDate = "20071230";
        return returnDate;
    }


    /**
     * 주어진 Date를 pattern화 된 문자열로 반환한다.
     *
     * @param date
     *            패턴화할 날짜
     * @param pattern
     *            string 패턴
     * @return string 패턴화된 날짜 문자열
     */
    public static String format(Date date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        } catch (Exception ex)  {
            return "";
        }
    }

    /**
     * java.util.Date 클래스를 yyyy-MM-dd 포맷의 String으로 변환한다. 데이타베이스 컬럼이 NULL인경우에는
     * ""값을 리턴한다.(with SqlMap.xml)
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        String str = format(date, "yyyy/MM/dd");
        if (str.equals("0001/01/01")) {
            str = "";
        }
        return str;
    }

	/** 문자열을 날짜로
	 *
	 * @param value
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	 public static Date stringToDate(String value, String pattern) throws ParseException
	 {
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}
}
