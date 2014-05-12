package com.zebra.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.zebra.process.parser.domain.ExpPattenBO;

import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class PattenUtil {

	private static final Logger log = Logger.getLogger(PattenUtil.class.getName());
	
	
	public static boolean pattenMaches(ExpPattenBO[] pattens,String inputStr)
	{
		boolean flag= false;
		for (ExpPattenBO patten : pattens )
		{       
				Pattern FILTERS = Pattern.compile(patten.getPattenStr());
	      		flag = FILTERS.matcher(inputStr).matches();
	      		if (flag) break;
		}
		
		return flag;
	}
	
	public static String pattenString(ExpPattenBO[] pattens,String inputStr)
	{
		String retVal= "";
		for (ExpPattenBO patten : pattens )
		{           		
				Pattern FILTERS = Pattern.compile(patten.getPattenStr());
				Matcher matcher = FILTERS.matcher(inputStr);
	      						
				if (matcher.find()) retVal = matcher.group(); 
	      		if (!retVal.equals("")) break;
		}
		
		return retVal;
	}
	

	
	
	
}
