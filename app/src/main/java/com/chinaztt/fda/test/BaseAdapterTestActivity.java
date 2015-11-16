package com.chinaztt.fda.test;

import android.os.Bundle;
import android.widget.ListView;

import com.chinaztt.fda.adapter.base.BaseAdapterHelper;
import com.chinaztt.fda.adapter.base.QuickAdapter;
import com.chinaztt.fda.entity.ModuleBean;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 当前类注释:baseAdapterhelper 使用实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/8 17:39
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.base_adapter_test_layout)
public class BaseAdapterTestActivity extends BaseActivity {
    @ViewById
    ListView lv_base_adapter;
    private QuickAdapter<ModuleBean> mAdapter;
    private List<ModuleBean> moduleBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @AfterViews
    public void bindLvData(){
        moduleBeans=DataUtils.getAdapterData();
        if(mAdapter==null) {
            mAdapter = new QuickAdapter<ModuleBean>(this, R.layout.lv_item_base_layout,moduleBeans) {
                @Override
                protected void convert(BaseAdapterHelper helper, ModuleBean item) {
                    //列表底下显示进度
                    mAdapter.showIndeterminateProgress(true);
                    helper.setText(R.id.text_lv_item_title, item.getModulename())
                            .setText(R.id.text_lv_item_description, item.getDescription())
                            .setImageUrl(R.id.img_lv_item, item.getImgurl());
                }
            };
            lv_base_adapter.setAdapter(mAdapter);
        }
    }
}
