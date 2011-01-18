package socialUp.batch.collect;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import socialUp.common.IWorkerThread;
import socialUp.common.ServiceFactory;
import socialUp.common.ThreadPool;
import socialUp.common.util.DateTime;
import socialUp.service.content.ContentService;
import socialUp.service.content.ContentServiceImpl;
import socialUp.service.content.dto.ContentSourceTblDTO;



public class ContentCollectDaemonThread extends Thread implements IWorkerThread
{


	public static Log log = LogFactory.getLog(ContentCollectDaemonThread.class);

	private ThreadPool pool;
	private ContentSourceTblDTO contentSource = null;


	/*
    public ContentCollectDaemonThread(ThreadPool pool)
    {
		this.pool = pool;
	}
*/
    public void setPool(ThreadPool pool)
    {
    	this.pool = pool;
    }

    public synchronized void run()
    {
    	String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
    	
	    while (true)
loop:   {
			try
			{
				this.wait();
			}
			catch (InterruptedException e)
			{
				// 인터럽트가 발생하면 해당 작업종료
				this.interrupted();
				log.debug(Thread.currentThread().getName() + "this.interrupted()");
				return;
			}

			if (this.contentSource == null)
			{
				log.info("trRefundWaitBO is null - pool.putThread");
				pool.putThread(this);
				break loop;
			}

			try
			{
				ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);

				// 수집시작
				this.contentSource.setCreate_dt(sCurrentDate);
				contentService.addContentCollect(this.contentSource);
			}
			catch (Exception e)
			{
				log.debug("대기건 환불실패");
				e.printStackTrace();
			}
			finally
			{
				/*sqlMap.endTransaction();*/
				this.contentSource = null;
				pool.putThread(this);
			}
	    }
	}

	public synchronized void workHard(ContentSourceTblDTO contentSource)
	{
		log.debug("workHard - work");
		this.contentSource = contentSource;
		this.notify();
	}




}
