package com.tzg.tool.support.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/*
 * 利用HttpClient进行post请求的工具类
 */

/**
 * HttpClient 工具类。提供 post 方法
 */
public class HttpClientUtil {

    public static String doPost( String url, Map< String, String > map, String charset ) {

        String result = null;

        try {

            HttpPost            httpPost   = new HttpPost( url );
            CloseableHttpClient httpClient = getSSLClient();

            List< NameValuePair > list = new ArrayList< NameValuePair >();
            map.forEach( ( key, value ) -> list.add( new BasicNameValuePair( key, value ) ) );

            Predicate< List > predicate = ( ls ) -> ls.size() > 0;
            if ( predicate.test( list ) ) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity( list, charset );
                httpPost.setEntity( entity );
            }

            HttpResponse        response      = httpClient.execute( httpPost );
            Predicate< Object > nullPredicate = Objects::isNull;
            if ( !nullPredicate.test( response ) ) {
                HttpEntity resEntity = response.getEntity();
                if ( !nullPredicate.test( resEntity ) ) {
                    result = EntityUtils.toString( resEntity, charset );
                }
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return result;

    }

    public static CloseableHttpClient getSSLClient() throws Exception {

        SSLContext ctx = SSLContext.getInstance( "TLS" );
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted( X509Certificate[] chain, String authType ) throws CertificateException {}
            @Override
            public void checkServerTrusted( X509Certificate[] chain, String authType ) throws CertificateException {}
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        ctx.init( null, new TrustManager[]{ tm }, null );

        HttpClientBuilder          builder              = HttpClientBuilder.create();
        SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory( ctx, new NoopHostnameVerifier() );
        builder.setSSLSocketFactory( sslConnectionFactory );

        Registry< ConnectionSocketFactory > registry = RegistryBuilder.< ConnectionSocketFactory >create()
                .register( "https", sslConnectionFactory )
                .build();

        HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager( registry );

        builder.setConnectionManager( ccm );

        return builder.build();

    }

}