package com.chinaztt.fda.test.RecyclerViewRefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinaztt.fda.adapter.RefreshRecyclerAdapter;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;
import com.chinaztt.fda.widget.AdvanceDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:RecyclerView结合SwipeRefreshLayout实现下拉刷新和上拉加载更多
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/21 21:23
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class RecyclerRefreshActivity extends BaseActivity {
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    private SwipeRefreshLayout demo_swiperefreshlayout;
    private RecyclerView demo_recycler;
    private RefreshRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_refresh_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("RecyclerView下拉刷新,下拉加载更多...");
        demo_swiperefreshlayout=(SwipeRefreshLayout)this.findViewById(R.id.demo_swiperefreshlayout);
        demo_recycler=(RecyclerView)this.findViewById(R.id.demo_recycler);
        //设置刷新时动画的颜色，可以设置4个
        demo_swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        demo_swiperefreshlayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        demo_swiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        demo_recycler.setLayoutManager(linearLayoutManager);
        //添加分隔线
        demo_recycler.addItemDecoration(new AdvanceDecoration(this, OrientationHelper.VERTICAL));
        demo_recycler.setAdapter(adapter = new RefreshRecyclerAdapter(this));
        demo_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("zttjiangqq", "invoke onRefresh...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> newDatas = new ArrayList<String>();
                        for (int i = 0; i < 5; i++) {
                            int index = i + 1;
                            newDatas.add("new item" + index);
                        }
                        adapter.addItem(newDatas);
                        demo_swiperefreshlayout.setRefreshing(false);
                        Toast.makeText(RecyclerRefreshActivity.this, "更新了五条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 1500);
            }
        });
        //RecyclerView滑动监听
        demo_recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> newDatas = new ArrayList<String>();
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                newDatas.add("more item" + index);
                            }
                            adapter.addMoreItem(newDatas);
                        }
                    },1000);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            RecyclerRefreshActivity.this.finish();
        }
    }
}
