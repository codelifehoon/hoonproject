
package socialUp.common.servlet;

import javax.servlet.ServletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import socialUp.common.properties.PropertiesManager;







public class InitServletContextListener extends HttpServlet implements ServletContextListener 
{

    private static final long serialVersionUID = -2150572506607158911L;
    private Log log = LogFactory.getLog( InitServletContextListener.class );

    /**
     * Initialize listener
     *
     * @param context
     */
    private void initialize( String propertyFile ) {
        new PropertiesManager().loadProperties( propertyFile );
        if( log.isInfoEnabled() ) {
            log.info("Tmall Properties Loaded..\n");
        }
    }
    
    /**
     * Destory listener
     *
     */
    private void destroyListener() {
        PropertiesManager.releaseProperties();
        if( log.isInfoEnabled() ) {
            log.info("Tmall Properties Deleted..");
        }
        
        //나중에 code table까지 캐시 되었을때 종료시 호출해줄것
        //TmallCommonCode.clear();
    }

    /**
     * ServletContext EventHandler(contextInitialized)
     * 
     * @param sce ServletContextEvent 인스턴스
     */
    public void contextInitialized( ServletContextEvent sce ) {
        ServletContext context = sce.getServletContext();
        String propertyFile = context.getInitParameter("PropertiesFileName");

        initialize( context.getRealPath( propertyFile ) );
    }

    /**
     * ServletContext EventHandler(contextInitialized)
     * 
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {
        String propertyFile = getInitParameter("PropertiesFileName");
        ServletContext context = getServletContext();

        initialize( context.getRealPath( propertyFile ) );
    }

    /**
     * ServletContext EventHandler(contextDestroyed)
     * 
     * @param sce ServletContextEvent 인스턴스
     */
    public void contextDestroyed( ServletContextEvent sce ) {
        destroyListener();
    }

    /**
     * ServletContext EventHandler(contextDestroyed)
     * 
     * @see javax.servlet.GenericServlet#destroy()
     */
    @Override
    public void destroy() {
        destroyListener();
    }

}
