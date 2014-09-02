package ref.sample.ExtractSubclass;

public class CalPay {

	private int pay;
    private int workingDay;
    private boolean speicalIm;
    
	public CalPay(int pay, int workingDay, boolean speicalIm) {
		super();
		this.pay = pay;
		this.workingDay = workingDay;
		this.speicalIm = speicalIm;
	}
	
	public int doCalc()
	{
		int calcPay=0;
		if (this.speicalIm)
		{
			calcPay = pay * workingDay * 2;
		}
		else
		{
			calcPay = pay * workingDay ;
		}
		
		return calcPay;
		
		
	}
    
    
    
    
}
