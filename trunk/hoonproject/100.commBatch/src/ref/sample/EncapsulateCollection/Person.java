/**
 * @FileName  : Person.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.EncapsulateCollection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.util.Assert;

public class Person {

	private Set  _courses= new HashSet<>();
 	
	public Set getCourses() {
        return Collections.unmodifiableSet(_courses);
    }
    public void initializeCourses(Set arg) {
    	 Assert.isTrue(_courses.isEmpty());
    	 _courses.addAll(arg);
    }
    
    public void addCourse (Course arg) {
        _courses.add(arg);
    }
    public void removeCourse (Course arg) {
        _courses.remove(arg);
    }
	/**
	 * @return
	 */
	public int numberOfAdvancedCourses() {
		Iterator iter = getCourses().iterator();
	    int count = 0;
	    while (iter.hasNext()) {
	    	ref.sample.EncapsulateCollection.Course each = (ref.sample.EncapsulateCollection.Course) iter.next();
	       if (each.isAdvanced()) count ++;
	    }
		return count;
	}
    
}
