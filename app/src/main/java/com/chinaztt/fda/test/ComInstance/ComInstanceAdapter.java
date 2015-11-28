package com.chinaztt.fda.test.ComInstance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chinaztt.fda.ui.R;

import java.util.ArrayList;
import java.util.List;
/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.ComInstance
 * 作者：江清清 on 15/11/27 21:46
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class ComInstanceAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<InstanceBean> mInstanceBeans;
    private LayoutInflater mInflater;
    //布局新增一项类别
    //普通ITEM
    private static final int ITEM_VIEW=1;
    //FOOT ITEM
    private static final int FOOT_VIEW=2;

    public ComInstanceAdapter(Context context,List<InstanceBean> pInstanceBeans){
        this.mContext=context;
        this.mInstanceBeans=pInstanceBeans;
        mInflater=LayoutInflater.from(this.mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW) {
            final View view = mInflater.inflate(R.layout.com_instance_item_layout, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, (int) view.getTag());
                    }
                }
            });
            return new ItemViewHolder(view);
        } else if (viewType == FOOT_VIEW) {
            View view = mInflater.inflate(R.layout.instance_load_more_layout, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            if(position<mInstanceBeans.size()){
                ((ItemViewHolder) holder).item_img.setImageResource(mInstanceBeans.get(position).getImg());
                ((ItemViewHolder)holder).item_tv.setText(mInstanceBeans.get(position).getTitle());
                holder.itemView.setTag(position);
                holder.itemView.setClickable(true);
            }else {
                ((ItemViewHolder) holder).item_img.setImageResource(R.drawable.moren);
                ((ItemViewHolder)holder).item_tv.setText("");
                holder.itemView.setClickable(false);
            }
        }else if(holder instanceof FootViewHolder){
            //上拉加载更多布局数据绑定
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return FOOT_VIEW;
        } else {
            return ITEM_VIEW;
        }
    }
    @Override
    public int getItemCount() {
        if(mInstanceBeans.size()%2==0){
            //偶数
            return mInstanceBeans.size()+1;
        }else{
            return mInstanceBeans.size()+2;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_img;
        private TextView item_tv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            item_img=(ImageView)itemView.findViewById(R.id.item_img);
            item_tv=(TextView)itemView.findViewById(R.id.item_tv);
        }
    }
    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends  RecyclerView.ViewHolder{
        private TextView foot_view_item_tv;
        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv=(TextView)view.findViewById(R.id.foot_view_item_tv);
        }
    }

    /**
     * Item 点击监听回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 进行下拉刷新数据添加 并且刷新UI
     * @param pInstanceBeans
     */
    public void addRefreshBeans(List<InstanceBean> pInstanceBeans){
         List<InstanceBean> temp=new ArrayList<InstanceBean>();
         temp.addAll(pInstanceBeans);
         temp.addAll(mInstanceBeans);
         mInstanceBeans.removeAll(mInstanceBeans);
         mInstanceBeans.addAll(temp);
         notifyDataSetChanged();
    }

    /**
     * 进行上拉加载更多 并且刷新UI
     * @param pInstanceBeans
     */
    public void addMoreBeans(List<InstanceBean> pInstanceBeans){
        mInstanceBeans.addAll(pInstanceBeans);
        notifyDataSetChanged();
    }
}
