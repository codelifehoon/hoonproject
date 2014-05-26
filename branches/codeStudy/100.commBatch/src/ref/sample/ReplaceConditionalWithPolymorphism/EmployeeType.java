/**
 * @FileName  : EmployeeType.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 13. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceConditionalWithPolymorphism;

public abstract class EmployeeType {

    public static final int ENGINEER = 0;
    public static final int SALESMAN = 1;
    public static final int MANAGER = 2;
    
    static EmployeeType newType(int code) {
        switch (code) {
            case ENGINEER:
               return new Engineer();
            case SALESMAN:
               return new Salesman();
            case MANAGER:
               return new Manager();
            default:
               throw new IllegalArgumentException("Incorrect Employee Code");
        }
    }

    abstract int getTypeCode();
    public abstract void Calc(Employee employee);
  }
