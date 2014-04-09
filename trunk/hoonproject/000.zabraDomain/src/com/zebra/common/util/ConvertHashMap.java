package com.zebra.common.util;

import java.util.HashMap;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;



/**
 *
 *
 * @author  장재훈
 * @version 1.0
 * @see
 * @since   2008.3.28
 *
 * HashMap의 사용에 있어서 변환이 필요한 작업을 효율적으로 처리 하고자
 * HashMap을 상속받은 서브 클레스 생성
 */
public class ConvertHashMap extends HashMap
{

    public ConvertHashMap()
    {};

    /**
     * @param 가져올 값의 Key
     * @return key에 해당 되는값을 String 형식으로 리턴
     */
    public String ToString(String key)
    {
        if (this.get(key) == null) return "";

        return this.get(key).toString();
    }


    /**
     * @param 가져올 값의 Key
     * @return key에 해당 되는값을 long 형식으로 리턴
     */
    public long ToLong(String key)
    {
        if (this.get(key) == null) return 0;
        return Long.parseLong(this.get(key).toString());
    }


    /**
     * @param 가져올 값의 Key
     * @param 짤라낼 문자수
     * @return 문자를 특정길이로 만들어서 리턴
     */
    public String fixLength(String key,int length)
    {
        if (this.get(key) == null) return "";

        CmnUtil cmnutil = new CmnUtil();

        return cmnutil.fixLength(this.get(key).toString(), length,"");
        
        
    }

    /**
     * @param 가져올 값의 Key
     * @return 숫자표현 형식으로 값을 리턴
     */
    public String notation(String key)
    {
        if (this.get(key) == null) return "0";
        CmnUtil cmnutil = new CmnUtil();
        
        return cmnutil.setComma(this.get(key).toString());
    }

    /**
     * @param 가져올 값의 Key
     * @return 소숫점 scale 자리 이후는 짜른다.
     */
    /*
    public String ToCeil(String key,int scale)
    {
        if (this.get(key) == null) return "";

        String buffer = this.get(key).toString();
        if (buffer.equals("")) return "0";

        division(buffer, String downStr, long multiply,  scale)
                
        return String.valueOf(NumUtil.ceilPoint(NumUtil.toDouble(buffer),scale));
    }
    */
    /**
     * @param 가져올 값의 Key
     * @return 8자리 문자(YYYMMDD)를 (YYYY-MM-DD)로 변환한다.
     */
    public String ToDateStr(String key)
    {
        if (this.get(key) == null) return "";

        String buffer = this.get(key).toString();
        if (buffer.length() != 8 ) return "Must8lengthstr";
        return format(buffer, "yyyyMMdd", "yyyy-MM-dd");


    }

    /**
     * @param 가져올 값의 Key , 키값을 어떠한 정의된 문자로 변환시킬 majorKey
     * @return 8자리 문자(YYYMMDD)를 (YYYY-MM-DD)로 변환한다.
     */
    /*
    public String GetCodeToStr(String key,String majorKey)
    {
        if (this.get(key) == null) return "";

        try
        {
            Code code = CodeRepository.getInstance();
            String buffer = this.get(key).toString();
            return code.getCodeName( majorKey,  buffer);
        }
        catch (CodeException e) {   return "";  }


    }
    */


    /**
     * @param 가져올 값의 Key
     * @return 문자를 HTML로 표현후 리턴
     */
    
    public String convertToPrintableHtml(String key)
    {
        if (this.get(key) == null) return "";
        CmnUtil cmnutil = new CmnUtil();
        return cmnutil.toHtml(this.get(key).toString());
    }
    

    
    public  String format(String datestr, String fromPattern,
            String toPattern) {
        if (datestr == null || datestr.equals("")) return "";
        
        try
        {
            datestr = format(parse(datestr, fromPattern), toPattern);
        }
        catch(Exception e)
        {
            datestr ="날짜형식오류";
        }
        
        return  datestr;
    }

// 내부적으로 쓰는 method
    
    /**
     * 주어진 Date를 pattern화 된 문자열로 반환한다.
     *
     * @param date
     *            패턴화할 날짜
     * @param pattern
     *            string 패턴
     * @return string 패턴화된 날짜 문자열
     */
    public String format(Date date, String pattern) {
        return format(date, pattern);
    }
    
    /**
     * pattern형식으로 포맷된 날짜 문자열을 java.util.Date 형태로 반환한다.
     *
     * @param s
     *            date string you want to check.
     * @param format
     *            string representation of the date format. For example,
     *            "yyyy-MM-dd".
     * @return date java.util.Date
     */
    public  Date parse(String str, String pattern) throws Exception {
        if (str == null) {
            throw new Exception("date string to check is null");
        }

        if (pattern == null) {
            throw new Exception("format string to check date is null");
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern,
                java.util.Locale.KOREA);
        Date date = null;

        try {
            date = formatter.parse(str);
        } catch (ParseException e) {

            throw new Exception(" wrong date:\"" + str
                    + "\" with format \"" + pattern + "\"");
        }

        if (!format(date, pattern).equals(str))
            throw new Exception("Out of bound date:\"" + str
                    + "\" with format \"" + pattern + "\"");
        return date;
    }
    
   
    
    


}
