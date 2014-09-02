/**
 * @FileName  : Employee.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 12. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceTypeCodeWithSubclasses;

public class Employee {
	private int type;
    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;
 
    Employee (int type) {
        this.type = type;
    }
}
