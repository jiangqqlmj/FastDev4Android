package com.chinaztt.fda.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinaztt.fda.application.FDApplication;
import com.chinaztt.fda.ui.R;

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
    private LayoutInflater mInflater;
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
    //此方法用来显示tab上的名字
//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        return titles[position % titles.length];
//    }
    public List<Fragment> getFragments() {
        return fragments;
    }
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    /**
     * 添加getTabView的方法，来进行自定义Tab的布局View
     * @param position
     * @return
     */
    public View getTabView(int position){
        mInflater=LayoutInflater.from(FDApplication.getInstance());
        View view=mInflater.inflate(R.layout.tab_item_layout,null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(titles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(R.mipmap.ic_launcher);
        return view;
    }

}
