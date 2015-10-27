package com.chinaztt.fda.ui.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.chinaztt.fda.utils.Log;
import com.chinaztt.fda.utils.ManagerActivity;

/**
 * 当前类注释:Activity框架封装类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.base
 * 作者：江清清 on 15/10/22 12:43
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class BaseFrameActvity extends Activity{
    private static final String TAG="lifescycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除系统标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "BaseActivity onCreate Invoke...");
        ManagerActivity.getInstance().addActivity(this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity onStart Invoke...");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity onRestart Invoke...");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity onResume Invoke...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity onPause Invoke...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "BaseActivity onStop Invoke...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BaseActivity onDestroy Invoke...");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "BaseActivity onLowMemory Invoke...");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "BaseActivity onBackPressed Invoke...");
        ManagerActivity.getInstance().removeActivity(this);
    }
}
