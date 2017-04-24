package com.tzg.tool.support.ip;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public final class RemoteIPUtil {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址。
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130
     * 用户真实IP为：192.168.1.110。
     * <p>
     * X-Forwarded-For和WL-Proxy-CLient-IP是Apache+Weblogic会对request对象进行再包装，附加一些WLS要用的头信息。
     *
     * @param request httpServletRequest
     * @return ip
     */
    public static String getIP( HttpServletRequest request ) {

        String ip = request.getHeader( "X-Forwarded-For" );

        if ( ip.length() > 15 ) {

            String[] ips = ip.split( "," );
            for ( String segment : ips ) {
                if ( !( "unknown".equalsIgnoreCase( segment ) ) ) {
                    ip = segment;
                    break;
                }
            }

        } else {

            String[] headers = new String[]{
                    "Proxy-Client-IP",
                    "WL-Proxy-Client-IP",
                    "HTTP_CLIENT_IP",
                    "HTTP_X_FORWARDED_FOR"
            };

            for ( String header : headers ) {
                ip = request.getHeader( header );
                if ( !StringUtils.isEmpty( ip ) && "unknown".equalsIgnoreCase( ip ) ) break;
            }

            ip = request.getRemoteAddr();
        }

        return ip.equals( "0:0:0:0:0:0:0:1" ) ? "127.0.0.1" : ip;

    }

}
