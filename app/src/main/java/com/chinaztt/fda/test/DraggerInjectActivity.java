package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;


/**
 * 当前类注释:依赖注入管理器Dragger使用实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/28 08:36
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class DraggerInjectActivity extends BaseActivity {
    private Button btn_show;
    private TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dragger_inject_layout);
        btn_show=(Button)this.findViewById(R.id.btn_show);
        tv_show=(TextView)this.findViewById(R.id.tv_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
