package com.chinaztt.fdv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.Map;

/**
 * 当前类注释:数据请求 返回JSONArray格式数据
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/16 20:50
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_JsonArrayRequest extends  Fdv_BaseRequest<JSONArray>{

    public Fdv_JsonArrayRequest(Context pContext) {
        super(pContext);
    }

    /**
     * 请求返回JSONArray对象 Get请求 无参数，或者get请求的参数直接拼接在URL上面
     * @param url  请求地址
     * @param listener  数据返回回调接口
     */
    public void get(String url, final Fdv_CallBackListener<JSONArray> listener){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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
        addRequest(jsonArrayRequest);
    }

    /**
     * 请求返回JSONArray对象 Get请求 有参数，或者get请求的参数直接拼接在URL上面
     * @param url  请求地址
     * @param listener  请求数据返回回调接口
     * @param params   请求参数
     */
    public void get(String url, final Fdv_CallBackListener<JSONArray> listener,Map<String,String> params){
        url=createGetUrlWithParams(url,params);
        this.get(url, listener);
    }

    /**
     * POST请求返回JSONArray对象
     * @param url   请求地址
     * @param listener   请求数据返回回调接口
     * @param params   POST请求参数
     */
    public void post(String url, final Fdv_CallBackListener<JSONArray> listener,Map<String,String> params){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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
        addRequest(jsonArrayRequest,params);
    }
}
