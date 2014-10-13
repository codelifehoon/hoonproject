package ref.selfTest.refect;
public class TaxiCharge extends ChargeCalculator  implements Chargable
{


    public double getBaseCharge() {
        return 3000.0;
    }

    public double getDiscountRate(int age) {
        return 1;
    }

    public double getDistanceRate(int kilometer) {
        return kilometer;
    }
}
