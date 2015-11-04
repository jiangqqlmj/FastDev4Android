package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chinaztt.fda.event.TestEventFirst;
import com.chinaztt.fda.event.TestEventSecond;
import com.chinaztt.fda.event.TestEventThird;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import de.greenrobot.event.EventBus;

/**
 * 当前类注释:EventBus组件间数据通信实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/3 13:14
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class EventBusTestActivity  extends BaseActivity{
    Button button_one;
    TextView textView_one,textView_two,textView_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bus_test_layout);
        EventBus.getDefault().register(this);
        button_one=(Button)this.findViewById(R.id.button_one);
        textView_one=(TextView)this.findViewById(R.id.textView_one);
        textView_two=(TextView)this.findViewById(R.id.textView_two);
        textView_third=(TextView)this.findViewById(R.id.textView_third);
        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(EventBusTestTwoActivity_.class);
            }
        });
    }
    /**
     * 收到消息 进行相关处理
     * @param event
     */
    public void onEventMainThread(TestEventFirst event) {
        Log.d("zttjiangqq","onEventMainThread收到消息:"+event.getMsg());
        textView_one.setText(event.getMsg());
        //showToastMsgShort(event.getMsg());
    }
    /**
     * 收到消息 进行相关处理
     * @param event
     */
    public void onEventMainThread(TestEventSecond event) {

        Log.d("zttjiangqq","onEventMainThread收到消息:"+event.getMsg());
        textView_two.setText(event.getMsg());
        //showToastMsgShort(event.getMsg());
    }
    /**
     * 收到消息 进行相关处理
     * @param event
     */
    public void onEventMainThread(TestEventThird event) {

        Log.d("zttjiangqq","onEventMainThread收到消息:"+event.getMsg());
        textView_third.setText(event.getMsg());
        //showToastMsgShort(event.getMsg());
    }
        @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
