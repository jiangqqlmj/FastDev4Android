package com.chinaztt.fda.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.utils.Log;

/**
 * 当前类注释:自定义SwipeRefreshLayout,进行扩展上拉加载更多的功能
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.widget
 * 作者：江清清 on 15/11/22 16:05
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {
    private LayoutInflater mInflater;
    //给控件新增的下拉加载更多的布局
    private View load_view;
    private ListView mInternalListView;
    /**
     * 用户滑动的距离
     */
    private int touchSlop;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;
    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;

    public CustomSwipeRefreshLayout(Context context) {
        this(context,null);
    }
    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        load_view = LayoutInflater.from(context).inflate(R.layout.recycler_load_more_layout, null,
                false);
    }

    /**
     * 当布局进行位置布局的时候，设置相关设置加入footview
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if (mInternalListView == null) {
            getListView();
        }
    }
    /**
     * 获取ListView对象
     */
    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(0);
           if (childView instanceof ListView) {
                mInternalListView = (ListView) childView;
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                mInternalListView.setOnScrollListener(this);
                Log.d("zttjiangqq","存在listview...");
            }else{
                Log.d("zttjiangqq","不存在listview...");
            }
        }
    }
    /*
     * (non-Javadoc)
     * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                // 抬起
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }
    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {

        if (mInternalListView != null && mInternalListView.getAdapter() != null) {
            return mInternalListView.getLastVisiblePosition() == (mInternalListView.getAdapter().getCount() - 1);
        }
        return false;
    }
    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        return (mYDown - mLastY) >= touchSlop;
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (mOnLoadMoreListener != null) {
            // 设置状态
            setLoading(true);
            //
            mOnLoadMoreListener.onLoadMore();
        }
    }

    /**
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mInternalListView.addFooterView(load_view);
        } else {
            mInternalListView.removeFooterView(load_view);
            mYDown = 0;
            mLastY = 0;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 滚动时到了最底部也可以加载更多
        if (canLoad()) {
            loadData();
        }
    }

    /**
     * 上拉滑动到一定距离，进行回调接口
     */
    private OnLoadMoreListener mOnLoadMoreListener;
    /**
     * 加载更多的监听器
     *
     * @author 江清清
     */
    public interface OnLoadMoreListener {
        //加载更多回调接口
        void onLoadMore();
    }

    //下面给mOnLoadMoreListener新增set和get方法
    public OnLoadMoreListener getOnLoadMoreListener() {
        return mOnLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }
}
