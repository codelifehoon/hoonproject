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
    * ���� ����
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
//           // ���ϰ���� �������� �˻�
//           File file1 = new File(saveDirectory);
//           if (!file1.exists()) {
//               // ��������
//               if (!file1.mkdirs()) {
//                   throw new FileNotFoundException(batchNm + "���� ���� �߻� !");
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
