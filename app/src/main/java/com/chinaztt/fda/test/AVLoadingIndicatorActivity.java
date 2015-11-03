package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import org.androidannotations.annotations.EActivity;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/3 08:37
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class AVLoadingIndicatorActivity extends BaseActivity{
    private Button progress_start,progress_stop;
    private AVLoadingIndicatorView avloadingIndicatorView_BallPulse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_loading_indicator_layout);
        avloadingIndicatorView_BallPulse=(AVLoadingIndicatorView)this.findViewById(R.id.avloadingIndicatorView_BallPulse);
        progress_start=(Button)this.findViewById(R.id.progress_start);
        progress_stop=(Button)this.findViewById(R.id.progress_stop);

        //打开动画
        progress_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avloadingIndicatorView_BallPulse.setVisibility(View.VISIBLE);
            }
        });
        //关闭动画
        progress_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avloadingIndicatorView_BallPulse.setVisibility(View.GONE);
            }
        });


    }
}
