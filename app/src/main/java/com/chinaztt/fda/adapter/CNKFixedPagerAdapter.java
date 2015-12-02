package com.chinaztt.fda.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import java.util.List;
/**
 * 当前类注释:Fragment，Viewpager的自定义适配器
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.adapter
 * 作者：江清清 on 15/12/2 10:08
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class CNKFixedPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    public void setTitles(String[] titles) {
        this.titles = titles;
    }
    private List<Fragment> fragments;
    public CNKFixedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }
    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try {
            fragment=(Fragment)super.instantiateItem(container,position);
        }catch (Exception e){

        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public List<Fragment> getFragments() {
        return fragments;
    }
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
