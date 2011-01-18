package socialUp.batch.collect;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import socialUp.common.ServiceFactory;
import socialUp.common.ThreadPool;
import socialUp.service.content.ContentService;
import socialUp.service.content.ContentServiceImpl;
import socialUp.service.content.dto.ContentSourceTblDTO;


public class ContentCollectDaemon extends BaseDaemon
{
	public Logger log = Logger.getLogger(this.getClass());
	public static final long sleepSec = 10;
	public static final int poolSize = 10;
	public static final int daemonchkInterval = 30;	// daemon ����Ȯ�� �ð� (������)


	private ThreadPool threadPool;
	

	public ContentCollectDaemon()
	{
	    super();
		log.info("ContentCollectDaemon Load.");
	}

	private void initBatch() throws Exception
	{
	    this.threadPool = new ThreadPool(poolSize,ContentCollectDaemonThread.class);
	}

	private void execute(ContentSourceTblDTO contentSource) throws Exception
	{
		ContentCollectDaemonThread worker = null;
		
		do
		{
			worker = (ContentCollectDaemonThread)threadPool.getThread();

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException _ignored)
			{
				log.debug("������������");
			}
		}
		while (worker == null);

		worker.workHard(contentSource);
	}

	private void stopThread() throws Exception
	{
		this.threadPool.stopThread();
	}



/*
 * ���� ����
 * */
	public static void main( String[] args )
	{
		try {
        BaseDaemon.initSqlMap();
        ContentCollectDaemon dm = new ContentCollectDaemon();
        dm.run(args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }


	public void run(String[] args) throws Exception
	{
		batch_no = 2537;
    	batchID = "ContentCollectDaemon";
    	batchName = "������ ���� ����";
    	String lastRunDt = "";


    	/*
    	 * daemonchkInterval�� ���� ������ �־����� Ȯ���ϰ� 60���� ������ �־��ٸ� �ߺ�chk�� �ϰ�
    	 * daemonchkInterval�� ���� ������ �����ٸ� �ߺ�chk�� ���� �ʴ´�.
    	 * lastRunDt �� ���� �����ϸ� daemonchkInterval�� �ȿ� ������ �Ѱ��̴�
    	 * */


		run_sub(args);
	}

	public void run_sub(String[] args)
	{

		try
		{
			// ������ ó���� ����  ��ü����(������ class �� ����� �����Ƽ� main �����ؼ� ���� ���)
			ContentCollectDaemon contentCollectDaemon = new ContentCollectDaemon();
			
			try
			{
				
				ContentService contentService = null;
				List<ContentSourceTblDTO> contentSourceList = null;
				
				contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
				
				contentCollectDaemon.initBatch();
				
				while (true)
				{


					// ������� ����� �����´�.
					contentSourceList = contentService.selectContentCollectWaitList();

					if (contentSourceList == null || contentSourceList.size() == 0 )
					{
						log.debug("ContentCollectDaemon - There is No Resultlist. Sleep "+(sleepSec)+" sec.");
						Thread.sleep(sleepSec * 1000);
					}
					else
					{
						log.info("ContentCollectDaemon - size :" +  contentSourceList.size());
						Iterator<ContentSourceTblDTO> it = contentSourceList.iterator();
						
						int whileCount = 0 ;

						while(it.hasNext())
						{
							whileCount++;
							log.debug("ContentCollectDaemon - execute one record : " + whileCount);
							//Thread.sleep(1000);		// ���� �ֹ���ȣ�� �κ���Ұ��� 1�� �̳���  ������ ����Ǹ� �ȵǱ� ������ sleep �� 1�ʷ� �ش�.
							contentCollectDaemon.execute(it.next());
						}

						log.debug("ContentCollectDaemon - There is No Resultlist. Sleep "+(sleepSec)+" sec.");
						Thread.sleep(sleepSec * 1000);

					}

					/*if (1==1)
						{
						log.debug("***** �����߻����� *****");
						throw new Exception("�����߻�");
						}*/
					// ����ó���� ���� �ʾƼ� ��� ��  ���డ���ϸ� ���� ���� flag�� �־����ʿ䰡 ����(����<->������� �� ������ ��ġ�� �������� �˰�
					// ����� cron�� ���ؼ� �ߺ����� �����ϱ� ������
					//batchLogEnd(batch_no, "0", "Success", "N/A", "N", "", "ȯ�Ҵ��� ȯ��ó��  ����");

				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
			finally
			{
				contentCollectDaemon.stopThread();
			}

		}
		catch (Exception e)
		{
			log.error(e);
		}
	}


	/*
	 * ���� ����
	 * */


}
