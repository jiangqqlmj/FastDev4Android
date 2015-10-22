package com.chinaztt.fda.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 当前类注释:判断网络状态 是连接还是断开状态
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.utils
 * 作者：江清清 on 15/10/22 09:46
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class JudgeNetWorker {
    private static final int CMNET=3;
    private static final int CMWAP=2;
    private static final int WIFI=1;
    public static boolean checkConnectionOk(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    /**
     * @author jiangqq
     * 获取当前的网络状态  -1：没有网络  1：WIFI网络 2：wap网络 3：net网络
     * @param context
     * @return
     */
    public static int getAPNType(Context context){
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo==null){
            return netType;
        }
        int nType = networkInfo.getType();
        if(nType==ConnectivityManager.TYPE_MOBILE){
            Log.e("networkInfo.getExtraInfo()", "networkInfo.getExtraInfo() is "+networkInfo.getExtraInfo());
            if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                netType = CMNET;
            }
            else{
                netType = CMWAP;
            }
        }
        else if(nType==ConnectivityManager.TYPE_WIFI){
            netType = WIFI;
        }
        return netType;
    }
}
