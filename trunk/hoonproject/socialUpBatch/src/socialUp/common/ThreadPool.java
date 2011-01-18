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
	 * ������ pool�� �����Ѵ�.
	 * @param size			������Ǯ ������
	 * @param workerClass	���� �����峻���� �۾��� ������ class
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
	 * ������ �ʿ�� pool���� ��������
	 *
	 * @return ������
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
	 * ����� �����带 ������ pool�� ��ȯ�Ѵ�.
	 * @param worker	��ȯ�� ������Ǯ
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
	 * �������� �����尡 ��ȯ�Ǳ⸦ ������� ��ü �����带 �����Ų��.
	 * app ����� �׻� stopThread() ����Ǿ�� ��Ȯ�� ���ᰡ �����ϴ�.
	 */
	public synchronized void stopThread()
	{
		try
		{
			log.debug("������ ȸ������");
			while  (this.poolSize != index+1)
			{
				Thread.sleep(10000);
			}
			log.debug("������ ȸ������");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.debug("������ ȸ������");
			return;
		}

		log.debug("������ ȸ���Ϸ�");
		while (index != -1)
		{
			 Thread killThread  = getThread();
			 String KillThreadName  = killThread.getName();
			 log.debug(KillThreadName + " ������ �������");
			 killThread.interrupt();
			 log.debug(KillThreadName + " ������ ���᳡");

		}



	}


	public boolean isWait() { return wait; }
	public void setWait(boolean wait) { this.wait = wait; }
}
