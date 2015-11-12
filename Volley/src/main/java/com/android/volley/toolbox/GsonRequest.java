package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * 当前类注释:进行扩展GSON数据解析json数据
 * 项目名：FastDev4Android
 * 包名：com.android.volley.toolbox
 * 作者：江清清 on 15/11/12 18:28
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class GsonRequest<T> extends Request<T> {
    private final Response.Listener<T> listener;
    private Gson gson;
    private Class<T> mClass;

    /**
     * GsonRequest 构造函数
     * @param method  请求方法
     * @param url     请求地址
     * @param listener    数据请求成功回调接口
     * @param errorListener  数据请求失败回调接口
     * @param pClass     需要进行解析的类
     */
    public GsonRequest(int method,String url,Response.Listener<T> listener,Response.ErrorListener errorListener,Class<T> pClass){
         super(method,url,errorListener);
         this.listener=listener;
         gson=new Gson();
         mClass=pClass;
    }

    /**
     * GsonRequest 构造函数  默认使用GET请求方法
     * @param url
     * @param listener
     * @param errorListener
     * @param pClass
     */
    public GsonRequest(String url,Response.Listener<T> listener,Response.ErrorListener errorListener,Class<T> pClass){
        super(Method.GET,url,errorListener);
        this.listener=listener;
        gson=new Gson();
        mClass=pClass;
    }

    /**
     * 数据解析
     * @param response Response from the network  网络请求返回数据
     * @return
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonStr=new String(response.data,HttpHeaderParser.parseCharset(response.headers));
            T data=gson.fromJson(jsonStr,mClass);
            return Response.success(data,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 数据分发
     * @param response The parsed response returned by
     */
    @Override
    protected void deliverResponse(T response) {
          listener.onResponse(response);
    }
}
