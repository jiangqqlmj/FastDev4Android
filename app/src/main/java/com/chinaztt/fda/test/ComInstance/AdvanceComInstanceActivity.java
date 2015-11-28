package com.chinaztt.fda.test.ComInstance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

/**
 * 当前类注释:SwipeRefreshLayout+RecyclerView+CardView升级版本
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.ComInstance
 * 作者：江清清 on 15/11/28 09:45
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class AdvanceComInstanceActivity extends BaseActivity{
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    private RecyclerView instance_recycler;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_instance_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("综合实例升级版");
        instance_recycler=(RecyclerView)this.findViewById(R.id.instance_recycler);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        instance_recycler.setLayoutManager(linearLayoutManager);

    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            AdvanceComInstanceActivity.this.finish();
        }
    }
}
