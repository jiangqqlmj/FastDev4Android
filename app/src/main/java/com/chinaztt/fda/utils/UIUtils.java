package com.chinaztt.fda.utils;

import android.annotation.SuppressLint;

import com.chinaztt.fda.application.FDApplication;
import com.chinaztt.fda.spreference.SharedPreferencesHelper;
import com.chinaztt.fda.widget.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 当前类注释:进行PullToRefreshListView 下拉刷新控件的更新时间设置
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.utils
 * 作者：江清清 on 15/10/23 13:35
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class UIUtils {

    public static  final  String DEMO_PULL_TIME_KEY="demo_pull_time_key";

    /**
     * 设置上次更新数据时间
     * @param listView
     * @param key
     */
    public static void setPullToRefreshLastUpdated(PullToRefreshListView listView, String key) {
        SharedPreferencesHelper spHelper = SharedPreferencesHelper.getInstance(FDApplication.getInstance());
        long lastUpdateTimeStamp = spHelper.getLongValue(key);
        listView.setLastUpdated(getUpdateTimeString(lastUpdateTimeStamp));
    }

    /**
     * 保存更新数据时间
     * @param listView
     * @param key
     */
    public static void savePullToRefreshLastUpdateAt(PullToRefreshListView listView, String key) {
        listView.onRefreshComplete();
        SharedPreferencesHelper spHelper = SharedPreferencesHelper.getInstance(FDApplication.getInstance());
        long lastUpdateTimeStamp=System.currentTimeMillis();
        spHelper.putLongValue(key, lastUpdateTimeStamp);
        listView.setLastUpdated(getUpdateTimeString(lastUpdateTimeStamp));
    }

    /**
     * 更新时间字符串
     * @param timestamp
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getUpdateTimeString(long timestamp) {
        if (timestamp <= 0) {
            return "上次更新时间:";
        } else {
            String textDate = "上次更新时间:";
            Calendar now = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timestamp);
            if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                    && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                    && c.get(Calendar.DATE) == now.get(Calendar.DATE)) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                return textDate += sdf.format(c.getTime());
            } else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
                return textDate += sdf.format(c.getTime());
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                return textDate += sdf.format(c.getTime());
            }
        }
    }
}
