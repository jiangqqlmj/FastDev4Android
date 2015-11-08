package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


/**
 * 当前类注释:依赖注入管理器AndroidAnnotations使用实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/28 08:36
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.dragger_inject_layout)
public class AnnotationsTestActivity extends BaseActivity {
    @ViewById
    Button btn_show;
    @ViewById
    TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Click(R.id.btn_show)
    public void btnShowClick(){
        tv_show.setText("按钮被点击了...");
    }
    @AfterViews
    public void setTv_show(){
        tv_show.setText("我已经被注入啦...");
    }
}
