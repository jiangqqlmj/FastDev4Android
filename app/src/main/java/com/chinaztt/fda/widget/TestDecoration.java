package com.chinaztt.fda.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 当前类注释:自定义实现一个Decoration 分隔线
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.widget
 * 作者：江清清 on 15/11/19 12:29
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class TestDecoration extends RecyclerView.ItemDecoration {
    //采用系统内置的风格的分割线
    private static final int[] attrs=new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public TestDecoration(Context context) {
        TypedArray typedArray=context.obtainStyledAttributes(attrs);
        mDivider=typedArray.getDrawable(0);
        typedArray.recycle();
    }

    /**
     * 进行自定义绘制
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top=parent.getPaddingTop();
        int bottom=parent.getHeight()-parent.getPaddingBottom();
        int childCount=parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams=(RecyclerView.LayoutParams)child.getLayoutParams();
            int left=child.getRight()+layoutParams.rightMargin;
            int right=left+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
    }
}
