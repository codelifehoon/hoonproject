package socialUp.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import socialUp.common.util.CookieManager;

/**
 * <pre>
 * TMCookieUtil
 * 
 * !! cookie form is shown below
 *  cookieId=cookieName[|cookieValue]#cookieName[|cookieValue]
 * !! cookie samples
 *  TD=FOO1#FOO2|BAR2#FOO3|BAR3
 *  TP=FOO|BAR#FOO_A|BAR_A#FOO_B#FOO_C
 *  TT=TOAST_MKT_OBJNO_HIST|FALSE<*>F^5702091<@>[F^5702092<@>]
 * 
 * Field Description -----
 * 1. COOKIE_ID_ARR - Array which contains string of cookie id
 *  TP : Temporary
 *  TD : A Day
 *  TT : Ten Year
 * 2. ckIdIndex - Index of COOKIE_ID_ARR
 *  0 : TP
 *  1 : TD
 *  2 : TT
 * 3. cookieName : The Name of Cookie
 * 4. cookieValue : Value for the cookie
 * 5. !! DO NOT INCLUDE "#" or "|" IN THE NAME OR THE VALUE OF COOKIE
 * 6. All cookie values are stored with url encode
 *  
 * Modified on 2010. 4. 1.
 * </pre>
 * 
 * @author jhjung
 */
public class CookieUtil {

    private Logger                        logger          = Logger.getLogger(CookieUtil.class);
    // request, response
    private HttpServletRequest            request         = null;
    private HttpServletResponse           response        = null;
    // some constant
    private static final String[]         COOKIE_ID_ARR   = { "TP", "TD", "TT" };                // DO NOT CHANGE ORDER
    // seperator for cookie
    private static final String           CK_SEPERATOR    = "#";                                 // cookie#cookie
    // seperator for name and value for cookie
    private static final String           VALUE_SEPERATOR = "|";                                 // cookieName|cookieValue
    private static final int              TD_PERIOD       = 86400;                               // 1Day = 86400Sec
    private static final int              TT_PERIOD       = 10 * 365 * TD_PERIOD;                // 10Year
    // private static final int COOKIE_LIFE = 60; // for Test
    private static final String           TMALL_DOMAIN    = ".goreee.com";
    // other object
    private CookieManager                 cookieManager   = null;
    private LinkedHashMap<String, String> cookieMap       = null;
    
    // cookie 유지시간에 대한 상수선언
    public static final int TP = 0;					
    public static final int TD = 1;
    public static final int TT = 2;
    

