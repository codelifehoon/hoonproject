
package com.zebra.common.util;

import java.io.BufferedReader;




import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * @author 장재훈
 *
 */
/**
 * @author 장재훈
 *
 */
/**
 * @author 장재훈
 *
 */
/**
 * @author 장재훈
 *
 */
public class CmnUtil {

	/**
	 /하여 String반환
	 <p>
	 multiply : * 값,0이면 /만
	 <p>
	 underNum : 소수점 아래값
	 */
	public String division(double above, double down, long multiply,
			int underNum)

	{
		double d = 0.0D;
		try
		{
			if (multiply == 0)
				d = above / down;
			else
				d = above / down * multiply;
		}
		catch (Exception _ex)
		{
			d = 0.0D;
		}
		return formatNum(d, underNum);
	}

	/**
	 /하여 String반환
	 <p>
	 multiply : * 값,0이면 /만
	 <p>
	 underNum : 소수점 아래값
	 */

	public String division(long above, long down, long multiply, int underNum)
	{
		double d = 0.0D;
		try
		{
			if (multiply == 0)
				d = above / down;
			else
				d = above / down * multiply;
		}
		catch (Exception _ex)
		{
			d = 0.0D;
		}
		return formatNum(d, underNum);
	}

	/**
	 /하여 String반환
	 <p>
	 multiply : * 값,0이면 /만
	 <p>
	 underNum : 소수점 아래값
	 */

	public String division(int above, int down, long multiply, int underNum)
	{
		double d = 0.0D;
		try
		{
			if (multiply == 0)
				d = above / down;
			else
				d = above / down * multiply;
		}
		catch (Exception _ex)
		{
			d = 0.0D;
		}
		return formatNum(d, underNum);
	}

	/**
	 //제목에서 title로 비고를 보여줄경우 특수기호를html형식으로
	 //getStatus와 비슷
	 */
	public static String toHtml(String in) {
		Hashtable replace = new Hashtable();
		String sRetrun = "";
		if (in != null && in.length() > 0) {
			replace.put("\r\n", "<br>");
			replace.put("\n", "<br>");
			replace.put("\"", "&#34;");
			replace.put("&", "&#38;");
			replace.put("<", "&#60;");
			replace.put(">", "&#62;");
			replace.put("  ", "&#160;");
			replace.put("'", "&#39;");
			replace.put("\"", "&quot;");
			return hashToHtml(in, replace);
		} else {
			return sRetrun;
		}
	}

	/***************************************************************************
	 * 설  명 : 뉴라인 \r\n(MicroSoft) or \n(Unix) or \r(Macintosh) 일괄 처리 (key = "\r\n")
	 *          space space1개 + 나머지는 &nbsp; (지정문자) 처리 (key = "  ")
	 *          기타 replace
	 * @param : in : 변경할 문자
	 *          ht : hashtabel
	 * @return: <code>String</code>
	 ***************************************************************************/
	public static String hashToHtml(String in, Hashtable ht) // list(String.valueOf(char), String), char = '\n' applied to '\n' and '\r' and '\r\n'
	{
		StringBuffer ret = new StringBuffer();
		int len = in.length();

		String newline = (String) ht.get("\r\n");
		boolean processNewline = (newline != null);
		String space = (String) ht.get("  ");
		boolean processSpace = (space != null);

		for (int i = 0; i < len; i++) {
			char x = in.charAt(i);
			String y = null;
			if (processNewline && (x == '\n' || x == '\r')) // newline 처리
			{
				if (in.charAt(i) == '\r' && i + 1 < len
						&& in.charAt(i + 1) == '\n')
					i++; //\r\n 처리
				ret.append(newline);
			} else if (processSpace && (x == ' ')) // space 처리
			{
				ret.append(' ');
				for (int j = i + 1; (j < len) && (in.charAt(j) == ' '); j++)
					ret.append(space);
			} else if ((y = (String) ht.get(String.valueOf(x))) != null) // 기타 처리
			{
				ret.append(y);
			} else
				ret.append(x);
		}
		return ret.toString();
	}

	/**
	 /하여 String반환
	 <p>
	 multiply : * 값,0이면 /만
	 <p>
	 underNum : 소수점 아래값
	 */

	public String division(String aboveStr, String downStr, long multiply,
			int underNum)
	{
		double above = 0.0D;
		double down = 0.0D;
		try
		{
			above = Double.valueOf(aboveStr).doubleValue();
		}
		catch (Exception _ex)
		{
			above = 0.0D;
		}
		try
		{
			down = Double.valueOf(downStr).doubleValue();
		}
		catch (Exception _ex)
		{
			down = 0.0D;
		}
		return division(above, down, multiply, underNum);
	}

	/*
	 특수문자코드 실제모양 특수문자코드 실제모양
	 &gt; > &auot; "
	 &lt; < &copy; 원문자 c
	 &amp; & &nbsp Space(공백)

	 */
	/**
	 개행문자대신 &lt;br&gt; 붙이기
	 */

