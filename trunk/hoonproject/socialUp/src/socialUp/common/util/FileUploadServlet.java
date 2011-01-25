package socialUp.common.util;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

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

/**
 * This is a File Upload Servlet that is used with AJAX
 * to monitor the progress of the uploaded file. It will
 * return an XML object containing the meta information
 * as well as the percent complete.
 * 
 * @author Frank T. Rios
 * 
 * Initial Creation Date: 6/24/2007
 */
public class FileUploadServlet 
	extends HttpServlet 
	implements Servlet 
{
	private static final long serialVersionUID = 2740693677625051632L;
	public Logger log = Logger.getLogger(this.getClass());
	

	public FileUploadServlet()
	{
		super();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException 
    {
//    	PrintWriter out = response.getWriter();
//    	HttpSession session = request.getSession();
//    	FileUploadListener listener = null; 
//	    StringBuffer buffy = new StringBuffer();
//    	long	 bytesRead = 0,
//    			contentLength = 0; 
//    	
//    	// Make sure the session has started
//    	if (session == null)
//    	{
//    		return;
//    	}
//    	else if (session != null)
//    	{
//    		// Check to see if we've created the listener object yet
//    		listener = (FileUploadListener)session.getAttribute("LISTENER");
//    		
//    		if (listener == null)
//    		{
//    			return;
//    		}
//    		else
//    		{
//    			// Get the meta information
//    	    	bytesRead = listener.getBytesRead();
//    			contentLength = listener.getContentLength();
//    		}
//    	}
//    	
//    	/*
//    	 * XML Response Code
//    	 */
//    	response.setContentType("text/xml");
//	    
//	    buffy.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
//	    buffy.append("<response>\n");
//	    buffy.append("\t<bytes_read>" + bytesRead + "</bytes_read>\n");
//	    buffy.append("\t<content_length>" + contentLength + "</content_length>\n");
//
//	    // Check to see if we're done
//	    if (bytesRead == contentLength) 
//	    {
//		    buffy.append("\t<finished />\n");
//		    
//		    // No reason to keep listener in session since we're done
//			session.setAttribute("LISTENER", null);
//	    }
//	    else
//	    {
//	    	// Calculate the percent complete
//		    long percentComplete = ((100 * bytesRead) / contentLength);  
//	
//		    buffy.append("\t<percent_complete>" + percentComplete + "</percent_complete>\n");
//	    }
//		
//	    buffy.append("</response>\n");
//
//	    out.println(buffy.toString());
//	    out.flush();
//	    out.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException 
    {
		// create file upload factory and upload servlet
		FileItemFactory 	factory = new DiskFileItemFactory();
		ServletFileUpload  	upload = new ServletFileUpload(factory);

		// set file upload progress listener
		FileUploadListener listener = new FileUploadListener();
		
		HttpSession  session = request.getSession();
		
		session.setAttribute("LISTENER", listener);
		
		// upload servlet allows to set upload listener
		upload.setProgressListener(listener);

		List 		uploadedItems = null;
		FileItem 	fileItem = null;
		String  	filePath = "D:\\javadevtool\\workSpace\\socialUp\\WebContent\\files";	// Path to store file on local system
		
 		try 
		{
			// iterate over all uploaded files
			uploadedItems = upload.parseRequest(request);
			
			Iterator i = uploadedItems.iterator();
			
			while (i.hasNext()) 
			{

				
				fileItem = (FileItem) i.next();
				
				log.debug("fileItem.getSize():" + fileItem.getSize());
				
				
				if (fileItem.isFormField() == false) 
				{
					if (fileItem.getSize() > 0) 
					{
						File 
							uploadedFile = null; 
						String
							myFullFileName = fileItem.getName(),
							myFileName = "",
							slashType = (myFullFileName.lastIndexOf("\\") > 0) ? "\\" : "/"; // Windows or UNIX
						int
							startIndex = myFullFileName.lastIndexOf(slashType);

						// Ignore the path and get the filename
						myFileName = myFullFileName.substring(startIndex + 1, myFullFileName.length());

						// Create new File object
						uploadedFile = new File(filePath, myFileName);
						
						// Write the uploaded file to the system
						fileItem.write(uploadedFile);
					}
				}
			}
		} 
		catch (FileUploadException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}       
}
