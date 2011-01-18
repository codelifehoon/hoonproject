package socialUp.common;

import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadPool
{
	public static Log log = LogFactory.getLog(ThreadPool.class);

	//private static final int MAX_POOLSIZE = 15;
	private static int poolSize = 3;

	private final ArrayList<Thread> queue = new ArrayList<Thread>();

	private boolean wait = false;
	private int total = 0;
	private int index = 0;


	/**
	 * 스레드 pool을 생성한다.
	 * @param size			스레드풀 사이즈
	 * @param workerClass	실제 스레드내에서 작업을 진행할 class
	 */
	public ThreadPool(int size,Class workerClass)
	{
		this.poolSize = size;

		try
		{

			Class cl = Class.forName(workerClass.getName());

			for (int i = 0; i < poolSize; i++)
			{
				Thread worker = (Thread)cl.newInstance();
				IWorkerThread iworker = (IWorkerThread)worker;
				iworker.setPool(this);

				worker.setName("Worker["+worker.toString()+"]");
				worker.start();
				queue.add(worker);
				index = i;
				total++;

				log.info("Constructor : ThreadPool Created - Current total["+total+"]["+worker.getName()+"]");
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 스레드 필요시 pool에서 가져간다
	 *
	 * @return 스레드
	 */
	public Thread getThread()
	{
		Thread worker = null;

		if (queue.size() > 0)
		{
			synchronized (queue)
			{
				worker = (Thread) queue.remove(index--);
			}
		}
		else
		{
			if (wait)
			{
				return waitQueue();
			}
			else
			{
// Old
//				if (total < MAX_POOLSIZE)
//				{
//					worker = new Thread(this, deliveryMmsService);
//					worker.setName("Worker["+worker.toString()+"]");
//					worker.start();
//					total++;
//
//					log.info("Add Worker - Current total["+total+"]WorkerName["+worker.getName()+"]");
//				}
//				else
//				{
//					return waitQueue();
//				}

				return waitQueue();
			}
		}

		log.info("\n\n\n\n\n\n\nThread Cnt["+queue.size()+"/"+total+"]");

		return worker;
	}

	private Thread waitQueue()
	{
		synchronized (queue)
		{
			while (queue.isEmpty())
			{
				try
				{
	                queue.wait();
	            }
				catch (InterruptedException ignored)
				{
					ignored.printStackTrace();
				}
			}

			return (Thread) queue.remove(index--);
		}
	}

	/**
	 * 사용한 스레드를 스레드 pool에 반환한다.
	 * @param worker	반환할 스레드풀
	 */
	public void putThread(Thread worker)
	{
//		if ( total >= MAX_POOLSIZE)
//		{
//			--total;
//			log.info("Delete Worker - Current total["+total+"]WorkerName["+worker.getName()+"]");
//			worker = null;
//		}
//		else
//		{
//			synchronized (queue)
//			{
//				queue.add(worker);
//				++index;
//				queue.notify();
//			}
//		}

		synchronized (queue)
		{
			queue.add(worker);
			++index;
			queue.notify();
		}
	}

	/**
	 * 실행중인 스레드가 반환되기를 대기한후 전체 스레드를 종료시킨다.
	 * app 종료시 항상 stopThread() 실행되어야 명확한 종료가 가능하다.
	 */
	public synchronized void stopThread()
	{
		try
		{
			log.debug("스레드 회수시작");
			while  (this.poolSize != index+1)
			{
				Thread.sleep(10000);
			}
			log.debug("스레드 회수종료");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("스레드 회수실패");
			return;
		}

		log.debug("스레드 회수완료");
		while (index != -1)
		{
			 Thread killThread  = getThread();
			 String KillThreadName  = killThread.getName();
			 log.debug(KillThreadName + " 스레드 종료시작");
			 killThread.interrupt();
			 log.debug(KillThreadName + " 스레드 종료끝");

		}



	}


	public boolean isWait() { return wait; }
	public void setWait(boolean wait) { this.wait = wait; }
}
