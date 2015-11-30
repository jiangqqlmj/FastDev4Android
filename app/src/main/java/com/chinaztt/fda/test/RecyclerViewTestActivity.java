package com.chinaztt.fda.test;

import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinaztt.fda.adapter.TestRecyclerAdapter;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.widget.AdvanceDecoration;

import java.util.Random;

/**
 * 当前类注释:RecyclerView使用实例测试demo
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/17 15:10
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class RecyclerViewTestActivity extends BaseActivity {
    private Button btn_add,btn_delete;
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    private RecyclerView recyclerView_one;
    private TestRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_test_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        btn_add=(Button)this.findViewById(R.id.btn_add);
        btn_delete=(Button)this.findViewById(R.id.btn_delete);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        btn_add.setOnClickListener(new CustomOnClickListener());
        btn_delete.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("RecyclerView使用实例");
        //开始设置RecyclerView
        recyclerView_one=(RecyclerView)this.findViewById(R.id.recyclerView_one);
        recyclerView_one.setHasFixedSize(true);
        //1.LinearLayoutManager 线性布局类型
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_one.setLayoutManager(mLayoutManager);

        //2.GridLayoutManager 表格布局类型
//        GridLayoutManager girdLayoutManager=new GridLayoutManager(this,4);
//        recyclerView_one.setLayoutManager(girdLayoutManager);
        //3.采用StaggeredGridLayoutManager  流式布局类型
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL);
//        recyclerView_one.setLayoutManager(staggeredGridLayoutManager);
        //添加默认的动画效果
        recyclerView_one.setItemAnimator(new DefaultItemAnimator());
        //添加分隔线
        recyclerView_one.addItemDecoration(new AdvanceDecoration(this,OrientationHelper.VERTICAL));
        mAdapter = new TestRecyclerAdapter(this, new TestRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewTestActivity.this, "点击了第"+position+"项", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView_one.setAdapter(mAdapter);

    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.top_bar_linear_back:
                    RecyclerViewTestActivity.this.finish();
                    break;
                case R.id.btn_add:
                    //添加数据
                    mAdapter.addItem("add item",5);
                    break;
                case R.id.btn_delete:
                    //删除数据
                    mAdapter.removeItem("item4");
                    break;
            }

        }
    }
}
