package com.chinaztt.fda.test;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.test.RecyclerViewAA.AAUserAdapter;
import com.chinaztt.fda.test.RecyclerViewAA.AAUserFinder;
import com.chinaztt.fda.test.RecyclerViewAA.ImMemoryUserFinder;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 当前类注释:RecyclerView集合AA(Android Annotations)注入框架实现实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/20 14:41
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.recycler_aa_layout)
public class RecyclerViewAAActivity  extends BaseActivity{
    @ViewById
    LinearLayout top_bar_linear_back;
    @ViewById
    TextView top_bar_title;
    @ViewById
    RecyclerView aa_recyclerview;
    @Bean
    AAUserAdapter adapter;
    @Bean(ImMemoryUserFinder.class)
    AAUserFinder userFinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @AfterViews
    public void initViews(){
        top_bar_title.setText("RecyclerView集合AA注入框架实例");
        //进行设置RecyerView ,并且绑定数据
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        aa_recyclerview.setLayoutManager(linearLayoutManager);
        adapter.setItems(userFinder.findAll());
        aa_recyclerview.setAdapter(adapter);
    }
    @Click(R.id.top_bar_linear_back)
    public void clickButton(View view){
        this.finish();
    }

}
