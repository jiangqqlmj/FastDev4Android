package com.chinaztt.fda.test.ComInstance;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/27 20:56
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class ComInstanceActivity extends BaseActivity {
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    private RecyclerView instance_recycler;
    private ComInstanceAdapter adapter;
    private SwipeRefreshLayout instance_swiperefreshlayout;
    private int lastVisibleItem;
    //是否正在加载更多的标志
    private boolean isMoreLoading=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_instance_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        instance_swiperefreshlayout=(SwipeRefreshLayout)this.findViewById(R.id.instance_swiperefreshlayout);
        //设置刷新时动画的颜色，可以设置4个
        instance_swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        instance_swiperefreshlayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("综合实例");
        instance_recycler=(RecyclerView)this.findViewById(R.id.instance_recycler);
        final GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        instance_recycler.setLayoutManager(gridLayoutManager);
        instance_recycler.setAdapter(adapter = new ComInstanceAdapter(this, InstanceDataUtils.getInstanceBeans()));
        //添加Item点击监听事件
        adapter.setOnItemClickListener(new ComInstanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ComInstanceActivity.this,"点击了第"+position+"项",Toast.LENGTH_SHORT).show();
            }
        });
        //下拉刷新
        instance_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                  new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          List<InstanceBean> temp=new ArrayList<InstanceBean>();
                          for(int i=0;i<5;i++){
                              InstanceBean bean=new InstanceBean("我是杨颖Item"+i,R.drawable.baby);
                              temp.add(bean);
                          }
                          adapter.addRefreshBeans(temp);
                          instance_swiperefreshlayout.setRefreshing(false);
                          Toast.makeText(ComInstanceActivity.this, "更新了五条数据...", Toast.LENGTH_SHORT).show();
                      }
                  },3500);
            }
        });
        //上拉加载更多
        instance_recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    if(!isMoreLoading){
                      isMoreLoading=true;
                      new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<InstanceBean> temp=new ArrayList<InstanceBean>();
                            for (int i = 0; i < 5; i++) {
                                    InstanceBean bean=new InstanceBean("我是MoreItem"+i,R.drawable.meinv);
                                    temp.add(bean);
                            }
                            adapter.addMoreBeans(temp);
                            Toast.makeText(ComInstanceActivity.this, "上拉加载了五条数据...", Toast.LENGTH_SHORT).show();
                            isMoreLoading=false;
                        }
                    },2000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            }
        });

    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
                 ComInstanceActivity.this.finish();
        }
    }
}
