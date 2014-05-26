/**
 * @FileName  : Performance.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 5. 8. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package ref.sample.ReplaceArrayWithObject;

public class Performance {
	 public String getName() {
	        return name;
	    }
	    public void setName(String arg) {
	        name = arg;
	    }
	 
	    public String getWins() {
	        return wins;
	    }
	    public void setWins(String arg) {
	        wins= arg;
	    }
	 
	 
	    private String name;
	    private String wins;
}
