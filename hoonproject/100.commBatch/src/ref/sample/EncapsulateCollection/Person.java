/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.EncapsulateCollection;

import java.util.Set;

public class Person {

	private Set  _courses;
 	
	public Set getCourses() {
        return _courses;
    }
    public void setCourses(Set arg) {
        _courses = arg;
    }
    
    
}
