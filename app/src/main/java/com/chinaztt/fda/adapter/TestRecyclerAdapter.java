package com.chinaztt.fda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;

/**
 * 当前类注释:RecyclerView 数据自定义Adapter
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.adapter.base
 * 作者：江清清 on 15/11/18 22:29
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder>{
    private LayoutInflater mInflater;
    private String[] mTitles=null;
    public  TestRecyclerAdapter(Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mTitles=new String[20];
        for (int i=0;i<20;i++){
            int index=i+1;
            mTitles[i]="item"+index;
        }
    }
    /**
     * item显示类型
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_recycler_layout,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    /**
     * 数据的绑定显示
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_tv.setText(mTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item_tv;
        public ViewHolder(View view){
            super(view);
            item_tv = (TextView) view.findViewById(R.id.item_tv);
        }
    }

}