	public String appendHtmlBr(String comment)
	{
		int length = comment.length();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; ++i)
		{
			String comp = comment.substring(i, i + 1);
			if ("\r".compareTo(comp) == 0)
			{
				comp = comment.substring(++i, i + 1);
				if ("\n".compareTo(comp) == 0)
					buffer.append("<BR>\r");
				else
					buffer.append("\r");
			}
			buffer.append(comp);
		}
		return buffer.toString();
	}

	/**
	 reqtColcmn은 한글기준 (30이면 영문은 60개)
	 <br>
	 <p>
	 \r \n: 길이 1 ,&lt;br&gt;길이 4
	 <p>
	 원하는 길이에서 &lt;br&gt;을 추가해 라인 바꾸기
	 */
	public String changeLine(String Data, int reqtColcmn)
	{
		String newStr = "";
		int len = 0;
		if (Data != null ) len = Data.length();
		
		try
		{
			if (Data == null)
			{
				return setSpace(reqtColcmn);
			}
			if (len > reqtColcmn)
			{// data길이가 요구 길이보다 클때
				if (Data.toLowerCase().indexOf("<br>\r") == -1)
				{//<br>\r이 없을때
					int cursor = 0;
					int engCount = 0;
					for (int i = 0; i < len; i++)
					{
						String addStr = Data.substring(i, i + 1);
						// 원하는 길이가 됐을때
						if (cursor == reqtColcmn)
						{
							addStr += "<br>\r";
							cursor = 0;
						}
						cursor++; // 위에 쓰면 첫라인은 1개가 들찍힘
						//30개로 끊을때 한글은 30개,영문은 60개로 끊기위해
						char c = addStr.charAt(0);
						if ((int) Character.toUpperCase(c) >= 65
							&& (int) Character.toUpperCase(c) <= 90)
						{
							engCount++;// 영문2개를 한개로 잡기위해
							if (engCount == 2)
							{
								cursor--;
								engCount = 0;
							}
						}
						newStr += addStr;
					}
				}
				else
				{//<br>\r이 있을때
					int cursor = 0;
					int engCount = 0;
					String currStr = "";
					for (int i = 0; i < len; i++)
					{
						if (len - i > 5)
						currStr = Data.substring(i, i + 5);
						else
						currStr = Data.substring(i, len);
						String addStr = "";
						if (currStr.toLowerCase().equals("<br>\r"))
						{//<br>이 있을때
							addStr = currStr;
							i += 4;
							cursor = 0;//cursor도중에 <br>\r있으면 0으로 초기화
						}
						else
						{//<br>이 없을때
							addStr = Data.substring(i, i + 1);
							// 원하는 길이가 됐을때
							if (cursor == reqtColcmn)
							{
								addStr += "<br>\r";
								cursor = 0;
							}
							cursor++; // 위에 쓰면 첫라인은 1개가 들찍힘
							//30개로 끊을때 한글은 30개,영문은 60개로 끊기위해
							char c = addStr.charAt(0);
							if ((int) Character.toUpperCase(c) >= 65
								&& (int) Character.toUpperCase(c) <= 90)
							{
								engCount++;// 영문2개를 한개로 잡기위해
								if (engCount == 2)
								{
									cursor--;
									engCount = 0;
								}
							}
						}// -  br이 있고 없을때
						newStr += addStr;
					}//for
				} //br이 있고 없을때
			}
			else
			{// data길이가 요구 길이보다 작을때
				newStr = Data;
			}
		}
		catch (java.lang.Exception ex)
		{
			return "changeline error :" + ex.getMessage();
		}
		return newStr;
	}

	/**
	 reqtColcmn은 한글기준 (30이면 영문은 60개)
	 <br>
	 <p>
	 \r \n: 길이 1 ,&lt;br&gt;길이 4
	 <p>
	 원하는 길이에서 &lt;br&gt;을 추가해 라인 바꾸기
	 */
	public String changeLineBr(String Data, int reqtColcmn)
	{
		String newStr = "";
		int len = 0;
		if (Data != null ) len = Data.length();
		
		try
		{
			if (Data == null)
			{
				return setSpace(reqtColcmn);
			}
			if (len > reqtColcmn)
			{// data길이가 요구 길이보다 클때
				if (Data.toLowerCase().indexOf("<br>") == -1)
				{//<br>\r이 없을때
					int cursor = 0;
					int engCount = 0;
					for (int i = 0; i < len; i++)
					{
						String addStr = Data.substring(i, i + 1);
						// 원하는 길이가 됐을때
						if (cursor == reqtColcmn)
						{
							addStr += "<br>";
							cursor = 0;
						}
						cursor++; // 위에 쓰면 첫라인은 1개가 들찍힘
						//30개로 끊을때 한글은 30개,영문은 60개로 끊기위해
						char c = addStr.charAt(0);
						if ((int) Character.toUpperCase(c) >= 65
							&& (int) Character.toUpperCase(c) <= 90)
						{
							engCount++;// 영문2개를 한개로 잡기위해
							if (engCount == 2)
							{
								cursor--;
								engCount = 0;
							}
						}
						newStr += addStr;
					}
				}
				else
				{//<br>\r이 있을때
					int cursor = 0;
					int engCount = 0;
					String currStr = "";
					for (int i = 0; i < len; i++)
					{
						if (len - i > 5)
						currStr = Data.substring(i, i + 5);
						else
						currStr = Data.substring(i, len);
						String addStr = "";
						if (currStr.toLowerCase().equals("<br>"))
						{//<br>이 있을때
							addStr = currStr;
							i += 4;
							cursor = 0;//cursor도중에 <br>\r있으면 0으로 초기화
						}
						else
						{//<br>이 없을때
							addStr = Data.substring(i, i + 1);
							// 원하는 길이가 됐을때
							if (cursor == reqtColcmn)
							{
								addStr += "<br>";
								cursor = 0;
							}
							cursor++; // 위에 쓰면 첫라인은 1개가 들찍힘
							//30개로 끊을때 한글은 30개,영문은 60개로 끊기위해
							char c = addStr.charAt(0);
							if ((int) Character.toUpperCase(c) >= 65
								&& (int) Character.toUpperCase(c) <= 90)
							{
								engCount++;// 영문2개를 한개로 잡기위해
								if (engCount == 2)
								{
									cursor--;
									engCount = 0;
								}
							}
						}// -  br이 있고 없을때
						newStr += addStr;
					}//for
				} //br이 있고 없을때
			}
			else
			{// data길이가 요구 길이보다 작을때
				newStr = Data;
			}
		}
		catch (java.lang.Exception ex)
		{
			return "changeline error :" + ex.getMessage();
		}
		return newStr;
	}


	/**
	 blank Check하여 &nbsp; 반환
	 */

	public String checkBlank2Nbsp(String string)
	{
		if (string.length() == 0 || string.equals(""))
		{
			return "&nbsp;";
		}
		else
		{
			return string;
		}
	}

	/**
	 blank Check하여 value 반환
	 */

	public String checkBlank2Value(String string, String value)
	{
		if (string.length() == 0 || string.equals(""))
		{
			return value;
		}
		else
		{
			return string;
		}
	}

	/**
	 NullCheck하여 공백 반환
	 */

	public String checkNull2Blank(String nullString)
	{
		if (nullString == null || nullString.equals("null")
			|| nullString.equals(""))
		{
			return "";
		}
		else
		{
			return nullString;
		}
	}

	public String checkNull2Blank(Object nullString)
	{
		if (nullString == null)
		{
			return "";
		}
		else
		{
			return (String) nullString;
		}
	}

	/**
	 NullCheck하여 - 반환
	 */

	public String checkNull2Dash(String nullString)
	{
		if (nullString == null || nullString.trim().equals("null")
				|| nullString.equals(""))
		{
			return "-";
		}
		else
		{
			return nullString.trim();
		}
	}

	/**
	 Null Check하여 &nbsp; 반환
	 */
	public String checkNull2Space(String nullString)
	{
		if (nullString == null || nullString.equals("null")
			|| nullString.equals(""))
		{
			return "&nbsp;";
		}
		else
		{
			return nullString;
		}
	}

	/**
	 String 배열을 입력받아 NullCheck하여 원하는 값 반환
	 */
	public String checkNull2Value(String[] nullArray, int i, String value)
	{
		if (nullArray == null)
		{
			return value;
		}
		else if (nullArray[i] == null)
		{
			return value;
		} else if (nullArray[i] != null && nullArray[i].equals(""))
		{
			return value;
		} else
		{
			//톰캣 5.0.6 패치가 안되서
			//return toHangul( nullArray[i] );
			return nullArray[i];
		}

	}

	/**
	 NullCheck하여 원하는 값 반환
	 */
	public String checkNull2Value(String nullString, String value)
	{
		if (nullString == null || nullString.equals("null")
				|| nullString.equals(""))
		{
			return value;
		}
		else
		{
			return nullString;
		}
	}

	/**
	 NullCheck하여 "0" 반환
	 */

	public String checkNull2Zero(String nullString)
	{
		if (nullString == null || nullString.equals("null")
				|| nullString.equals(""))
		{
			return "0";
		}
		else
		{
			return nullString;
		}
	}

	/**
	 null이나 빈공백시 0로 setting
	 */

	public String checkReturnZero(String str)
	{
		if (str == null)
			str = "0";
		else if (str.trim().equals("") || str.trim().length() < 1)
			str = "0";
		return str;
	}

	/**
	 디렉토리 만들기 - 상위 디렉토리까지 만들기
	 */
	public String makeDir(String path_file)
	//throws Exception,IOException
	{
		String file_flag = "";
		try {
			File file = new File(path_file);
			if (file.exists()) {
				file_flag = "Y";
			} else {
				boolean _file_flag = file.mkdirs();
				file_flag = "Y";
				//dirs의 이상 : 정상되도 _file_fla = false
				//if(_file_flag== true)       file_flag = "Y";
				//else                        file_flag = "Problem make dir : "+path_file;
			}

		}
		/*
		 catch (IOException e)
		 {
		 file_flag = "Problems IOException make dir : "+e.getMessage();
		 }
		 */
		catch (Exception e) {
			file_flag = "Problems Exception  make dir : " + e.getMessage();
		} finally {

		}
		return file_flag;
	}

	/**
	 디렉토리 만들기 - 상위 디렉토리까지 만들기
	 */

	public String makeDir(String path, String fileName)
	//throws Exception,IOException
	{
		String path_file = "";
		try {
			if (path.endsWith(System.getProperty("file.separator"))) {
				path_file = path + fileName;

			} else {
				path_file = path + System.getProperty("file.separator")
						+ fileName;
			}
		} catch (Exception e) {

		} finally {

		}

		return makeDir(path_file);
	}

	/**
	 디렉토리,파일 존재 여부 확인
	 */

	public boolean existFile(String path_file)
	//throws Exception,IOException
	{
		boolean file_flag = false;
		try {
			File file = new File(path_file);

			if (file.exists())
				file_flag = true;
			else
				file_flag = false;

		} catch (Exception e) {

		} finally {
		}

		return file_flag;

	}

	/**
	 디렉토리,파일 존재 여부 확인
	 */

	public boolean existFile(String path, String fileName)

	//throws Exception,IOException

	{
		/*
		 String path_file  = "";
		 boolean file_flag = false;

		 try
		 {
		 if ( path.endsWith( System.getProperty("file.separator") ))
		 {
		 path_file     = path+fileName;

		 }else
		 {
		 path_file     = path+System.getProperty("file.separator")+fileName;

		 }

		 File file = new File(path_file);


		 if(file.exists())   file_flag = true;
		 else                file_flag = false;

		 } catch(Exception e)
		 {

		 }
		 finally
		 {
		 return file_flag;
		 }
		 */
		String path_file = "";

		try {
			if (path.endsWith(System.getProperty("file.separator"))) {
				path_file = path + fileName;

			} else {
				path_file = path + System.getProperty("file.separator")
						+ fileName;

			}

		} catch (Exception e) {

		} finally {

		}
		return existFile(path_file);

	}

	/**
	 디렉토리,파일 존재 여부 확인
	 */

	public boolean existDir(String path)

	//throws Exception,IOException

	{
		/*
		 String path_file  = "";
		 boolean file_flag = false;

		 try
		 {
		 if ( path.endsWith( System.getProperty("file.separator") ))
		 {
		 path_file     = path+fileName;

		 }else
		 {
		 path_file     = path+System.getProperty("file.separator")+fileName;

		 }

		 File file = new File(path_file);


		 if(file.exists())   file_flag = true;
		 else                file_flag = false;

		 } catch(Exception e)
		 {

		 }
		 finally
		 {
		 return file_flag;
		 }
		 */
		boolean file_flag = false;

		String path_file = "";

		try {
			if (!path.endsWith(System.getProperty("file.separator")))
				path += System.getProperty("file.separator");

			File file = new File(path);

			if (file.isDirectory())
				file_flag = true;
			else
				file_flag = false;

		} catch (Exception e) {

		} finally {

		}

		return file_flag;
	}

	/**
	 실제 파일지우기
	 */
	//
	public String runDeleteFile(String path_file)

	{

		String mesg = "";

		File file = new File(path_file);

		if (file.exists())

		{

			if (file.delete()) {
				mesg = "Y";
			}

			else {
				mesg = "File error:" + path_file;
			}

		} else

		{

			mesg = "Not exist File:" + path_file;

		}

		return mesg;

	}

	/**
	 파일지우기
	 */

	public String deleteFile(String path, String fileName)

	{

		String path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

			path_file = path + fileName;

		else

			path_file = path + System.getProperty("file.separator") + fileName;

		return runDeleteFile(path_file);

	}

	/**
	 파일이름 실제로 바꾸기
	 */

	public String runRenameFile(String path_file, String new_path_file) {
		String mesg;

		try

		{

			File file = new File(path_file);

			File newfile = new File(new_path_file);

			//새이름으로 이미 존재 하면 삭제 - 의미 없음

			//if (newfile.exists()) newfile.delete();

			if (file.exists() && file.isFile())

			{

				if (file.renameTo(newfile))

				{

					mesg = "Y";

				}

				else

				{

					mesg = "Rename Error :  Not exist source file(" + path_file
							+ ") , Exist target file(" + new_path_file + ")";

				}

			} else

			{

				mesg = "Not Exist Source File: " + path_file;

			}

		} catch (Exception e)

		{

			mesg = "Exception: " + e.getMessage()
					+ "<br>Rename Error :  Not exist source file(" + path_file
					+ ") , Exist target file(" + new_path_file + ")";

			//hrow new Exception("Exception: " + e.getMessage());

		}

		finally

		{

		}

		return mesg;

	}

	/**
	 파일이름 바꾸기
	 */

	public String renameFile(String path, String fileName, String newName)

	{

		String path_file = "";

		String new_path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

		{

			path_file = path + fileName;

			new_path_file = path + newName;

		} else

		{

			path_file = path + System.getProperty("file.separator") + fileName;

			new_path_file = path + System.getProperty("file.separator")
					+ newName;

		}

		return runRenameFile(path_file, new_path_file);

	}

	/**
	 디렉토리및 파일이름 바꾸기
	 */

	public String renameFile(String path, String fileName, String newPath,
			String newName)

	{

		String path_file = "";

		String new_path_file = "";

		if (path.endsWith(System.getProperty("file.separator"))) {
			path_file = path + fileName;
		} else

		{
			path_file = path + System.getProperty("file.separator") + fileName;
		}

		if (newPath.endsWith(System.getProperty("file.separator"))) {
			new_path_file = newPath + newName;
		} else

		{
			new_path_file = newPath + System.getProperty("file.separator")
					+ newName;
		}

		return runRenameFile(path_file, new_path_file);

	}

	/**
	 파일 copy
	 */

	public String copyFile(String read_path_file, String write_path_file)
	//throws IOException
	{

		String rtn = "";

		try

		{

			FileInputStream fis = new FileInputStream(read_path_file);
			InputStream is = fis;

			byte buffer[] = new byte[1024 * 4];

			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			//buffbytes

			int i;
			while ((i = is.read(buffer)) != -1) {

				bytes.write(buffer, 0, i);

			}
			//content.toByteArray()
			//(byte[])hashtable.get("content")

			FileOutputStream fout = new FileOutputStream(write_path_file);
			fout.write(bytes.toByteArray());

			if (fout != null)
				fout.flush();
			if (fout != null)
				fout.close();

			if (fis != null)
				fis.close();

			if (bytes != null)
				bytes.close(); //20041101 sskim

			return "Y";

		}

		catch (IOException e)

		{

			rtn = "Problems reading file" + e.getMessage();

			return rtn;

		}

	}

	/**
	 파일 copy
	 */

	public String copyFile(String path, String fileName, String newPath,
			String newFileName)

	{

		String path_file = "";

		String new_path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

		{
			path_file = path + fileName;

		} else {
			path_file = path + System.getProperty("file.separator") + fileName;
		}

		if (newPath.endsWith(System.getProperty("file.separator"))) {
			new_path_file = newPath + newFileName;

		} else {
			new_path_file = newPath + System.getProperty("file.separator")
					+ newFileName;
		}

		return copyFile(path_file, new_path_file);

	}

	/**
	 확장자에 따른 이미지 뿌리기
	 */

	public String fileImage(String attc_file, String imgPath)

	{

		//확장자

		String file_type = fileType(attc_file).toLowerCase();

		String file_name = "";

		String file_image = "";

		if (attc_file == null)
			return "";

		if (attc_file.length() > 15)
			file_name = attc_file.substring(15);

		else
			file_name = attc_file;

		if (file_type.equals("exe"))

			file_image = "<img src=\"" + imgPath + "k_exe.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("doc"))

			file_image = "<img src=\"" + imgPath + "k_doc.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("gif"))

			file_image = "<img src=\"" + imgPath + "k_gif.gif\" border=0>"
					+ file_name;
		else if (file_type.equals("jpg"))

			file_image = "<img src=\"" + imgPath + "k_jpg.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("htm") || file_type.equals("html"))

			file_image = "<img src=\"" + imgPath + "k_htm.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("ppt"))

			file_image = "<img src=\"" + imgPath + "k_ppt.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("zip") || file_type.equals("arj")
				|| file_type.equals("rar"))

			file_image = "<img src=\"" + imgPath + "k_zip.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("hwp"))

			file_image = "<img src=\"" + imgPath + "k_hwp.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("txt"))

			file_image = "<img src=\"" + imgPath + "k_txt.gif\" border=0>"
					+ file_name;
		else if (file_type.equals("xls"))

			file_image = "<img src=\"" + imgPath + "k_xls.gif\" border=0>"
					+ file_name;

		else if (file_type.equals("null") || file_type.equals(""))

			file_image = "";

		else

			file_image = "<img src=\"" + imgPath + "k_unkn.gif\" border=0>"
					+ file_name;

		return file_image;

	}

	/**
	 확장자 반환
	 */

	public String fileType(String file)

	{

		String fileType = "";

		if (file == null || file.trim().equals("") || file.length() < 1)
			return "";

		int point = file.lastIndexOf('.');

		fileType = file.substring(point + 1);

		return fileType;

	}

	/**
	 원하는 문자로 원하는 갯수(totalLength-data크기)만큼 채워준다
	 <p>
	 반환 크기 data length
	 */

	public String fillChar2String(String str, int totalLength, String fillChar,
			String align)

	{

		if (str == null)

			str = "";

		String strData = "";

		int CheckNum = totalLength - str.length();

		for (int i = 0; i < CheckNum; i++)

			strData += fillChar;

		if (align.toUpperCase().equals("RIGHT"))

			strData = str + strData;

		else

			strData = strData + str;

		return strData;

	}

	/**
	 원하는 문자로 원하는 갯수(totalLength-data크기)만큼 채워준다
	 <p>
	 반환 크기 data length
	 한글을 2바이트로 한다.: c와 동일
	 */

	public String fillChar2String2Hangul(String Data, int totalLength,
			String fillChar, String Align)

	{

		String LeftSpace = "";

		String RightSpace = "";

		String ReturnValue = "";

		//int CheckNum  = totalLength - str.length();

		int CheckNum = 0;

		int SpaceNum = 0;

		try

		{

			if (Data == null) {
				SpaceNum = totalLength;

				return setSpace(SpaceNum);

			}

			// 가져온 데이터가 보여주고자하는 길이보다 클 경우 보여주고자하는 길이만큼 잘라준다.

			if (toCode(Data).length() > totalLength)

			{

				// 잘리는 부분에 한글이 들어가면 그 컬럼 전체가 빠짐

				// 그래서 한글일 경우 그 전에서 자름

				if (Data.length() == toCode(Data).length())

				{

					Data = Data.substring(0, totalLength);

				}

				else

				{

					if (toHangul(
							toCode(Data).substring(totalLength - 1,
									totalLength + 1)).equals(

					toCode(Data).substring(totalLength - 1, totalLength + 1))

					)

					{

						Data = toCode(Data).substring(0, totalLength - 1) + " ";

					}

					else

					{

						Data = toCode(Data).substring(0, totalLength);

					}

					Data = toHangul(Data);

				}

			}

			else

			{

				CheckNum = totalLength - toCode(Data).length();

			}

			int LeftCheckNum = CheckNum / 2;

			int RightCheckNum = CheckNum - LeftCheckNum;

			for (int i = 0; i < LeftCheckNum; i++)

			{

				LeftSpace += fillChar;

			}

			for (int i = 0; i < RightCheckNum; i++)

			{

				RightSpace += fillChar;

			}

			// 왼쪽으로 정렬

			if (Align.toUpperCase().equals("LEFT"))

			{

				ReturnValue = LeftSpace + RightSpace + Data;

			}

			// 오른쪽으로 정렬

			else if (Align.toUpperCase().equals("RIGHT"))

			{

				ReturnValue = Data + LeftSpace + RightSpace;

			}

			// 가운데로 정렬

			else if (Align.toUpperCase().equals("CENTER"))

			{

				ReturnValue = LeftSpace + Data + RightSpace;

			}

		}

		catch (java.lang.Exception ex)

		{

			ReturnValue = Data + ":" + ex.getMessage();

		}

		return ReturnValue;

	}

	/**
	 원하는 문자로 원하는 갯수(totalLength)만큼 채워준다
	 <p>

	 반환 크기 data length +totalLength
	 */

	public String fillChar2StringSumSize(String str, int totalLength,
			String data, String align)

	{

		if (str == null)
			str = "";

		String strData = "";

		for (int i = 0; i < totalLength; i++)

			strData += data;

		if (align.toUpperCase().equals("RIGHT"))

			strData = str + strData;

		else

			strData = strData + str;

		return strData;

	}

	/**
	 좌측정렬    : 문자열+원하는 스페이스크기
	 <p>
	 오른쪽 정렬 : 원하는 스페이스크기 + 문자열
	 <p>
	 중간 정렬   : 원하는 스페이스크기/2 + 문자열 + 원하는 스페이스크기/2
	 <p>
	 반환 크기 SpaceNum
	 */

	public String fillSpace2String(String Data, int SpaceNum, String Align)

	{

		String LeftSpace = "";

		String RightSpace = "";

		String ReturnValue = "";

		int CheckNum = 0;

		try

		{

			if (Data == null)

			{

				return setSpace(SpaceNum);

			}

			// 가져온 데이터가 보여주고자하는 길이보다 클 경우 보여주고자하는 길이만큼 잘라준다.

			if (toCode(Data).length() > SpaceNum)

			{

				// 잘리는 부분에 한글이 들어가면 그 컬럼 전체가 빠짐

				// 그래서 한글일 경우 그 전에서 자름

				if (Data.length() == toCode(Data).length())

				{

					Data = Data.substring(0, SpaceNum);

				}

				else

				{

					if (toHangul(
							toCode(Data).substring(SpaceNum - 1, SpaceNum + 1))
							.equals(

							toCode(Data).substring(SpaceNum - 1, SpaceNum + 1))

					)

					{

						Data = toCode(Data).substring(0, SpaceNum - 1) + " ";

					}

					else

					{

						Data = toCode(Data).substring(0, SpaceNum);

					}

					Data = toHangul(Data);

				}

			}

			else

			{

				CheckNum = SpaceNum - toCode(Data).length();

			}

			int LeftCheckNum = CheckNum / 2;

			int RightCheckNum = CheckNum - LeftCheckNum;

			for (int i = 0; i < LeftCheckNum; i++)

			{

				LeftSpace += " ";

			}

			for (int i = 0; i < RightCheckNum; i++)

			{

				RightSpace += " ";

			}

			// 왼쪽으로 정렬

			if (Align.toUpperCase().equals("LEFT"))

			{

				ReturnValue = Data + LeftSpace + RightSpace;

			}

			// 오른쪽으로 정렬

			else if (Align.toUpperCase().equals("RIGHT"))

			{

				ReturnValue = LeftSpace + RightSpace + Data;

			}

			// 가운데로 정렬

			else if (Align.toUpperCase().equals("CENTER"))

			{

				ReturnValue = LeftSpace + Data + RightSpace;

			}

		}

		catch (java.lang.Exception ex)

		{

			ReturnValue = Data + ":" + ex.getMessage();

		}

		return ReturnValue;

	}

	/**

	 1.23*10E23같이 나오는 결과를 123,323,232,332,232,323,233,232와 같이
	 <p>
	 반올림
	 */

	public String formatNum(double num, int underNum)

	{

		String formats = formatReturn(underNum);

		DecimalFormat df = new DecimalFormat(formats);

		return df.format(num);

	}

	/**

	 1.23*10E23같이 나오는 결과를 123,323,232,332,232,323,233,232와 같이
	 <p>
	 반올림
	 */

	public String formatNum(int num, int underNum)

	{

		String formats = formatReturn(underNum);

		DecimalFormat df = new DecimalFormat(formats);

		return df.format(num);

	}

	/**

	 1.23*10E23같이 나오는 결과를 123,323,232,332,232,323,233,232와 같이
	 <p>
	 반올림
	 */

	public String formatNum(long num, int underNum)

	{

		String formats = formatReturn(underNum);

		DecimalFormat df = new DecimalFormat(formats);

		return df.format(num);

	}

	/**

	 1.23*10E23같이 나오는 결과를 123,323,232,332,232,323,233,232와 같이
	 <p>
	 반올림
	 */

	public String formatNum(String num, int underNum)

	{

		num = removeComma(num);
		double d = 0.0D;

		try

		{

			d = Double.valueOf(num).doubleValue();

		}

		catch (Exception _ex)

		{

			d = 0.0D;

		}

		return formatNum(d, underNum);

	}

	/**

	 format반환
	 <p>

	 */

	public String formatReturn(int underNum)

	{

		String formats = "";

		if (underNum == 0)
			formats = "###,###,###,##0";

		else
			formats = "###,###,###,##0." + setChar(underNum, "0");// 0의 갯수만큼 채운다 ,#:0을 한번만

		return formats;

	}

	/**

	 년월일입력해 월일에 가감하여 년월일 출력
	 <p>

	 */

	public String getChangeBoth(int year, int month, int day, int addmonth,
			int addday)

	{

		GregorianCalendar cal = new GregorianCalendar();

		cal.set(year, month - 1 + addmonth, day + addday);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		return stryear + strmonth + strday;

	}

	/**
	 String 년월일 8자리or10자리 입력해 월일에 가감하여 년월일8자리 출력
	 */

	public String getChangeBoth(String date, int addmonth, int addday)

	{

		GregorianCalendar cal = new GregorianCalendar();

		int year = 0;

		int month = 0;

		int day = 0;

		if (date.length() < 8)
			return "";

		int pointDash = date.lastIndexOf("-");

		int pointSlash = date.lastIndexOf("/");

		if (pointDash == -1 && pointSlash == -1)

		{//20001212입력

			// 년 ,월 ,일

			year = Integer.valueOf(date.substring(0, 4)).intValue();

			month = Integer.valueOf(date.substring(4, 6)).intValue();

			day = Integer.valueOf(date.substring(6, 8)).intValue();

		}

		else

		{//2000-12-12,2000/12/12입력

			// 년 ,월 ,일

			year = Integer.valueOf(date.substring(0, 4)).intValue();

			month = Integer.valueOf(date.substring(5, 7)).intValue();

			day = Integer.valueOf(date.substring(8, 10)).intValue();

		}

		cal.set(year, month - 1 + addmonth, day + addday);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		return stryear + strmonth + strday;

	}

	/**
	 년월일입력해 일에 가감하여 년월일 출력
	 */

	public String getChangeDay(int year, int month, int day, int addday)

	{

		GregorianCalendar cal = new GregorianCalendar();

		cal.set(year, month - 1, day + addday);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		return stryear + strmonth + strday;

	}

	/**
	 String 년월일 8자리or10자리 입력해 일에 가감하여 년월일8자리 출력
	 */

	public static String getChangeDay(String date, int addday)

	{

		GregorianCalendar cal = new GregorianCalendar();

		int year = 0;

		int month = 0;

		int day = 0;

		if (date.length() < 8)
			return "";

		int pointDash = date.lastIndexOf("-");

		int pointSlash = date.lastIndexOf("/");

		if (pointDash == -1 && pointSlash == -1)

		{//20001212입력

			// 년 ,월 ,일

			year = Integer.valueOf(date.substring(0, 4)).intValue();

			month = Integer.valueOf(date.substring(4, 6)).intValue();

			day = Integer.valueOf(date.substring(6, 8)).intValue();

		}

		else

		{//2000-12-12,2000/12/12입력

			// 년 ,월 ,일

			year = Integer.valueOf(date.substring(0, 4)).intValue();

			month = Integer.valueOf(date.substring(5, 7)).intValue();

			day = Integer.valueOf(date.substring(8, 10)).intValue();

		}

		cal.set(year, month - 1, day + addday);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		return stryear + strmonth + strday;

	}

	/**
	 년월일입력해 월에 가감하여 년월일 출력
	 <p>
	 <p>
	 월만 변경을 시켜야 할 경우 아래의 내용은 명확한 차이가 있다
	 <p>
	 즉 현재 날짜가 2000년 12월 30일인 경우
	 <p>
	 getChangeMonth(getCurrDate(),2)    -> 2001년 3월 2일
	 <p>
	 getChangeMonth(getYYYYMM()+"01",2) -> 2001년 2월 1일

	 */

	public String getChangeMonth(int year, int month, int day, int addmonth)

	{

		GregorianCalendar cal = new GregorianCalendar();

		cal.set(year, month - 1 + addmonth, day);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		return stryear + strmonth + strday;

	}

	/**
	 String 년월일 8자리or10자리 입력해 월에 가감하여 년월일8자리 출력
	 */

	public String getChangeMonth(String date, int addmonth)

	{

		GregorianCalendar cal = new GregorianCalendar();

		int year = 0;

		int month = 0;

		int day = 0;

		if (date.length() < 8)
			return "";

		int pointDash = date.lastIndexOf("-");

		int pointSlash = date.lastIndexOf("/");

		if (pointDash == -1 && pointSlash == -1)

		{//20001212입력

			// 년 ,월 ,일

			year = Integer.valueOf(date.substring(0, 4)).intValue();

			month = Integer.valueOf(date.substring(4, 6)).intValue();

			day = Integer.valueOf(date.substring(6, 8)).intValue();

		}

		else

		{//2000-12-12,2000/12/12입력

			// 년 ,월 ,일

			year = Integer.valueOf(date.substring(0, 4)).intValue();

			month = Integer.valueOf(date.substring(5, 7)).intValue();

			day = Integer.valueOf(date.substring(8, 10)).intValue();

		}

		cal.set(year, month - 1 + addmonth, day);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		return stryear + strmonth + strday;

	}

	/**
	 YYYYMMDD 20000427 반환
	 */

	public String getCurrDate()

	{

		GregorianCalendar cal = new GregorianCalendar();

		StringBuffer date = new StringBuffer();

		date.append(cal.get(1));

		if (cal.get(2) < 9)
			date.append('0');

		date.append(cal.get(2) + 1);

		if (cal.get(5) < 10)
			date.append('0');

		date.append(cal.get(5));

		return date.toString();

	}

	/**
	 YYYYMMDDHHmm 200004272109 반환
	 */

	public String getCurrDateTime()

	{

		GregorianCalendar cal = new GregorianCalendar();

		StringBuffer date = new StringBuffer();

		date.append(cal.get(1));

		if (cal.get(2) < 9)
			date.append('0');

		date.append(cal.get(2) + 1);

		if (cal.get(5) < 10)
			date.append('0');

		date.append(cal.get(5));

		if (cal.get(11) < 10)
			date.append('0');

		date.append(cal.get(11));

		if (cal.get(12) < 10)
			date.append('0');

		date.append(cal.get(12));

		return date.toString();

	}

	/**
	 현재 일 반환
	 */

	public String getCurrDay() {

		GregorianCalendar cal = new GregorianCalendar();

		String day = String.valueOf(cal.get(Calendar.DATE)).toString();

		if (day.length() == 1)
			day = "0" + day;

		return day;

	}

	public String getCurrHour() {

		GregorianCalendar cal = new GregorianCalendar();

		String day = String.valueOf(cal.get(Calendar.HOUR_OF_DAY)).toString();

		if (day.length() == 1)
			day = "0" + day;

		return day;
	}

	public String getCurrMinute() {

		GregorianCalendar cal = new GregorianCalendar();

		String day = String.valueOf(cal.get(Calendar.MINUTE)).toString();

		if (day.length() == 1)
			day = "0" + day;

		return day;

	}

	public String getCurrSecond() {

		GregorianCalendar cal = new GregorianCalendar();

		String day = String.valueOf(cal.get(Calendar.SECOND)).toString();

		if (day.length() == 1)
			day = "0" + day;

		return day;

	}

	/**
	 오늘의 요일 가져오기
	 */

	public String getCurrDayOfWeek()

	{

		GregorianCalendar cal = new GregorianCalendar();

		int day = cal.get(Calendar.DAY_OF_WEEK);

		String days = "";

		if (day == 1)
			days = "일";

		else if (day == 2)
			days = "월";

		else if (day == 3)
			days = "화";

		else if (day == 4)
			days = "수";

		else if (day == 5)
			days = "목";

		else if (day == 6)
			days = "금";

		else if (day == 7)
			days = "토";

		return days;

	}

	/**현재 월가져오기
	 */

	public String getCurrMonth()

	{

		GregorianCalendar cal = new GregorianCalendar();

		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);

		if (month.length() == 1)
			month = "0" + month;

		return month;

	}

	/**
	 시:분:초
	 */

	public String getCurrTime()

	{

		GregorianCalendar cal = new GregorianCalendar();

		int hour = cal.get(Calendar.HOUR_OF_DAY);

		int minute = cal.get(Calendar.MINUTE);

		int sec = cal.get(Calendar.SECOND);

		String time = String.valueOf(toLen2(hour)) + ":"
				+ String.valueOf(toLen2(minute)) + ":"
				+ String.valueOf(toLen2(sec)) + "";

		return time;

	}

	/**
	 시:분
	 */

	public String getCurrTimeNoSec()

	{

		GregorianCalendar cal = new GregorianCalendar();

		int hour = cal.get(Calendar.HOUR_OF_DAY);

		int minute = cal.get(Calendar.MINUTE);

		int sec = cal.get(Calendar.SECOND);

		String time = String.valueOf(toLen2(hour)) + ":"
				+ String.valueOf(toLen2(minute));

		return time;

	}

	/**
	 년도 반환
	 */

	public String getCurrYear()

	{

		GregorianCalendar cal = new GregorianCalendar();

		String year = String.valueOf(cal.get(Calendar.YEAR));

		return year;

	}

	/**
	 YYYYMMDDHHmmss 20000427210948 반환
	 */

	public String getDateTimeSec()

	{

		GregorianCalendar cal = new GregorianCalendar();

		StringBuffer date = new StringBuffer();

		//년

		date.append(cal.get(1));

		//월

		if (cal.get(2) < 9)
			date.append('0');

		date.append(cal.get(2) + 1);

		//일

		if (cal.get(5) < 10)
			date.append('0');

		date.append(cal.get(5));

		//시

		if (cal.get(11) < 10)
			date.append('0');

		date.append(cal.get(11));

		//분

		if (cal.get(12) < 10)
			date.append('0');

		date.append(cal.get(12));

		//초

		if (cal.get(13) < 10)
			date.append('0');

		date.append(cal.get(13));

		return date.toString();

	}

	/**현재년월의 마지막날인 yyyymmdd반환
	 */

	public String getEndDate()

	{

		int year = Integer.valueOf(getCurrYear()).intValue();

		int month = Integer.valueOf(getCurrMonth()).intValue();

		return toLen(year, 4) + toLen2(month) + getEndOfMonthDay(year, month);

	}

	/**
	 년월의 마지막날인 yyyymmdd반환
	 */

	public String getEndDate(int year, int month)

	{

		return toLen(year, 4) + toLen2(month)
				+ toLen2(getEndOfMonthDay(year, month));

	}

	/**
	 년월의 마지막날인 yyyymmdd반환
	 */

	public String getEndDate(String date)

	{

		int year = Integer.valueOf(date.substring(0, 4)).intValue();

		int month = Integer.valueOf(date.substring(4, 6)).intValue();

		return toLen(year, 4) + toLen2(month)
				+ toLen2(getEndOfMonthDay(year, month));

	}

	/**
	 마지막날 구하기
	 */

	public int getEndOfMonthDay(int year, int month)

	{

		if (String.valueOf(year).length() != 4
				|| String.valueOf(month).length() < 1
				|| String.valueOf(month).length() > 2)
			return 0;

		int daysList[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 2)

		{

			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))

			{

				return (29);

			}

		}

		return (daysList[month - 1]);

	}

	/**
	 마지막날 구하기
	 */

	public int getEndOfMonthDay(String date)

	{

		int year = Integer.valueOf(date.substring(0, 4)).intValue();

		int month = Integer.valueOf(date.substring(4, 6)).intValue();

		if (String.valueOf(year).length() != 4
				|| String.valueOf(month).length() < 1
				|| String.valueOf(month).length() > 2)
			return 0;

		int daysList[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 2)

		{

			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))

			{

				return (29);

			}

		}

		return (daysList[month - 1]);

	}

	/**
	 년월의 마지막날인 yyyy.mm.dd반환(format)
	 */

	public String getEndDateFormat(String date)

	{

		int year = Integer.valueOf(date.substring(0, 4)).intValue();

		int month = Integer.valueOf(date.substring(4, 6)).intValue();

		return toLen(year, 4) + "." + toLen2(month) + "."
				+ toLen2(getEndOfMonthDay(year, month));

	}

	/**
	 dateTime를 "20020930171807" format형식으로 yyyy.mm.dd PM 05:18:07반환(format)
	 */

	public String getDateTimeFormat(String date)

	{

		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		String time = date.substring(8, 10);
		String min = date.substring(10, 12);
		String sec = date.substring(12, 14);
		String apm = null;

		int times = Integer.valueOf(date.substring(8, 10)).intValue();
		if (times > 12) {
			apm = "PM";
			times = times - 12;
			time = String.valueOf(times);
		} else if (times == 12) {
			apm = "PM";
			times = times;
			time = String.valueOf(times);
		} else {
			apm = "AM";
			times = times;
			time = String.valueOf(times);
		}

		return year + "." + month + "." + day + " " + apm + " " + time + ":"
				+ min + ":" + sec;

	}

	/**
	 2000년04월22일 12:13:23반환
	 */

	public String getFormatCurrDateTime()

	{

		GregorianCalendar cal = new GregorianCalendar();

		int year = cal.get(Calendar.YEAR);

		int month = cal.get(Calendar.MONTH) + 1;

		int day = cal.get(Calendar.DATE);

		int hour = cal.get(Calendar.HOUR_OF_DAY);

		int minute = cal.get(Calendar.MINUTE);

		int sec = cal.get(Calendar.SECOND);

		StringBuffer date = new StringBuffer();

		date.append(year);

		date.append("년");

		date.append(toLen2(month));

		date.append("월");

		date.append(toLen2(day));

		date.append("일");

		date.append(" ");

		date.append(toLen2(hour));

		date.append(":");

		date.append(toLen2(minute));

		date.append(":");

		date.append(toLen2(sec));

		return date.toString();

	}

	/**
	 현재시간을 원하는 형식으로
	 <p>
	 format "YYYYMMDD hh:mm:ss
	 <p>
	 format "YYYYMMDDhhmmssms
	 <p>
	 format "YYYY년 MM월 DD일 hh-mm-ss 등등
	 <p>
	 format YYYY
	 <p>
	 format mm
	 <p>
	 format mm.dd
	 <p>
	 */
	public String getFormatCurrDateTime(String format)

	{
		GregorianCalendar cal = new GregorianCalendar();

		int year = cal.get(Calendar.YEAR);

		int month = cal.get(Calendar.MONTH) + 1;

		int day = cal.get(Calendar.DATE);

		int hour = cal.get(Calendar.HOUR_OF_DAY);

		int minute = cal.get(Calendar.MINUTE);

		int sec = cal.get(Calendar.SECOND);

		int msec = cal.get(Calendar.MILLISECOND);

		int am_pm = cal.get(Calendar.AM_PM);

		format = replaceStringAll(format, "YYYY", toLen(year, 4));

		//format = replaceString( format, "YY", yy );

		format = replaceStringAll(format, "MM", toLen2(month));

		format = replaceStringAll(format, "DD", toLen2(day));

		format = replaceStringAll(format, "hh", toLen2(hour));

		format = replaceStringAll(format, "mm", toLen2(minute));

		format = replaceStringAll(format, "ss", toLen2(sec));

		format = replaceStringAll(format, "ms", toLen2(msec));
		format = replaceStringAll(format, "AM", am_pm == 1 ? "오후" : "오전");

		return format.toString();

	}

	/**
	 data 20030313152350를 14자리로 받아 원하는 형식으로
	 <p>
	 format "YYYYMMDD AM hh:mm:ss
	 <p>
	 format "YYYYMMDDhhmmssms
	 <p>
	 format "YYYY년 MM월 DD일 hh-mm-ss 등등
	 <p>
	 format YYYY
	 <p>
	 format mm
	 <p>
	 format mm.dd
	 <p>
	 */
	public String getFormatDateTime(String data, String format)

	{

		int _year = 0;
		int _month = 0;
		int _day = 0;
		int _hour = 0;
		int _minute = 0;
		int _second = 0;

		if (data.length() == 14) {
			_year = string2Int(data.substring(0, 4));
			_month = string2Int(data.substring(4, 6));
			_day = string2Int(data.substring(6, 8));
			_hour = string2Int(data.substring(8, 10));
			_minute = string2Int(data.substring(10, 12));
			_second = string2Int(data.substring(12, 14));

		} else if (data.length() == 12) {
			_year = string2Int(data.substring(0, 4));
			_month = string2Int(data.substring(4, 6));
			_day = string2Int(data.substring(6, 8));
			_hour = string2Int(data.substring(8, 10));
			_minute = string2Int(data.substring(10, 12));
		} else if (data.length() == 10) {
			_year = string2Int(data.substring(0, 4));
			_month = string2Int(data.substring(4, 6));
			_day = string2Int(data.substring(6, 8));
			_hour = string2Int(data.substring(8, 10));
		} else if (data.length() == 8) {
			_year = string2Int(data.substring(0, 4));
			_month = string2Int(data.substring(4, 6));
			_day = string2Int(data.substring(6, 8));
		} else if (data.length() == 6) {
			_year = string2Int(data.substring(0, 4));
			_month = string2Int(data.substring(4, 6));

		}

		GregorianCalendar cal = new GregorianCalendar(_year, _month - 1, _day,
				_hour, _minute, _second);

		int year = cal.get(Calendar.YEAR);

		int month = cal.get(Calendar.MONTH) + 1;

		int day = cal.get(Calendar.DATE);

		int hour = cal.get(Calendar.HOUR_OF_DAY);

		int minute = cal.get(Calendar.MINUTE);

		int sec = cal.get(Calendar.SECOND);

		int msec = cal.get(Calendar.MILLISECOND);

		int am_pm = cal.get(Calendar.AM_PM);

		format = replaceStringAll(format, "YYYY", toLen(year, 4));

		//format = replaceString( format, "YY", yy );

		format = replaceStringAll(format, "MM", toLen2(month));

		format = replaceStringAll(format, "DD", toLen2(day));

		format = replaceStringAll(format, "hh", toLen2(hour));

		format = replaceStringAll(format, "mm", toLen2(minute));

		format = replaceStringAll(format, "ss", toLen2(sec));

		format = replaceStringAll(format, "ms", toLen2(msec));
		format = replaceStringAll(format, "AM", am_pm == 1 ? "오후" : "오전");

		return format.toString();

	}

	/**
	 date를 format형식으로 yyyy.mm.dd반환(format)
	 */

	public static String getFormatDate(String date)

	{

		String result = "";
		if (!date.equals("")) {
			if (date.length() == 8) {
				String year = date.substring(0, 4);

				String month = date.substring(4, 6);

				String day = date.substring(6, 8);

				result = year + "." + month + "." + day;
			} else if (date.length() == 6) {
				String year = date.substring(0, 4);

				String month = date.substring(4, 6);

				result = year + "." + month;

			} else {
				result = date;
			}

		} else {
			result = date;
		}
		return result;

	}

	public String getDefaultFromDate(String date) {

		String result = "20000101";

		return result;

	}

	/**
	 date를 format형식으로 yyyy.mm.dd을(format)yyyymmdd로 변환
	 */
	public String getNoFormatDate(String date) {

		String result = "";

		result = replaceStringAll(date, ".", "");
		result = replaceStringAll(result, "-", "");
		result = replaceStringAll(result, "/", "");

		return result;

	}

	public String getDefaultToDate(String date) {

		String result = "99990101";

		return result;

	}

	/**
	 년월일을 원하는 형식으로
	 */
	public static String getFormatDate(String date, String flag) {

		String result = "";

		if (date == null)
			return date; //|| (date.length() != 8 && date.length() != 6)

		if (!date.equals(""))

		{

			StringBuffer newDate = new StringBuffer(date);

			if (date.length() == 8)

			{

				if (flag.toUpperCase().equals("KOR"))

				{

					newDate.insert(8, "일");

					newDate.insert(6, "월");

					newDate.insert(4, "년");

				} else {

					newDate.insert(6, flag);

					newDate.insert(4, flag);

				}

			} else if (date.length() == 6)

			{

				if (flag.toUpperCase().equals("KOR"))

				{

					newDate.insert(6, "월");

					newDate.insert(4, "년");

				} else {

					newDate.insert(4, flag);

				}

			}

			else if (date.length() == 4)

			{

				if (flag.toUpperCase().equals("KOR"))

				{

					newDate.insert(4, "일");

					newDate.insert(2, "월");

				} else {

					newDate.insert(2, flag);

				}

			}

			else if (date.length() == 10)

			{

				newDate = new StringBuffer(date);

			}

			else

			{

				//newDate= new StringBuffer( getCurrDate() );

				newDate = new StringBuffer(date);

			}

			result = newDate.toString();

		}

		return result;

	}



	/** Date의 영문 포맷 DD-MON-YYYYY를 YYYYMM 변경
	 cmnJs.js의  getFormatDateDefault2Eng와 연동되서 동작
	 */
	public String getFormatDateEng2Default(String data, String flag) {

		Vector strMonth = new Vector();
		strMonth.addElement("JAN");
		strMonth.addElement("FEB");
		strMonth.addElement("MAR");
		strMonth.addElement("APR");
		strMonth.addElement("MAY");
		strMonth.addElement("JUN");
		strMonth.addElement("JUL");
		strMonth.addElement("AUG");
		strMonth.addElement("SEP");
		strMonth.addElement("OCT");
		strMonth.addElement("NOV");
		strMonth.addElement("DEC");

		data = data.toUpperCase();
		String result = "";

		if (data.length() == 0)
			return data;

		if ( !"".equals(data)  ) {

			if (data.length() == 11) {
				data = replaceStringAll(data, "-", "");

				String day = data.substring(0, 2);
				String month = data.substring(2, 5);
				String year = data.substring(5, 9);

				result = replaceStringAll(flag, "YYYY", year);
				result = replaceStringAll(result, "MM", toLen2(strMonth
						.indexOf(month) + 1));
				result = replaceStringAll(result, "DD", day);

			} else {
				result = data;
			}
		}

		return result;
	}

	/**
	 년월일 8자리or10자리 입력받아 그 차를 일로 표시

	 public long getDiffDay(String from_date,String to_date)
	 */
	public long getSubtractDay(String from_date, String to_date)

	{

		long diff_day = 0; //차를 일로 표시

		// 년 ,월 ,일

		int from_year = 0;

		int from_month = 0;

		int from_day = 0;

		// 년 ,월 ,일

		int to_year = 0;

		int to_month = 0;

		int to_day = 0;

		int pointDash = from_date.lastIndexOf("-");

		int pointSlash = from_date.lastIndexOf("/");

		if (pointDash == -1 && pointSlash == -1)

		{//20001212입력

			// 년 ,월 ,일

			from_year = Integer.valueOf(from_date.substring(0, 4)).intValue();

			from_month = Integer.valueOf(from_date.substring(4, 6)).intValue();

			from_day = Integer.valueOf(from_date.substring(6, 8)).intValue();

		}

		else

		{//2000-12-12,2000/12/12입력

			// 년 ,월 ,일

			from_year = Integer.valueOf(from_date.substring(0, 4)).intValue();

			from_month = Integer.valueOf(from_date.substring(5, 7)).intValue();

			from_day = Integer.valueOf(from_date.substring(8, 10)).intValue();

		}

		pointDash = to_date.lastIndexOf("-");

		pointSlash = to_date.lastIndexOf("/");

		if (pointDash == -1 && pointSlash == -1)

		{//20001212입력

			// 년 ,월 ,일

			to_year = Integer.valueOf(to_date.substring(0, 4)).intValue();

			to_month = Integer.valueOf(to_date.substring(4, 6)).intValue();

			to_day = Integer.valueOf(to_date.substring(6, 8)).intValue();

		}

		else

		{//2000-12-12,2000/12/12입력

			// 년 ,월 ,일

			to_year = Integer.valueOf(to_date.substring(0, 4)).intValue();

			to_month = Integer.valueOf(to_date.substring(5, 7)).intValue();

			to_day = Integer.valueOf(to_date.substring(8, 10)).intValue();

		}

		GregorianCalendar cal_of_start = new GregorianCalendar();

		cal_of_start.set(from_year, from_month - 1, from_day);

		java.util.Date startDate = cal_of_start.getTime();

		GregorianCalendar cal_of_end = new GregorianCalendar();

		cal_of_end.set(to_year, to_month - 1, to_day);

		java.util.Date endDate = cal_of_end.getTime();

		//1970.1.1부터의 초

		long msec1 = startDate.getTime();

		long msec2 = endDate.getTime();

		long msec3 = 0;

		if (msec2 >= msec1)

		{

			msec3 = msec2 - msec1;

			// msec에 해당하는 날짜 수를 계산한다

			diff_day = msec3 / (24 * 60 * 60 * 1000L);

		}

		else

		{

			msec3 = msec1 - msec2;

			// msec에 해당하는 날짜 수를 계산한다

			diff_day = -(msec3 / (24 * 60 * 60 * 1000L));

		}

		//diff_day = msec3/(24 * 60 * 60 * 1000L);

		return diff_day;

	}

	/**
	 년월일 8자리또는 6자리씩을 입력받아 그 월의 차를  표시
	 <p>

	 통상적으로 199903에서 199904의 차는  1개월이지만 여기서는 2개월로
	 <p>

	 년월일 8자리또는 6자리씩을 입력받아 그 월의 차를  표시
	 <p>
	 */

	public int getSubtractMonth(String from_date, String toxx_date)

	throws IOException

	{

		// 뿌려질 결과의 컬럼개수

		int diff_month = 0;

		int END_MONTH = 12;

		int start_mon_of_from_date = 0;//시작년월의 시작월

		int start_mon_of_toxx_date = 0;//끝년월의   시작월

		int loop_of_from_date = 0;//시작년월의  loop 수

		int loop_of_toxx_date = 0;//끝년월의    loop 수

		int loop_of_year = 0;

		//시작 년월의 시작년

		int year_of_from_date = Integer.valueOf(from_date.substring(0, 4))
				.intValue();

		//끝 년월의 끝년

		int year_of_toxx_date = Integer.valueOf(toxx_date.substring(0, 4))
				.intValue();

		int pointDash = 0;

		int pointSlash = 0;

		if (year_of_from_date == year_of_toxx_date)

		{//시작 년과 끝 년이 같을때

			pointDash = from_date.lastIndexOf("-");

			pointSlash = from_date.lastIndexOf("/");

			if (pointDash == -1 && pointSlash == -1)

			{

				start_mon_of_from_date = Integer.valueOf(
						from_date.substring(4, 6)).intValue();

				start_mon_of_toxx_date = Integer.valueOf(
						toxx_date.substring(4, 6)).intValue();

			}

			else

			{

				start_mon_of_from_date = Integer.valueOf(
						from_date.substring(5, 7)).intValue();

				start_mon_of_toxx_date = Integer.valueOf(
						toxx_date.substring(5, 7)).intValue();

			}

			//

			loop_of_from_date = start_mon_of_toxx_date;

			loop_of_toxx_date = 0;

			// 컬럼에 개수 = toxx_date 와 from_date 컬럼 격차 +1

			if (start_mon_of_toxx_date >= start_mon_of_from_date)

				diff_month = (start_mon_of_toxx_date - start_mon_of_from_date + 1);

			else

				diff_month = -(start_mon_of_from_date - start_mon_of_toxx_date + 1);

		}

		else if (year_of_from_date < year_of_toxx_date)

		{

			pointDash = from_date.lastIndexOf("-");

			pointSlash = from_date.lastIndexOf("/");

			if (pointDash == -1 && pointSlash == -1)

			{

				//시작달

				start_mon_of_from_date = Integer.valueOf(
						from_date.substring(4, 6)).intValue();

			}

			else

			{

				start_mon_of_from_date = Integer.valueOf(
						from_date.substring(5, 7)).intValue();

			}

			start_mon_of_toxx_date = 1;

			//

			loop_of_from_date = END_MONTH;

			pointDash = toxx_date.lastIndexOf("-");

			pointSlash = toxx_date.lastIndexOf("/");

			if (pointDash == -1 && pointSlash == -1)

			{

				loop_of_toxx_date = Integer.valueOf(toxx_date.substring(4, 6))
						.intValue();

			}

			else

			{

				loop_of_toxx_date = Integer.valueOf(toxx_date.substring(5, 7))
						.intValue();

			}

			loop_of_year = year_of_toxx_date - year_of_from_date - 1;

			diff_month = (loop_of_from_date - start_mon_of_from_date + 1)
					+ (loop_of_year * END_MONTH)
					+ (loop_of_toxx_date - start_mon_of_toxx_date + 1);

		}

		else if (year_of_from_date > year_of_toxx_date)

		{

			pointDash = from_date.lastIndexOf("-");

			pointSlash = from_date.lastIndexOf("/");

			if (pointDash == -1 && pointSlash == -1)

			{

				//시작달

				start_mon_of_from_date = Integer.valueOf(
						toxx_date.substring(4, 6)).intValue();

				start_mon_of_toxx_date = 1;

				//

				loop_of_from_date = END_MONTH;

				loop_of_toxx_date = Integer.valueOf(from_date.substring(4, 6))
						.intValue();

			}

			else

			{

				//시작달

				start_mon_of_from_date = Integer.valueOf(
						toxx_date.substring(5, 7)).intValue();

				start_mon_of_toxx_date = 1;

				//

				loop_of_from_date = END_MONTH;

				loop_of_toxx_date = Integer.valueOf(from_date.substring(5, 7))
						.intValue();

			}

			loop_of_year = year_of_from_date - year_of_toxx_date - 1;

			diff_month = (loop_of_from_date - start_mon_of_from_date + 1)
					+ (loop_of_year * END_MONTH)
					+ (loop_of_toxx_date - start_mon_of_toxx_date + 1);

			diff_month = -diff_month;

		}

		return diff_month;

	}

	/**
	 사용 주의 사항 년/월/일 미리 체크 요망

	 입력한날의 요일 가져오기
	 */

	public String getWantDayOfWeek(int yyyy, int mm, int dd)

	{

		GregorianCalendar calendar = new GregorianCalendar();

		String days = "";

		int day = 0;

		calendar.set(yyyy, mm - 1, dd);

		day = calendar.get(calendar.DAY_OF_WEEK);

		if (day == 1)
			days = "일";

		else if (day == 2)
			days = "월";

		else if (day == 3)
			days = "화";

		else if (day == 4)
			days = "수";

		else if (day == 5)
			days = "목";

		else if (day == 6)
			days = "금";

		else if (day == 7)
			days = "토";

		return days;

	}

	/**
	 현재 년월 200003반환
	 */

	public String getYYYYMM()

	{

		GregorianCalendar cal = new GregorianCalendar();

		StringBuffer date = new StringBuffer();

		date.append(cal.get(1));

		if (cal.get(2) < 9)
			date.append('0');

		date.append(cal.get(2) + 1);

		return date.toString();

	}

	/**
	 * Date 타입을 전달받아 입력받은 format으로 반환
	 *
	 * @param dDate
	 * @param sFormat (ex. yyyy/MM/dd HH:mm )
	 * @return
	 */
	public String getDateFormat(Date dDate, String sFormat) {

        String rDate = "";

        if(dDate != null){
            SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
            rDate = formatter.format(dDate);
        }

        return rDate;
    }

	/**
	 nareadme.htm입력받아 20000614090248_nareadme.htm반환
	 */

	public String makeFileName(String srcfile)

	{

		StringBuffer filename = new StringBuffer();

		GregorianCalendar cal = new GregorianCalendar();

		//년

		filename.append(cal.get(1));

		//월

		if (cal.get(2) < 9)
			filename.append('0');

		filename.append(cal.get(2) + 1);

		//일

		if (cal.get(5) < 10)
			filename.append('0');

		filename.append(cal.get(5));

		//시

		if (cal.get(11) < 10)
			filename.append('0');

		filename.append(cal.get(11));

		//분

		if (cal.get(12) < 10)
			filename.append('0');

		filename.append(cal.get(12));

		//초

		if (cal.get(13) < 10)
			filename.append('0');

		filename.append(cal.get(13));

		filename.append('_');

		// 파일이름중 공백에 _로

		srcfile = replaceStringAll(srcfile, " ", "_");

		if (srcfile.lastIndexOf("\\") == -1)

			filename.append(srcfile);

		else

			filename.append(srcfile.substring(srcfile.lastIndexOf("\\") + 1));

		try

		{

			return filename.toString();

		}

		catch (Exception e)

		{

			return srcfile + ":" + e.getMessage();

		}

		//return null;

	}

	/**
	 file만들고 output스트림 만들기
	 */

	public PrintWriter openLog(String filename)

	{

		try

		{

			return new PrintWriter(new BufferedWriter(new FileWriter(filename)));

		}

		catch (IOException ie)

		{

			System.err.println("Error:" + ie.toString());

			return null;

		}

		catch (Exception e)

		{

			System.err.println("Error:" + e.toString());

			return null;

		}

	}

	/**
	 file만들고 output스트림 만들기

	 appent가  true시 덧 붙이기 ,false시  덮어쓰기
	 */

	public PrintWriter openLog(String path, String fileName, boolean append)
			throws IOException, Exception {

		String path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

			path_file = path + fileName;

		else

			path_file = path + System.getProperty("file.separator") + fileName;

		try

		{

			return new PrintWriter(

			new BufferedWriter(

			new FileWriter(path_file, append)

			)

			);

		}

		catch (IOException ie)

		{

			System.err.println("Error:" + ie.toString());

			return null;

		}

		catch (Exception e)

		{

			System.err.println("Error:" + e.toString());

			return null;

		}

	}



	/**
	 파일읽기
	 <p>
	 가능      readFile("c:\\","autoexec.bat")
	 <p>
	 가능      readFile("c:\\osdk\\","Ws_ftp.log")
	 <p>
	 불가능    readFile("\\","Ws_ftp.log")
	 <p>
	 불가능    readFile("/","Ws_ftp.log")
	 */

	public String readFile(String path_file)

	{

		StringBuffer ta = new StringBuffer();

		try

		{

			FileReader fr = new FileReader(path_file);

			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null)

			{

				ta.append(line + "\n"); //nt에서 ftp.bat의 line마다 command를 실행할때 문제 발생
				//ta.append(line);//win98의 sql문은 안됨

			}
			if (fr != null)
				fr.close();

		}

		catch (IOException e)

		{

			ta.append("Problems reading file" + e.getMessage());

		}

		return String.valueOf(ta).toString();

	}

	/**
	 파일 읽기
	 <p>
	 사용법
	 <p>
	 가능      readFile("c:\\","autoexec.bat")
	 <p>
	 가능      readFile("c:\\htdocs\\","Ws_ftp.log")
	 <p>
	 불가능    readFile("\\","Ws_ftp.log")
	 <p>
	 불가능    readFile("/","Ws_ftp.log")
	 <p>
	 한 라인씩읽어 \n붙이기
	 Query문 읽기
	 */
	public String readFile(String path, String fileName)

	{

		String path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

			path_file = path + fileName;

		else

			path_file = path + System.getProperty("file.separator") + fileName;

		StringBuffer ta = new StringBuffer();

		try

		{

			FileReader fr = new FileReader(path_file);

			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null)

			{

				ta.append(line + "\n");//nt에서 ftp.bat의 line마다 command를 실행할때 문제 발생
				//ta.append(line);//win98의 sql문은 안됨

			}
			if (fr != null)
				fr.close();

		}

		catch (IOException e)

		{

			ta.append("Problems reading file" + e.getMessage());

		}

		return String.valueOf(ta).toString();

	}

	/**
	 파일읽기
	 <p>
	 가능      readFileOfOriginal("c:\\","autoexec.bat")
	 <p>
	 가능      readFileOfOriginal("c:\\htdocs\\","Ws_ftp.log")
	 <p>
	 불가능    readFileOfOriginal("\\","Ws_ftp.log")
	 <p>
	 불가능    readFileOfOriginal("/","Ws_ftp.log")
	 */
	public String readFileOfOriginal(String path_file)

	{

		File myFile = new File(path_file);

		byte buf[] = new byte[(int) myFile.length()];

		FileInputStream i = null;

		try

		{

			i = new FileInputStream(myFile);

			i.read(buf);

			if (i != null)
				i.close();

		}

		catch (IOException e)

		{

			buf = ("Problems reading file :" + e.getMessage()).getBytes();

			e.printStackTrace();

		}

		return new String(buf);

	}

	/**
	 파일읽기
	 <p>
	 가능      readFileOfOriginal("c:\\","autoexec.bat")
	 <p>
	 가능      readFileOfOriginal("c:\\htdocs\\","Ws_ftp.log")
	 <p>
	 불가능    readFileOfOriginal("\\","Ws_ftp.log")
	 <p>
	 불가능    readFileOfOriginal("/","Ws_ftp.log")
	 <p>
	 한 라인씩읽어 그 모양대로 읽기

	 <p>
	 모양을 그대로 유지 - batch실행(ftp.bat)
	 */
	public String readFileOfOriginal(String path, String fileName)

	{

		String path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

			path_file = path + fileName;

		else

			path_file = path + System.getProperty("file.separator") + fileName;

		File myFile = new File(path_file);

		byte buf[] = new byte[(int) myFile.length()];

		FileInputStream i = null;

		try

		{

			i = new FileInputStream(myFile);

			i.read(buf);

			if (i != null)
				i.close();
			//if(myFile != null) myFile.close();

		}

		catch (IOException e)

		{

			buf = ("Problems reading file" + e.getMessage()).getBytes();

			e.printStackTrace();

		}

		return new String(buf);

	}

	/**
	 가능      readFileOfUrl("http://localhost:8080/test/","autoexec.bat")
	 <p>
	 화일 읽기
	 */
	public String readFileOfUrl(String path, String fileName)
			throws IOException

	{

		String path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

			path_file = path + fileName;

		else

			path_file = path + System.getProperty("file.separator") + fileName;

		String rtn = "";

		try

		{

			StringBuffer rtnBuffer = new StringBuffer();

			URLConnection LinkPage = new URL(path_file).openConnection();

			LinkPage.connect();

			BufferedReader Data = new BufferedReader(new InputStreamReader(
					LinkPage.getInputStream()));

			while ((rtn = Data.readLine()) != null)

				//rtnBuffer.append(rtn + "\n");//nt에서 ftp.bat의 line마다 command를 실행할때 문제 발생
				rtnBuffer.append(rtn);

			return rtnBuffer.toString();

		}

		catch (IOException e)

		{

			rtn = "Problems reading file" + e.getMessage();

			return rtn;

		}

	}

	/**
	 다른 서버 : 화일 읽기
	 */
	public String readURLFile(String protocol, String host, int port,
			String path, String file) {
		String path_file = "";

		//System.getProperty("file.separator") =="\"는 안됨 - url통신이므로
		if (path.endsWith("/"))
			path_file = path + file;
		else
			path_file = path + "/" + file;

		//URL구성
		String urlString = protocol + "://" + host + ":" + port + "/"
				+ path_file;

		return readURLFile(urlString);
	}

	/**
	 다른 서버 : 화일 읽기
	 */
	public String readURLFile(String protocol, String host, String path,
			String file) {
		String path_file = "";
		//System.getProperty("file.separator") =="\"는 안됨 - url통신이므로
		if (path.endsWith("/"))
			path_file = path + file;
		else
			path_file = path + "/" + file;

		//URL구성
		String urlString = protocol + "://" + host + "/" + path_file;

		return readURLFile(urlString);
	}

	/**
	 다른 서버 : 화일 읽기
	 <p>
	 new URL(String protocol,String host,int port, String path, String file )
	 <p>
	 new URL(String protocol,String host,String path, String file )
	 <p>
	 new URL(String urlString )
	 <p>
	 */
	public String readURLFile(String urlString)
	//throws IOException
	{

		String rtn = "";

		try

		{

			StringBuffer rtnBuffer = new StringBuffer();

			//          URLConnection   LinkPage = new URL(path_file).openConnection();
			//          LinkPage.connect();

			URL url = new URL(urlString);

			InputStream is = url.openStream();
			byte buffer[] = new byte[1024];
			int i;
			while ((i = is.read(buffer)) != -1) {

				rtn = new String(buffer, 0, i);
				//rtn = new String(buffer);
				//rtnBuffer.append(rtn + "\n");////nt에서 ftp.bat의 line마다 command를 실행할때 문제 발생
				rtnBuffer.append(rtn);

			}

			if (is != null)
				is.close(); //20041101 sskim

			return rtnBuffer.toString();

		}

		catch (IOException e)

		{

			rtn = "Problems reading file" + e.getMessage();

			return rtn;

		}

	}

	/**
	 다른 서버 : 화일 읽어서 서버에 저장
	 */
	public String writeURLFile(String protocol, String host, int port,
			String path, String file, String save_path, String save_file)

	{
		String path_file = "";

		//System.getProperty("file.separator") =="\"는 안됨 - url통신이므로
		if (path.endsWith("/"))
			path_file = path + file;
		else
			path_file = path + "/" + file;

		//URL구성
		String urlString = protocol + "://" + host + ":" + port + "/"
				+ path_file;

		//저장될 path및 file
		String save_path_file = "";

		if (save_path.endsWith(System.getProperty("file.separator")))
			save_path_file = save_path + save_file;
		else
			save_path_file = save_path + System.getProperty("file.separator")
					+ save_file;

		return writeURLFile(urlString, save_path_file);
	}

	/**
	 다른 서버 : 화일 읽어서 서버에 저장
	 */
	public String writeURLFile(String protocol, String host, String path,
			String file, String save_path, String save_file) {
		String path_file = "";
		//System.getProperty("file.separator") =="\"는 안됨 - url통신이므로
		if (path.endsWith("/"))
			path_file = path + file;
		else
			path_file = path + "/" + file;

		//URL구성
		String urlString = protocol + "://" + host + "/" + path_file;

		//저장될 path및 file
		String save_path_file = "";
		if (save_path.endsWith(System.getProperty("file.separator")))
			save_path_file = save_path + save_file;
		else
			save_path_file = save_path + System.getProperty("file.separator")
					+ save_file;

		return writeURLFile(urlString, save_path_file);
	}

	/**
	 다른 서버 : 화일 읽어서 서버에 저장
	 */

	public String writeURLFile(String urlString, String path_file)
	//throws IOException
	{

		String rtn = "";

		try

		{

			URL url = new URL(urlString);

			InputStream is = url.openStream();
			byte buffer[] = new byte[1024 * 4];

			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			//buffbytes

			int i;
			while ((i = is.read(buffer)) != -1) {

				bytes.write(buffer, 0, i);

			}
			//content.toByteArray()
			//(byte[])hashtable.get("content")

			FileOutputStream fout = new FileOutputStream(path_file);
			fout.write(bytes.toByteArray());
			if (fout != null)
				fout.flush();
			if (fout != null)
				fout.close();

			if (is != null)
				is.close(); //20041101 sskim
			if (bytes != null)
				bytes.close(); //20041101 sskim

			return "Y";

		}

		catch (IOException e)

		{

			rtn = "Problems reading file" + e.getMessage();

			return rtn;

		}

	}

	public long getURLFileSize(String urlString)
	//throws IOException
	{
		long size = 0;

		String rtn = "";

		try

		{

			URL url = new URL(urlString);

			InputStream is = url.openStream();
			byte buffer[] = new byte[1024 * 4];

			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			//buffbytes

			int i;
			while ((i = is.read(buffer)) != -1) {

				bytes.write(buffer, 0, i);

			}

			if (bytes != null)
				size = bytes.size();
			else
				size = 0;
			//content.toByteArray()
			//(byte[])hashtable.get("content")

			if (bytes != null)
				bytes.close(); //20041101 sskim

		}

		catch (IOException e)

		{

			size = -991;

		}

		return size;

	}

	/**
	 //원하는 문자 삭제
	 */

	public String removeChar(String str, String deli)

	{

		String result = "";

		if (!str.equals(""))

		{

			StringTokenizer st = new StringTokenizer(str, deli);

			StringBuffer buffer = new StringBuffer();

			for (; st.hasMoreTokens(); buffer.append(st.nextToken()))
				;

			result = buffer.toString();

		}

		return result;

	}

	/**
	 comma제거
	 */

	public String removeComma(String s)

	{

		if (s == null)
			return null;

		if (s.indexOf(",") != -1)

		{

			StringBuffer buf = new StringBuffer();

			for (int i = 0; i < s.length(); i++) {

				char c = s.charAt(i);

				if (c != ',')
					buf.append(c);

			}

			return buf.toString();

		}

		return s;

	}

	/**
	 점이하 제거
	 */

	public String removePoint(String str)

	{

		int pt_index = 0;

		if (str == null || str == "" || str.length() < 1)
			return "";

		pt_index = str.indexOf('.');

		if (pt_index == -1)
			return str;

		str = str.substring(0, pt_index);

		return str;

	}

	/**
	 makefilename중 실제 파일명가져오기

	 20000614090248_nareadme.htm중nareadme.htm
	 */

	public String removeTimestamp(String TimestampedFilename)

	{
		if (TimestampedFilename == null)
			return "";
		return TimestampedFilename
				.substring(TimestampedFilename.indexOf("_") + 1);

	}

	/**
	 마지막으로 일치하는 문자열 교체
	 */

	public static String replace(String source, String patt, String rep)

	{

		int pos = source.lastIndexOf(patt);

		if (pos != -1)

		{

			return source.substring(0, pos) + rep
					+ source.substring(pos + patt.length());

		} else
			return source;

	}

	/**
	 특수문자를  임의의 기호로
	 */

	public String replaceCode(String orgstr)

	{

		int len = orgstr.length();

		String rplStr = "";

		String currStr = "";

		String replaceStr = "";

		int i = 0;

		for (i = 0; i < len; i++)

		{

			currStr = orgstr.substring(i, i + 1);

			if (currStr.equals("\""))

			{

				rplStr = "##34";

			} else if (currStr.equals("\'"))

			{

				rplStr = "##39";

			} else if (currStr.equals(">"))

			{

				rplStr = "##60";

			} else if (currStr.equals("<"))

			{

				rplStr = "##62";

			} else if (currStr.equals("/"))

			{

				rplStr = "##47";

			} else if (currStr.equals("\\"))

			{

				rplStr = "##92";

			} else if (currStr.equals("("))

			{

				rplStr = "##40";

			} else if (currStr.equals(")"))

			{

				rplStr = "##41";

			} else if (currStr.equals(","))

			{

				rplStr = "##44";

			} else

			{

				rplStr = currStr;

			}

			replaceStr += rplStr;

		}

		return replaceStr;

		/* 개행문자도 가능

		 }else     if( currStr.equals("\n"))

		 {

		 rplStr = "##10";

		 */

	}

	/**
	 임의의 기호를 특수문자로
	 */

	public String replaceSign(String orgstr)

	{

		int len = orgstr.length();

		String rplStr = "";

		String currChrs = "";

		String currStr = "";

		String replaceStr = "";

		int j = 0, i = 0;

		boolean flag = true;

		for (i = 0; i < len; i++)

		{

			currChrs = orgstr.substring(i, i + 1);

			flag = true;

			j = i;

			if (len - j >= 4)
				currStr = orgstr.substring(j, j + 4);

			else
				currStr = orgstr.substring(j, j + 1);

			if (currStr.equals("##34"))

			{

				rplStr = "\"";

			} else if (currStr.equals("##39"))

			{

				rplStr = "\'";

			} else if (currStr.equals("##60"))

			{

				rplStr = ">";

			} else if (currStr.equals("##62"))

			{

				rplStr = "<";

			} else if (currStr.equals("##47"))

			{

				rplStr = "/";

			} else if (currStr.equals("##92"))

			{

				rplStr = "\\";

			} else if (currStr.equals("##40"))

			{

				rplStr = "(";

			} else if (currStr.equals("##41"))

			{

				rplStr = ")";

			} else if (currStr.equals("##44"))

			{

				rplStr = ",";

			} else

			{

				rplStr = currChrs;

				flag = false;

			}

			replaceStr += rplStr;

			if (flag == true)

			{

				i = i + 3;

			}

		}

		return replaceStr;

		/*개행문자도 가능



		 }else     if( currStr.equals("##10"))

		 {

		 rplStr = "";

		 */

	}

	/**
	 임의의 기호를 특수문자로
	 */
	public String replaceSignSave(String orgstr)

	{

		// pos = 0;

		int len = orgstr.length();

		String rplStr = "";

		String currChrs = "";

		String currStr = "";

		String replaceStr = "";

		int j = 0, i = 0;

		boolean flag = true;

		for (i = 0; i < len; i++)

		{

			currChrs = orgstr.substring(i, i + 1);

			flag = true;

			j = i;

			if (len - j >= 4)
				currStr = orgstr.substring(j, j + 4);

			else
				currStr = orgstr.substring(j, j + 1);

			if (currStr.equals("##34"))

			{

				rplStr = "\"";

			} else if (currStr.equals("##39"))

			{

				rplStr = "\'";

			} else if (currStr.equals("##60"))

			{

				rplStr = ">";

			} else if (currStr.equals("##62"))

			{

				rplStr = "<";

			} else if (currStr.equals("##47"))

			{

				rplStr = "/";

			} else if (currStr.equals("##92"))

			{

				rplStr = "\\";

			} else if (currStr.equals("##10"))

			{

				rplStr = "";

			} else if (currStr.equals("##40"))

			{

				rplStr = "(";

			} else if (currStr.equals("##41"))

			{

				rplStr = ")";

			} else if (currStr.equals("##44"))

			{

				rplStr = ",";

			} else

			{

				rplStr = currChrs;

				flag = false;

			}

			replaceStr += rplStr;

			if (flag == true)

			{

				i = i + 3;

			}

		}

		return replaceStr;

	}

	/**
	 처음으로 일치하는 문자열 교체
	 */

	public String replaceFirst(String source, String patt, String rep)

	{

		int pos = source.indexOf(patt);

		if (pos != -1)

		{

			return source.substring(0, pos) + rep
					+ source.substring(pos + patt.length());

		} else
			return source;

	}

	/**
	 마지막으로 일치하는 문자열 교체
	 */

	public static String replaceLast(String source, String patt, String rep)

	{

		int pos = source.lastIndexOf(patt);

		if (pos != -1)

		{

			return source.substring(0, pos) + rep
					+ source.substring(pos + patt.length());

		} else
			return source;

	}

	/**
	 String Replace 시킴 - 문자열의 변환(전체문자열에는 영향없음)
	 */

	public String replaceStringAll(String in, String find, String replace)

	{
		if (in == null) {
			return "";
		}

		String source = in;
		String keyStr = find;
		String toStr = replace;

		int startIndex = 0;
		int curIndex = 0;
		StringBuffer result = new StringBuffer();

		while ((curIndex = source.indexOf(keyStr, startIndex)) >= 0) {
			result.append(source.substring(startIndex, curIndex)).append(toStr);
			startIndex = curIndex + keyStr.length();
		}

		if (startIndex <= source.length()) {
			result.append(source.substring(startIndex, source.length()));
		}

		return result.toString();

		/*
		 String data  = in ;

		 int    pos  = 0 ;

		 int    npos = in.indexOf (find , pos);



		 while ( npos >= 0 )

		 {

		 data = data.substring ( 0 , npos ) + replace + data.substring (npos + find.length(),data.length());

		 pos = npos + find.length();

		 npos = data.indexOf (find, pos);

		 }
		 return data;
		 */

	}

	// 문자열의 변환(전체문자열에는 영향없음)

	public String replaceStringAll2(String Source, String FindText,
			String replace)

	{

		String rtnSource = "";

		String rtnCheck = "";

		int FindLength = Source.length();

		// 찾을 문자 길이

		int FindTextLength = FindText.length();

		char SourceArrayChar[] = new char[FindLength];

		SourceArrayChar = Source.toCharArray();

		for (int i = 0; i < FindLength; i++)

		{

			rtnCheck = Source.substring(i, i + 1);

			int j = i;

			if (FindLength - j >= FindTextLength)
				rtnCheck = Source.substring(j, j + FindTextLength);

			else
				rtnCheck = Source.substring(j, j + 1);

			if (rtnCheck.equals(FindText))

			{

				rtnSource += replace;

				i += (FindTextLength - 1);

			}

			else

			{

				rtnSource += SourceArrayChar[i];

			}

		}

		return rtnSource;

	}

	/**
	 문자열의 색깔변환(전체문자열에는 영향없음)
	 */

	public String replaceStringColor(String Source, String FindText,
			String Color, String replaceFlag)

	{

		String rtnSource = "";

		String rtnCheck = "";

		int FindLength = Source.length();

		int FindIndex = Source.indexOf(FindText);

		// 찾을 문자 길이

		int FindTextLength = FindText.length();

		char SourceArrayChar[] = new char[FindLength];

		SourceArrayChar = Source.toCharArray();

		for (int i = 0; i < FindLength; i++)

		{

			rtnCheck = Source.substring(i, i + 1);

			int j = i;

			if (FindLength - j >= FindTextLength)
				rtnCheck = Source.substring(j, j + FindTextLength);

			else
				rtnCheck = Source.substring(j, j + 1);

			if (replaceFlag.equals("BOTH"))

			{// 대문자이건 소문자이건

				if (rtnCheck.equals(FindText.toUpperCase())
						|| rtnCheck.equals(FindText.toLowerCase()))

				{

					rtnSource += "<font color='" + Color + "'>" + rtnCheck
							+ "</font>";

					i += (FindTextLength - 1);

				}

				else

				{

					rtnSource += SourceArrayChar[i];

				}

			}

			else

			{//일치하는 것만 - ONLY

				if (rtnCheck.equals(FindText))

				{

					rtnSource += "<font color='" + Color + "'>" + rtnCheck
							+ "</font>";

					i += (FindTextLength - 1);

				}

				else

				{

					rtnSource += SourceArrayChar[i];

				}

			}

		}

		return rtnSource;

	}

	/**
	 소수 반올림
	 <p>
	 <xmp>
	 double round = Math.pow(10,pntLen);

	 newDoub = Math.floor(doub * round +.5)/round;

	 즉

	 소수 2째자리 반올림은

	 y= Math.floor(x*100+.5)/100;

	 즉
	 double round = Math.pow(10,underNum);
	 double newDoub = Math.floor(num * round +.5)/round;
	 이 로직은 일정자리수 넘어가면 4.7*E07식으로 나옴
	 </xmp>

	 */

	public double roundDouble(double num, int underNum)

	{

		/*

		 double round = Math.pow(10,underNum);

		 double newDoub = Math.floor(num * round +.5)/round;

		 */

		double newDoub = 0.0d;

		try

		{

			String formats = formatReturn(underNum);

			DecimalFormat df = new DecimalFormat(formats);

			newDoub = Double.valueOf(df.format(num)).doubleValue();

		}

		catch (Exception _ex)

		{

			newDoub = 0.0D;

		}

		return newDoub;

	}

	/**
	 소수 반올림
	 <p>
	 <xmp>
	 double round = Math.pow(10,pntLen);

	 newDoub = Math.floor(doub * round +.5)/round;

	 즉

	 소수 2째자리 반올림은

	 y= Math.floor(x*100+.5)/100;

	 즉
	 double round = Math.pow(10,underNum);
	 double newDoub = Math.floor(num * round +.5)/round;
	 이 로직은 일정자리수 넘어가면 4.7*E07식으로 나옴
	 </xmp>

	 */

	public double roundDouble(String doub, int pntLen)

	{

		double d = 0.0D;

		try

		{

			d = Double.valueOf(doub).doubleValue();

		}

		catch (Exception _ex)

		{

			d = 0.0D;

		}

		return roundDouble(d, pntLen);

	}

	/**
	 byte단위로 넘어온 data를 서버에 저장

	 파일의 실제 저장
	 */

	public String saveFile(String path, String file, byte bytes[])

	{

		String fileName = "";

		fileName = makeFileName(file);

		String path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

			path_file = path + fileName;

		else

			path_file = path + System.getProperty("file.separator") + fileName;

		try

		{

			FileOutputStream fout = new FileOutputStream(path_file);

			fout.write(bytes);

			if (fout != null)
				fout.flush();

			if (fout != null)
				fout.close();

			return fileName;

		}

		catch (Exception e)

		{

			return path_file + " : " + e.getMessage();

			//e.printStackTrace();

		}

		//return null;

	}

	/**
	 String단위로 넘어온 data를 서버에 저장

	 파일의 실제 저장
	 */

	public String saveFile(String path, String file, String data) {
		String mesg = "";
		File files = null;

		try {

			mesg = makeDir(path);
			if (mesg.equals("Y")) {//디렉토리 있으면
				//common파일 생성
				files = new File(path, file);
				files.createNewFile();

				if (!files.isFile()) {
					mesg = "파일을 생성하는데 실패하였습니다.(file.isFile)";
				} else {
					//=== create에 파일 생성
					BufferedWriter out = new BufferedWriter(new FileWriter(
							files));
					out.write(data);
					out.close();

					mesg = "Y";
				}
			}
		} catch (IOException ie) {
			mesg = "IOException: " + ie.getMessage()
					+ "<br>saveFile Error :  (" + path + "," + file + ") ";

		} catch (Exception fe) {
			mesg = "Exception: " + fe.getMessage() + "<br>saveFile Error :  ("
					+ path + "," + file + ") ";

		} finally {
		}
		return mesg;

		/*
		 String path_file =  "";

		 if ( path.endsWith( System.getProperty("file.separator") ))

		 path_file = path+fileName;

		 else

		 path_file = path+System.getProperty("file.separator")+fileName;

		 return path_file+" : "+e.getMessage();
		 */

		//e.printStackTrace();

	}

	/**원하는 사이즈만큼의 char넣기
	 */

	public String setChar(int loopNum, String fillChar)

	{

		String rtn = "";

		for (int i = 0; i < loopNum; i++)

			rtn = rtn + fillChar;

		return rtn;

	}

	/**comma세팅
	 */

	public String setComma(String data)

	{

		if (data == null || data.trim().length() == 0)
			return "";

		data = data.trim();

		data = removeComma(data);

		//.이하를 제외한 길이

		int strLen = 0;

		// -가 있으면 잘라냈다  반환시 붙여 준다

		int dash = data.indexOf('-');

		// - 의 위치를 못 찾았으면

		if (dash == -1)
			data = data;

		else
			data = data.substring(dash + 1);

		// .가 있으면 반환시 붙여 준다

		int point = data.indexOf('.');

		if (point == -1)
			strLen = data.length();

		else
			strLen = point;

		int underNum = 0;//data.length() - point;

		double d = 0.0D;

		try

		{

			d = Double.valueOf(data.substring(0, strLen)).doubleValue();

		}

		catch (Exception _ex)

		{

			d = 0.0D;

		}

		String formats = "";

		if (underNum == 0)
			formats = "###,###,###,###";

		else
			formats = "###,###,###,###." + setChar(underNum, "#");

		DecimalFormat df = new DecimalFormat(formats);

		String commaStr = df.format(d);

		// 소숫점이하 값이 있으면 마지막에 붙여 준다.

		if (point > -1)
			commaStr += data.substring(point, data.length());

		// - 가 있으면 앞에 붙여준다

		if (dash > -1)
			commaStr = '-' + commaStr;

		return commaStr;

	}

	/**comma세팅
	 */

	public String setComma(double num)

	{

		String data = "";

		try

		{

			data = String.valueOf(num).toString();

		}

		catch (Exception _ex)

		{

			data = "0.00";

		}

		return setComma(data);

	}

	/**
	 comma세팅
	 */

	public String setComma(long num)

	{

		String data = "";

		try

		{

			data = String.valueOf(num).toString();

		}

		catch (Exception _ex)

		{

			data = "0";

		}

		return setComma(data);

	}

	/**comma세팅
	 */

	public String setComma(int num)

	{

		String data = "";

		try

		{

			data = String.valueOf(num).toString();

		}

		catch (Exception _ex)

		{

			data = "0";

		}

		return setComma(data);

	}

	/*
	 jsp에서 쓰기위한 out만들기
	 public  void  setPrintWriter(ServletResponse res)

	 throws ServletException

	 {

	 try {

	 this.out = new java.io.PrintWriter(res.getOutputStream(),true);//false - 에러

	 //  this.out = res.getWriter();

	 } catch(Throwable e) {



	 }



	 }

	 */


	/**
	 /원하는 사이즈만큼의 space넣기
	 */

	public String setSpace(int loopNum)

	{

		String rtn = "";

		for (int i = 0; i < loopNum; i++)

			rtn = rtn + " ";

		return rtn;

	}

	/**
	 주어진 문자열(str)을 double 형 숫자로 변환한다.
	 <p>
	 단, 숫자 문자열로서 유효하지 않은 문자열은 0 을 리턴한다.
	 */

	public double string2Double(String str)

	{
		str = removeComma(str);

		double ret = 0.0;

		try {

			ret = new Double(str).doubleValue();

		}

		catch (Exception e) {

			return 0.0;

		}

		return ret;

	}

	/**
	 주어진 문자열(str)을 Integer 형 숫자로 변환한다.
	 <p>
	 단, 숫자 문자열로서 유효하지 않은 문자열은 0 을 리턴한다.
	 */

	public int string2Int(String str)

	{
		str = removeComma(str);

		int ret = 0;

		try {

			if (str == null || str.trim().length() == 0)
				return 0;

			int position = str.indexOf(".");

			switch (position) {

			case -1:

				ret = new Integer(str).intValue();

				break;

			case 0:

				ret = 0;

				break;

			default:

				ret = new Integer(str.substring(0, position)).intValue();

			}

		}

		catch (Exception e) {

			return 0;

		}

		return ret;

	}

	/**
	 주어진 문자열(str)을 long 형 숫자로 변환한다.
	 <p>
	 단, 숫자 문자열로서 유효하지 않은 문자열은 0 을 리턴한다.
	 */

	public long string2Long(String str)

	{
		str = removeComma(str);

		long ret = 0;

		try {

			if (str == null || str.trim().length() == 0)
				return 0;

			int position = str.indexOf(".");

			switch (position) {

			case -1:

				ret = new Long(str).longValue();

				break;

			case 0:

				ret = 0;

				break;

			default:

				ret = new Long(str.substring(0, position)).longValue();

			}

		}

		catch (Exception e) {

			return 0;

		}

		return ret;

	}

	/**
	 한글을 일반코드로 바꾸기 위해서
	 */

	public String toCode(String kscode)// throws UnsupportedEncodingException

	{

		if (kscode == null)
			return null;

		String data = "";

		try

		{

			data = new String(kscode.getBytes("KSC5601"), "8859_1");

		}

		catch (UnsupportedEncodingException e)

		{

			data = kscode + " : " + e.getMessage();

		}

		catch (Exception e)

		{

			data = kscode + " : " + e.getMessage();

		}

		return data;

	}

	/**
	 유니코드를 한글로
	 */

	public String toHangul(String str) //throws java.io.UnsupportedEncodingException

	{

		if (str == null)
			return null;

		String data = "";

		try

		{

			data = new String(str.getBytes("ISO-8859-1"), "EUC-KR");

		}

		catch (UnsupportedEncodingException e)

		{

			data = str + " : " + e.getMessage();

		}

		catch (Exception e)

		{

			data = str + " : " + e.getMessage();

		}

		return data;

		//한글인데 한번 더 쓰면 ??? 표시

	}

	/**
	 한글을 유니코드
	 */

	public String toIso(String str) //throws java.io.UnsupportedEncodingException

	{

		if (str == null)
			return null;

		String data = "";

		try

		{

			data = new String(str.getBytes("EUC-KR"), "ISO-8859-1");

		}

		catch (UnsupportedEncodingException e)

		{

			data = str + " : " + e.getMessage();

		}

		catch (Exception e)

		{

			data = str + " : " + e.getMessage();

		}

		return data;

		//한글인데 한번 더 쓰면 ??? 표시

	}

	/**
	 length크기에 맞추어 0을 붙여 반환
	 */

	public String toLen(int nums, int length)

	{

		String num = String.valueOf(nums).toString();

		int space = length - num.length();

		int i = 0;

		String buf = "";

		for (i = 0; i < space; i++)

			buf += "0";

		num = buf + num;

		return num;

	}

	/**
	 2->02
	 */

	public static String toLen2(int nums)

	{

		String num = String.valueOf(nums).toString();

		if (num.length() == 1)

			num = "0" + num;

		return num;

	}

	/**
	 실제로 파일에 쓰기
	 */

	public void writeLog(String string, PrintWriter log)

	{

		if (log == null)
			return;

		// writing operation

		log.println(string);

		// print() method never throws IOException,

		// so we should check error while printing

		if (log.checkError())

		{

			System.err.println("File write error.");

		}

	}

	/**
	 실제로 파일에 쓰기
	 */

	public void writeLog(String string, PrintWriter log, boolean isEnter)

	{

		if (log == null)
			return;

		// writing operation
		if (isEnter)
			log.println(string);
		else
			log.print(string);

		// print() method never throws IOException,

		// so we should check error while printing

		if (log.checkError())

		{

			System.err.println("File write error.");

		}

	}

	/**
	 Vector에 있는 내용을 화일에 기록
	 */
	public String writeOfVector(String real_path, String filename,
			Vector messages, boolean flag) {
		long getErrorCode = 0;
		String getMessage = "";

		try {

			PrintWriter outfile = openLog(real_path, filename, flag);//false면 다시 작성

			int size = messages.size();
			if (size != 0) {
				for (int i = 0; i < size; i++) {
					Object object = messages.elementAt(i);
					if (object instanceof java.lang.String) {
						writeLog((String) object, outfile);
					}
					/*
					 else  if (  object instanceof cmn.SQLent   )
					 {
					 cmn.SQLent sqlEntity = (cmn.SQLent)object;
					 writeLog(sqlEntity.getMessage,outfile);
					 }
					 */
					else {
						writeLog("등록된 Data Type이 아닙니다.", outfile);
					}

					//SQLent가 없으면 주석

					//cmnUtil.writeLog(sqlEntity1.getMessage,outfile);
				}

				getErrorCode = 0;
				getMessage = "Log화일이 정상적으로 저장이 되었습니다.";
			} else {
				getErrorCode = 0;
				getMessage = "Log를 남길 기록이 없습니다.";

			}

			if (outfile != null)
				outfile.flush();
			if (outfile != null)
				outfile.close();

		} catch (Exception e) {

			getErrorCode = -99900;
			;
			getMessage = "" + e.getMessage();
			;

		} finally {

		}
		/*
		 String[] mesg = new String[3];
		 mesg[0] = getErrorCode +"";
		 mesg[1] = getMessage;
		 mesg[2] = real_path+filename;

		 return mesg;
		 */

		/*
		 cmn.SQLent sqlEntity = new cmn.SQLent();
		 sqlEntity.getErrorCode = getErrorCode;
		 sqlEntity.getMessage   = getMessage;
		 sqlEntity.temp         = real_path+filename;

		 */
		String mesg = "";

		if (getErrorCode == 0) {
			mesg = "Y";
		} else {
			mesg = getMessage;
		}
		return mesg;

	}

	/**

	 Html Textarea에서 오는 값에 Sql의 in문에 쓸수 있도록 정의
	 _find_str :  "\r\n" ,"_"

	 */
	public String conditionToTextArea(String data, String _find_str,
			String _replace_str)

	{

		//Html textarea에서 enter치면  \r\n

		if (data == null || data.equals(""))
			return "";

		String find_str = _find_str; //"\r\n";

		String replace_str = _replace_str;//"','";

		int pos = data.indexOf(find_str);

		if (pos == -1)

		{

			//data = "('"+data+"')";
			data = data;

		} else

		{

			//data = "('"+replaceStringAll2( data,find_str,replace_str)+"')";
			data = replaceStringAll2(data, find_str, replace_str);

		}

		return data;

	}

	/**
	 그 주의 월요일부터 일요일을 제외하고 3일씩 끊어 Format대로 반환
	 */

	public String getHalfOfWeek(String date, int destday, String format)

	{

		//입력받은 날의 요일 반환

		int year = Integer.valueOf(date.substring(0, 4)).intValue();

		int month = Integer.valueOf(date.substring(4, 6)).intValue();

		int day = Integer.valueOf(date.substring(6, 8)).intValue();

		format = getHalfOfWeek(year, month, day, destday, format);

		return format;

	}

	/**
	 그 주의 월요일부터 일요일을 제외하고 3일씩 끊어 Format대로 반환
	 */

	public String getHalfOfWeek(int year, int month, int day, int destday,
			String format)

	{

		GregorianCalendar calendar = new GregorianCalendar();

		calendar.set(year, month - 1, day);

		//(1-일,2-월,...7-토)

		int dayofweek = calendar.get(calendar.DAY_OF_WEEK);

		//그주의 월요일을 받아오기

		GregorianCalendar cal = new GregorianCalendar();

		cal.set(year, month - 1, day - dayofweek + destday + 1);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		format = replaceStringAll(format, "YYYY", stryear);

		//format = replaceString( format, "YY", yy );

		format = replaceStringAll(format, "MM", strmonth);

		format = replaceStringAll(format, "DD", strday);

		return format;

	}

	/**현재 날짜에서 월을 더해 Format대로 반환
	 */

	public String getNextMonth(String date, int destmonth, String format)

	{

		//var arg  = getUserDate1( oneday )

		//arg - date형식

		int year = Integer.valueOf(date.substring(0, 4)).intValue();

		int month = Integer.valueOf(date.substring(4, 6)).intValue();

		int day = Integer.valueOf(date.substring(6, 8)).intValue();

		format = getNextMonth(year, month, day, destmonth, format);

		return format;

	}

	/**현재 날짜에서 월을 더해 Format대로 반환
	 */

	public String getNextMonth(int year, int month, int day, int destmonth,
			String format)

	{

		GregorianCalendar cal = new GregorianCalendar();

		cal.set(year, month - 1 + destmonth, day);

		String stryear = String.valueOf(cal.get(Calendar.YEAR)).toString();

		String strmonth = toLen2(cal.get(Calendar.MONTH) + 1);

		String strday = toLen2(cal.get(Calendar.DATE));

		format = replaceStringAll(format, "YYYY", stryear);

		//format = replaceString( format, "YY", yy );

		format = replaceStringAll(format, "MM", strmonth);

		format = replaceStringAll(format, "DD", strday);

		return format;

	}

	/**원하는 크기로 자르기
	 */
	public static String subString(String str, int i)

	{
		if (str == null)
			return "";

		if (str.length() == 0 || str.trim().equals(""))
			return "";

		try {

			if (str.length() >= i)
				str = str.substring(0, i);

		} catch (Exception e)

		{

			str = "Cut error from 0 to " + i + ": [" + str + "]";

		}

		return str;

	}

	/**
	 원하는 크기로 자르기
	 <p>
	 subString("abcde",2 ,4)  =>  cd
	 */
	public static  String subString(String str, int start, int end)

	{

		if (str == null)
			return "";

		if (str.length() == 0 || str.trim().equals(""))
			return "";

		try {

			if (str.length() >= end)
				str = str.substring(start, end);

		} catch (Exception e)

		{
			str = "Cut error from " + start + " to " + end + ": [" + str + "]";
		}

		return str;

	}

	/**
	 Hash table에  name이 같으면 String을 배열로
	 */
	public Hashtable putOfHashtableToArray(Hashtable parameters, String name,
			String value)

	{

		if (parameters.get(name) == null)

		{

			String values[] = new String[1];

			values[0] = value;

			parameters.put(name, values);

		} else {

			Object prevobj = parameters.get(name);

			if (prevobj instanceof String[])

			{

				String prev[] = (String[]) prevobj;

				String newStr[] = new String[prev.length + 1];

				System.arraycopy(prev, 0, newStr, 0, prev.length);

				newStr[prev.length] = value;

				parameters.put(name, newStr);

			} else

			{

				throw new IllegalArgumentException(
						"failure in parseMulti hashtable building code");

			}

		}

		return parameters;

	}

	/**
	 파일사이즈 얻기
	 */

	public long getFileSize(String path_file)

	{

		long size = 0;

		File file = new File(path_file);

		try

		{

			if (file.exists())
				size = file.length();

			else
				size = -990;

		}

		catch (Exception e)

		{

			size = -991;

		}

		return size;

	}

	/**
	 파일사이즈 얻기
	 */

	public long getFileSize(String path, String fileName)

	{

		String path_file = "";

		if (path.endsWith(System.getProperty("file.separator")))

			path_file = path + fileName;

		else

			path_file = path + System.getProperty("file.separator") + fileName;

		return getFileSize(path_file);

	}

	/**
	 File size에 대한  Message출력
	 */
	public String getFileSizeMesg(long file_size) {
		String _file_size = "";
		if (file_size < 0) {// cmnUtil.getFileSize() -> 파일이 없을때 or 에러
			if (file_size == -990)
				_file_size = "파일이 존재하지 않습니다. ";
			else
				_file_size = "디렉토리나 파일이 문제가 있습니다. ";
		} else if (file_size >= 0 && file_size < 1000)
			_file_size = file_size + "byte";
		else if (file_size >= 1000 && file_size < 1000000)
			_file_size = setComma(file_size / 1000) + "Kbyte";
		else
			_file_size = setComma(file_size / 1000) + "kbyte";

		return _file_size;

	}

	/**정렬 순서
	 스트링,스트링,스티링반환
	 */
	public String getSortOrder(String[] SORT, String start_sort_name) {
		int len = SORT.length;
		if (len == 0)
			return "1";

		String sort_name = "";
		if (start_sort_name.equals("")) {
			for (int i = 0; i < len; i++) {
				String _sort = SORT[i];
				if (i != 0)
					_sort = "," + _sort;

				sort_name += _sort;
			}
		} else {//초기 값이 존재
			sort_name = start_sort_name;
			for (int i = 0; i < len; i++) {
				String _sort = SORT[i];
				if (!start_sort_name.equals(_sort)) {
					sort_name += "," + _sort;
				}

			}

		}

		return sort_name;
	}

	/**
	 start+스트링+end +gubun+start+스트링+end +start+스트링+end 로 반환
	 <p>
	 getFormatData(String[] Data,"\"","\"",",")
	 <p>
	 "1","6","2"
	 */
	public String getFormatData(String[] DATA, String start, String end,
			String gubun) {
		int len = DATA.length;
		if (len == 0)
			return "";

		String data = "";

		for (int i = 0; i < len; i++) {
			String _data = start + DATA[i] + end;
			if (i == 0)
				data += _data;
			else
				data += gubun + _data;

		}

		return data;
	}

	/**
	 start+스트링+end +gubun+start+스트링+end +start+스트링+end 로 반환
	 <p>
	 getFormatData(String[] Data,"\"","\"",",")
	 <p>
	 "1","6","2"
	 */
	public String getFormatData(Vector DATA, String start, String end,
			String gubun) {

		int len = DATA.size();
		if (len == 0)
			return "";

		String data = "";

		for (int i = 0; i < len; i++) {
			String vec_data = (String) DATA.elementAt(i);

			String _data = start + vec_data + end;

			if (i == 0)
				data += _data;
			else
				data += gubun + _data;

		}

		return data;
	}

	/**
	 start+스트링+end +gubun+start+스트링+end +start+스트링+end 로 반환
	 <p>
	 getFormatData(String[] Data,"\"","\"",",")
	 <p>
	 "1","6","2"
	 in 절은 999개뿐이 안되서
	 */
	public String getFormatData(Vector DATA, String start, String end,
			String gubun, int count) {

		int len = DATA.size();
		if (len == 0)
			return "";

		String data = "";

		for (int i = 0; i < len; i++) {
			String vec_data = (String) DATA.elementAt(i);

			String _data = start + vec_data + end;

			if (i == 0)
				data += _data;
			else
				data += gubun + _data;

			if (count == i + 1)
				break;

		}

		return data;
	}

	/**
	 start+스트링+end +gubun+start+스트링+end +start+스트링+end 로 반환
	 <p>
	 getFormatData(String[] Data,"\"","\"",",")
	 <p>
	 "1","6","2"
	 */
	public Vector getWantDataForVector(Vector DATA, int index) {

		int len = DATA.size();
		if (len == 0)
			return new Vector();

		Vector data = new Vector();

		for (int i = 0; i < len; i++) {
			String vec_data = (String) DATA.elementAt(i);

			if (vec_data == null)
				vec_data = "";

			data.add(vec_data);

			if (i + 1 == index)
				break;

		}

		return data;
	}

	/**
	 Default data채우기
	 */
	public Vector getWantDefaultDataForVector(String default_data, int index) {

		Vector data = new Vector();

		for (int i = 0; i < index; i++) {
			String vec_data = default_data == null ? "" : default_data;

			data.add(vec_data);
		}

		return data;
	}

	/**토큰 갯수 count
	 tokenCount("dfdf,fdfdd,fdfd",",")  : 결과 3
	 */
	public long tokenCount(String str, String deli)

	{

		long result = 0;

		if (str.equals("")) {
			result = 0;
		} else {

			try {
				//token이 붙어있을때 처리법
				str = replaceStringAll(str, deli + deli + deli, deli + deli
						+ " " + deli);
				str = replaceStringAll(str, deli + deli, deli + " " + deli);
				StringTokenizer st = new StringTokenizer(str, deli);

				if (st.hasMoreTokens()) {
					result = st.countTokens();
				} else {
					result = 1;
				}
			} catch (Exception e) {
				result = 0;
			}

		}

		return result;

	}

	/**
	 Sting를 decode하기
	 * Decode a string from <code>x-www-form-urlencoded</code> format.
	 *
	 * @param   s   an encoded <code>String</code> to be translated.
	 * @return  the original <code>String</code>.
	 * @see     java.net.URLEncoder#encode(java.lang.String)
	 */
	public String decode(String s) {
		String data = "";
		try {

			data = java.net.URLDecoder.decode(s);

			//data =    s;
		} catch (Exception e) {
		}

		return data;
	}

	/**
	 Sting[]를 decode하기
	 */
	public static String[] decodes(String[] s) {
		String data = "";
		String[] temp = null;

		if (s != null) {
			temp = new String[s.length];
			try {
				for (int i = 0; i < s.length; i++) {

					temp[i] = java.net.URLDecoder.decode(s[i]);

					//temp[i] = s[i];
				}
			} catch (Exception e) {
			}
		}
		return temp;
	}

	/**
	 Sting를 encode하기
	 * Translates a string into <code>x-www-form-urlencoded</code> format.
	 *
	 * @param   s   <code>String</code> to be translated.
	 * @return  the translated <code>String</code>.
	 * @see     java.net.URLEncoder#encode(java.lang.String)
	 */
	public String encode(String s) {
		//return s;
		return java.net.URLEncoder.encode(s);
	}

	/**
	 프로그램은 /servlet/test.system   -> /common/
	 <p>
	 html       /common/test.html       -> /common/  반환
	 <p>
	 디렉토리 제외한 호출 프로그램반환
	 */

	public String getDirectoryName(String getServlet) {

		String name = "";

		if (getServlet == null || getServlet.startsWith("."))
			return getServlet;

		int dash_pos = getServlet.lastIndexOf("/");

		if (dash_pos == -1) {

			name = getServlet;
		} else {
			name = getServlet.substring(0, dash_pos);
			//if(!name.startsWith("/"))  name= "/"+ name;
			//if(!name.endsWith("/"))  name=  name+"/";
		}

		return name;
	}

	/**
	 프로그램은 /servlet/test.system   -> test.system
	 <p>
	 html       /common/test.html       -> test.html  반환
	 <p>
	 디렉토리 제외한 호출 프로그램반환
	 */

	public String getProgramName(String getServlet) {

		String name = "";

		if (getServlet == null || getServlet.startsWith("."))
			return getServlet;

		int dash_pos = getServlet.lastIndexOf("/");

		if (dash_pos == -1) {

			name = getServlet;
		} else {
			name = getServlet.substring(dash_pos + 1);
		}

		return name;
	}

	/**
	 프로그램은 /servlet/test.system  -> system
	 <p>
	 html       /common/test.html       -> test  반환
	 <p>
	 디렉토리제외하고 실제 이름
	 <p>
	 index.html[index]
	 <p>
	 index.com.html[index.com]
	 <p>
	 ordpk.ordpk100[ordpk100]
	 <p>
	 ordpk.com.ordpk100[ordpk100]
	 <p>
	 */
	public String getProgramRuleName(String getServlet) {

		String name = "";
		int spot_pos = getServlet.lastIndexOf(".");

		if (getServlet.endsWith(".html") || getServlet.endsWith(".htm")
				|| getServlet.endsWith(".jsp") || getServlet.endsWith(".asp")) {
			int dash_pos = getServlet.lastIndexOf("/");
			name = getServlet.substring(dash_pos + 1, spot_pos);

		} else {
			int dash_pos = getServlet.lastIndexOf("/");
			//      if (spot_pos == -1 )
			if (spot_pos < dash_pos) {

				name = getServlet.substring(dash_pos + 1);
			} else {
				name = getServlet.substring(spot_pos + 1);
			}
		}
		return name;
	}


	/**
	 총 페이지수를 반환
	 */
	public long getTotalPage(long total_count, long REQUESTROW) {
		int total_page = 0;
		try {
			if (REQUESTROW == 0) {
				total_page = 1;
			} else {

				total_page = 0;
				if (total_count == 0)
					total_page = 0;
				else
					total_page = (int) (total_count / REQUESTROW); //큰수중 제일 작은값 ,floor작은수중 제일 큰값// total_page = (int) Math.ceil( total_count / REQUESTROW);

				if ((total_count) % (REQUESTROW) > 0)
					total_page++;
			}
		} catch (Exception e) {
		}

		return total_page;
	}


	/**
	 * 문자열을 일정길이 만큼만 보여주고
	 * 그 길이에 초과되는 문자열일 경우 "..."를 덧붙여 보여준다.
	 * @return the translated string.
	 * @param s String 변환할 문자열
	 * @param limitLength int 문자열의 제한 길이
	 */
	public String fixLength(String input, int limit) {
		return fixLength(input, limit, "...");
	}

	/**
	 * 문자열을 일정길이 만큼만 보여주고
	 * 그 길이에 초과되는 문자열일 경우 특정문자를 덧붙여 보여준다.
	 * @return the translated string.
	 * @param s String 변환할 문자열
	 * @param limitLength int 문자열의 제한 길이
	 * @param postfix String 덧붙일 문자열
	 */
	public String fixLength(String input, int limit, String postfix) {

		String buffer = "";
		char[] charArray = input.toCharArray();
		if (limit >= charArray.length) {
			return input;
		}

		for (int j = 0; j < limit; j++) {
			buffer += charArray[j];
		}
		buffer += postfix;
		return buffer;
	}

	/**

	 *
	 */
	public String getConvert(String a_convert_i) {

		String convert_str = "";
		String tmp_str;
		String tmp_desc;

		int index;

		int j = 0;
		int t_cnt;
		int[] a_temp = new int[1000];

		t_cnt = 0;

		char[] _input = a_convert_i.toCharArray();
		for (index = 0; index < _input.length; index++) {
			char chr = _input[index];
			if ((int) chr >= 0 && (int) chr <= 127) {//아스키이면
				/* a_convert_i[index] != '\''       &&  */

				if (!Character.isUpperCase(chr) && !Character.isLowerCase(chr)
						&& !Character.isDigit(chr) && !Character.isSpace(chr)
						&& chr != '?' && chr != '(' && chr != ')' && chr != '.'
						&& chr != ',' && chr != '"' && chr != '+' && chr != '/'
						&& chr != '-') {//특수문자면
					a_temp[t_cnt] = index;
					t_cnt++;
				}

				if (Character.isISOControl(chr)) {//제어문자면
					a_temp[t_cnt] = index;
					t_cnt++;
				}
			}
		}

		if (t_cnt > 0) {
			for (index = 0; index < t_cnt; index++) {
				/// strncpy(tmp_str, a_convert_i + j, a_temp[index] - j );
				tmp_str = a_convert_i.substring(j, j + a_temp[index] - j);
				convert_str += tmp_str;

				j = a_temp[index];
				char comp = a_convert_i.substring(j, j + 1).charAt(0);
				if (comp == '=')
					convert_str += "EQUAL";
				else if (comp == '%')
					convert_str += "PCT";
				else if (comp == '&')
					convert_str += "AND";
				else if (comp == '`')
					convert_str += " ";
				else if (comp == '~')
					convert_str += "-";
				else if (comp == '[')
					convert_str += "(";
				else if (comp == '{')
					convert_str += "(";
				else if (comp == ']')
					convert_str += ")";
				else if (comp == '}')
					convert_str += ")";
				else
					convert_str += " ";

				/*
				 else if ( a_convert_i.substring(j,j+1) == '"' )
				 sprintf(convert_str+= convert_str, "'");
				 else if ( a_convert_i[j] == '"' )
				 sprintf(convert_str, "%s%s", convert_str, "'");
				 */
				j = j + 1;

			}

			if (a_convert_i.length() > j) {
				tmp_str = a_convert_i
						.substring(j, j + a_convert_i.length() - j);
				convert_str += tmp_str;
			}
		} else {
			convert_str = a_convert_i;
		}

		return convert_str;

	}

	/**
	 token에서의 index에 해당하는 String가져오기
	 */
	public String getTokenString(String s, String deli, int search) {
		String str = "";
		String token = "";
		int inx = 0;

		if (s != null)
			str = s;

		//token이 붙어있을때 처리법

		str = replaceStringAll(str, deli + deli + deli, deli + deli + " "
				+ deli);
		str = replaceStringAll(str, deli + deli, deli + " " + deli);
		try {
			StringTokenizer st = new StringTokenizer(str, deli);

			if (st.hasMoreTokens()) {// 토큰이 있을때
				while (st.hasMoreTokens()) {

					token = st.nextToken();
					if (search == inx)
						break;
					inx++;
				}
			} else {
				token = "";
			}

		} catch (Exception e) {
			token = "";
		}

		return token;

	}

	/**
	 token에서의 index까지의 해당하는 String을 더해서 가져오기
	 */
	public String getTokenStringToIndex(String s, String deli, int search) {
		String str = "";
		String token = "";
		int inx = 0;

		if (s != null)
			str = s;

		//token이 붙어있을때 처리법

		str = replaceStringAll(str, deli + deli + deli, deli + deli + " "
				+ deli);
		str = replaceStringAll(str, deli + deli, deli + " " + deli);
		try {
			StringTokenizer st = new StringTokenizer(str, deli);

			if (st.hasMoreTokens()) {// 토큰이 있을때
				while (st.hasMoreTokens()) {

					token += (inx == 0 ? "" : deli) + st.nextToken();

					if (search == inx)
						break;
					inx++;
				}
			} else {
				token = "";
			}

		} catch (Exception e) {
			token = "";
		}

		return token;

	}

	/**
	 엑셀 다운로드이 다양한 형식을 지원하기 위해
	 */

	public String getExcelStart()

	{

		StringBuffer buffer = new StringBuffer();

		buffer
				.append("<html xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>\n");
		buffer
				.append("<head>                                                           \n");
		buffer
				.append("<meta http-equiv=Content-Type content=\"text/html; charset=ks_c_5601-1987\">\n");
		buffer
				.append("<meta name=ProgId content=Excel.Sheet>                           \n");
		buffer
				.append("<meta name=Generator content='Microsoft Excel 9'>                \n");
		buffer.append("\n");
		buffer
				.append("<style>                                                          \n");
		buffer
				.append("</style>                                                         \n");
		buffer
				.append("<!--[if gte mso 9]><xml>                                         \n");
		buffer
				.append(" <x:ExcelWorkbook>                                               \n");
		buffer
				.append("  <x:ExcelWorksheets>                                            \n");
		buffer
				.append("   <x:ExcelWorksheet>                                            \n");
		buffer
				.append("    <x:Name>Recovered_Sheet1</x:Name>                            \n");
		buffer
				.append("    <x:WorksheetOptions>                                         \n");
		buffer
				.append("     <x:Selected/>                                               \n");
		buffer
				.append("     <x:ProtectContents>False</x:ProtectContents>                \n");
		buffer
				.append("     <x:ProtectObjects>False</x:ProtectObjects>                  \n");
		buffer
				.append("     <x:ProtectScenarios>False</x:ProtectScenarios>              \n");
		buffer
				.append("    </x:WorksheetOptions>                                        \n");
		buffer
				.append("   </x:ExcelWorksheet>                                           \n");
		buffer
				.append("  </x:ExcelWorksheets>                                           \n");
		buffer
				.append(" </x:ExcelWorkbook>                                              \n");
		buffer
				.append("</xml><![endif]-->                                               \n");
		buffer
				.append("</head>                                                          \n");
		buffer.append("\n");
		buffer
				.append("<body link=blue vlink=purple >                                   \n");
		buffer.append("\n");
		buffer.append("\n");
		buffer.append("\n");
		buffer.append("\n");

		return buffer.toString();

	}


	/**
	 엑셀 다운로드이 다양한 형식을 지원하기 위해
	 */

	public String getExcelEnd() {

		StringBuffer buffer = new StringBuffer();

		buffer.append("</body>\n");
		buffer.append("\n");
		buffer.append("</html>\n");
		buffer.append("\n");

		return buffer.toString();

	}

	/**
	 milliseconds 를 받아 원하는 형식으로 반환

	 gubun_start  DD : 일부터
	 gubun_start  hh : 시간부터
	 gubun_start  mm : 분부터
	 gubun_start  ss : 초부터

	 format "DD일hh시간mm분ss초ms밀리초
	 <p>
	 format "hh시간mm분ss초ms밀리초
	 <p>
	 format "mm분ss초ms밀리초
	 <p>
	 format "ss초ms밀리초
	 <p>
	 */
	public String getSubtractFormat(long militime, String gubun_start,
			String format)

	{
		String data = "";
		long day = 0;
		long hour = 0;
		long minute = 0;
		long sec = 0;
		long msec = 0;

		if (gubun_start.equals("DD")) {
			day = (long) Math.round((double) militime / (60 * 60 * 24 * 1000));
			hour = (long) Math.floor((militime / (60 * 60 * 1000)) % 24);
			minute = (long) (Math.floor((militime / (60 * 1000)) % (24 * 60)) - (long) Math
					.floor(((militime / (60 * 60 * 1000)) % 24) * 60));
			sec = (long) Math.floor(((militime / 1000) % 60) % 60);
			msec = (long) Math.floor(militime % 1000);
			;

			format = replaceStringAll(format, "DD", day + "");
			format = replaceStringAll(format, "hh", hour + "");
			format = replaceStringAll(format, "mm", minute + "");
			format = replaceStringAll(format, "ss", sec + "");
			format = replaceStringAll(format, "ms", msec + "");

		} else if (gubun_start.equals("hh")) {

			hour = (long) Math.round((double) militime / (60 * 60 * 24 * 1000))
					* 24
					+ (long) Math.floor((militime / (60 * 60 * 1000)) % 24);
			minute = (long) (Math.floor((militime / (60 * 1000)) % (24 * 60)) - Math
					.floor(((militime / (60 * 60 * 1000)) % 24) * 60));
			sec = (long) Math.floor(((militime / 1000) % 60) % 60);
			msec = (long) Math.floor(militime % 1000);
			;

			format = replaceStringAll(format, "hh", hour + "");
			format = replaceStringAll(format, "mm", minute + "");
			format = replaceStringAll(format, "ss", sec + "");
			format = replaceStringAll(format, "ms", msec + "");

		} else if (gubun_start.equals("mm")) {
			minute = (long) Math.floor(militime / (60 * 1000));
			sec = (long) Math.floor(((militime / 1000) % 60) % 60);
			msec = (long) Math.floor(militime % 1000);

			format = replaceStringAll(format, "mm", minute + "");
			format = replaceStringAll(format, "ss", sec + "");
			format = replaceStringAll(format, "ms", msec + "");

		} else //if(gubun_start.equals("ss")
		{

			sec = (long) (Math.floor(militime / (60 * 1000) * 60) + Math
					.floor(((militime / 1000) % 60) % 60));
			msec = (long) Math.floor(militime % 1000);

			format = replaceStringAll(format, "ss", sec + "");
			format = replaceStringAll(format, "ms", msec + "");

		}

		return format;

	}

	/*
	 // 10과 25 사이의 임의 정수를 발생한다
	 int count = (int)(15 * Math.random() + 10);


	 // 가우스 분포로 이루어진 10에서 25 사이의
	 // 임의 double 값을 발생한다
	 //Random random = new Random(count );
	 Random random = new Random(count);

	 for(int i = 0; i < count; i++)
	 {
	 System.out.println(random.nextInt());
	 */
	/**
	 Status에 뿌릴때 변환
	 */
	public String getStatusData(String data) {
		String result = "";

		if (data == null)
			return "";

		result = replaceStringAll(data, "\r\n", " "); //Web입력 대비용
		result = replaceStringAll(result, "\n", " "); //Plsql dev입력 대비용
		result = replaceStringAll(result, "\"", " ");
		result = replaceStringAll(result, "'", " ");

		return result;
	}

	public String getDetailContent(String data) {
		String result = "";

		if (data == null)
			return "";

		result = replaceStringAll(data, "\r\n", "<br>"); //Web입력 대비용
		result = replaceStringAll(result, "\n", "<br>"); //Plsql dev입력 대비용
		//result = replaceStringAll(result, "\""  , " ");
		//result = replaceStringAll(result, "'"   , " ");

		return result;
	}

	/* 제목을 요약해서 보여준다 */
	public String getSummaryData(String view_content, int line) {
		String result = "";

		if (view_content == null)
			return "";

		long token_count = tokenCount(view_content, "\r\n");

		if (token_count >= line)
			view_content = getTokenStringToIndex(view_content, "\r\n", line);

		//view_content = cmnUtil.toHtml(view_content); //개행같은것이 안된다.
		view_content = replaceStringAll(view_content, "\"", "&#34;");
		if (token_count >= line)
			view_content += " \n " + line + " lines more...  ( total : "
					+ token_count + " lines ) ";

		return view_content;
	}

	//DB에 넣을때 제거해야할 내용
	public String toDB(String in) {
		String sRetrun = "";
		if (in == null)
			in = "";

		sRetrun = replaceStringAll(in, ",", "^");
		sRetrun = replaceStringAll(sRetrun, "'", "`");

		return sRetrun;
	}

	//메세지 출력할때 제거해야할 내용
	public String toDisplay(String in) {
		String sRetrun = "";
		if (in == null)
			in = "";

		sRetrun = replaceStringAll(in, "\n", "\\\n");
		sRetrun = replaceStringAll(sRetrun, "\r", " ");

		return sRetrun;
	}

	//StringTokener  --> 배열
	public String[] split(String str, String spliter) throws Exception {
		StringTokenizer st;
		int len;
		String[] bufArr;
		int i;

		if (str == null || str.equals("")) 
		{
			return "".split("");
		}
		try {
			st = new StringTokenizer(str, spliter);
			len = st.countTokens();
			bufArr = new String[len];
			for (i = 0; i < len; i++) {
				bufArr[i] = (String) st.nextElement();
			}
			return (String[]) bufArr;
		} catch (Exception e) {
			return new String[] {};
		} // try, catch end..
	} // split Method end

	//
	public String[] arraySort(String[] SORT) {
		int len = SORT.length;

		String min_data = "";
		for (int i = 0; i < SORT.length; i++) {
			min_data = SORT[i];
			for (int j = i + 1; j < SORT.length; j++) {

				//if (SORT[j].hashCode() < min_data.hashCode() )
				//if (  toCode( SORT[j] ) < toCode( min_data ) )
				if ((int) SORT[j].toLowerCase().charAt(0) < (int) min_data
						.toLowerCase().charAt(0)) {
					min_data = SORT[j];
					SORT[j] = SORT[i];
					SORT[i] = min_data;
				}
			}
		}

		return SORT;
	}

	/**HEX에 따른 RGB반환
	 #FFCC00 -> 255,204,0
	 new Color(R,G,B);
	 new Color(Integer.parseInt("#ff00ff",16))

	 */
	public String getRGB(String colorString) {

		String data = colorString.replace('#', ' ').trim();
		String R = Integer.valueOf(data.substring(0, 2), 16).intValue() + "";
		String G = Integer.valueOf(data.substring(2, 4), 16).intValue() + "";
		String B = Integer.valueOf(data.substring(4, 6), 16).intValue() + "";

		return R + "," + G + "," + B;

	}

	/**RGB에 따른 HEX반환
	 255,204,0 -> #FFCC00
	 new Color(R,G,B);
	 new Color(Integer.parseInt("#ff00ff",16))

	 */
	public String getHEX(String colorString) {
		int R = string2Int(getTokenString(colorString, ",", 0));
		int G = string2Int(getTokenString(colorString, ",", 1));
		int B = string2Int(getTokenString(colorString, ",", 2));

		String color = "#" + Integer.toHexString(R) + Integer.toHexString(G)
				+ Integer.toHexString(B);

		return color;
	}

	/**
	 Random Color 반환
	 */
	public synchronized String getRandomColor() {
		String s = Integer.toHexString((int) (Math.random() * 256D));
		String s1 = Integer.toHexString((int) (Math.random() * 256D));
		String s2 = Integer.toHexString((int) (Math.random() * 256D));
		return "#" + s + s1 + s2;
	}

	public boolean isUnix() {
		boolean isUnix = false;

		if (System.getProperty("file.separator").equals("/")) {//unix
			isUnix = true;
		} else {//windows
			isUnix = false;
		}

		return isUnix;
	}

	/**
	 아스키를 String으로
	 177,232를 김으로 변환

	 */
	public String getDbAscii2String(String command, String row_token) {

		String s_data = "";

		int count = 0;

		int inx = 0;

		try {
			count = (int) tokenCount(command, row_token);

			byte data[] = new byte[count]; //번호

			StringTokenizer data_row = new StringTokenizer(command, row_token);//"\r\n"

			if (data_row.hasMoreTokens()) {// 토큰이 있을때

				//     타이틀SKIP및 다른 것 skip

				while (data_row.hasMoreTokens()) {
					String row = data_row.nextToken();
					data[inx] = Integer.valueOf(row).byteValue();
					inx++;
				}
				s_data = new String(data, "KSC5601");
			} else {
				s_data = command;
			}

		} catch (Exception e) {
			s_data = command;
		}

		return s_data;

	}

	/**
	 String를 아스키로
	 김를 177,232으로 변환

	 */
	public String getString2DbAscii(String command, String row_token) {

		String s_data = "";

		int count = 0;

		int inx = 0;

		try {

			byte data[] = command.getBytes("KSC5601"); //번호

			for (int i = 0; i < data.length; i++) {
				if (i != 0)
					s_data += row_token;

				//byte _data[] = { data[i] };
				//int  val = Integer.valueOf( new String( _data )  ).intValue();

				int val = (int) data[i];

				if (val < 0)
					val = 256 + val;//한글

				s_data += val;
			}
		} catch (Exception e) {
			s_data = command;
		}

		return s_data;

	}

	/**
	 그 년월의 주 반환
	 */

	public String getMonthWeek(String date)

	{

		//입력받은 날의 요일 반환

		int year = Integer.valueOf(date.substring(0, 4)).intValue();

		int month = Integer.valueOf(date.substring(4, 6)).intValue();
		int day = Integer.valueOf(date.substring(6, 8)).intValue();

		String format = getMonthWeek(year, month, day);

		return format;

	}

	/**
	 그 년월의  주 반환

	 */

	public String getMonthWeek(int year, int month, int day)

	{

		GregorianCalendar calendar = new GregorianCalendar();

		calendar.set(year, month - 1, day);

		//1,2,3,4..주
		int dayofweek = calendar.get(Calendar.WEEK_OF_MONTH);

		//--dayofweek;

		return dayofweek + "";

	}

	/**
	 그 년월의 마지막 주 반환
	 */

	public String getEndOfMonthWeek(String date)

	{

		//입력받은 날의 요일 반환

		int year = Integer.valueOf(date.substring(0, 4)).intValue();

		int month = Integer.valueOf(date.substring(4, 6)).intValue();

		String format = getEndOfMonthWeek(year, month);

		return format;

	}

	/**
	 그 년월의 마지막 주 반환

	 */

	public String getEndOfMonthWeek(int year, int month)

	{

		GregorianCalendar calendar = new GregorianCalendar();

		calendar.set(year, month - 1, getEndOfMonthDay(year, month));

		//1,2,3,4..주
		int dayofweek = calendar.get(Calendar.WEEK_OF_MONTH);

		return dayofweek + "";

	}

	/**현재년월의 마지막 주 반환
	 */
	public String getEndOfMonthWeek() {

		int year = Integer.valueOf(getCurrYear()).intValue();

		int month = Integer.valueOf(getCurrMonth()).intValue();

		return getEndOfMonthWeek(year, month);

	}

	public String getWeekName(int i) {
		String name = "";
		if (i == 1)
			name = "첫째 주";
		else if (i == 2)
			name = "둘째 주";
		else if (i == 3)
			name = "셋째 주";
		else if (i == 4)
			name = "넷째 주";
		else if (i == 5)
			name = "다섯째 주";
		else if (i == 6)
			name = "여섯째 주";
		else
			name = i + "째 주";

		return name;

	}

	/**프로그램ID에 따른 ID반환
	 */
	public String getPgmId(String cmn_pgm_cd) {

		String data = "A00";
		if (cmn_pgm_cd.indexOf("WP_INV") != -1)
			data = "A00";
		else if (cmn_pgm_cd.indexOf("WP_LTI") != -1)
			data = "B00";
		else if (cmn_pgm_cd.indexOf("WP_YUL") != -1)
			data = "C00";
		else if (cmn_pgm_cd.indexOf("WP_QMI") != -1)
			data = "F00";
		else if (cmn_pgm_cd.indexOf("WP_EIS") != -1)
			data = "D00";
		else if (cmn_pgm_cd.indexOf("WP_ADM") != -1)
			data = "E00";
		else if (cmn_pgm_cd.indexOf("WP_HLP") != -1)
			data = "G00";

		return data;

	}

	public ArrayList putOfBox(String data) {
		ArrayList arraylist = new ArrayList();
		arraylist.add(data);
		return arraylist;
	}

	//LMultidata용
	public Vector getVector(HashMap infoBox, String key) {

		Vector vector = new Vector();
		ArrayList arrayList = (ArrayList) infoBox.get(key);

		try {
			int len = arrayList.size();
			for (int i = 0; i < len; i++) {
				Object item = arrayList.get(i);
				//Kim sung su
				//vector.addElement(item)
				if (item == null)
					vector.addElement("");
				else
					vector.addElement(item.toString());
			} // end for(int i=0; i<length;i++)

			/*
			 Object o = (Object)arrayList.get(key);
			 Class c = o.getClass();
			 if ( o != null ) {
			 if( c.isArray() ) {
			 int length = Array.getLength(o);
			 if ( length != 0 ) {
			 for(int i=0; i<length;i++) {
			 Object item = Array.get(o, i);
			 //Kim sung su
			 //vector.addElement(item)
			 if (item == null ) vector.addElement("");
			 else vector.addElement(item.toString());
			 } // end for(int i=0; i<length;i++)
			 } // end if ( length != 0 )

			 } else {
			 vector.addElement(o.toString() );
			 } // end if( c.isArray() )
			 } // end if ( o != null )
			 */

		} catch (Exception e) {
		}

		return vector;
	}

	//LMultidata용
	public Vector getVector(HashMap infoBox, String key, int cnt) {

		Vector vector = new Vector();
		ArrayList arrayList = (ArrayList) infoBox.get(key);

		try {
			int len = arrayList.size();
			for (int i = 0; i < cnt; i++) {
				Object item = arrayList.get(i);
				//Kim sung su
				//vector.addElement(item)
				if (item == null)
					vector.addElement("");
				else
					vector.addElement(item.toString());
			} // end for(int i=0; i<length;i++)

			/*
			 Object o = (Object)arrayList.get(key);
			 Class c = o.getClass();
			 if ( o != null ) {
			 if( c.isArray() ) {
			 int length = Array.getLength(o);
			 if ( length != 0 ) {
			 for(int i=0; i<length;i++) {
			 Object item = Array.get(o, i);
			 //Kim sung su
			 //vector.addElement(item)
			 if (item == null ) vector.addElement("");
			 else vector.addElement(item.toString());
			 } // end for(int i=0; i<length;i++)
			 } // end if ( length != 0 )

			 } else {
			 vector.addElement(o.toString() );
			 } // end if( c.isArray() )
			 } // end if ( o != null )
			 */

		} catch (Exception e) {
		}

		return vector;
	}

	//Box용
	public Vector getVector(Hashtable infoBox, String key) {

		Vector vector = new Vector();

		try {

			Object o = (Object) infoBox.get(key);
			Class c = o.getClass();
			if (o != null) {
				if (c.isArray()) {
					int len = Array.getLength(o);
					if (len != 0) {
						for (int i = 0; i < len; i++) {
							Object tiem = Array.get(o, i);
							if (tiem == null)
								vector.addElement("");
							else
								vector.addElement(tiem.toString());
						}
					}
				} else
					vector.addElement(o.toString());
			}

		} catch (Exception e) {
		}

		return vector;
	}

	//Box용
	public Vector getVector(Hashtable infoBox, String key, int cnt) {

		Vector vector = new Vector();

		try {

			Object o = (Object) infoBox.get(key);
			Class c = o.getClass();
			if (o != null) {
				if (c.isArray()) {
					//int len = Array.getLength(o);
					if (cnt != 0) {
						for (int i = 0; i < cnt; i++) {
							Object tiem = Array.get(o, i);
							if (tiem == null)
								vector.addElement("");
							else
								vector.addElement(tiem.toString());
						}
					}
				} else
					vector.addElement(o.toString());
			}

		} catch (Exception e) {
		}

		return vector;
	}

	//Box용
	public Vector getVector(String[] datas, int cnt) {

		Vector vector = new Vector();

		try {
			//FormFile[] fileList = listForm.getFileList();
			//    		ㄹㅇㄹㅁ
			for (int i = 0; i < datas.length; i++) {
				String data = datas[i];

				if ((data != null) && (!data.equals(""))) {
					vector.addElement(data.toString());

				}
				if (i + 1 == cnt)
					break;

			}

		} catch (Exception e) {
		}

		return vector;
	}

	/**
	 * 전달받은 key값에 해당되는 Value를 배열 type으로 return한다.
	 * @param key String
	 * @return java.util.Vector
	 */
	public String[] getStringArrayOfObject(HashMap infoBox, String key) {
		ArrayList arrayList = (ArrayList) infoBox.get(key);

		//String[] data = new String[1];
		String[] data = {};

		try {

			int len = arrayList.size();
			data = new String[len];

			for (int i = 0; i < len; i++) {
				Object item = arrayList.get(i);
				//Kim sung su
				//vector.addElement(item)
				if (item == null)
					data[i] = "";
				else
					data[i] = (String) item;
			} // end for(int i=0; i<length;i++)

		} catch (Exception e) {
		}

		return data;
	}

	/**
	 * 전달받은 key값에 해당되는 Value를 배열 type으로 return한다.
	 * @param key String
	 * @return java.util.Vector
	 */
	public Vector stringArrayToVector(String[] key) {

		//String[] data = new String[1];
		Vector data = new Vector();

		try {

			int len = key.length;

			for (int i = 0; i < len; i++) {
				data.add(key[i]);
			} // end for(int i=0; i<length;i++)

		} catch (Exception e) {
		}

		return data;
	}

	//test_ctx -> testCtx
	public static String attrTranform(String attr) {

		//attr = StreamUtil.nullToBlank(attr);
		attr = attr == null ? "" : attr;
		//attr = attr.toLowerCase(); 20051222 sskim
		int inx = 0;

		inx = attr.indexOf("_");
		if (inx == -1)
			return attr;

		String head = "";
		String large = "";
		String tail = "";

		while ((inx = attr.indexOf("_")) != -1) {

			if (inx == attr.length() - 1)
				return attr = attr.substring(0, attr.length() - 1);

			head = attr.substring(0, inx);
			large = attr.substring(inx + 1, inx + 2).toUpperCase();
			tail = attr.substring(inx + 2, attr.length());

			attr = head + large + tail;

		}

		return attr;
	}



	/**
	 * @param request parameter 값을 읽어올 request 객체
	 * @param src request의 값을 세팅할 Source Class의 Class객체
	 * @return Object 세팅된 클래스의 Object 객체
	 */
	public Object boxToClass(Hashtable box, Class src, int i) {
		Object returnValue = null;
		//System.out.println(">>>>"+box.size()+":22");

		try {
			//src 클래스의 새로운 객체 생성
			returnValue = src.newInstance();
			//request에서 parameter들의 이름을 가져옴
			Enumeration parameterNames = box.keys();

			//System.out.println(">>>>"+box.size()+":33");

			while (parameterNames.hasMoreElements()) {

				try {
					//parameter의 이름을 가져옴
					String _parameterName = (String) parameterNames
							.nextElement();
					//parameter의 값을 가져옴

					String value = (String) getString(box, _parameterName, i);

					value = value == null ? "" : value;

					//String value = (String)box.get(parameterName,i);
					//
					//test_class -> testClass
					String parameterName = attrTranform(_parameterName);

					//System.out.println(">>>>parameterName:"+parameterName+"::"+value);
					//source 클래스에서 parameter 이름과 일치하는 field의 reflection 객체(Field) 생성
					Field field = src.getDeclaredField(_parameterName);//_parameterName,parameterName 일수 있다.

					//"setXxxx" 메소드를 호출하기 위해서 field의 데이터 type을 가져옴
					Class[] parameter = new Class[] { field.getType() };

					//parameter 이름의 첫문자를 대문자로 변환 ex)name -> Name
					parameterName = (parameterName.substring(0, 1)
							.toUpperCase())
							+ parameterName.substring(1);

					//soruce 클래스에서 "setXxxx" 메소드의 reflection 객체(Method) 생성
					Method m = src.getMethod("set" + parameterName, parameter);
					//getObjectValueByType으로 parameter의 type과 값을 넘겨서 type에 맞는 Object객체 생성
					//생성된 Object객체를 불러낸 "setXxxx"의 argument로 넘기기 위한 배열 생성
					Object[] argument = new Object[] { getObjectValueByType(
							field.getType(), value) };

					//reflect된 "setXxxx" 메소드 실행
					m.invoke(returnValue, argument);

				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
		}
		return returnValue;
	}



	/**
	 * check box 와 같이 같은 name에 대해 여러 value들이 String의 Vector로 넘겨준다.
	 * @return Vector
	 * @param key java.lang.String
	 */
	/*
	 public Vector getVector(Hashtable box,String key) {
	 Vector vector = new Vector();
	 try {
	 Object o = (Object)box.get(key);
	 Class c = o.getClass();
	 if ( o != null ) {
	 if( c.isArray() ) {
	 int length = Array.getLength(o);
	 if ( length != 0 ) {
	 for(int i=0; i<length;i++) {
	 Object tiem = Array.get(o, i);
	 if (tiem == null ) vector.addElement("");
	 else vector.addElement(tiem.toString());
	 }
	 }
	 }
	 else
	 vector.addElement(o.toString());
	 }
	 }
	 catch(Exception e) {}
	 return vector;
	 }
	 */
	public String getString(Hashtable box, String key, int index) {
		String value = "";
		Vector v = getVector(box, key);

		try {
			Object o = (Object) v.elementAt(index);
			Class c  =null;
			
			if (o != null) c = o.getClass();
			if (o == null) {
				value = "";
			} else if (c.isArray()) {
				int length = Array.getLength(o);
				if (length == 0) {
					value = "";
				} else {
					Object item = Array.get(o, 0);
					if (item == null) {
						value = "";
					} else {
						value = item.toString();
					} // end if ( item == null )
				} // end if ( length == 0 )
			} else {
				value = o.toString();
			} // end if ( o == null )

		} catch (Exception e) {
			value = "";
		}

		return value;
	}

	/**
	 * @return java.lang.String
	 * @param key java.lang.String
	 */
	public Object getObject(Hashtable box, String key) {
		Object value = null;
		try {
			Object o = (Object) box.get(key);
			Class c = null;
			
			if (o != null) c = o.getClass();
			if (o == null)
				value = new Object();
			else if (c.isArray()) {
				int length = Array.getLength(o);
				if (length == 0)
					value = new Object();
				else {
					Object item = Array.get(o, 0);
					if (item == null)
						value = new Object();
					else
						value = item;
				}
			} else
				value = o;
		} catch (Exception e) {
			value = new Object();
		}
		return value;
	}

	/**
	 * @param type 특정 필드의 데이터 타입
	 * @param value 변환될 값
	 * @return 특정 데이터 타입형식으로 변환된 Object 객체
	 * char, Character 타입의 경우 value(문자열)에서 첫번째 문자의 char값 반환
	 */

	public Object getObjectValueByType(Class type, String value) {
		Object returnValue = null;
		if (byte.class == type || Byte.class == type) {
			returnValue = new Byte(Byte.parseByte(value));
		} else if (int.class == type || Integer.class == type) {
			returnValue = new Integer(Integer.parseInt(value));
		} else if (long.class == type || Long.class == type) {
			returnValue = new Long(Long.parseLong(value));
		} else if (double.class == type || Double.class == type) {
			returnValue = new Double(Double.parseDouble(value));
		} else if (float.class == type || Float.class == type) {
			returnValue = new Float(Float.parseFloat(value));
		} else if (char.class == type || Character.class == type) {
			char[] charArr = value.toCharArray();
			returnValue = (Object) new Character(charArr[0]);
		} else if (boolean.class == type || Boolean.class == type) {
			returnValue = new Boolean(value);
		} else if (String.class == type) {
			returnValue = value;
		}
		return returnValue;
	}

	/**
	 * @param type 특정 필드의 데이터 타입
	 * @param value 변환될 값
	 * @return 특정 데이터 타입형식으로 변환된 Object 객체
	 * char, Character 타입의 경우 value(문자열)에서 첫번째 문자의 char값 반환
	 */
	public Object getObjectValueByType(Class type, Object value) {
		Object returnValue = null;
		/*
		 if(byte.class == type || Byte.class == type){
		 returnValue = new Byte(Byte.parseByte(value));
		 }else if(int.class == type || Integer.class == type){
		 returnValue = new Integer(Integer.parseInt(value));
		 }else if(long.class == type || Long.class == type){
		 returnValue = new Long(Long.parseLong(value));
		 }else if(double.class == type || Double.class == type){
		 returnValue = new Double(Double.parseDouble(value));
		 }else if(float.class == type || Float.class == type){
		 returnValue = new Float(Float.parseFloat(value));
		 }else if(char.class == type || Character.class 	== type){
		 char[] charArr = value.toCharArray();
		 returnValue = (Object)new Character(charArr[0]);
		 }else if(boolean.class == type || Boolean.class == type){
		 returnValue = new Boolean(value);
		 }else
		 */
		if (String.class == type) {
			returnValue = value.toString();
		} else if (value instanceof List) {
			returnValue = (List) value;
		} else if (value instanceof Vector) {
			returnValue = (Vector) value;
		} else if (value instanceof Object) {
			returnValue = value;
		}
		return returnValue;
	}

	public String getError(Exception e, String name) {
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		java.io.PrintWriter writer = new java.io.PrintWriter(bos);
		writer.println("Error Trace: " + name);

		e.printStackTrace(writer);
		writer.flush();

		return e.getMessage() + "\n" + bos.toString();
	}

	/**
	 * @param fullFileName
	 * @return String : fileName
	 *
	 * 필드값으로 넘어온 fullpath에서 실제 파일명만 얻기위한 method
	 */
	public String getFileName(String fullFileName,String split) {
		String fileName = null;
		String[] splitStr = fullFileName.split(split);
		fileName = splitStr[splitStr.length - 1];
		return fileName;
	}

	/**
	 * @param path
	 * @param fileName
	 * @return File
	 *
	 * 업로드할 파일과 같은 파일명을 가진 파일이 존재할 경우
	 * 새로운 이름을 부여후 파일 객체를 생성한다.
	 */
	public File getFile(String path, String fileName) {
		String fn = fileName;
		File f = new File(path, fileName);
		if (f.exists()) {
			String withoutExt = fn.substring(0, fn.lastIndexOf("."));
			String fileExt = fn.substring(fn.lastIndexOf("."));
			fn = withoutExt + System.currentTimeMillis() + fileExt;
			f = new File(path, fn);
		}
		return f;
	}

	/**
	 *
	 * 지정한 정수의 개수 만큼 빈칸(" ")을 스트링을 구한다.
	 *
	 * @param int 문자 개수
	 * @return String 지정된 개수 만큼의 빈칸들로 연결된 String
	 */
	public static String spaces(int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

	/**
	 *
	 * 지정한 정수의 개수 만큼 빈칸(" ")을 스트링을 구한다.
	 * 절단된 String의 바이트 수가 자를 바이트 개수보다 모자라지 않도록 한다.
	 *
	 * @param str 원본 String
	 * @param int 자를 바이트 개수
	 * @return String 절단된 String
	 */
	public String cutStringByBytes(String str, int length) {
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;
		if (length >= len) {
			return str + spaces(length - len);
		}
		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0)
				counter++;
		}
		return new String(bytes, 0, length + (counter % 2));
	}

	/**
	 *
	 * 지정한 정수의 개수 만큼 빈칸(" ")을 스트링을 구한다.
	 * 절단된 String의 바이트 수가 자를 바이트 개수를 넘지 않도록 한다.
	 *
	 * @param str 원본 String
	 * @param int 자를 바이트 개수
	 * @return String 절단된 String
	 */
	public String cutInStringByBytes(String str, int length) {
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;
		if (length >= len) {
			return str + spaces(length - len);
		}
		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0)
				counter++;
		}
		return new String(bytes, 0, length - (counter % 2));
	}

	/*****************************************************
	******************************************************/
	public String decimalPointint(int _count)	{
		String count	= String.valueOf(_count);
		int sum_len		= count.length();
		int rem_len		= sum_len/3;
		int len			= sum_len - (rem_len*3);
		String sum		= null;

		if(count.length() > 3)		{
			if(len == 0)
				sum		= "";
			else
				sum		= count.substring(0,len) + ",";
			for(int i = len; i < sum_len-3; i = i+3)
				sum		+= count.substring(i, i+3) + ",";
				sum		+= count.substring(sum_len-3, sum_len);
		}else{
			sum	= count;
		}
	    return sum;
	}

	public String decimalPointLong(long _count)	{
		String count	= Long.toString(_count);
		int sum_len		= count.length();
		int rem_len		= sum_len/3;
		int len			= sum_len - (rem_len*3);
		String sum		= null;

		if(count.length() > 3)		{
			if(len == 0)
				sum		= "";
			else
				sum		= count.substring(0,len) + ",";
			for(int i = len; i < sum_len-3; i = i+3)
				sum		+= count.substring(i, i+3) + ",";
				sum		+= count.substring(sum_len-3, sum_len);
		}else{
			sum	= count;
		}
	    return sum;
	}

	public String decimalPointString(String  _count)	{
		String count	= _count;
		int sum_len		= count.length();
		int rem_len		= sum_len/3;
		int len			= sum_len - (rem_len*3);
		String sum		= null;

		if(count.length() > 3)		{
			if(len == 0)
				sum		= "";
			else
				sum		= count.substring(0,len) + ",";
			for(int i = len; i < sum_len-3; i = i+3)
				sum		+= count.substring(i, i+3) + ",";
				sum		+= count.substring(sum_len-3, sum_len);
		}else{
			sum	= count;
		}
	    return sum;
	}

	/*****************************************************
	 * 9999-12-12 00:00:00.0 -> 9999년 12월 12일 00시 00분 00초	 	 2007.09.06 강용준 추가
	 * flag 0 -> 9999년 12월 12일
	 * flag 1 -> 9999년 12월 12일 00시 00분
	 * else -> 9999년 12월 12일 00시 00분 00초
	******************************************************/
	public String convertDateToString(String dateString, int flag) {
		String yyyy = "";
		String mm = "";
		String dd = "";
		String clock = "";
		String min = "";
		String second = "";
		String returnValue = "";

		String [] tmpDate = dateString.split("-");
		if(tmpDate.length == 3) {
			yyyy = tmpDate[0];
			mm = tmpDate[1];
			dd = tmpDate[2].substring(0, 2);
			if(tmpDate[2].length() > 2) {
				dateString = tmpDate[2].substring(3);
				tmpDate = dateString.split(":");

				returnValue = yyyy +"년 "+ mm +"월 "+ dd + "일 ";
				if(flag == 1) {
					if(tmpDate.length == 2) {
						clock = tmpDate[0];
						min = tmpDate[1];
					}
					if(clock=="") { clock = "00"; }
					if(min=="") { min = "00"; }
					returnValue = returnValue + clock + "시 " + min + "분 ";
				} else if(flag != 0){
					if(tmpDate.length == 3) {
						clock = tmpDate[0];
						min = tmpDate[1];
						second = tmpDate[2].substring(0, 2);
					}
					if(clock=="") { clock = "00"; }
					if(min=="") { min = "00"; }
					if(second=="") { second = "00"; }
					returnValue = returnValue + clock + "시 " + min + "분 " + second + "초";
				}
			} else {
				returnValue = yyyy +"년 "+ mm +"월 "+ dd + "일 ";
			}
		}
		return returnValue;
	}
    /**
     * 문자열을 배열로 리턴
     * @param txt 배열로 리턴할 문자열 ex) a,b,c
     * @param deli 구분자 ex) ,
     * @return 배열 ex) a b c 각각 배열
     */
    public String[] getArrayFromString(String txt, String deli) {
        String str = txt;
        StringTokenizer st = new StringTokenizer(str, deli);
        String arrayStr[] = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++)
            arrayStr[i] = st.nextToken();
        return arrayStr;
    }

    /**
     * 우편번호 포맷
     * @param str
     * @return
     * 		모든 문자열을 "000-000" 형태로 리턴한다. (6자리로 고정시킴)
     */
    public static String PostNoFormat(String str) {
    	if (str == null)
    		return "";

    	str = str.replaceAll("-", "");

    	if 		(str.length() < 1)	return "";
    	else if (str.length() < 4)  return str;
    	else if (str.length() > 6)	return (str.substring(0,3) + "-" + str.substring(3,6));

    	return (str.substring(0,3) + "-" + str.substring(3,str.length()));
    }

    /**
     * 문자열 공백검사
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
    	return (str == null || str.length() < 1);
    }

    /**
     * 문자배열 공백검사
     * @param arr
     * @return
     */
    public static boolean isEmpty(String[] arr) {
    	return (arr == null || arr.length < 1);
    }

    /**
     * List 공백검사
     * @param lst
     * @return
     */
    public static boolean isEmpty(List lst) {
    	return (lst == null || lst.size() < 1);
    }

    /**
     * 숫자 스트링에서 콤마(,) 부호(+)를 제거한다.
     * @param s
     * @return
     */
    public static String numberFilter(String s) {
    	String str = s;
		if (str != null) {
			str = str.replace(",", "");
			str = str.replace("+", "");
		}
		return str;
    }

    /**
	 * int 숫자변환
	 * @param str
	 * @return
	 */
    public static int parseInt(String str) {
    	try {
    		str = numberFilter(str);
    		return Integer.parseInt(str);
    	} catch (NumberFormatException e) {
    		//System.out.println(e.toString());
    		return 0;
    	}
    }

	/**
	 * long 숫자변환
	 * @param str
	 * @return
	 */
    public static long parseLong(String str) {
    	try {
    		str = numberFilter(str);
    		return Long.parseLong(str);
    	} catch (NumberFormatException e) {
    		//System.out.println(e.toString());
    		return 0L;
    	}
    }

	/**
	 * double 숫자변환
	 * @param str
	 * @return
	 */
    public static double parseDouble(String str) {
    	try {
    		str = numberFilter(str);
    		return Double.parseDouble(str);
    	} catch (NumberFormatException e) {
    		//System.out.println(e.toString());
    		return 0.0d;
    	}
    }

	/**
	 * float 숫자변환
	 * @param str
	 * @return
	 */
    public static float parseFloat(String str) {
    	try {
    		str = numberFilter(str);
    		return Float.parseFloat(str);
    	} catch (NumberFormatException e) {
    		//System.out.println(e.toString());
    		return 0.0f;
    	}
    }

	/**
	 * min, max 사이의 랜덤숫자 반환
	 * @param min
	 * @param max
	 * @return
	 */
	public static long random(long min, long max) {
		if (min > max) {
			long temp = min;
			min = max;
			max = temp;
		}

		if (min == max) {
			return min;
		}

		long bound = max - min;

		/**
		 * Math.random() = 0.0 ~ 0.999999... 의 난수발생
		 */
		long num = min + Math.round(Math.random()*bound);

		return num;
	}


    /**
     * 카드 번호에 뒤에서 부터 idx까지 '*'를 추가한여 반환한다.
     *
     * @param cardNo 카드번호
     * @param idx '*'표시할 갯수
     * @return
     */
	public static String getCardNo(String cardNo, int idx) {

        if( cardNo == null ) {
            return "";
        }

        if( idx <= cardNo.length() ) {
            return cardNo;
        }

        int loopCnt = 0;

        char[] tempChar = cardNo.toCharArray();

        for( int i=cardNo.length()-1; i>=0; i-- ) {
            if( loopCnt < idx ) {
               tempChar[i] = '*';
            } else {
                break;
            }
            loopCnt++;
        }

        return String.valueOf(tempChar);
    }

    /**
     * 두 날짜 사이에 들어가는지 확인.
     *
     * @param sDate:시작날짜
     * @param eDate:끝날짜
     * @return -1:이전/0:포함/1:이후
     */
	public int compareToDate(String sDate,String eDate,String tDate) {

		long sRet = 0;
		long eRet = 0;
		int retNum = 0;
		sRet = getSubtractDay(sDate,tDate);
		eRet = getSubtractDay(eDate,tDate);

		if(sRet < 0){
			retNum = -1;
		}else if((sRet > 0) && (eRet < 0)){
			retNum = 0;
		}else if(eRet > 0){
			retNum = 1;
		}

        return retNum;
    }

	/**
	 *  XML 생성시 Excape 문자 치환
	 *
	 * @param String s
	 * @return String
	 */
	public static String getXMLEscapeString(String s) {
		if (s == null)
			return s;

		//replaceAll 사용시 순서에 주의
		s = s.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("'","&#39;").replaceAll("<", "&lt;").replaceAll(">","&gt;");

		return s;
	}

	public static String makeXmlNode(String key, String value) {
		if (key == null || key.length() <= 0) {
			return "";
		}

		if (value == null) {
			value = "";
		}

		String str = "<" + key + ">";
		str += getXMLEscapeString(value);
		str += "</" + key + ">";
		return str;
	}

	/**
     * vaule가 null일 경우 "" return
     * @param value
     * @return
     */
    public static String nvl(String value) {
        return nvl(value, "");
    }

    /**
     * vaule가 null일 경우 defaultValue return
     * @param value
     * @return
     */
    public static String nvl(String value, String defaultValue) {
        if (value == null || value.equals(""))
            return defaultValue;
        else
            return value;
    }

    /**
     * sql의 in , not in 과 같은 기능을 하는 함수
     * inNotinFlag가 IN 경우 source에  target의 값과 같은것이 몇개인지 리턴
     * <P/>
     * 상세한 메소드 설명.
     *
     * @param source    "01,02,03"
     * @param target    "03"
     * @param inNotinFlag IN,in 일경우 예제 파라메터로는 1을 리턴
     * @return
     */
    public static int getInStr(String source,String target)
    {
        int retVal=0;
        boolean chkFlag= false;

        if ( source == null || target ==  null ) return 0;

        String[] sourceArr = source.split(",");
        String[] targetArr = target.split(",");

        for (int i=0;i<sourceArr.length;i++)
        {
            for (int j=0;j < targetArr.length ;j++)
            {
                    // 문구가 존재하는것을 count 할경우
                    if ( sourceArr[i].equals(targetArr[j]))    chkFlag = true;
            }

            if (chkFlag) retVal++;
            chkFlag = false;
        }

        return retVal;


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
    public static Date parse(String str, String pattern) throws Exception {
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
        } catch (Exception e) {

            throw new Exception(" wrong date:\"" + str+ "\" with format \"" + pattern + "\"");
        }

        if (!getFormatDate(date, pattern).equals(str))
            throw new Exception("Out of bound date:\"" + str+ "\" with format \"" + pattern + "\"");
        return date;
    }


    public static String getFormatDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String getFormatDate(Date date) {
        String str = getFormatDate(date, "yyyy-MM-dd");
        if (str.equals("0001-01-01")) {
            str = "";
        }
        return str;
    }


    /**
     * <pre>
     *      주어진 일시를 add만큼 더한다.
     *      더하는 필드는 두번째 인수인 int when에 따른다.
     *      when 값은 아래의 3가지 중에 선택할 수 있다.
     *      년 : java.util.Calendar.YEAR
     *      월 : java.util.Calendar.MONTH
     *      일 : java.util.Calendar.DATE
     * </pre>
     *
     * @see java.util.Calendar
     * @param date
     *            {java.util.Date} 기준이 되는 일시
     * @param when
     *            수정할 필드
     * @param add
     *            더할 수
     * @return java.util.Date
     */
    public static Date addDate(Date date, int when, int add) {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        calendar.setTime(date);
        calendar.set(when, calendar.get(when) + add);
        return calendar.getTime();
    }


    /**
     * 주어진 일시의 일을 add만큼 더한다.
     *
     * @see com.skcomm.commerce.frm.util.DateUtil#addDate(Date, int, int);
     * @param date
     *            {java.util.Date} 기준이 되는 일시
     * @param add
     *            더할 수
     * @return java.util.Date
     */
    public static Date addDays(Date date, int add) {
        return addDate(date, Calendar.DATE, add);
    }

   private final static String maskFormat = "******************************";
    /**
     *@author ()
     * 원본데이타를  마스크(*)처리 한다.
     * stage -- > stage****
     *   char aaa [] = " 바보".toCharArray();
     *   Arrays.fill(aaa, 2, 4, '*');
     *   System.out.println(new String(aaa));
     *   위 처럼 할 수도 있게지만, 판매관리 툴에서 많은 캐스팅 비용을 감안하여 ...
     * @param orignal
     * @param idx
     * @param format
     * @return
     */
    public static String getMaskingView(String original, int idx, String format) {

        StringBuilder masking  = null;
        try {

            masking  = new StringBuilder(original);
            int index = idx == 0 ? 4 : idx;
            String viewFormat = format == null ? maskFormat : format;

            int maskingLength = masking.length();
            if (index > 0 ) {
                int limits = maskingLength - index;

                index =  limits > 0 ? index : maskingLength/2;

                masking.replace(index, maskingLength, viewFormat.substring(0,limits));
            } else {
                //index 가 마이너스이면 앞에서 부터 index만큼 처리함
                int limits = maskingLength;
                index =  limits - -index;
                masking.replace(index, maskingLength, viewFormat.substring(0,-idx));
            }


            return masking.toString();
        }catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 특정 문자의 가운데 전체 마스킹 처리
     * ex)  --> 장*효
     *     는 바보다 --> 어******다
     * @param original
     * @param format
     * @return
     */
    public static String getMaskingViewMiddle(String original, String format) {
        StringBuilder masking  = null;
        try {
            masking  = new StringBuilder(original);
            String viewFormat = format == null ? maskFormat : format;
            int maskingLength = masking.length() -1;

            masking.replace(1, maskingLength , viewFormat.substring(1,maskingLength));
            return masking.toString();
        }catch (Exception ignore) {
            return null;
        }
    }

    /**
     * @author 
     * xml 1.0 에서 표현할 수 없는 문자를 제거 한다.
     * @param s
     * @param replaceChar
     * @return
     */
    public static String replaceInvalidTitle(String s, char replaceChar) {
        if( s == null) return s;

        s = s.replaceAll("&", "").replaceAll("\"", "").replaceAll("'","").replaceAll("<", "").replaceAll(">","");
        StringBuffer sb = new StringBuffer(s.length());
        char ch;
        for( int i = 0; i < s.length(); i++)
        {
            ch = s.charAt(i);
            if(
                !(ch < 0xFF
                ||(ch > 0x3130 && ch < 0x318F)
                ||(ch >= 0xAC00 && ch <= 0xD7A3))
            ) {
                sb.append("");
            }else {
                sb.append(ch);
            }

        }
        return sb.toString();
    }


    /**
     * @author 
     *
	 *	http://www.w3.org/TR/REC-xml/#sec-cdata-sect
	 *	문서에 보면 CDATA는
	 *	CDATA Sections
	 *	CDSect    ::=    CDStart CData CDEnd
	 *  CDStart    ::=    '<![CDATA['
	 *	CData    ::=    (Char* - (Char* ']]>' Char*))
	 *  CDEnd    ::=    ']]>'
 	 * 로 정의 되었습니다.
 	 * 여기에 Char 쪽을 살펴 보면
 	 * http://www.w3.org/TR/REC-xml/#NT-Char
   	 * Character Range
	 * Char    ::=    #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
	 * any Unicode character, excluding the surrogate blocks, FFFE, and FFFF
     *
     * @param s
     * @param replaceChar
     * @return
     */
    public static String replaceInvalidXMLChar(String s, char replaceChar) {
        if( s == null) return s;

        StringBuffer sb = new StringBuffer(s.length());
        char ch;
        for( int i = 0; i < s.length(); i++)
        {
            ch = s.charAt(i);
            if(
                !(ch == 0x0009
                || ch == 0x000A
                || ch == 0x000D
                ||(ch >= 0x0020 && ch <= 0xD7FF)
                ||(ch >= 0xE000 && ch <= 0xFFFD)
                ||(ch >= 0x10000 && ch <= 0x10FFFF))
            )
                sb.append( replaceChar);
            else
                sb.append( ch);

        }
        return sb.toString();
    }

    /**
     * @
     * 특정 데이타를 끝에서 부터 마스킹(*) 처리 한다.
     */
    public static String replaceDataMasking(String original, int maskSize, String format) {

    	String maskingData = original;

    	if (maskingData == null)
    		return "";

    	int maskingSize = maskingData.length();
    	int idx = maskSize;
    	String maskingFormat = format == null ? "****" : format;

        if(maskingData != null && maskingSize > idx) {
        	maskingData = maskingData.substring(0, maskingSize - idx).concat("****");
        	//maskingData = maskingData.replaceAll(maskingData.substring(maskingSize - idx), maskingFormat);
        }

        return maskingData;

    }

    /**
     * <PRE>
     * 1. MethodName : roundLong
     * 2. ClassName  : CmnUtil
     * 3. Comment   :  정수부분에 대한 반올림 처리
     * 					roundLong(111.11, 0) :  110 리턴
     * 					roundLong(115.11, 0) :  120 리턴
     * 					roundLong(111.11, 1) :  100 리턴
     * 					roundLong(151.11, 1) :  200 리턴
     * 4. 작성자    : 장재훈
     * 5. 작성일    : 2010. 5. 18. 오후 4:26:03
     * </PRE>
     *   @return long
     *   @param num  		반올림대상값
     *   @param roundPos	정수반올림자릿수
     *   @return
     */
    public static long roundLong(long num, long roundPos)
    {

		long   result = 0;

		// 정수자리 반올림할 값이  0보다 작으면  0 리턴
		if (num <= 0 ) return 0;
		// 정수이상에서 반올림 처리


		// 반올림을 하기 위해서 반올림을 원하는 자리의 숫자를
		try
		{
		num = num + ( 5 * (long)Math.pow(10,roundPos));
		result = (long)(num / Math.pow(10,roundPos+1)) * (long)Math.pow(10,roundPos+1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}


		return result;

    }

    /**
     * <PRE>
     * 1. MethodName : numTrunc
     * 2. ClassName  : CmnUtil
     * 3. Comment   :  숫자를 절삭처리한다 numTrunc(117,1) => 110 리턴 numTrunc(117,2) => 100 리턴
     * 4. 작성자    : 장재훈
     * 5. 작성일    : 2010. 7. 9. 오전 11:40:03
     * </PRE>
     *   @return long
     *   @param num				: 절삭값
     *   @param truncPos		: 절삭위치(최소 1이상)
     *   @return
     */
    public static long numTrunc(long num, long truncPos)
    {
		long   result = 0;
		long   truncNum = 10;

		result = num;

		if (truncPos <= 0 ) return result;

		truncNum =  truncNum * truncPos;

		try
		{
			result  = (num / truncNum) * truncNum;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return result;
		}


		return result;
    }


    /**
     * http url의 파일을 다운로드 한다.
     * @param address
     * @param localFileName
     */
    public static boolean httpFileDownload(String address, String localFileName) { 
        OutputStream out = null; 
        URLConnection conn = null; 
        InputStream  in = null;
        boolean retVal = false;
        try 
        { 
            URL url = new URL(address);
            
            out = new BufferedOutputStream( new FileOutputStream(localFileName)); 
            conn = url.openConnection();
            conn.setConnectTimeout(3000);	// connect timeout 3초 설정
            
            in = conn.getInputStream();
            
            byte[] buffer = new byte[1024*10]; 
            int numRead; 
            long numWritten = 0; 
            while ((numRead = in.read(buffer)) != -1) 
            { 
                out.write(buffer, 0, numRead); 
                numWritten += numRead; 
            }
            
            retVal = true;
            
        } catch (Exception e) 
        { 
            e.printStackTrace(); 
        } finally 
        { 
            try { 
                if (in != null) { 
                    in.close(); 
                } 
                if (out != null) { 
                    out.close(); 
                } 
            } catch (IOException e)
            { 
            	e.printStackTrace(); 
            } 
        } 
        
        return retVal;
    } 
    
    


    /*
     * html 테그를 모두 없앤다.
     * */
    public static String  removeHtmlTag(String buffer)
    {
    	return  buffer.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }
    
    /*
     * html 테그를 모두 없앤다.(태그 아닌 꺽쇠 포함)
     * */
    public static String  removeFullHtmlTag(String buffer)
    {
    	return  buffer.replaceAll("\\<.*?\\>", "");
    }
    
    
    /**
     * http url의 파일을 다운로드 한다.
     * @param address
     * @param localFileName
     */
    public static boolean httpFileDownload2(String address, String localFileName) { 
        OutputStream out = null; 
        URLConnection conn = null; 
        InputStream  in = null;
        boolean retVal = false;
        try 
        { 
            URL url = new URL(address);
            
            out = new BufferedOutputStream( new FileOutputStream(localFileName)); 
            conn = url.openConnection();
            conn.setConnectTimeout(3000);	// connect timeout 3초 설정
            
            in = conn.getInputStream();
            
            byte[] buffer = new byte[1024*10]; 
            int numRead; 
            long numWritten = 0; 
            while ((numRead = in.read(buffer)) != -1) 
            { 
                out.write(buffer, 0, numRead); 
                numWritten += numRead; 
            }
            
            retVal = true;
            
        } catch (Exception e) 
        { 
            e.printStackTrace(); 
        } finally 
        { 
            try { 
                if (in != null) { 
                    in.close(); 
                } 
                if (out != null) { 
                    out.close(); 
                } 
            } catch (IOException e)
            { 
            	e.printStackTrace(); 
            } 
        } 
        
        return retVal;
    } 
    
    /**
     * http url의 파일을 다운로드 한다.
     * @param address
     * @param localFileName
     */
    public static boolean httpFileDownload3(String address, String localFileName) { 
        OutputStream out = null; 
        URLConnection conn = null; 
        InputStream  in = null;
        boolean retVal = false;
        try 
        { 
            URL url = new URL(address);
            
            out = new BufferedOutputStream( new FileOutputStream(localFileName)); 
            conn = url.openConnection();
            conn.setConnectTimeout(3000);	// connect timeout 3초 설정
            
            in = conn.getInputStream();
            
            byte[] buffer = new byte[1024*10]; 
            int numRead; 
            long numWritten = 0; 
            while ((numRead = in.read(buffer)) != -1) 
            { 
                out.write(buffer, 0, numRead); 
                numWritten += numRead; 
            }
            
            retVal = true;
            
        } catch (Exception e) 
        { 
            e.printStackTrace(); 
        } finally 
        { 
            try { 
                if (in != null) { 
                    in.close(); 
                } 
                if (out != null) { 
                    out.close(); 
                } 
            } catch (IOException e)
            { 
            	e.printStackTrace(); 
            } 
        } 
        
        return retVal;
    } 
    

    public static String getOnlyNumberString(String str) {
    	if (str == null)	return "";

    	StringBuffer sb = new StringBuffer();
    	int length = str.length();
    	for (int i = 0; i < length; i++) {
    		char curChar = str.charAt(i);
    		if (Character.isDigit(curChar))
    			sb.append(curChar);
    	}
    	return sb.toString();
    }

    

} // end of class