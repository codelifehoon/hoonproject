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

	 
    private EmployeeType _type;

    
    private int _monthlySalary = 10;
    private int _commission = 20;
    private int _bonus = 30;
    private int sum = 0;
    
    
 
    public Employee (int type) {
        _type = _type.newType(type);
    }
 
    public int getType() {
        return _type.getTypeCode();
    }
 
    public void setType(int arg) {
        _type = _type.newType(arg);
    }
 
    public int payAmount() {
    	
        switch (getType()) {
            case EmployeeType.ENGINEER:
            	sum += _monthlySalary;
            	return sum;
            case EmployeeType.SALESMAN:
            	sum += _monthlySalary + _commission;
            	return sum;
            case EmployeeType.MANAGER:
            	sum += _monthlySalary + _bonus;
            	return sum;
            default:
               throw new RuntimeException("Incorrect Employee");
        }
    }
}
