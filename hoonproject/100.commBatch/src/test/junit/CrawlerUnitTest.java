/**
 * @FileName  : CrawlerUnitTest.java
 * @Project     : code refactoring exam proj
 * @Date         : 2014. 6. 13. 
 * @작성자      : codelife
 * @변경이력 :
 * @프로그램 설명 :
 */
package test.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebra.common.BaseFactory;
import com.zebra.process.crawler.dao.CrawConfigDAO;
import com.zebra.process.crawler.domain.CrawConfigBO;

@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations="classpath:com/zebra/batch/resource/spring-context-common.xml")

//@RunWith(MockitoJUnitRunner.class )
public class CrawlerUnitTest {

	@Mock @Autowired CrawConfigDAO crawConfigDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		CrawConfigBO crawConfigBO  = BaseFactory.create(CrawConfigBO.class);

		crawConfigBO.setCrawlThreadCount(4);
		crawConfigBO.setSiteConfigSeq("100000");
		crawConfigBO.setSiteNm("11st");
		crawConfigBO.setCrawlDepth(1);
		
		when (crawConfigDAO.selectCrawConfigList(crawConfigBO)).thenAnswer(new Answer () {
				@Override
				public List<CrawConfigBO> answer(InvocationOnMock invocation) throws Throwable {
					
					Object[] args = invocation.getArguments();
					List<CrawConfigBO> crawConfigBOList = new ArrayList();
					
					CrawConfigBO d1  = (CrawConfigBO) BeanUtils.cloneBean( ((CrawConfigBO)args[0]));
					d1.setSiteConfigSeq("2");
					
					crawConfigBOList.add(d1);
					
					return crawConfigBOList;
				}
		});
	}

	@Test
	public void mockitoSpringAutoTest() 
	{
		CrawConfigBO crawConfigBO  = BaseFactory.create(CrawConfigBO.class);

		crawConfigBO.setCrawlThreadCount(4);
		crawConfigBO.setSiteConfigSeq("100000");
		crawConfigBO.setSiteNm("11st");
		crawConfigBO.setCrawlDepth(1);
		
		assertThat("100000",is(crawConfigDAO.selectCrawConfigList(crawConfigBO).get(0).getSiteConfigSeq()));
		
	}

	
	
	
	
	
}
