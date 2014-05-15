/**
 * @FileName  : Employee.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 13. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceConditionalWithPolymorphism;

public class Employee {

	 
    private EmployeeType _type;

    
    public int _monthlySalary = 10;
    public int _bonus = 30;
    public int sum = 0;
    public int _commission = 20;
    
    
 
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
    	
    	_type.Calc(this);
    	return  sum;
    }

}
