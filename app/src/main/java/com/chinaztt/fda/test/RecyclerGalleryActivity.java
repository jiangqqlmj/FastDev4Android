package com.chinaztt.fda.test;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinaztt.fda.adapter.GalleryRecyclerAdapter;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
/**
 * 当前类注释:使用RecyclerView实现Gallery效果
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/19 20:07
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class RecyclerGalleryActivity extends BaseActivity {
    private RecyclerView gallery_recycler;
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_gallery_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("RecyclerView打造Gallery效果");
        //初始化RecyclerView控件
        gallery_recycler=(RecyclerView)this.findViewById(R.id.gallery_recycler);
        //固定高度
        gallery_recycler.setHasFixedSize(true);
        //创建布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //设置横向
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //设置布局管理器
        gallery_recycler.setLayoutManager(linearLayoutManager);
        //创建适配器
        GalleryRecyclerAdapter adapter=new GalleryRecyclerAdapter(this);
        //绑定适配器
        gallery_recycler.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new GalleryRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerGalleryActivity.this,"您点击的Item的索引为:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            RecyclerGalleryActivity.this.finish();
        }
    }
}
