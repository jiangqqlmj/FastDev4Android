package com.chinaztt.fda.test.CardView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

/**
 * 当前类注释:CardView结合RecyclerView使用实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.CardView
 * 作者：江清清 on 15/11/23 19:34
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class CardViewRecyclerActivity extends BaseActivity {
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    private RecyclerView recycler_cardview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view_recycler_layout);
        top_bar_linear_back=(LinearLayout)this.findViewById(R.id.top_bar_linear_back);
        top_bar_linear_back.setOnClickListener(new CustomOnClickListener());
        top_bar_title=(TextView)this.findViewById(R.id.top_bar_title);
        top_bar_title.setText("CardView结合RecyclerView使用实例");

        recycler_cardview=(RecyclerView)this.findViewById(R.id.recycler_cardview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler_cardview.setLayoutManager(linearLayoutManager);
        recycler_cardview.setAdapter(new CardViewAdapter(this));


    }
    class CustomOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            CardViewRecyclerActivity.this.finish();
        }
    }
}
