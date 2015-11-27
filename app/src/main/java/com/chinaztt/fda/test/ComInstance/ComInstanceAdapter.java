package com.chinaztt.fda.test.ComInstance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;

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
public class ComInstanceAdapter extends  RecyclerView.Adapter<ComInstanceAdapter.ItemViewHolder> {
    private Context mContext;
    private List<InstanceBean> mInstanceBeans;
    private LayoutInflater mInflater;
    public ComInstanceAdapter(Context context,List<InstanceBean> pInstanceBeans){
        this.mContext=context;
        this.mInstanceBeans=pInstanceBeans;
        mInflater=LayoutInflater.from(this.mContext);
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.com_instance_item_layout,parent,false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.item_img.setImageResource(mInstanceBeans.get(position).getImg());
        holder.item_tv.setText(mInstanceBeans.get(position).getTitle());

    }
    @Override
    public int getItemCount() {
        return mInstanceBeans!=null?mInstanceBeans.size():0;
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

}
