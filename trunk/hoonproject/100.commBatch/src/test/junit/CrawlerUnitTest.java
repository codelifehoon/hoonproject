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
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import com.zebra.business.craw.dao.CrawConfigDAO;
import com.zebra.business.craw.dao.PageCodeListDAO;
import com.zebra.business.craw.domain.CrawConfigBO;
import com.zebra.business.craw.domain.CrawlerDataCombBO;
import com.zebra.business.craw.domain.ExpPattenBO;
import com.zebra.common.BaseFactory;
import com.zebra.process.crawregister.CrawMngService;
import com.zebra.process.crawregister.CrawMngServiceImpl;

//@RunWith(SpringJUnit4ClassRunner.class )
//@ContextConfiguration(locations="classpath:com/zebra/batch/resource/spring-context-common.xml")
@RunWith(MockitoJUnitRunner.class )
public class CrawlerUnitTest {

	
	@Mock @Autowired CrawConfigDAO crawConfigDAO;
	@Mock @Autowired private CrawConfigDAO crawConfigDao;
	@Mock @Autowired private PageCodeListDAO pageCodeListDao;
	
	@InjectMocks private CrawMngService crawMngService = new CrawMngServiceImpl();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String log4jConfigPath = "D:/workspace/100.commBatch/src/com/zebra/batch/config/batch_log4j.xml";
								  
		if (new java.io.File(log4jConfigPath).exists() == false)
		{
			throw new java.io.FileNotFoundException("log4j config file is not found ["+log4jConfigPath+"]");
		}
		org.apache.log4j.xml.DOMConfigurator.configure(log4jConfigPath); 
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
		
		
		when (crawConfigDao.addCrawConfig(null)).thenAnswer( new Answer()
			{
			@Override
			public Long answer(InvocationOnMock invocation) throws Throwable 
				{
					return (long)111;
				}
			}
			);
		
		when (pageCodeListDao.addPageCodeList(anyString(),any(HashMap.class))).thenAnswer( new Answer()
		{
		@Override
		public Integer answer(InvocationOnMock invocation) throws Throwable 
			{
				System.out.println("##### addPageCodeList mokito");
				return 888;
			}
		}
		);
	
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
	
	@Test
	public void mockitoCrawMngServiceTest()
	{
		CrawlerDataCombBO crawlerDataCombBO = BaseFactory.create(CrawlerDataCombBO.class);
		crawMngService.addCrawInfo(crawlerDataCombBO);
		
	}

	
	
}
