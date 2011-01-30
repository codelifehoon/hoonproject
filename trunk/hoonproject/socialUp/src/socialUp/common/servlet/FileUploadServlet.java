package socialUp.common.servlet;

import javax.imageio.ImageIO;
import javax.media.jai.Interpolation;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.RequestDispatcher;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import socialUp.common.AuthInfo;
import socialUp.common.AuthService;
import socialUp.common.ServiceFactory;
import socialUp.common.util.CmnUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.ImgProcUtil;
import socialUp.service.content.ResourceMngService;
import socialUp.service.content.ResourceMngServiceImpl;
import socialUp.service.content.dto.UploadFilesDTO;

/**
 * This is a File Upload Servlet that is used with AJAX to monitor the progress
 * of the uploaded file. It will return an XML object containing the meta
 * information as well as the percent complete.
 * 
 * @author Frank T. Rios
 * 
 *         Initial Creation Date: 6/24/2007
 */
public class FileUploadServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 2740693677625051632L;
	public Logger log = Logger.getLogger(this.getClass());

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter out = response.getWriter();
		// HttpSession session = request.getSession();
		// FileUploadListener listener = null;
		// StringBuffer buffy = new StringBuffer();
		// long bytesRead = 0,
		// contentLength = 0;
		//
		// // Make sure the session has started
		// if (session == null)
		// {
		// return;
		// }
		// else if (session != null)
		// {
		// // Check to see if we've created the listener object yet
		// listener = (FileUploadListener)session.getAttribute("LISTENER");
		//
		// if (listener == null)
		// {
		// return;
		// }
		// else
		// {
		// // Get the meta information
		// bytesRead = listener.getBytesRead();
		// contentLength = listener.getContentLength();
		// }
		// }
		//
		// /*
		// * XML Response Code
		// */
		// response.setContentType("text/xml");
		//
		// buffy.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
		// buffy.append("<response>\n");
		// buffy.append("\t<bytes_read>" + bytesRead + "</bytes_read>\n");
		// buffy.append("\t<content_length>" + contentLength +
		// "</content_length>\n");
		//
		// // Check to see if we're done
		// if (bytesRead == contentLength)
		// {
		// buffy.append("\t<finished />\n");
		//
		// // No reason to keep listener in session since we're done
		// session.setAttribute("LISTENER", null);
		// }
		// else
		// {
		// // Calculate the percent complete
		// long percentComplete = ((100 * bytesRead) / contentLength);
		//
		// buffy.append("\t<percent_complete>" + percentComplete +
		// "</percent_complete>\n");
		// }
		//
		// buffy.append("</response>\n");
		//
		// out.println(buffy.toString());
		// out.flush();
		// out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AuthInfo authInfo = null;

		try {
			authInfo = AuthService.getAuthInfo(request, response);
			if (!authInfo.isAuth())
				throw new Exception("로그인한 사용자가 아닙니다.");

			ResourceMngService resourceMngService = (ResourceMngService) ServiceFactory
					.createService(ResourceMngServiceImpl.class);
			UploadFilesDTO uploadFilesParam = new UploadFilesDTO();

			// create file upload factory and upload servlet
			FileItemFactory factory = new DiskFileItemFactory();
			// factory.setRepository(temporaryDir); -- 임시저장파일 위치
			ServletFileUpload upload = new ServletFileUpload(factory);
			// set file upload progress listener
			FileUploadListener listener = new FileUploadListener();
			CmnUtil cmnUtil = new CmnUtil();

			String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
			HttpSession session = request.getSession();

			session.setAttribute("LISTENER", listener);

			// upload servlet allows to set upload listener
			upload.setProgressListener(listener);

			List uploadedItems = null;
			FileItem fileItem = null;
			String fileUrl = "/files";
			String filePath = "D:/javadevtool/workSpace/socialUp/WebContent/files"; // Path

			filePath += "/" + sCurrentDate.substring(0, 8);
			fileUrl  += "/" + sCurrentDate.substring(0, 8);

			
			// image 저장경로 확인및 설정
			{
				File fileIO = new File(filePath);
				File thumbFileIO = new File(filePath + "/thumb");

	            if (!fileIO.exists()) 
				  {
	            	// 폴더생성
	            	if (!fileIO.mkdirs()) { throw new Exception("폴더 생성 오류 발생."); } 
				  }
	            
	            if (!thumbFileIO.exists()) 
				  {
		          	// 폴더생성
		          	if (!thumbFileIO.mkdirs()) { throw new Exception("thumb 폴더 생성 오류 발생."); } 
				  }
			}
            
			upload.setSizeMax(1024 * 1024 * 2); // 전체 upload 되는 파일 사이즈 check
												// (1024*1024 1m)
			// iterate over all uploaded files
			uploadedItems = upload.parseRequest(request);

			Iterator i = uploadedItems.iterator();

			int ii = 0;
			while (i.hasNext()) {

				ii++;
				fileItem = (FileItem) i.next();

				log.debug("fileItem.getSize():" + fileItem.getSize());
				log.debug("ii:" + ii);

				if (fileItem.isFormField() == false) 
				{
					if (fileItem.getSize() > 0) 
					{
						File uploadedFile = null;
						String myFullFileName = fileItem.getName(), myFileName = "", slashType = (myFullFileName.lastIndexOf("\\") > 0) ? "\\" : "/"; // Windows or UNIX

						int startIndex = myFullFileName.lastIndexOf(slashType);

						// Ignore the path and get the filename
						myFileName = myFullFileName.substring(startIndex + 1,
								myFullFileName.length());
						myFileName = sCurrentDate + String.valueOf(ii)
								+ authInfo.getMt_no() + "_" + myFileName;

						String fileType = cmnUtil.fileType(myFileName);

						// Create new File object
						uploadedFile = new File(filePath, myFileName);

						// Write the uploaded file to the system
						fileItem.write(uploadedFile);

						// upload파일내용 DB에 저장
						uploadFilesParam.setFile_name(myFileName);
						uploadFilesParam.setCd_no("0");
						uploadFilesParam.setFile_path(fileUrl);
						uploadFilesParam.setFile_kind("01"); // 이미지
						uploadFilesParam.setFile_size(String.valueOf(fileItem.getSize()));
						uploadFilesParam.setUse_yn("Y");
						uploadFilesParam.setCreate_no(authInfo.getMt_no());
						uploadFilesParam.setCreate_dt(sCurrentDate);
						resourceMngService.insertUploadFiles(uploadFilesParam);
						
						// 이미지 썸네일 생성
						{
							UploadFilesDTO soParam = new UploadFilesDTO();
							UploadFilesDTO taParam = new UploadFilesDTO();

							soParam.setFile_path(filePath);
							soParam.setFile_name(myFileName);
							
							taParam.setFile_path(filePath + "/thumb");
							taParam.setFile_name(myFileName);
							
							ImgProcUtil.createResizeImg(soParam,taParam,80) ;	
						}
						
						
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("reloadFlag", "true"); // jsp가 나중에 읽을수 있게
													// Request객체의 속성(Attribute)에
													// 값을 설정

		// 나중에 styles라는 이름으로 jsp에서 객체를 읽어 올수 있다
		RequestDispatcher view = request
				.getRequestDispatcher("/jsp/content/fileUploadForm.jsp"); // jsp로
																			// 작업을
																			// 부탁할
																			// RequestDispatcher
																			// 를
																			// 인스턴스한다
		view.forward(request, response); // RequestDispatcher는 컨테이너에게 jsp를 준비하라고
											// 요청한다,

	}
}
