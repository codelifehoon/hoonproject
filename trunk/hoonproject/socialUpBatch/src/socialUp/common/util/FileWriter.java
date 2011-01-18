package socialUp.common.util;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FileWriter {
	private static Log log = LogFactory.getLog(FileWriter.class);
    /**
    *
    * 파일 생성
    *
    * @param fileName, filePath, str
    * @return
    * @throws
    */
   public static void write(long batchNo, String batchNm, String fileName, String filePath, StringBuilder str) {
//
//       try{
//    	   
//           log.debug("filePath : " + filePath);
//           String saveDirectory = filePath;
//
//           // 파일경로의 존재유무 검사
//           File file1 = new File(saveDirectory);
//           if (!file1.exists()) {
//               // 폴더생성
//               if (!file1.mkdirs()) {
//                   throw new FileNotFoundException(batchNm + "폴더 오류 발생 !");
//               }
//           }
//
//           log.debug("fileName : " + fileName);
//           log.debug("saveDirectory : " + saveDirectory);
//
//           String filePath1 = saveDirectory+fileName;
//           new TmallFileGenerator().generateXMLforStream(str.toString(),filePath1);
//        }
//        catch (IOException e)
//        {
//           e.printStackTrace();
//        }
   }
}
