package ref.sample.ExtractSubclass;

public class CalPay {

	protected int pay;
	protected int workingDay;

    
	public CalPay(int pay, int workingDay) {
		super();
		this.pay = pay;
		this.workingDay = workingDay;
	}
	
	public int doCalc()
	{
		
		return	 pay * workingDay ;
		
	}
    
    
    
    
}
