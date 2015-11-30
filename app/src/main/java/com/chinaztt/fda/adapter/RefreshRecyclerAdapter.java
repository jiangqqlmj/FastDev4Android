package com.chinaztt.fda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.adapter
 * 作者：江清清 on 15/11/21 21:43
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class RefreshRecyclerAdapter extends RecyclerView.Adapter<RefreshRecyclerAdapter.ViewHolder>{
    private LayoutInflater mInflater;
    private List<String> mTitles=null;
    public  RefreshRecyclerAdapter(Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mTitles=new ArrayList<String>();
        for (int i=0;i<20;i++){
            int index=i+1;
            mTitles.add("item"+index);
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
        final View view=mInflater.inflate(R.layout.item_recycler_layout,parent,false);
        //这边可以做一些属性设置，甚至事件监听绑定
        //view.setBackgroundColor(Color.RED);
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
        holder.item_tv.setText(mTitles.get(position));
        holder.itemView.setTag(position);
    }
    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item_tv;
        public ViewHolder(View view){
            super(view);
            item_tv = (TextView) view.findViewById(R.id.item_tv);
        }
    }

    //添加数据
    public void addItem(List<String> newDatas) {
        //mTitles.add(position, data);
        //notifyItemInserted(position);
        newDatas.addAll(mTitles);
        mTitles.removeAll(mTitles);
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(List<String> newDatas) {
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }
}
