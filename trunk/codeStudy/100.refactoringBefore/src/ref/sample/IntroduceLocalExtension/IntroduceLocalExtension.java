/**
 * @FileName  : IntroduceForeignMethod.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.IntroduceLocalExtension;

import java.util.Date;

public class IntroduceLocalExtension extends Date {

	public IntroduceLocalExtension(int year, int month, int date) {
		super(year, month, date);
		// TODO Auto-generated constructor stub
	}

	public Date newStart( )
	{
		 Date newStart = NextDay();
		 return newStart;
	}

	/**
	 * @param previousEnd
	 * @return
	 */
	private  Date NextDay() {
		return new Date (getYear(),
                 getMonth(), getDate() + 1);
	}
}
