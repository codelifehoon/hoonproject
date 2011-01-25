package socialUp.common.util;

import org.apache.commons.fileupload.ProgressListener;

/**
 * This is a File Upload Listener that is used by Apache
 * Commons File Upload to monitor the progress of the 
 * uploaded file.
 * 
 * @author Frank T. Rios
 * 
 * Initial Creation Date: 6/24/2007
 */
public class FileUploadListener  implements ProgressListener
{
    private volatile long 
    	bytesRead = 0L,
    	contentLength = 0L,
    	item = 0L;   
    
    public FileUploadListener() 
    {
    	super();
    }
    
    public void update(long aBytesRead, long aContentLength, int anItem) 
    {
        bytesRead = aBytesRead;
        contentLength = aContentLength;
        item = anItem;
    }
    
    public long getBytesRead() 
    {
        return bytesRead;
    }
    
    public long getContentLength() 
    {
        return contentLength;
    }
    
    public long getItem() 
    {
        return item;
    }
}
