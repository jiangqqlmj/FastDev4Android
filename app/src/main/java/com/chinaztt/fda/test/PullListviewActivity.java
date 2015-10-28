package com.chinaztt.fda.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.UIUtils;
import com.chinaztt.fda.widget.PullToRefreshListView;

import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 当前类注释:下拉刷新，上拉加载更多组件实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/23 11:25
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class PullListviewActivity extends BaseActivity{
    private PullToRefreshListView lv_pull_item;
    private PullAdapter mPullAdapter;
    private LayoutInflater mInflater;
    private List<String> mTitles;
    private View load_more;
    private TextView load_more_tv; // listview底部加载view 显示数据
    private ProgressBar load_more_progress; // listview底部加载view 显示进度
    private LinearLayout load_next_page_layout;

    private Handler newHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                refreshTitles();
                UIUtils.savePullToRefreshLastUpdateAt(lv_pull_item,UIUtils.DEMO_PULL_TIME_KEY);
                //刷新view
                mPullAdapter.notifyDataSetChanged();
            }else if(msg.what==2){
                moreTitles();
                //刷新view
                mPullAdapter.notifyDataSetChanged();
                showToastMsgShort("加载更多数据成功...");
                load_more_tv.setText("数据加载完成");
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_listview_layout);
        lv_pull_item=(PullToRefreshListView)this.findViewById(R.id.lv_pull_item);
        mInflater=getLayouInflater();

        //特别注意 里边的view的控件可以根据当前的状态 修改字符串信息
        load_more = mInflater.inflate(R.layout.load_more_footview_layout,
                null);
        load_more_tv = (TextView) load_more
                .findViewById(R.id.load_next_page_text);
        load_more_progress = (ProgressBar) load_more
                .findViewById(R.id.load_next_page_progress);
        load_next_page_layout = (LinearLayout) load_more
                .findViewById(R.id.load_next_page_layout);
        //listview添加尾部 上拉加载更多view
        lv_pull_item.addFooterView(load_more, null, false);
        load_more_tv.setText("上拉加载更多数据...");

        initTitles();
        //初始化 上次下拉刷新的时间
        UIUtils.setPullToRefreshLastUpdated(lv_pull_item,UIUtils.DEMO_PULL_TIME_KEY);
        mPullAdapter=new PullAdapter();
        lv_pull_item.setAdapter(mPullAdapter);
        //listview item点击事件处理
        lv_pull_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = position++;
                showToastMsgShort("点击了第:" + index + "个item");
            }
        });

        //listview 开始下拉刷新回调函数
        lv_pull_item.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //进行加载数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            newHandler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });
        //listview 滑动  进行上拉加载更多
        lv_pull_item.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (lv_pull_item.getLastVisiblePosition() == (lv_pull_item
                            .getCount() - 1)) {
                        load_more_tv.setText("正在加载最新数据....");
                          //进行获取最新数据
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(5000);
                                    newHandler.sendEmptyMessage(2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }).start();
                    }
                }
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    //正在滑动中，当前listview正在滑动 可以暂停图片加载器或者其他一些耗时操作
                } else {

                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
    private void initTitles(){
        mTitles=new ArrayList<String>();
        for(int i=0;i<20;i++){
            int index=i+1;
            mTitles.add("当前是:"+index+"");
        }
    }

    private void refreshTitles(){
        List<String> newTitles=new ArrayList<String>();
        for(int i=0;i<5;i++){
            int index=i+1;
            newTitles.add("新数据是:"+index+"");
        }
        newTitles.addAll(mTitles);
        mTitles.removeAll(mTitles);
        mTitles.addAll(newTitles);
    }
    private void moreTitles(){
        List<String> newTitles=new ArrayList<String>();
        for(int i=0;i<8;i++){
            int index=i+1;
            newTitles.add("更多数据是:"+index+"");
        }
        mTitles.addAll(newTitles);
    }

    class PullAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public Object getItem(int position) {
            return mTitles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Hondler _Hondler=null;
            if(convertView==null){
                _Hondler=new Hondler();
                convertView=mInflater.inflate(R.layout.lv_main_item,null);
                _Hondler.tv_item=(TextView)convertView.findViewById(R.id.tv_item);
                convertView.setTag(_Hondler);
            }else
            {
                _Hondler=(Hondler)convertView.getTag();
            }
            _Hondler.tv_item.setText(mTitles.get(position));
            return convertView;
        }
    }

    static class Hondler{
        TextView tv_item;
    }
}
