package com.tzg.tool.support.properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取属性文件（env.properties）工具。
 *
 * @author 曾林 2016/11/30.
 */
public final class PropUtil {

    public static String get( String key ) throws IOException {

        String property = System.getProperty( key );

        if ( StringUtils.isEmpty( property ) ) {

            Properties        properties = new Properties();
            String            location   = "file:/opt/resource/system.properties";
            Resource          resource   = new DefaultResourceLoader().getResource( location );
            InputStreamReader reader     = new InputStreamReader( resource.getInputStream(), "UTF-8" );

            DefaultPropertiesPersister persist = new DefaultPropertiesPersister();
            persist.load( properties, reader );

            property = properties.getProperty( key );

            reader.close();

        }

        return property;

    }

}
