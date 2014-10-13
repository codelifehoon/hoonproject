package ref.selfTest.refect;

public class SubwayCharge extends ChargeCalculator implements Chargable {

    public double getBaseCharge() {
        return 1000.0;
    }

    public double getDiscountRate(int age) {
        if (age < 15) {
            return 0.5;
        } else if (age > 60) {
            return 0.7;
        } else {
            return 1.0;
        }
    }

    public double getDistanceRate(int kilometer) {
        if (kilometer > 50) {
            return 1.5;
        } else {
            return 1;
        }
    }
}
