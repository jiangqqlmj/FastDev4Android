package com.chinaztt.fda.test.RecyclerViewAA;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.EViewGroup;

/**
 * 当前类注释:创建一个泛型类来把所有类型的View包装成ViewHonlder
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.RecyclerViewAA
 * 作者：江清清 on 15/11/21 09:24
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {
    public V view;
    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }
    public V getView() {
        return view;
    }
}
