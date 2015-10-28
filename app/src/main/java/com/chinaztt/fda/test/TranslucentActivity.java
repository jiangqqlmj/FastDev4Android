package com.chinaztt.fda.test;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.androidannotations.annotations.EActivity;
import org.w3c.dom.Text;

import java.lang.reflect.Field;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/27 08:48
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class TranslucentActivity extends BaseActivity {
    //++++++++++++++++使用注意事项++++++++++++++++++
    //这边采用第一种模式(mode=0)所以在布局文件translucent_layout.xml中跟界面linearlayout去除了
    //android:fitsSystemWindows="true"
    //android:clipToPadding="true"
    //当人们采用第三方库的时候(mode=1)，需要上面两句加上即可
    //+++++++++++++++++++++++++++++++++++++++++++++
    private static final  String TAG="TranslucentActivity";
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translucent_layout);
        tv=(TextView)this.findViewById(R.id.tv);
        int mode=getIntent().getIntExtra("mode",0);
        Log.d(TAG,"MODE:"+mode);
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (mode == 0) {
                 LinearLayout linear_bar=(LinearLayout)findViewById(R.id.linear_bar);
                 linear_bar.setVisibility(View.VISIBLE);
                 int statusHeight=getStatusBarHeight();
                 android.widget.LinearLayout.LayoutParams params=(android.widget.LinearLayout.LayoutParams )linear_bar.getLayoutParams();
                 params.height=statusHeight;
                 linear_bar.setLayoutParams(params);
                 tv.setText("系统方法沉浸式实现");
            } else if (mode == 1) {
                // create our manager instance after the content view is set
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                // 激活状态栏
                tintManager.setStatusBarTintEnabled(true);
                // enable navigation bar tint 激活导航栏
                tintManager.setNavigationBarTintEnabled(true);
                //设置系统栏设置颜色
                //tintManager.setTintColor(R.color.red);
                //给状态栏设置颜色
                tintManager.setStatusBarTintResource(R.color.middle_red);
                // 设置导航栏设置资源
                tintManager.setNavigationBarTintResource(R.color.color_nav);
                tv.setText("第三方库沉浸式实现");
            }
        }
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    private int getStatusBarHeight(){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
