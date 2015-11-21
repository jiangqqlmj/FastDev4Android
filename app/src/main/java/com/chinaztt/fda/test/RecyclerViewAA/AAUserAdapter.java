package com.chinaztt.fda.test.RecyclerViewAA;

import android.content.Context;
import android.view.ViewGroup;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.RecyclerViewAA
 * 作者：江清清 on 15/11/21 09:35
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EBean
public class AAUserAdapter extends RecyclerViewAdapterBase<TestUserBean,AAUserItemView> {
    @RootContext
    Context context;
    /**
     * 创建Item视图View
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    protected AAUserItemView onCreateItemView(ViewGroup parent, int viewType) {
        return AAUserItemView_.build(context);
    }
    /**
     * 进行绑定数据View
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewWrapper<AAUserItemView> holder, int position) {
        AAUserItemView view =holder.getView();
        TestUserBean userBean = items.get(position);
        view.bind(userBean);
    }
}
