package com.tzg.tool.support.datatype;

import org.springframework.util.StringUtils;

public final class StringUtil {

    public String camel( String param ) {

        char UNDERLINE = '_';

        if ( StringUtils.isEmpty( param ) ) return "";

        int           len = param.length();
        StringBuilder sb  = new StringBuilder( len );
        for ( int i = 0; i < len; i++ ) {
            char c = param.charAt( i );
            if ( c == UNDERLINE ) {
                if ( ++i < len ) {
                    sb.append( Character.toUpperCase( param.charAt( i ) ) );
                }
            } else {
                sb.append( c );
            }
        }
        return sb.toString();
    }

}
