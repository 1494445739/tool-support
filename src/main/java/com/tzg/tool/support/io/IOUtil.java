package com.tzg.tool.support.io;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class IOUtil extends IOUtils {

    private static Logger logger = LoggerFactory.getLogger( IOUtil.class );

    public static String convertStream2String( InputStream inputStream ) {
        try {
            return IOUtils.toString( inputStream, "UTF-8" );
        } catch ( IOException e ) {
            logger.error( "{}:", e.getClass(), e );
        } finally {
            closeQuietly( inputStream );
        }
        return "";
    }

    /**
     * 读取request流
     *
     * @throws IOException
     */
    public static String getInputStream( HttpServletRequest request ) throws IOException {
        InputStream instream = request.getInputStream();
        if ( instream == null ) {
            return null;
        }
        return convertStream2String( instream );
    }

}
