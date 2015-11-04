package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chinaztt.fda.event.TestEventFirst;
import com.chinaztt.fda.event.TestEventSecond;
import com.chinaztt.fda.event.TestEventThird;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/3 14:25
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class EventBusTestTwoActivity extends BaseActivity {
    Button button_one,button_two,button_third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bus_test_two_layout);
        button_one=(Button)this.findViewById(R.id.button_one);
        button_two=(Button)this.findViewById(R.id.button_two);
        button_third=(Button)this.findViewById(R.id.button_third);
        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new TestEventFirst("One Event button"));
                //EventBusTestTwoActivity.this.finish();
            }
        });
        button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new TestEventSecond("Two Event button"));
                //EventBusTestTwoActivity.this.finish();
            }
        });
        button_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new TestEventThird("Third Event button"));
                //EventBusTestTwoActivity.this.finish();
            }
        });
    }
}
