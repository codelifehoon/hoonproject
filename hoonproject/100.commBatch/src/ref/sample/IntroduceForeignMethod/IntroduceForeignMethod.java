/**
 * @FileName  : IntroduceForeignMethod.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.IntroduceForeignMethod;

import java.util.Date;

public class IntroduceForeignMethod {

	public Date newStart(Date previousEnd )
	{
		 Date newStart = new Date (previousEnd.getYear(),
                 previousEnd.getMonth(), previousEnd.getDate() + 1);
		 return newStart;
	}
}
