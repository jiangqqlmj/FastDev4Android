package com.chinaztt.fda.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

import com.chinaztt.fda.adapter.CNKFixedPagerAdapter;
import com.chinaztt.fda.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:使用google支持库实现Tab标签
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.fragment
 * 作者：江清清 on 15/12/2 21:03
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class TabInfoFragment extends Fragment {
    private String[] titles=new String[]{"全部","氪TV","O2O","新硬件","Fun!!","企业服务","Fit&Health","在线教育","互联网金融","大公司","专栏","新产品"};
    private View mView;
    private TabLayout tab_layout;
    private ViewPager info_viewpager;
    private List<Fragment> fragments;
    private CNKFixedPagerAdapter mPagerAdater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
             mView=inflater.inflate(R.layout.tab_info_fragment_layout,container,false);
            initViews();
            initValidata();
        }
        return mView;
    }
    private void initViews(){
        tab_layout=(TabLayout)mView.findViewById(R.id.tab_layout);
        info_viewpager=(ViewPager)mView.findViewById(R.id.info_viewpager);

    }
    private void initValidata(){
        fragments=new ArrayList<>();
        for(int i=0;i<12;i++){
            OneFragment oneFragment=new OneFragment();
            Bundle bundle=new Bundle();
            bundle.putString("extra",titles[i]);
            oneFragment.setArguments(bundle);
            fragments.add(oneFragment);
        }
        //创建Fragment的 ViewPager 自定义适配器
        mPagerAdater=new CNKFixedPagerAdapter(getChildFragmentManager());
        //设置显示的标题
        mPagerAdater.setTitles(titles);
        //设置需要进行滑动的页面Fragment
        mPagerAdater.setFragments(fragments);

        info_viewpager.setAdapter(mPagerAdater);
        tab_layout.setupWithViewPager(info_viewpager);


        //设置Tablayout
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置自定义Tab--加入图标的demo
        for(int i=0;i<12;i++){
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            tab.setCustomView(mPagerAdater.getTabView(i));
        }
    }
}
