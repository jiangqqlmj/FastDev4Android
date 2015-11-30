package com.chinaztt.fda.test.RecyclerViewAA;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.RecyclerViewAA
 * 作者：江清清 on 15/11/21 09:31
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class  RecyclerViewAdapterBase<T,V extends View>extends RecyclerView.Adapter<ViewWrapper<V>>{
    /**
     * 存储需要绑定的数据
     */
    protected List<T> items = new ArrayList<T>();
    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * 进行创建视图承载类
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }
    /**
     * 创建视图Item，交给具体实现类完成
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

}
