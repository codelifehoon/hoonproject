package socialUp.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Loader {

    private static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";

    // We conservatively assume that we are running under Java 1.x
    private static boolean java1 = true;
    private static Log logger = LogFactory.getLog( Loader.class );

	// 초기화 블럭
	static {
	    String prop = getSystemProperty("java.version", null);
        if (prop != null) {
            int i = prop.indexOf('.');
            if (i != -1) {
                if (prop.charAt(i + 1) != '1')
                    java1 = false;
            }
        }
        // --------
	}
	
	/**
	 * 지정한 경로 및 파일의 정보를 클래스 패스에서 찾아 반환한다.
	 *
	 * @param resource 찾고자하는 파일의 정보.
	 * @return 클래스패스에서 찾은 파일의 URL 정보.
	 */
	public static URL getResource(String resource) {
	    ClassLoader classLoader = null;
	    URL url = null;

	    try {
	        if(!java1) {
	            classLoader = getTCL();
	            if(classLoader != null) {
	                if( logger.isDebugEnabled() ) {
	                    logger.debug("Trying to find [" + resource + "] using context classloader " + classLoader + ".");
	                }
	                url = classLoader.getResource(resource);      
	                if(url != null) {
	                    return url;
	                }
	            }
	        }

	        // We could not find resource. Ler us now try with the
	        // classloader that loaded this class.
	        classLoader = Loader.class.getClassLoader(); 
	        if(classLoader != null) {
	            if( logger.isDebugEnabled() ) {
	                logger.debug("Trying to find [" + resource + "] using " + classLoader + " class loader.");
	            }
	            url = classLoader.getResource(resource);
	            if(url != null) {
	                return url;
	            }
	        }
	    } catch(Throwable t) {
	        if( logger.isWarnEnabled() ) {
	            logger.warn(TSTR, t);
	        }
	    }

	    // Last ditch attempt: get the resource from the class path. It
	    // may be the case that clazz was loaded by the Extentsion class
	    // loader which the parent of the system class loader. Hence the
	    // code below.
	    if( logger.isDebugEnabled() ) {
	        logger.debug("Trying to find [" + resource + "] using ClassLoader.getSystemResource().");
	    }
	    return ClassLoader.getSystemResource(resource);
	} 

	/**
	 * JDK 1.x 버전대인지 확인한다.
	 */
	public static boolean isJava1() {
	    return java1;
	}

	/**
     * Very similar to <code>System.getProperty</code> except that the
     * {@link SecurityException} is hidden.
     * 
     * @param key The key to search for.
     * @param def The default value to return.
     * @return the string value of the system property, or the default value if
     *         there is no property with that key.
     */
	public static String getSystemProperty( String key, String def ) {
        try {
            return System.getProperty(key, def);
        }
        catch (Throwable e) { // MS-Java throws com.ms.security.SecurityExceptionEx
            if( logger.isDebugEnabled() ) {
                logger.debug("Was not allowed to read system property \"" + key + "\".");
            }
            return def;
        }
    }

	/**
	 * Get the Thread Context Loader which is a JDK 1.2 feature. If we
	 * are running under JDK 1.1 or anything else goes wrong the method
	 * returns <code>null<code>.
	 *
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static ClassLoader getTCL() throws IllegalAccessException, InvocationTargetException {

        // Are we running on a JDK 1.2 or later system?
        Method method = null;
        try {
            method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
        }
        catch (NoSuchMethodException e) {
            // We are running on JDK 1.1
            return null;
        }

        return (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
    }

}
