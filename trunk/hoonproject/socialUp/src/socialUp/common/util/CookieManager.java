// Source File Name:   CookieManager.java

package socialUp.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.*;

public class CookieManager
{

    public CookieManager(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        comment = null;
        domain = null;
        maxAge = 0x80000000;
        path = "/";
        secure = false;
        encodeCharSet = "8859_1";
        decodeCharSet = "EUC-KR";
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public int getMaxAge()
    {
        return maxAge;
    }

    public void setMaxAge(int maxAge)
    {
        this.maxAge = maxAge;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public boolean isSecure()
    {
        return secure;
    }

    public void setSecure(boolean secure)
    {
        this.secure = secure;
    }

    public String getEncodeCharSet()
    {
        return encodeCharSet;
    }

    public void setEncodeCharSet(String encodeCharSet)
    {
        this.encodeCharSet = encodeCharSet;
    }

    public String getDecodeCharSet()
    {
        return decodeCharSet;
    }

    public void setDecodeCharSet(String decodeCharSet)
    {
        this.decodeCharSet = decodeCharSet;
    }

    public void setCookie(String name, String value)
    {
        try
        {
            value = URLEncoder.encode(value, encodeCharSet);
        }
        catch(UnsupportedEncodingException e) { }
        Cookie cookie = new Cookie(name, value);
        if(domain != null)
            cookie.setDomain(domain);
        if(comment != null)
            cookie.setComment(comment);
        if(maxAge > 0x80000000)
            cookie.setMaxAge(maxAge);
        if(path != null)
            cookie.setPath(path);
        if(secure)
            cookie.setSecure(secure);
        response.addCookie(cookie);
    }

    public String getCookie(String name)
    {
        Cookie cookies[] = request.getCookies();
        if(cookies == null)
            return "";
        String value = "";
        int i = 0;
        do
        {
            if(i >= cookies.length)
                break;
            if(name.equals(cookies[i].getName()))
            {
                try
                {
                    value = URLDecoder.decode(cookies[i].getValue(), decodeCharSet);
                }
                catch(UnsupportedEncodingException e) { }
                break;
            }
            i++;
        } while(true);
        return value;
    }

    public Map toMap()
    {
        HashMap cookieMap = new HashMap();
        Cookie cookies[] = request.getCookies();
        if(cookies == null || cookies.length == 0)
            return cookieMap;
        for(int idx = 0; idx < cookies.length; idx++)
            cookieMap.put(cookies[idx].getName(), cookies[idx].getValue());

        return cookieMap;
    }

    public int removeCookie(String value)
    {
        int i = 0;
        if(request == null)
            return i;
        Cookie acookie[] = request.getCookies();
        if(acookie == null)
            return i;
        if(value == null)
        {
            for(int j = 0; j < acookie.length; j++)
            {
                if(domain != null)
                    acookie[j].setDomain(domain);
                acookie[j].setMaxAge(0);
                acookie[j].setPath("/");
                response.addCookie(acookie[j]);
                i++;
            }

        } else
        {
            for(int k = 0; k < acookie.length; k++)
            {
                Cookie cookie = acookie[k];
                if(!value.equalsIgnoreCase(cookie.getName()))
                    continue;
                if(domain != null)
                    cookie.setDomain(domain);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                i++;
            }

        }
        return i;
    }

    private HttpServletResponse response;
    private HttpServletRequest request;
    private String comment;
    private String domain;
    private int maxAge;
    private String path;
    private boolean secure;
    private String encodeCharSet;
    private String decodeCharSet;
}