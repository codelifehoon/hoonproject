package ref.sample.ExtractSubclass;

public class CalcSpecDay extends CalPay{

	public CalcSpecDay(int pay, int workingDay) {
		super(pay, workingDay);
		// TODO Auto-generated constructor stub
	}
	
	public int doCalc()
	{
		
		return	 pay * workingDay * 2;
	
	
	}
		

}
