package com.chinaztt.fda.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.widget.AutoGallery;
import com.chinaztt.fda.widget.FlowIndicator;

import org.androidannotations.annotations.EActivity;

/**
 * 当前类注释: 图片轮播封装类的简单使用
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/23 08:35
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class GalleryIndicatorActivity extends BaseActivity{
    private LayoutInflater mInflater;
    private int[] mImages;
    private AutoGallery headline_image_gallery;        //自动图片轮播Gallery
    private FlowIndicator galleryFlowIndicator;  //指示器控件
    private int circleSelectedPosition = 0; // 默认指示器的圆圈的位置为第一项
    private int gallerySelectedPositon = 0; // 默认gallery的图片为第一张

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_indicator_layout);
        mInflater=getLayouInflater();
        mImages=new int[]{
                R.drawable.one,
                R.drawable.two,
                R.drawable.three,
                R.drawable.four
        };
        headline_image_gallery=(AutoGallery)this.findViewById(R.id.headline_image_gallery);
        galleryFlowIndicator=(FlowIndicator)this.findViewById(R.id.headline_circle_indicator);

        int topSize = mImages.length;
        //设置指示器圆点的数量
        galleryFlowIndicator.setCount(topSize);
        //设置当前的位置
        galleryFlowIndicator.setSeletion(circleSelectedPosition);
        //设置画廊 图片的数量
        headline_image_gallery.setLength(topSize);
        headline_image_gallery.setAdapter(new GalleryIndicatorAdapter());

        gallerySelectedPositon = topSize * 20 + circleSelectedPosition;
        //设置画廊当前所指的位置 索引
        headline_image_gallery.setSelection(gallerySelectedPositon);
        headline_image_gallery.start();

        //gallery滚动选择监听
        headline_image_gallery
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        gallerySelectedPositon = position;
                        circleSelectedPosition = position
                                % headline_image_gallery.getLength();
                        galleryFlowIndicator
                                .setSeletion(circleSelectedPosition);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        //gallery点击选中事件
        headline_image_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index=position
                        % headline_image_gallery.getLength()+1;
                showToastMsgShort("点击了第"+index+"个图片!");
            }
        });
    }

    class GalleryIndicatorAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return mImages[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Hondler _Hondler=null;
            if(convertView==null){
                _Hondler=new Hondler();
                convertView=mInflater.inflate(R.layout.headline_gallery_item,null);
                _Hondler.headline_gallery_imageview=(ImageView)convertView.findViewById(R.id.headline_gallery_imageview);
                convertView.setTag(_Hondler);
            }else
            {
                _Hondler=(Hondler)convertView.getTag();
            }
            int mPosition = position % mImages.length;
            _Hondler.headline_gallery_imageview.setImageResource(mImages[mPosition]);
            return convertView;
        }
    }

    static class Hondler{
        ImageView headline_gallery_imageview;
    }
}
