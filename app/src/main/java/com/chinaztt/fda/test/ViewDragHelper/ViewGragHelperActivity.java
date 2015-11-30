package com.chinaztt.fda.test.ViewDragHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

/**
 * 当前类注释:ViewGragHelper使用实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/24 20:15
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class ViewGragHelperActivity extends BaseActivity {
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_grag_helper_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)findViewById(R.id.top_bar_title);
        top_bar_title.setText("ViewGragHelper使用详解");
    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
           ViewGragHelperActivity.this.finish();
        }
    }
}
