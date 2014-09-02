/**
 * @FileName  : Customer.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 15. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.IntroduceNullObject;

public class NullCustomer  extends Customer {


	@Override
	public boolean isNull() {
        return true;
    }
	
	
	@Override
	public String getName() {
		return "name";
	}
	
	@Override
	public String getAge() {
		return "age";
	}

	@Override
	public String getGender() {
		return "gender";
	}
	
	
}
