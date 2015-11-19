package com.chinaztt.fda.test;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.adapter.TestRecyclerAdapter;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 当前类注释:RecyclerView使用实例测试demo
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/17 15:10
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.recyclerview_test_layout)
public class RecyclerViewTestActivity extends BaseActivity {
    @ViewById
    LinearLayout top_bar_linear_back;
    @ViewById
    TextView top_bar_title;

    private RecyclerView recyclerView_one;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @AfterViews
    public void initView(){
        top_bar_title.setText("RecyclerView使用实例");
        //设置设置RecyclerView
        recyclerView_one=(RecyclerView)this.findViewById(R.id.recyclerView_one);
        recyclerView_one.setHasFixedSize(true);
        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager=new GridLayoutManager(this,3);
        mLayoutManager=new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        recyclerView_one.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new TestRecyclerAdapter(this);
        recyclerView_one.setAdapter(mAdapter);


    }
    @Click(R.id.top_bar_linear_back)
    public void viewClick(){
        this.finish();
    }
}
