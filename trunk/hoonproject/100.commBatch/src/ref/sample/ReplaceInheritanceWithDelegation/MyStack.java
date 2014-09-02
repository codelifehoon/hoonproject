/**
 * @FileName  : MyStack.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 22. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceInheritanceWithDelegation;

import java.util.Vector;

public class MyStack extends Vector<String>  {

	public void push(String element) {
        insertElementAt(element,0);
    }

    public String pop() {
    	String result = firstElement();
        removeElementAt(0);
        return result;
    }
    
}
