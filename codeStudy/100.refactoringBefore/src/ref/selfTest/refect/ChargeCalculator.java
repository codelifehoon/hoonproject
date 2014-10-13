package ref.selfTest.refect;
public abstract  class ChargeCalculator implements Chargable
{
	  public double calculate(int age, int kilometer) {
	        return getBaseCharge()
	            * getDiscountRate(age)
	            * getDistanceRate(kilometer);
	    }
}
