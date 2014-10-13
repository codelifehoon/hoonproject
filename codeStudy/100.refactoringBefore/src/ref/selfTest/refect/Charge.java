package ref.selfTest.refect;

public class Charge {
    public static double calculate(String type, int age, int kilometer) {
        return create(type).calculate(age, kilometer);
    }

    private static ChargeCalculator create(String type) {
        try {
        	return  (ChargeCalculator) Class.forName("skt.example.after.duplicated_code." +  type + "Charge").newInstance();


        } catch (Exception e) {
        	System.err.println("Init Object Error");
            System.err.println(e.toString());
        }
        return null;
    }
}
