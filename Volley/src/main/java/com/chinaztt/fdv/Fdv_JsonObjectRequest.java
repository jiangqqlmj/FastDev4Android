package com.chinaztt.fdv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * 当前类注释:Volley JSON数据请求封装类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/16 20:31
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_JsonObjectRequest extends  Fdv_BaseRequest<JSONObject>{
    public Fdv_JsonObjectRequest(Context pContext) {
        super(pContext);
    }
    /**
     * 请求返回JSONObject对象 Get请求 无参数，或者get请求的参数直接拼接在URL上面
     * @param url   请求地址
     * @param listener  数据回调接口
     */
    public void get(String url, final Fdv_CallBackListener<JSONObject> listener){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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
        addRequest(jsonObjectRequest);
    }

    /**
     * 普通请求返回JSONObject对象 Get请求 携带请求参数
     * @param url  请求地址
     * @param listener   数据返回回调接口
     * @param params    请求参数
     */
    public void get(String url, final Fdv_CallBackListener<JSONObject> listener,Map<String,String> params){
        url=createGetUrlWithParams(url,params);
        this.get(url, listener);
    }

    /**
     * 发送POST请求, 返回JSONObject对象数据
     * @param url    请求地址
     * @param listener  数据返回回调接口
     * @param params   POST请求参数
     */
    public void post(String url, final Fdv_CallBackListener<JSONObject> listener,Map<String,String> params){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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
        addRequest(jsonObjectRequest,params);
    }

}
