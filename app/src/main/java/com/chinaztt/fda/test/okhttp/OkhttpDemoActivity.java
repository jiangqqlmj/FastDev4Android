package com.chinaztt.fda.test.okhttp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * 当前类注释:Okhttp 网络请求框架 实例demo功能测试入口
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.okhttp
 * 作者：江清清 on 15/12/4 15:56
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class OkhttpDemoActivity extends BaseActivity {
    private Button btn_one,btn_two;
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title,tv_result;
    private OkHttpClient client;
    private Response response;
    private Request request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_http_demo_layout);
        btn_one=(Button)this.findViewById(R.id.btn_one);
        btn_two=(Button)this.findViewById(R.id.btn_two);
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        tv_result=(TextView)this.findViewById(R.id.tv_result);
        top_bar_title.setText("Okhttp实例");
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        btn_one.setOnClickListener(new CustomOnClickListener());
        btn_two.setOnClickListener(new CustomOnClickListener());

        client=new OkHttpClient();
    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.top_bar_linear_back:
                    OkhttpDemoActivity.this.finish();
                    break;
                case R.id.btn_one://同步get
                    request = new Request.Builder()
                            .url("http://interface.zttmall.com/update/mallUpdate")
                            .build();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                response= client.newCall(request).execute();
                                Headers responseHeaders = response.headers();
                                for (int i = 0; i < responseHeaders.size(); i++) {
                                    Log.d("zttjiangqq", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                                }
                                final String message=response.body().string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_result.setText(message);
                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    break;
                case R.id.btn_two: //异步Get
                    request = new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                        }
                        @Override
                        public void onResponse(Response response) throws IOException {
                            final String message=response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_result.setText(message);
                                }
                            });
                        }
                    });
                    break;

            }
        }
    }
}
