package skt.tmall.daemon.common;

import skt.tmall.business.escrow.trade.domain.TrRefundWaitBO;


public interface IWorkerThread
{
	public void setPool(ThreadPool pool);
}