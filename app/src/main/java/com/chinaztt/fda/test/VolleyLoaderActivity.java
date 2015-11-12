package com.chinaztt.fda.test;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.chinaztt.fda.adapter.base.BaseAdapterHelper;
import com.chinaztt.fda.adapter.base.QuickAdapter;
import com.chinaztt.fda.entity.ModuleBean;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fdv.Fdv_ImageCache;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 当前类注释:使用ImageLoader来进行测试列表图片异步加载以及缓存
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/12 15:19
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.base_adapter_test_layout)
public class VolleyLoaderActivity  extends BaseActivity {
    @ViewById
    ListView lv_base_adapter;
    @ViewById
    TextView tv_title;
    private QuickAdapter<ModuleBean> mAdapter;
    private List<ModuleBean> moduleBeans;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private ImageLoader.ImageListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue= Volley.newRequestQueue(this);
        imageLoader=new ImageLoader(requestQueue,new Fdv_ImageCache());
    }
    @AfterViews
    public void setViews(){
        tv_title.setText("Loader 列表图片");
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
                            .setText(R.id.text_lv_item_description, item.getDescription());
                    //setImageUrl(R.id.img_lv_item, item.getImgurl());
                    //使用ImageLoader进行加载图片
                    ImageView loader_img=helper.getView(R.id.img_lv_item);
                    listener=ImageLoader.getImageListener(loader_img,R.drawable.ic_loading,R.drawable.ic_loading);
                    imageLoader.get(item.getImgurl(),listener);
                }
            };
            lv_base_adapter.setAdapter(mAdapter);
        }
    }
}
