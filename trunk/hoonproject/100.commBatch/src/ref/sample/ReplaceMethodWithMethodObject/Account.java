package ref.sample.ReplaceMethodWithMethodObject;

public class Account {


	public int gamma (int inputVal, int quantity, int yearToDate) 
	{
		return new Gamma(this, inputVal, quantity, yearToDate).compute();
	}

	public int delta()
	{
		return 13*5;
	}

}
