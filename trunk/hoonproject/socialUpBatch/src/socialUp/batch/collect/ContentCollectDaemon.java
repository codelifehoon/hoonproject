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
	public static final int daemonchkInterval = 30;	// daemon 중지확인 시간 (단위분)


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
				log.debug("스레생성에러");
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
 * 메인 시작
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
    	batchName = "컨텐츠 수집 데몬";
    	String lastRunDt = "";


    	/*
    	 * daemonchkInterval분 동안 실행이 있었는지 확인하고 60동안 실행이 있었다면 중복chk를 하고
    	 * daemonchkInterval분 동안 실행이 없었다면 중복chk를 하지 않는다.
    	 * lastRunDt 에 값이 존재하면 daemonchkInterval분 안에 실행을 한것이다
    	 * */


		run_sub(args);
	}

	public void run_sub(String[] args)
	{

		try
		{
			// 스레드 처리를 위한  객체생성(별도의 class 로 만들기 귀찮아서 main 포함해서 같이 사용)
			ContentCollectDaemon contentCollectDaemon = new ContentCollectDaemon();
			
			try
			{
				
				ContentService contentService = null;
				List<ContentSourceTblDTO> contentSourceList = null;
				
				contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class);
				
				contentCollectDaemon.initBatch();
				
				while (true)
				{


					// 수집대기 목록을 가져온다.
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
							//Thread.sleep(1000);		// 같은 주문번호의 부분취소건이 1초 이내로  여러번 실행되면 안되기 때문에 sleep 을 1초로 준다.
							contentCollectDaemon.execute(it.next());
						}

						log.debug("ContentCollectDaemon - There is No Resultlist. Sleep "+(sleepSec)+" sec.");
						Thread.sleep(sleepSec * 1000);

					}

					/*if (1==1)
						{
						log.debug("***** 오류발생실행 *****");
						throw new Exception("오류발생");
						}*/
					// 종료처리를 하지 않아서 계속 재  실행가능하면 굳이 종료 flag를 넣어줄필요가 없다(시작<->종료사이 텀 때문에 배치가 중지된줄 알고
					// 배실행 cron에 의해서 중복실행 가능하기 때문에
					//batchLogEnd(batch_no, "0", "Success", "N/A", "N", "", "환불대기건 환불처리  종료");

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
	 * 메인 종료
	 * */


}
