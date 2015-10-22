package com.chinaztt.fda.listlogic;

import java.io.InputStream;

/**
 * 当前类注释:网络请求回调接口
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.listlogic
 * 作者：江清清 on 15/10/22 09:52
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public interface RequestCallBack {

    int HTTPSTATUSERROR=0;
    int HTTPRESPONSEERROR=1;
    int OUTOFMEMORYERROR=2;

    void onRequestStart();
    void onRequestError(int errorCode, String errorMessage);
    void onRequestSuccess(String successMessage);
    void onRequestSuccess(InputStream successStream);
    void onRequestLoading();
    void onCancel();
}
