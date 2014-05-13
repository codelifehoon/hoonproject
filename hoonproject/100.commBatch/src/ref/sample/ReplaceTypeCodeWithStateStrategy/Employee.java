/**
 * @FileName  : Employee.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 13. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceTypeCodeWithStateStrategy;

public class Employee {

	 
    private int _type;
    public static final int ENGINEER = 0;
    public static final int SALESMAN = 1;
    public static final int MANAGER = 2;
    
    private int _monthlySalary = 10;
    private int _commission = 20;
    private int _bonus = 30;
    private int sum = 0;
    
    
 
    public Employee (int type) {
        _type = type;
    }
 
    public int getType() {
        return _type;
    }
 
    public void setType(int arg) {
        _type = arg;
    }
 
    public int payAmount() {
    	
        switch (_type) {
            case ENGINEER:
            	sum += _monthlySalary;
            	return sum;
            case SALESMAN:
            	sum += _monthlySalary + _commission;
            	return sum;
            case MANAGER:
            	sum += _monthlySalary + _bonus;
            	return sum;
            default:
               throw new RuntimeException("Incorrect Employee");
        }
    }
}
