package com.chinaztt.fda.test.CardView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinaztt.fda.entity.CardDataUtils;
import com.chinaztt.fda.entity.CardViewBean;
import com.chinaztt.fda.ui.R;

import java.util.List;

/**
 * 当前类注释:CardView结合RecyclerView 自定义Adapter
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.CardView
 * 作者：江清清 on 15/11/23 19:41
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ItemCardViewHolder>{
    private List<CardViewBean> beans;
    private LayoutInflater mInflater;
    private Context mContext;
    public CardViewAdapter(Context context){
        this.mContext=context;
        beans= CardDataUtils.getCardViewDatas();
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public ItemCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_cardview_layout,parent,false);
        return new ItemCardViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ItemCardViewHolder holder, int position) {
        holder.item_cardview.setCardBackgroundColor(mContext.getResources().getColor(beans.get(position).getColor()));
        holder.item_tv.setText(beans.get(position).getTitle());
    }
    @Override
    public int getItemCount() {
        return beans.size();
    }

    public static class ItemCardViewHolder extends RecyclerView.ViewHolder{
        private CardView item_cardview;
        private TextView item_tv;
        public ItemCardViewHolder(View itemView) {
            super(itemView);
            item_cardview=(CardView)itemView.findViewById(R.id.item_cardview);
            item_tv=(TextView)itemView.findViewById(R.id.item_tv);

        }
    }

}
