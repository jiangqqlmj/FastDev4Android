package com.chinaztt.fda.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinaztt.fda.ui.R;

/**
 * 当前类注释:第一个Fragment
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.fragment
 * 作者：江清清 on 15/11/16 13:45
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class OneFragment  extends Fragment{
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.one_frag_layout,container,false);
            TextView tv_content=(TextView)mView.findViewById(R.id.tv_content);
            Bundle bundle=getArguments();
            if(bundle!=null){
                tv_content.setText(bundle.getString("extra"));
            }

        }
        return mView;
    }
}
