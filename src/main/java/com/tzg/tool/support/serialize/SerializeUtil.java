package com.tzg.tool.support.serialize;

import com.tzg.tool.support.io.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializeUtil {

    private static final Logger logger = LoggerFactory.getLogger( SerializeUtil.class );

    /**
     * byte[]转换为Object
     *
     * @param bytes
     * @return
     */
    public static Object deserialize( byte[] bytes ) {
        if ( null == bytes ) {
            logger.warn( "unserialize method's parameter(bytes) cann't be null" );
            return null;
        }
        ByteArrayInputStream bais = null;
        ObjectInputStream    ois  = null;
        try {
            bais = new ByteArrayInputStream( bytes );
            ois = new ObjectInputStream( bais );
            return ois.readObject();
        } catch ( Exception e ) {
            logger.error( " unserialize {}:", e.getClass(), e );
        } finally {
            close( ois );
            close( bais );
        }
        return null;
    }
    /**
     * Object转换为byte[]
     *
     * @param object
     * @return
     */
    public static byte[] serialize( Object object ) {
        ObjectOutputStream    oos  = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream( baos );
            oos.writeObject( object );
            return baos.toByteArray();
        } catch ( Exception e ) {
            logger.error( " serializable:{},{}:", object, e.getClass(), e );
        } finally {
            close( oos );
            close( baos );
        }
        return new byte[ 0 ];
    }


    public static void close( Closeable closeable ) {
        IOUtil.closeQuietly( closeable );
    }

}