    public CookieUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        cookieManager = new CookieManager(request, response);
        cookieMap = new LinkedHashMap<String, String>();
        setCookieDataBox();
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Put all matched cookie with cookie id into cookieMap
     * </pre>
     * 
     * void
     */
    private void setCookieDataBox() {
        try {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                String cookieName = "";
                int cookieIdsLength = COOKIE_ID_ARR.length;
                int cookieLength = cookies.length;
                for (int index = 0; index < cookieLength; index++) {
                    cookieName = cookies[index].getName();
                    for (int cookieIdIndex = 0; cookieIdIndex < cookieIdsLength; cookieIdIndex++) {
                        if(StringUtils.equals(cookieName, COOKIE_ID_ARR[cookieIdIndex])) {
                            cookieMap.put(COOKIE_ID_ARR[cookieIdIndex], URLDecoder.decode(cookies[index].getValue(),
                                    getDecodeCharSet()));
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            if(logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Create new cookie
     *                 If no cookie string specified, then remove cookid ID
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @param establishedCookieStr
     *        void
     */
    private void make(int cookieIdIndex, String establishedCookieStr) {
        try {
            if(StringUtils.isNotBlank(establishedCookieStr)) {
                String encodedCookieStr = URLEncoder.encode(stripSharp(establishedCookieStr), getEncodeCharSet());
                Cookie cookie = new Cookie(COOKIE_ID_ARR[cookieIdIndex], encodedCookieStr);
                if(cookieIdIndex == 1) {
                    cookie.setMaxAge(TD_PERIOD);
                } else if(cookieIdIndex == 2) {
                    cookie.setMaxAge(TT_PERIOD);
                }
                cookie.setDomain(TMALL_DOMAIN);
                cookie.setPath("/");
                response.addCookie(cookie);
                cookieMap.put(COOKIE_ID_ARR[cookieIdIndex], establishedCookieStr);
            } else {
                // remove when there is no sub cookie at all
                Cookie cookie = new Cookie(COOKIE_ID_ARR[cookieIdIndex], "");
                cookie.setMaxAge(0);
                cookie.setDomain(TMALL_DOMAIN);
                cookie.setPath("/");
                response.addCookie(cookie);
                cookieManager.removeCookie(COOKIE_ID_ARR[cookieIdIndex]);
                cookieMap.remove(COOKIE_ID_ARR[cookieIdIndex]);
            }
        } catch (UnsupportedEncodingException e) {
            if(logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Add new cookie without value, if named cookie has preexists then remove value of it
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @param cookieName
     *        void
     */
    public void add(int cookieIdIndex, String cookieName) {
        add(cookieIdIndex, cookieName, "");
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 1.
     * Description   : Add new cookie without value, if named cookie has preexists then remove value of it
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @param cookieName
     * @param cookieValue
     *        void
     */
    public void add(int cookieIdIndex, String cookieName, String cookieValue) {
        try {
            String cookieId = "";
            cookieId = COOKIE_ID_ARR[cookieIdIndex];
            if(StringUtils.isNotBlank(cookieId) && StringUtils.isNotBlank(removeAllSeperator(cookieName).trim())) {
                cookieName = removeAllSeperator(cookieName).trim(); // formatted cookieName
                String newCookieStr = cookieName;
                if(StringUtils.isNotBlank(removeAllSeperator(cookieValue).trim())) {
                    newCookieStr += VALUE_SEPERATOR + removeAllSeperator(cookieValue).trim();
                }

                StringBuilder newCookieSb = new StringBuilder();
                if(isExistCookie(cookieIdIndex)) {
                    String[] preexistCookieArr = StringUtils.split(stripSharp(cookieMap
                            .get(COOKIE_ID_ARR[cookieIdIndex])), CK_SEPERATOR);
                    for (int index = 0; index < preexistCookieArr.length; index++) {
                        if(!StringUtils.equals(cookieName,
                                StringUtils.split(preexistCookieArr[index], VALUE_SEPERATOR)[0])) {
                            newCookieSb.append(CK_SEPERATOR).append(preexistCookieArr[index]);
                        }
                    }
                }
                newCookieSb.append(CK_SEPERATOR).append(newCookieStr);
                String establishedCookieStr = stripSharp(newCookieSb.toString());
                clear(cookieIdIndex);
                make(cookieIdIndex, establishedCookieStr);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if(logger.isDebugEnabled()) {
                logger.debug("cookieIdIndex\n" + e.getMessage());
            }
        }
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Clear whole cookie ID
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @return boolean
     */
    public boolean clear(int cookieIdIndex) {
        make(cookieIdIndex, "");
        return !isExistCookie(cookieIdIndex);
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : remove cookie
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @param cookieName
     * @return boolean
     */
    public boolean remove(int cookieIdIndex, String cookieName) {
        try {
            String cookieId = "";
            cookieId = COOKIE_ID_ARR[cookieIdIndex];
            boolean isRemoved = false;
            if(StringUtils.isNotBlank(cookieId)) {
                if(isExist(cookieIdIndex, cookieName)) {
                    StringBuilder newCookieSb = new StringBuilder();

                    String[] preexistCookieArr = StringUtils.split(stripSharp(cookieMap.get(cookieId)), CK_SEPERATOR);
                    int preexistCookieCnt = preexistCookieArr.length;

                    for (int index = 0; index < preexistCookieCnt; index++) {
                        if(!StringUtils.equals(cookieName,
                                StringUtils.split(preexistCookieArr[index], VALUE_SEPERATOR)[0])) {
                            newCookieSb.append(CK_SEPERATOR).append(preexistCookieArr[index]);
                        }
                    }

                    String establishedCookieStr = stripSharp(newCookieSb.toString());

                    clear(cookieIdIndex); // cookieManager.removeCookie()

                    if(!isExist(cookieIdIndex, cookieName)) {
                        isRemoved = true;
                    }

                    make(cookieIdIndex, establishedCookieStr);
                }
            }
            return isRemoved;
        } catch (ArrayIndexOutOfBoundsException e) {
            if(logger.isDebugEnabled()) {
                logger.debug("cookieIdIndex\n" + e.getMessage());
            }
        }
        return false;
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : check cookie ID
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @return boolean
     */
    public boolean isExistCookie(int cookieIdIndex) {
        try {
            String cookieId = "";
            cookieId = COOKIE_ID_ARR[cookieIdIndex];
            return cookieMap.containsKey(cookieId);
        } catch (ArrayIndexOutOfBoundsException e) {
            if(logger.isDebugEnabled()) {
                logger.debug("cookieIdIndex\n" + e.getMessage());
            }
        }
        return false;
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : check cookie with cookieName
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @param cookieName
     * @return boolean
     */
    public boolean isExist(int cookieIdIndex, String cookieName) {
        try {
            String subCookieStr = cookieMap.get(COOKIE_ID_ARR[cookieIdIndex]);
            if(StringUtils.isBlank(subCookieStr)) {
                return false;
            }

            String[] subCookieArr = StringUtils.split(subCookieStr, CK_SEPERATOR);
            if(subCookieArr == null || subCookieArr.length < 1) {
                return false;
            }

            for (int index = 0; index < subCookieArr.length; index++) {
                String[] subCookie = StringUtils.split(subCookieArr[index], VALUE_SEPERATOR);
                if(subCookie != null && StringUtils.equals(subCookie[0], cookieName)) {
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if(logger.isDebugEnabled()) {
                logger.debug("cookieIdIndex\n" + e.getMessage());
            }
        }
        return false;
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Get value of cookie ID
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @return String
     */
    public String getCookie(int cookieIdIndex) {
        try {
            String cookieString = "";
            if(isExistCookie(cookieIdIndex)) {
                cookieString = cookieMap.get(COOKIE_ID_ARR[cookieIdIndex]);
            }
            return cookieString;
        } catch (ArrayIndexOutOfBoundsException e) {
            if(logger.isDebugEnabled()) {
                logger.debug(" cookieIdIndex\n" + e.getMessage());
            }
        }
        return null;
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 5.
     * Description   : Get valuf of cookie ID
     * </pre>
     * 
     * @param cookieId
     * @return String
     */
    public String getCookie(String cookieId) {
        String cookieString = null;
        if(cookieMap.containsKey(cookieId)) {
            cookieString = cookieMap.get(cookieId);
        }
        return cookieString;
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Get value of cookieName
     * cookieIdIndex : 0 - TP, 1 - TD, 2 - TT
     * </pre>
     * 
     * @param cookieIdIndex
     * @param cookieName
     * @return String
     */
    public String getSubCookie(int cookieIdIndex, String cookieName) {
        try {
            String cookieString = "";
            String cookieValue = "";
            if(isExist(cookieIdIndex, cookieName)) {
                cookieString = getCookie(cookieIdIndex);
            }

            if(StringUtils.isNotBlank(cookieString)) {
                String[] cookieArr = StringUtils.split(cookieString, CK_SEPERATOR);
                // cookieArr.length must be more than 0
                for (int index = 0; index < cookieArr.length; index++) {
                    String[] cookie = StringUtils.split(cookieArr[index], VALUE_SEPERATOR);
                    if(StringUtils.equals(cookieName, cookie[0])) {
                        return cookie[1];
                    }
                }
            }
            return cookieValue;
        } catch (ArrayIndexOutOfBoundsException e) {
            if(logger.isDebugEnabled()) {
                logger.debug(" cookieIdIndex\n" + e.getMessage());
            }
        }
        return null;
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 1.
     * Description   : Strip Sharp
     * </pre>
     * 
     * @param param
     * @return String
     */
    public String stripSharp(String param) {
        String returnParam = "";
        if(StringUtils.isBlank(param)) {
            return returnParam;
        } else {
            return StringUtils.strip(param, CK_SEPERATOR).trim();
        }
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Remove all seperator for cookie from param
     * </pre>
     * 
     * @param param
     * @return String
     */
    public String removeCookieSeperator(String param) {
        String returnParam = "";
        if(StringUtils.isBlank(param)) {
            return returnParam;
        } else {
            return param.replaceAll("\\" + CK_SEPERATOR, "");
        }
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Remove all seperator for name and value from param
     * </pre>
     * 
     * @param param
     * @return String
     */
    public String removeValueSeperator(String param) {
        String returnParam = "";
        if(StringUtils.isBlank(param)) {
            return returnParam;
        } else {
            return param.replaceAll("\\" + VALUE_SEPERATOR, "");
        }
    }

    /**
     * <pre>
     * Creator       : jhjung
     * Created Date  : 2010. 4. 2.
     * Description   : Remove every single seperator
     * </pre>
     * 
     * @param param
     * @return String
     */
    public String removeAllSeperator(String param) {
        return removeValueSeperator(removeCookieSeperator(param));
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public Map toMap() {
        return cookieManager.toMap();
    }

    public String getComment() {
        return cookieManager.getComment();
    }

    public void setComment(String comment) {
        cookieManager.setComment(comment);
    }

    public String getDomain() {
        return cookieManager.getDomain();
    }

    public void setDomain(String domain) {
        cookieManager.setDomain(domain);
    }

    public int getMaxAge() {
        return cookieManager.getMaxAge();
    }

    public void setMaxAge(int maxAge) {
        cookieManager.setMaxAge(maxAge);
    }

    public String getPath() {
        return cookieManager.getPath();
    }

    public void setPath(String path) {
        cookieManager.setPath(path);
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public boolean isSecure() {
        return cookieManager.isSecure();
    }

    public void setSecure(boolean secure) {
        cookieManager.setSecure(secure);
    }

    public String getEncodeCharSet() {
        return cookieManager.getEncodeCharSet();
    }

    public void setEncodeCharSet(String encodeCharSet) {
        cookieManager.setEncodeCharSet(encodeCharSet);
    }

    public String getDecodeCharSet() {
        return cookieManager.getDecodeCharSet();
    }

    public void setDecodeCharSet(String decodeCharSet) {
        cookieManager.setDecodeCharSet(decodeCharSet);
    }

    // public String getCookie(String name) {
    // return cookieManager.getCookie(name);
    // }

    @Deprecated
    public void setCookie(String name, String value) {
        cookieManager.setCookie(name, value);
    }
}
