
package socialUp.common.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Properties Manager 클래스<br>
 * /WEB-INF/properties/progam.properties 파일 정보를 메모리에 로딩시키며 web.xml에 아래와 같이 설정하여야 함.
 *
 *   <servlet>
		<servlet-name>INIT_LOADER</servlet-name>
		<servlet-class>socialUp.common.servlet.InitServletContextListener </servlet-class>
		<init-param>
			<param-name>PropertiesFileName</param-name>
			<param-value>/WEB-INF/properties/[$server_type]_goreee.properties</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
 *
 * 
 */
public class PropertiesManager {

    private static final String DEFAULT_SERVER_TYPE = "real";

    private Log log = LogFactory.getLog(PropertiesManager.class);

    /**
     * Application 프로그래밍 시 사용되는 Properties 인스턴스
     */
    static Properties goreeeProperties = new Properties();

    /**
     * 시스템 변수중에서 '-Dserver.type=xxxx' 값을 읽어서 [$server.type]_program.properties 로 되어
     * 있는 파일명에 [$server.type] 부분을 찾아 치환한다.
     *
     * ex) -Dserver.type=real 이면 real_program.properties 파일을 찾도록 한다.
     *
     * @param propertiesFileName 읽어들일 properties file path.
     * @return 변경된 properties 파일.
     */
    private String choosePropertiesName( String propertiesFileName ) {
        String serverType = null;
        try {
            serverType = System.getProperty("server.type");
            if( isEmpty(serverType) ) {
                if( log.isWarnEnabled() ) {
                    log.warn("시스템 Property( -Dserver.type ) 값을 읽지 못했습니다. 기본값인 'real' 로 설정합니다.");
                }
                serverType = DEFAULT_SERVER_TYPE;
            }
        }
        catch( Exception ex ) {
            if( log.isWarnEnabled() ) {
                log.warn("시스템 Property( -Dserver.type ) 값을 읽지 못했습니다. 기본값인 'real' 로 설정합니다.", ex);
            }
            serverType = DEFAULT_SERVER_TYPE;
        }
        String newPropertiesName = replace( propertiesFileName, "[$server_type]", serverType );

        if( log.isInfoEnabled() ) {
            log.info("Property file : " + newPropertiesName);
        }

        return newPropertiesName;
    }

    /**
     * Properties 파일 loader
     *
     * @param context ServletContext 인스턴스
     */
    public void loadProperties( String propertiesFileName ) {
        String newPropertiesFileName = choosePropertiesName(propertiesFileName);

        if (newPropertiesFileName == null || "".equals(newPropertiesFileName)) {
            if (log.isErrorEnabled()) {
                log.error("'" + newPropertiesFileName + "' 파일 없음");
            }
            return;
        }
        try {
            if( log.isInfoEnabled() ) {
                log.info( "Loading property from '" + newPropertiesFileName + "'." );
            }
            goreeeProperties.load( new FileInputStream( newPropertiesFileName ) );
        }
        catch (IOException e) {
            e.printStackTrace();
            if (log.isErrorEnabled()) {
                log.error("'" + newPropertiesFileName + "' 파일 입출력 오류", e);
            }
        }
    }

    /**
     * Application Property(/WEB-INF/properties/program.properties) 검색
     *
     * @return 검색 key
     */
    public static String getProperty( String key ) {
        return goreeeProperties.getProperty(key);
    }

    /**
     * Application Property 세팅
     *
     * @param key 저장 key
     * @param value 저장 value
     */
    public static void setProperty( String key, String value ) {
        goreeeProperties.setProperty(key, value);
    }

    /**
     * Properties 업데이트
     *
     * @param goreeeProperties 업데이트할 Properties 인스턴스
     */
    public static void changeProperties( Properties goreeeProperties ) {
        PropertiesManager.goreeeProperties = goreeeProperties;
    }

    /**
     * Properties 삭제
     */
    public static void releaseProperties() {
        if (goreeeProperties != null) {
            goreeeProperties = null;
        }
    }

    /**
     * srcStr 문자열에서 oldStr 문자열을 찾아 newStr 문자열로 치환한다.
     *
     * @param srcStr 원본 문자열
     * @param oldStr 찾을 문자열
     * @param newStr 바꿀 문자열
     * @return 치환된 문자열
     */
    public static String replace(String srcStr, String oldStr, String newStr) {
        StringBuffer sb = new StringBuffer(srcStr);
        int start = 0;
        int end = 0;
        int pos = 0;
        // 무한루프
        while (true) {
            start = srcStr.indexOf(oldStr, pos);
            // 문자열에서 from이 더이상 발견되지 않으면 끝
            if (start == -1)
                break;
            end = start + oldStr.length();
            // replace
            sb.replace(start, end, newStr);
            pos = start + newStr.length();
            srcStr = sb.toString();
        }
        return sb.toString();
    }


    /**
     * 문자열이 비어있는지...
     *
     * @param str 원본 문자열.
     * @return true if the String is null, or length zero once trimmed
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

}
