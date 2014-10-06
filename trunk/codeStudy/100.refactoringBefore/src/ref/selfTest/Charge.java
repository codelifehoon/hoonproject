
package ref.selfTest;


/**
 * @author codelife
 *
 *	나이와 거리별로 버스, 택시, 지하철의 요금을 계산해 주는 코드
 */
public class Charge {
    public static final int BUS = 0;
    public static final int TAXI = 1;
    public static final int SUBWAY = 2;

    public double calculate(int type, int age, int killometer) {
        double result = 0;
        switch(type) {
            case (BUS) :
                result = calculateBus(age, killometer);
                break;
            case (TAXI):
                result = calculateTaxi(age, killometer);
                break;
            case (SUBWAY):
                result = calculateSubway(age, killometer);
                break;
            default :
                throw new RuntimeException("Such type does not exist");
        }
        return result;
    }

    public double calculateBus(int age, int kilometer) {
        double baseBusCharge = 600;
        if(age < 15 ) {
            baseBusCharge = baseBusCharge * 0.5;
        }else if(age > 60) {
            baseBusCharge = baseBusCharge * 0.7;
        }
        return baseBusCharge;
    }

    public double calculateTaxi(int age, int kilometer) {
        double baseTaxiCharge = 3000;
        return baseTaxiCharge * kilometer;
    }

    public double calculateSubway(int age, int kilometer) {
        double baseSubwayCharge = 1000;
        if (age < 15) {
            baseSubwayCharge = baseSubwayCharge * 0.5;
        }else if(age > 60) {
            baseSubwayCharge = baseSubwayCharge * 0.7;
        }
        if(kilometer > 50) {
            return baseSubwayCharge * 1.5;
        }else {
            return baseSubwayCharge;
        }
    }
}
