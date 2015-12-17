package com.chinaztt.fda.test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;

/**
 * 当前类注释:承载Fragment的Activity类,采用google支持库 TabLayout实现Tab标签
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/12/2 21:02
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class TabLayoutActivity extends FragmentActivity{
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout_activity_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabLayoutActivity.this.finish();
            }
        });
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("TabLayout打造网易新闻Tab标签");
    }
}
