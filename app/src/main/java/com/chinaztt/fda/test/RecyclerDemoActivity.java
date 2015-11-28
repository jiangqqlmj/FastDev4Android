package com.chinaztt.fda.test;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaztt.fda.test.ComInstance.AdvanceComInstanceActivity;
import com.chinaztt.fda.test.ComInstance.ComInstanceActivity;
import com.chinaztt.fda.test.RecyclerViewRefresh.RecyclerFootActivity;
import com.chinaztt.fda.test.RecyclerViewRefresh.RecyclerRefreshActivity;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 当前类注释:RecyclerView 完全解析相关实例入口
 * 主要有1.RecyclerView基础以及高级使用;
 * 2.Recycler实战实例打造Gallery;
 * 3.Recycler结合AA(AndroidAnnotations)注入框架实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/19 16:57
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.recycler_demo_layout)
public class RecyclerDemoActivity  extends BaseActivity{
    @ViewById
    LinearLayout top_bar_linear_back;
    @ViewById
    TextView top_bar_title;
    @ViewById
    Button btn_one,btn_two,btn_three,btn_four,btn_five,btn_six,btn_seven;
    @Click({R.id.top_bar_linear_back,R.id.btn_one,R.id.btn_two,R.id.btn_three,R.id.btn_four
            ,R.id.btn_five,R.id.btn_six,R.id.btn_seven})
    public void clickButton(View view){
        switch (view.getId()){
            case R.id.top_bar_linear_back:
                this.finish();
                break;
            case R.id.btn_one:
                openActivity(RecyclerViewTestActivity.class);
                break;
            case R.id.btn_two:
                openActivity(RecyclerGalleryActivity.class);
                break;
            case R.id.btn_three:
                Intent mIntent=new Intent(this,RecyclerViewAAActivity_.class);
                this.startActivity(mIntent);
                break;
            case R.id.btn_four:
                Intent refreshIntent=new Intent(this,RecyclerRefreshActivity.class);
                this.startActivity(refreshIntent);
                break;
            case R.id.btn_five:
                Intent fiveIntent=new Intent(this,RecyclerFootActivity.class);
                this.startActivity(fiveIntent);
                break;
            case R.id.btn_six:
                Intent sixIntent=new Intent(this,ComInstanceActivity.class);
                this.startActivity(sixIntent);
                break;
            case R.id.btn_seven:
                Intent sevenIntent=new Intent(this,AdvanceComInstanceActivity.class);
                this.startActivity(sevenIntent);
                break;
        }
    }
    @AfterViews
    public void initViews(){
        top_bar_title.setText("RecyclerView测试入口");
    }
}
