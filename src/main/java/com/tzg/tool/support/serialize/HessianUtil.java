package com.tzg.tool.support.serialize;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianUtil {

    public static byte[] serialize( Object object ) {
        if ( null == object ) {
            return new byte[ 0 ];
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput         ho = new HessianOutput( os );
            ho.writeObject( object );
            return os.toByteArray();
        } catch ( IOException e ) {
            return SerializeUtil.serialize( object );
        }
    }

    public static Object deserialize( byte[] bytes ) {
        if ( null == bytes ) {
            return null;
        }
        try {
            ByteArrayInputStream is = new ByteArrayInputStream( bytes );
            HessianInput         hi = new HessianInput( is );
            return hi.readObject();
        } catch ( IOException e ) {
            return SerializeUtil.deserialize( bytes );
        }
    }

}
