/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.FormTemplateMethod;

import java.util.Vector;

public abstract class Statement {

	protected Vector<Rental> vRentals;

	public Statement() {
		super();
	}

	public void setvRentals(Vector<Rental> vRentals) {
		this.vRentals = vRentals;
	}

	protected abstract String getHeader();

	protected abstract String getFooter(String result);

	protected abstract String getBody(String result, Rental each);



}