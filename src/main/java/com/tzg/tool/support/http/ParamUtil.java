package com.tzg.tool.support.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tzg.tool.support.io.IOUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

public final class ParamUtil {

    public static JSONObject getJSONFromRequest( HttpServletRequest request ) throws Exception {
        InputStream is          = request.getInputStream();
        String      paramString = IOUtil.convertStream2String( is );
        return JSON.parseObject( paramString );
    }

}
