/**
 * @FileName  : IntroduceAssertion.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.IntroduceAssertion;

public class IntroduceAssertion {

	private String conditionA = "conditionA";
	private String conditionB = null;
	
	public String getConditionA() {
		return conditionA;
	}
	public void setConditionA(String conditionA) {
		this.conditionA = conditionA;
		this.conditionB = null;
	}
	public String getConditionB() {
		return conditionB;
	}
	public void setConditionB(String conditionB) {
		this.conditionA = null;
		this.conditionB = conditionB;
	}

	
	public String getCondition() {
	       // expense limit 또는  a primary project를 가지고 있어야함.
	       return (getConditionA() != null) ? conditionA : conditionB.toString();
	   }
	 
}
