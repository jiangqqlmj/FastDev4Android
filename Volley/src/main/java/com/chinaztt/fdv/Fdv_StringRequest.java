package com.chinaztt.fdv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * 当前类注释:Volley 字符串、文本数据请求封装类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/11 13:43
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_StringRequest extends Fdv_BaseRequest{
    public Fdv_StringRequest(Context pContext){
        super(pContext);
    }
    public void get(String url, final Fdv_CallBackListener listener){
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
}
