package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chinaztt.fda.application.FDApplication;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/26 13:38
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class CrashTestActivity extends BaseActivity {
    private Button crash_btn;
    private TextView crash_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crash_test_layout);
        crash_btn=(Button)this.findViewById(R.id.crash_btn);
        crash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crash_tv.setText("客户端崩溃了...");
            }
        });
    }
}
