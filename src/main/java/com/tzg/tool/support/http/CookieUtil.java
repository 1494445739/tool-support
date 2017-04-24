package com.tzg.tool.support.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * http cookie工具.
 *
 * @author 曾林 2016/12/14.
 */
public final class CookieUtil {

    /**
     * 根据cookie中的key获取cookie中的值。如果cookie中并不存在key，则返回null。
     *
     * @param req HttpServletRequest
     * @param key cookie key
     */
    public static String getVal( HttpServletRequest req, String key ) {

        String val = null;

        Cookie[] cookies = req.getCookies();

        for ( Cookie cookie : cookies ) {
            if ( key.equals( cookie.getName() ) ) {
                val = cookie.getValue();
            }
        }

        return val;

    }

}
