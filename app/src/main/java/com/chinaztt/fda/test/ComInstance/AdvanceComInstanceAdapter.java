package com.chinaztt.fda.test.ComInstance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.ComInstance
 * 作者：江清清 on 15/11/28 09:50
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class AdvanceComInstanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<AdvanceInstanceBean> mAdvanceInstanceBeans;
    private LayoutInflater mInflater;
    //布局新增一项类别
    //普通ITEM
    private static final int ITEM_VIEW=1;
    //FOOT ITEM
    private static final int FOOT_VIEW=2;

    public AdvanceComInstanceAdapter(Context context,List<AdvanceInstanceBean> pAdvanceInstanceBeans){
        this.mContext=context;
        this.mAdvanceInstanceBeans=pAdvanceInstanceBeans;
        mInflater=LayoutInflater.from(this.mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW) {
            final View view = mInflater.inflate(R.layout.advance_com_instance_item_layout, parent, false);
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
            AdvanceInstanceBean advanceInstanceBean=mAdvanceInstanceBeans.get(position);
            if(advanceInstanceBean!=null){
                final List<InstanceBean> instanceBeans=advanceInstanceBean.getInstanceBeans();
                if(instanceBeans.size()==2){
                    ((ItemViewHolder) holder).item_img_one.setImageResource(instanceBeans.get(0).getImg());
                    ((ItemViewHolder)holder).item_tv_one.setText(instanceBeans.get(0).getTitle());
                    ((ItemViewHolder) holder).item_img_two.setImageResource(instanceBeans.get(1).getImg());
                    ((ItemViewHolder)holder).item_tv_two.setText(instanceBeans.get(1).getTitle());
                            ((ItemViewHolder) holder).leftL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemClickListener != null) {
                                onItemClickListener.onItemClick(instanceBeans.get(0));
                            }
                        }
                    });
                    ((ItemViewHolder) holder).rightL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(onItemClickListener!=null){
                                onItemClickListener.onItemClick(instanceBeans.get(1));
                            }
                        }
                    });
                }else {
                    ((ItemViewHolder) holder).item_img_one.setImageResource(instanceBeans.get(0).getImg());
                    ((ItemViewHolder)holder).item_tv_one.setText(instanceBeans.get(0).getTitle());
                    ((ItemViewHolder) holder).item_img_two.setImageResource(R.drawable.moren);
                    ((ItemViewHolder)holder).item_tv_two.setText("");
                }
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
       return  mAdvanceInstanceBeans.size()+1;
    }

    /**
     * Item 点击监听回调接口
     */
    public interface OnItemClickListener {

        /**
         * item回调的数据
         * @param instanceBean
         */
        void onItemClick(InstanceBean instanceBean);
    }
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout leftL,rightL;
        private ImageView item_img_one;
        private TextView item_tv_one;
        private ImageView item_img_two;
        private TextView item_tv_two;
        public ItemViewHolder(View itemView) {
            super(itemView);
            leftL=(LinearLayout)itemView.findViewById(R.id.leftL);
            rightL=(LinearLayout)itemView.findViewById(R.id.rightL);
            item_img_one=(ImageView)itemView.findViewById(R.id.item_img_one);
            item_tv_one=(TextView)itemView.findViewById(R.id.item_tv_one);
            item_img_two=(ImageView)itemView.findViewById(R.id.item_img_two);
            item_tv_two=(TextView)itemView.findViewById(R.id.item_tv_two);
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
     * 进行下拉刷新数据添加 并且刷新UI
     * @param pInstanceBeans
     */
    public void addRefreshBeans(List<AdvanceInstanceBean> pInstanceBeans){
        List<AdvanceInstanceBean> temp=new ArrayList<AdvanceInstanceBean>();
        temp.addAll(pInstanceBeans);
        temp.addAll(mAdvanceInstanceBeans);
        mAdvanceInstanceBeans.removeAll(mAdvanceInstanceBeans);
        mAdvanceInstanceBeans.addAll(temp);
        notifyDataSetChanged();
    }

    /**
     * 进行上拉加载更多 并且刷新UI
     * @param pInstanceBeans
     */
    public void addMoreBeans(List<AdvanceInstanceBean> pInstanceBeans){
        mAdvanceInstanceBeans.addAll(pInstanceBeans);
        notifyDataSetChanged();
    }
}
