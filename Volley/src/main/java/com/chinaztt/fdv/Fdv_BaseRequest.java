package com.chinaztt.fdv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/11 22:59
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_BaseRequest<T>{
    private static final String TAG=Fdv_BaseRequest.class.toString();
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    protected static RequestQueue requestQueue;
    private Context mContext;
    protected Fdv_BaseRequest(Context pContext){
        this.mContext=pContext;
        requestQueue=Fdv_Volley.getInstace(mContext);
    }
    /**
     * 请求加入到Volley Request请求队列中
     * @param request
     */
    protected void addRequest(Request request){
           this.addRequest(request,null);
    }

    /**
     * 请求和请求参数 加入到Volley Request请求队列中
     * @param request
     * @param params
     */
    protected void addRequest(Request request,Map<String,String> params){
        //请求中添加 请求参数
        request.setParams(params);
        requestQueue.add(request);
    }

    /**
     * 根据请求地址和请求参数进行拼接成新的请求地址
     * @param url  请求服务器地址
     * @param params  请求参数
     * @return  拼接过后的地址
     */
    protected  String createGetUrlWithParams(String url,Map<String,String> params){
        if(params!=null){
            StringBuffer stringBuffer=new StringBuffer(url);
            if(!url.contains("?")){
                stringBuffer.append('?');
            }
            for(Map.Entry<String,String> entry:params.entrySet()){
                String key=entry.getKey().toString();
                String value=null;
                if(entry.getValue()==null){
                    value="";
                }else {
                    value=entry.getValue().toString();
                }
                stringBuffer.append(key);
                stringBuffer.append("=");
                try {
                    Fdv_Log.d(TAG,"get获取数据的key:"+key);
                    Fdv_Log.d(TAG,"get获取数据的value:"+value);
                    value= URLEncoder.encode(value, DEFAULT_PARAMS_ENCODING);
                    Fdv_Log.d(TAG,"get编码后value:"+value);
                    stringBuffer.append(value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                stringBuffer.append('&');
            }
            //删除最后一个'&'
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
            url=stringBuffer.toString();
        }
        Fdv_Log.d(TAG,"get请求地址url:"+url);
        return url;
    }
}
