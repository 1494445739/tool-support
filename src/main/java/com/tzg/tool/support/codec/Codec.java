package com.tzg.tool.support.codec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 封装各种格式的编码解码工具类。
 * 1. commons-codec 的 hex/base64 编码
 * 2. 自制的 base62 编码
 * 3. commons-lang 的 xml/html/csv escape
 * 4. JDK 提供的 URLEncoder
 */
public final class Codec {

    private static final String DEFAULT_URL_CHARSET_ENCODING = "UTF-8";
    private static final char[] BASE62                       = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Hex编码
     **/
    public static String encodeHex( byte[] input ) {
        return Hex.encodeHexString( input );
    }

    /**
     * Hex解码
     */
    public static byte[] decodeHex( String input ) {
        try {
            return Hex.decodeHex( input.toCharArray() );
        } catch ( DecoderException e ) {
            throw new IllegalStateException( "Hex Decoder exception", e );
        }
    }

    /**
     * Base64 编码.
     * <p>
     * Base64 是网络上常见的传输 8 bit 字节码的编码方式之一。Base64 就是一种基于64个可打印字符来表示二进制数据的方法。一般用于HTTP环境下传递较长的标识信息。
     * 比如作为 HTTP 表单和 HTTP GET URL 中的参数。在其他应用中，也常常需要把二进制数据编码为适合放在URL（包括隐藏表单域）中的形式。此时，采用 Base64 编码
     * 具有不可读性，需要解码后才能阅读。
     */
    public static String encodeBase64( byte[] input ) {
        return Base64.encodeBase64String( input );
    }

    /**
     * Base64 编码.
     * URL安全（ 将Base64中的URL非法字符 '+' 和 '/' 转为 '-' 和 '_'， 见RFC3548 ）.因为标准的Base64并不适合直接放在URL里进行传输，因为URL会把标准的Base64
     * 中的 ‘/’ 和 '+' 字符变为形如 "%XX" 的形式，而这些 '%' 号在存入数据库时还需要再进行转换，因为 ANSI SQL 中已将 '%' 号用作通配符。
     */
    public static String encodeUrlSafeBase64( byte[] input ) {
        return Base64.encodeBase64URLSafeString( input );
    }

    /**
     * Base62 编码
     */
    public static String encodeBase62( byte[] input ) {
        char[] chars = new char[ input.length ];
        for ( int i = 0; i < input.length; i++ ) {
            chars[ i ] = BASE62[ ( ( input[ i ] & 0xFF ) % BASE62.length ) ];
        }
        return new String( chars );
    }

    /**
     * Html 转码。防止恶意HTML注入攻击。它指的是恶意攻击者往Web页面里插入恶意的html代码，当用户浏览该网页之时，嵌入其中的Web里面的html代码会被执行,从而达到恶意用户
     * 的特殊目的。针对不可信的输入可以采用 apache.commons.lang3.StringEscapeUtils对输入字符串进行过滤，将 '<'、'>'、'&'三个字符转换成html编码格式。&lt; &gt; 和 &amp;
     * 防止HTML注入攻击。
     */
    public static String escapeHtml( String html ) {
        return StringEscapeUtils.escapeHtml4( html );
    }

    /**
     * Html 解码。
     */
    public static String unescapeHtml( String htmlEscaped ) {
        return StringEscapeUtils.unescapeHtml4( htmlEscaped );
    }

    /**
     * Xml 转码
     */
    public static String escapeXml( String xml ) {
        return StringEscapeUtils.escapeXml10( xml );
    }

    /**
     * Xml 解码
     */
    public static String unescapeXml( String xmlEscaped ) {
        return StringEscapeUtils.unescapeXml( xmlEscaped );
    }

    /**
     * csv 转码
     */
    public static String escapeCsv( String csv ) {
        return StringEscapeUtils.escapeCsv( csv );
    }

    /**
     * csv 解码
     */
    public static String unescapeCsv( String csvEscaped ) {
        return StringEscapeUtils.unescapeCsv( csvEscaped );
    }

    /**
     * URL 编码，默认是 UTF-8
     */
    public static String urlEncode( String part ) throws UnsupportedEncodingException {
        return URLEncoder.encode( part, DEFAULT_URL_CHARSET_ENCODING );
    }

    /**
     * URL 解码，Encode默认为 UTF-8
     */
    public static String urlDecode( String part ) throws UnsupportedEncodingException {
        return URLDecoder.decode( part, DEFAULT_URL_CHARSET_ENCODING );
    }

}
