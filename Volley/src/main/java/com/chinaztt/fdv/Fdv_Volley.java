package com.chinaztt.fdv;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 当前类注释:全局Fdv_Volley封装类管理类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/11 23:02
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_Volley {
    private static RequestQueue instance;
    public static RequestQueue getInstace(Context pContext){
        if(instance==null){
            instance= Volley.newRequestQueue(pContext);
        }
        return instance;
    }
}
