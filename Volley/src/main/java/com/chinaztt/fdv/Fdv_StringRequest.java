package com.chinaztt.fdv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;
/**
 * 当前类注释:Volley 字符串、文本数据请求封装类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/11 13:43
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_StringRequest extends Fdv_BaseRequest<String>{
    public Fdv_StringRequest(Context pContext){
        super(pContext);
    }
    /**
     * 普通文本等信息 Get请求 无参数，或者get请求的参数直接拼接在URL上面
     * @param url  请求的地址
     * @param listener  数据请求返回接口回调
     */
    public void get(String url, final Fdv_CallBackListener<String> listener){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(listener!=null){
                    listener.onSuccessResponse(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(listener!=null){
                    listener.onErrorResponse(error);
                }
            }
        });
        addRequest(stringRequest);
    }

    /**
     * 普通文本等信息 Get请求 携带请求参数
     * @param url   请求地址
     * @param listener  数据请求接口回调
     * @param params    请求的参数
     */
    public void get(String url, final Fdv_CallBackListener<String> listener,Map<String,String> params){
        //进行在url地址上面拼接请求参数
        url=createGetUrlWithParams(url,params);
        this.get(url,listener);
    }
    /**
     * 根据地址和请求参数 发送POST请求
     * @param url   地址服务器地址
     * @param listener  数据加载回调接口
     * @param params  请求参数
     */
    public void post(String url, final Fdv_CallBackListener<String> listener,Map<String,String> params){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(listener!=null){
                    listener.onSuccessResponse(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(listener!=null){
                    listener.onErrorResponse(error);
                }
            }
        });
        addRequest(stringRequest,params);
    }
}
