package com.chinaztt.fdv;

import com.android.volley.VolleyError;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/11 23:18
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public interface Fdv_CallBackListener<T> {
    void onSuccessResponse(T response);
    void onErrorResponse(VolleyError error);
}
