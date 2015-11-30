package com.chinaztt.fda.test.ViewDragHelper;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chinaztt.fda.utils.Log;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.ViewGragHelper
 * 作者：江清清 on 15/11/24 20:29
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class ViewGragOne extends LinearLayout{
    private View view_one,view_two;
    private ViewDragHelper mDragHelper;
    public ViewGragOne(Context context) {
        this(context, null);
    }
    public ViewGragOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ViewGragOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }
    class  DragHelperCallback extends Callback {
        /**
         * 进行捕获拦截，那些View可以进行drag操作
         * @param child
         * @param pointerId
         * @return  直接返回true，拦截所有的VIEW
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }
        /**
         * 水平滑动 控制left
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d("DragLayout", "clampViewPositionHorizontal " + left + "," + dx);
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - view_one.getWidth();
            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }
        /**
         * 垂直滑动，控制top
         * @param child
         * @param top
         * @param dy
         * @return
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.d("DragLayout", "clampViewPositionVertical " + top + "," + dy);
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - view_one.getHeight();
            final int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            Toast.makeText(getContext(), "edgeTouched", Toast.LENGTH_SHORT).show();
        }

        /**
         * 在边界滑动的时候 同时滑动dragView2
         * @param edgeFlags
         * @param pointerId
         */
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mDragHelper.captureChildView(view_two, pointerId);
        }

        /**
         * 当手指松开的时候回调方法
         * @param releasedChild  滑动手指松开的View
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            Log.d("zttjiangqq","onViewReleased");
        }
    }
    /**
     * 事件分发
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDragHelper.processTouchEvent(ev);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view_one=getChildAt(0);
        view_two=getChildAt(1);
    }
}
