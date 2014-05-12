
package com.zebra.common.util;

import java.awt.Graphics2D;


import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.BufferedReader;


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

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * @author 장재훈
 *
 */
public class ImgProcUtil 
{
	
	private static final Logger	log	= Logger.getLogger(CookieUtil.class);
	/*
	*//**
	 * 
	 * @param soParam
	 * 					soParam.getFile_path() : 원본파일 경로
	 * 					soParam.getFile_name()  : 파일명
	 * @param taParam
	 * 					taParam.getFile_path() : 리사이즈파일 경로
	 * 					taParam.getFile_name()  :리사이즈파일명
	 * @param thumbSize 변경할려는 사이즈(기준)
	 * @return
	 *//*
	public static boolean createResizeImg(UploadFilesDTO  soParam,UploadFilesDTO  taParam,float thumbSize)
	{
		 int imgWidth 	= 0;
		 int imgHeight	= 0;
		 int imgWidthNew 	= 0;
		 int imgHeightNew	= 0;
		 boolean retVal = true;
		 
		 try
		 {
		 // 이미지 리사이즈
		 ParameterBlock pb = new ParameterBlock();
		 
		 // full file name이 있으면 그걸 사용하고없으면 분리된걸 사용한다.
		 if (!"".equals(CmnUtil.nvl(soParam.getFileFullName()))) pb.add(soParam.getFileFullName());
		 else pb.add(soParam.getFile_path() + "/" + soParam.getFile_name());
		 
		 RenderedOp  rOp = JAI.create("fileload", pb);
		 
		 
		 imgWidth  = rOp.getWidth();
		 imgHeight =  rOp.getHeight();
		 
		 
		 
		 // 가로가 큰 이미지 일때 가로를 기준으로 이미지 비율산정(축소비율:80.0/imgWidth)
		 if (imgWidth > imgHeight)
		 {
			 imgWidthNew  	= (int)((thumbSize/imgWidth) * imgWidth);
			 imgHeightNew  	= (int)((thumbSize/imgWidth) * imgHeight);
		 }
		 else
		 {
			 imgWidthNew  	= (int)((thumbSize/imgHeight) * imgWidth);
			 imgHeightNew  	= (int)((thumbSize/imgHeight) * imgHeight);
		 }
		 
		 if (log.isDebugEnabled())
		 {
			 log.debug("원본파일경로 :" + soParam.getFile_path() + "/" + soParam.getFile_name());
			 log.debug("thumb파일경로:" + taParam.getFile_path() + "/" + taParam.getFile_name());
			 log.debug("원본파일경로 :" + soParam.getFileFullName());
			 log.debug("thumb파일경로:" + taParam.getFileFullName());
			 log.debug("imgWidth:" + imgWidth);
			 log.debug("imgHeight:" + imgHeight);
			 log.debug("imgWidthNew:" + imgWidthNew);
			 log.debug("imgHeightNew:" + imgHeightNew);
			 log.debug("thumbSize/imgHeight:" + thumbSize/imgHeight);
		 }

		 // JAI.create()의 두번째 인자로 각각을 넣어줄 수도 있지만 JAI 1.1 이후 deprecated되었으므로 ParameterBlock를 생성된하여 넘기는 방법을 쓰자,
		 // 생성한 ParameterBlock객체에 .add로 파일명을 넣어주면 된다.
		 BufferedImage im = rOp.getAsBufferedImage();

		 // 입력 파일에 대해 BufferedImage형식으로 받아오고,
		 BufferedImage thumb = new BufferedImage(imgWidthNew, imgHeightNew, BufferedImage.TYPE_INT_RGB);

		//썸네일(리사이즈)이미지를 위한 공간을 만든다. 50,50은 width, height되겠다.
		 Graphics2D g2 = thumb.createGraphics();
		
		 //썸네일 버퍼공간에 대해 Graphics2D객체를 얻어와서 입력이미지에 있는 내용을 그린다.(0,0위치에 50,50크기로 복사)
		 g2.drawImage(im, 0, 0, imgWidthNew, imgHeightNew, null);


		  //출력파일에 대한 객체를 만들고 ImageIO.write로 출력.
		  File outfile = null;
		  
		  // full file name이 있으면 그걸 사용하고없으면 분리된걸 사용한다.
		  if (!"".equals(CmnUtil.nvl(taParam.getFileFullName()))) outfile = new File(taParam.getFileFullName());
		  else outfile =  new File(taParam.getFile_path()  + "/" + taParam.getFile_name() );
			 
		  
		  ImageIO.write(thumb, "jpg", outfile);
		 }catch(Exception e)
		 {
			 retVal = false;
			 e.printStackTrace();
		 }
		 return retVal;
	}*/
	
} // end of class