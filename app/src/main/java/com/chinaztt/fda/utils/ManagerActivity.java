package com.chinaztt.fda.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释: Activity 管理，可以支持完全退出，不过是否能够完全退出，
 * 肯定是不行，如果应用中有一些service，可能会重启
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.utils
 * 作者：江清清 on 15/10/22 09:01
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class ManagerActivity {
    public static ManagerActivity instance = new ManagerActivity();
    private List<Activity> mLists = new ArrayList<Activity>();
    private ManagerActivity() {
    }
    public synchronized static ManagerActivity getInstance() {

        return instance;
    }

    /**
     * 往集合中添加一个Activity
     * @param pActivity
     */
    public void addActivity(Activity pActivity) {
        if (pActivity != null) {
            mLists.add(pActivity);
        }
    }

    /**
     * 从集合中删除一个Activity
     * @param pActivity  需要删除的Activity
     */
    public void removeActivity(Activity pActivity) {
        if (pActivity != null) {
            if (mLists.contains(pActivity)) {
                mLists.remove(pActivity);
                pActivity.finish();
                pActivity = null;
            }
        }
    }

    //从栈中进行删除集合顶得Activity
    public void popActivity() {
        Activity activity = mLists.get(mLists.size() - 1);
        removeActivity(activity);
    }
    public int getNum() {
        return mLists.size();
    }
    /**
     * 完全删除集合中
     */
    public void finishActivity() {
        if (mLists != null && mLists.size() >= 0) {
            for (Activity pActivity : mLists) {
                pActivity.finish();
                pActivity = null;
            }
        }
    }
}
