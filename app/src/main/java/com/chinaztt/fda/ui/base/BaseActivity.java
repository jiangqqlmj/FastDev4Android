package com.chinaztt.fda.ui.base;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * 当前类注释:基类Actvity 主要封装一些工具类的使用,公共方法,配置
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.base
 * 作者：江清清 on 15/10/22 08:59
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class BaseActivity extends BaseFrameActvity{
    /**
     * 获取当前view的LayoutInflater实例
     * @return
     */
    protected LayoutInflater getLayouInflater() {
        LayoutInflater _LayoutInflater = LayoutInflater.from(this);
        return _LayoutInflater;
    }

    /**
     * 弹出toast 显示时长short
     * @param pMsg
     */
    protected void showToastMsgShort(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 弹出toase 显示时长long
     * @param pMsg
     */
    protected void showToastMsgLong(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_LONG).show();
    }
    /**
     * 根据传入的类(class)打开指定的activity
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        Intent _Intent = new Intent();
        _Intent.setClass(this, pClass);
        startActivity(_Intent);
    }

    protected void openActivityByIntent(Intent pIntent){
        startActivity(pIntent);
    }

}
