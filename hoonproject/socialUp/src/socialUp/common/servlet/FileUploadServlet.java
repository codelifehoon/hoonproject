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
			String filePath = "D:\\javadevtool\\workSpace\\socialUp\\WebContent\\files"; // Path

			filePath += "\\" + sCurrentDate.substring(0, 8);
			fileUrl  += "//" + sCurrentDate.substring(0, 8);

			
			// image 저장경로 확인및 설정
			{
				File fileIO = new File(filePath);
				File thumbFileIO = new File(filePath + "\\thumb");

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
						String myFullFileName = fileItem.getName(), myFileName = "", slashType = (myFullFileName
								.lastIndexOf("\\") > 0) ? "\\" : "/"; // Windows
																		// or
																		// UNIX
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
						
						

						{
							 int imgWidth 	= 0;
							 int imgHeight	= 0;
							 int imgWidthNew 	= 0;
							 int imgHeightNew	= 0;
							 float thumbSize 	= 80;
							 
							 // 이미지 리사이즈
							 ParameterBlock pb = new ParameterBlock();
							 
							 pb.add(filePath + "\\" + myFileName);
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
								 log.debug("원본파일경로 :" + filePath + "\\" + myFileName);
								 log.debug("thumb파일경로:" + filePath + "\\thumb\\" + myFileName);
								 log.debug("imgWidth:" + imgWidth);
								 log.debug("imgHeight:" + imgHeight);
								 log.debug("imgWidthNew:" + imgWidthNew);
								 log.debug("imgHeightNew:" + imgHeightNew);
								 log.debug("thumbSize/imgHeight:" + thumbSize/imgHeight);
							 }
	
							 // JAI.create()의 두번째 인자로 각각을 넣어줄 수도 있지만 JAI 1.1 이후 deprecated되었으므로 ParameterBlock를 생성하여 넘기는 방법을 쓰자,
							 // 생성한 ParameterBlock객체에 .add로 파일명을 넣어주면 된다.
							 BufferedImage im = rOp.getAsBufferedImage();
	
							 // 입력 파일에 대해 BufferedImage형식으로 받아오고,
							 BufferedImage thumb = new BufferedImage(imgWidthNew, imgHeightNew, BufferedImage.TYPE_INT_RGB);
	
							//썸네일(리사이즈)이미지를 위한 공간을 만든다. 50,50은 width, height되겠다.
							  Graphics2D g2 = thumb.createGraphics();
							
							  //썸네일 버퍼공간에 대해 Graphics2D객체를 얻어와서 입력이미지에 있는 내용을 그린다.(0,0위치에 50,50크기로 복사)
							  g2.drawImage(im, 0, 0, imgWidthNew, imgHeightNew, null);
	
	
							  //출력파일에 대한 객체를 만들고 ImageIO.write로 출력.
							  File outfile = new File(filePath + "\\thumb\\" + myFileName );
							  ImageIO.write(thumb, "jpg", outfile);
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
