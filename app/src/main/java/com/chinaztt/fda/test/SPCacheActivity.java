package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chinaztt.fda.application.FDApplication;
import com.chinaztt.fda.cache.ACache;
import com.chinaztt.fda.cache.CacheConsts;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EApplication;

/**
 * 当前类注释:采用本地文件进行做数据缓存Cache实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/25 17:29
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class SPCacheActivity extends BaseActivity{
    private Button save_cache;
    private Button query_cache;
    private EditText edit_cache;
    private TextView tv_cache;
    private ACache mAcache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_cache_layout);
        save_cache=(Button)this.findViewById(R.id.save_cache);
        query_cache=(Button)this.findViewById(R.id.query_cache);
        edit_cache=(EditText)this.findViewById(R.id.edit_cache);
        tv_cache=(TextView)this.findViewById(R.id.tv_cache);
        mAcache=ACache.get(this);
        //进行保存数据
        save_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String save_str = edit_cache.getText().toString().trim();
                mAcache.put(CacheConsts.DEMO_CACHE_KEY, save_str);
                showToastMsgShort("缓存成功...");
            }
        });
        //进行查询数据
        query_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_str=mAcache.getAsString(CacheConsts.DEMO_CACHE_KEY);
                tv_cache.setText(query_str);
            }
        });

    }
}
