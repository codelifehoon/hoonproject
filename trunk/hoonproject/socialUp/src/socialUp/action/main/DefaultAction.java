package socialUp.action.main;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;

public class DefaultAction extends ActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 721999781797176947L;

	public Logger logger = Logger.getLogger(this.getClass());
	
	private String commonVal;
	public String getCommonVal() {
		return commonVal;
	}

	public void setCommonVal(String commonVal) {
		this.commonVal = commonVal;
	}

	
	@Override
	public String execute() throws Exception {
		
		logger.debug("index.jsp 실행");
		return "SUCCESS";
	}

	
	

}
