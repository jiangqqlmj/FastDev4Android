package com.chinaztt.fda.test.ComInstance;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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
    private AdvanceComInstanceAdapter adapter;
    private SwipeRefreshLayout instance_swiperefreshlayout;
    private int lastVisibleItem;
    //是否正在加载更多的标志
    private boolean isMoreLoading=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_instance_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("综合实例升级版");
        instance_swiperefreshlayout=(SwipeRefreshLayout)this.findViewById(R.id.instance_swiperefreshlayout);
        //设置刷新时动画的颜色，可以设置4个
        instance_swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        instance_swiperefreshlayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        instance_recycler=(RecyclerView)this.findViewById(R.id.instance_recycler);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        instance_recycler.setLayoutManager(linearLayoutManager);
        instance_recycler.setAdapter(adapter = new AdvanceComInstanceAdapter(this, AdvanceIntanceDataUtils.getAdvanceInstanceBeans()));
        //下拉刷新
        instance_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        List<AdvanceInstanceBean> temp = new ArrayList<AdvanceInstanceBean>();
                        for (int i = 0; i < 3; i++) {
                            AdvanceInstanceBean advanceInstanceBean = new AdvanceInstanceBean();
                            List<InstanceBean> instanceBeans = new ArrayList<InstanceBean>();
                            for (int j = 0; j < 2; j++) {
                                InstanceBean bean = null;
                                if (j == 0) {
                                    bean = new InstanceBean("我是左边标题Item" + i, R.drawable.baby);
                                } else {
                                    bean = new InstanceBean("我是右边标题Item" + i, R.drawable.baby);
                                }
                                instanceBeans.add(bean);
                            }
                            advanceInstanceBean.setInstanceBeans(instanceBeans);
                            temp.add(advanceInstanceBean);
                        }
                        adapter.addRefreshBeans(temp);
                        instance_swiperefreshlayout.setRefreshing(false);
                        Toast.makeText(AdvanceComInstanceActivity.this, "更新了6条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 3500);

            }
        });
            //上拉加载更多
            instance_recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                        if (!isMoreLoading) {
                            isMoreLoading = true;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    List<AdvanceInstanceBean> temp=new ArrayList<AdvanceInstanceBean>();
                                    for (int i=0;i<2;i++){
                                        AdvanceInstanceBean advanceInstanceBean=new AdvanceInstanceBean();
                                        List<InstanceBean> instanceBeans=new ArrayList<InstanceBean>();
                                        for(int j=0;j<2;j++){
                                            InstanceBean bean=null;
                                            if(j==0){
                                                bean=new InstanceBean("我是左边标题Item"+i, R.drawable.meinv);
                                            }else {
                                                bean=new InstanceBean("我是右边标题Item"+i, R.drawable.meinv);
                                            }
                                            instanceBeans.add(bean);
                                        }
                                        advanceInstanceBean.setInstanceBeans(instanceBeans);
                                        temp.add(advanceInstanceBean);
                                    }
                                    adapter.addMoreBeans(temp);
                                    Toast.makeText(AdvanceComInstanceActivity.this, "上拉加载了四条数据...", Toast.LENGTH_SHORT).show();
                                    isMoreLoading = false;
                                }
                            }, 2000);
                        }
                    }
                }
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }
            });

        adapter.setOnItemClickListener(new AdvanceComInstanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(InstanceBean instanceBean) {
                Toast.makeText(AdvanceComInstanceActivity.this,"点击的Item数据为:"+instanceBean,Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            AdvanceComInstanceActivity.this.finish();
        }
    }
}
