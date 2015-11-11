package com.chinaztt.fdv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/11 22:59
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_BaseRequest {
    protected static RequestQueue requestQueue;
    private Context mContext;
    protected Fdv_BaseRequest(Context pContext){
        this.mContext=pContext;
    }
    /**
     * 请求加入到Volley Request请求队列中
     * @param request
     */
    protected  void addRequest(Request request){
        Fdv_Volley.getInstace(mContext).add(request);
    }
}
