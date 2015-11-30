package com.chinaztt.fda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinaztt.fda.entity.GalleryModel;
import com.chinaztt.fda.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.adapter
 * 作者：江清清 on 15/11/19 20:34
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.ViewHolder> {

    private List<GalleryModel> models;
    private LayoutInflater mInflater;

    public  GalleryRecyclerAdapter(Context context){
        models=new ArrayList<GalleryModel>();
        for (int i=0;i<20;i++){
            int index=i+1;
            models.add(new GalleryModel(R.drawable.ic_item_gallery,"Item"+index));
        }
        mInflater=LayoutInflater.from(context);
    }
    /**
     * 创建Item View  然后使用ViewHolder来进行承载
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view=mInflater.inflate(R.layout.item_gallery_recycler,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecyclerViewItemClickListener!=null){
                    onRecyclerViewItemClickListener.onItemClick(view,(int)view.getTag());
                }
            }
        });
        return viewHolder;
    }
    /**
     * 进行绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_img.setImageResource(models.get(position).getImgurl());
        holder.item_tv.setText(models.get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_img;
        private TextView item_tv;
        public ViewHolder(View view){
            super(view);
            item_img=(ImageView)view.findViewById(R.id.item_img);
            item_tv=(TextView)view.findViewById(R.id.item_tv);
        }
    }

    /**
     * 类似ListView的 onItemClickListener接口
     */
    public interface OnRecyclerViewItemClickListener{
        /**
         * Item View发生点击回调的方法
         * @param view   点击的View
         * @param position  具体Item View的索引
         */
        void onItemClick(View view,int position);
    }
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public OnRecyclerViewItemClickListener getOnRecyclerViewItemClickListener() {
        return onRecyclerViewItemClickListener;
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    //添加数据
    public void addItem(GalleryModel model, int position) {
        models.add(position, model);
        notifyItemInserted(position);
    }
    //删除数据
    public void removeItem(int position) {
        models.remove(position);
        notifyItemRemoved(position);
    }
}
