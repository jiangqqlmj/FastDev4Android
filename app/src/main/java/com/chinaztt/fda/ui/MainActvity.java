package com.chinaztt.fda.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chinaztt.fda.html5.HTML5WebViewCustomAD;
import com.chinaztt.fda.test.AVLoadingIndicatorActivity_;
import com.chinaztt.fda.test.AnnotationsTestActivity_;
import com.chinaztt.fda.test.BaseAdapterTestActivity_;
import com.chinaztt.fda.test.CardView.CardViewActivity;
import com.chinaztt.fda.test.CrashTestActivity_;
import com.chinaztt.fda.test.EventBusTestActivity_;
import com.chinaztt.fda.test.GalleryIndicatorActivity_;
import com.chinaztt.fda.test.MVPTestActivity_;
import com.chinaztt.fda.test.MainFrameActivity_;
import com.chinaztt.fda.test.MainInfoActivity;
import com.chinaztt.fda.test.PullListviewActivity_;
import com.chinaztt.fda.test.RecyclerDemoActivity_;
import com.chinaztt.fda.test.SPCacheActivity_;
import com.chinaztt.fda.test.TabLayoutActivity;
import com.chinaztt.fda.test.TextDrawablesTestActivity_;
import com.chinaztt.fda.test.TranslucentActivity_;
import com.chinaztt.fda.test.ViewDragHelper.ViewGragHelperActivity;
import com.chinaztt.fda.test.VolleyTestActivity_;
import com.chinaztt.fda.test.okhttp.OkhttpDemoActivity;
import com.chinaztt.fda.ui.base.BaseActivity;
import org.androidannotations.annotations.EActivity;
/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.ui
 * 作者：江清清 on 15/10/22 08:59
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */

@EActivity
public class MainActvity extends BaseActivity implements View.OnTouchListener {
    private String[] mItems;
    private Class[] mClassItems;
    private LayoutInflater mInflater;
    private ListView lv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mItems = this.getResources().getStringArray(R.array.main_list);
        mClassItems = new Class[]{GalleryIndicatorActivity_.class, PullListviewActivity_.class,
                SPCacheActivity_.class, CrashTestActivity_.class
                , TranslucentActivity_.class,MVPTestActivity_.class,
                AnnotationsTestActivity_.class,AVLoadingIndicatorActivity_.class,
                EventBusTestActivity_.class, TextDrawablesTestActivity_.class,
                HTML5WebViewCustomAD.class, BaseAdapterTestActivity_.class,
                VolleyTestActivity_.class, MainFrameActivity_.class,
                RecyclerDemoActivity_.class, CardViewActivity.class, ViewGragHelperActivity.class,
                MainInfoActivity.class, TabLayoutActivity.class, OkhttpDemoActivity.class
        };

        lv_main = (ListView) this.findViewById(R.id.lv_main);
        mInflater = getLayouInflater();
        lv_main.setAdapter(new MainAdapter());
        lv_main.setOnItemClickListener(new CustomOnItemClick());
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    class CustomOnItemClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==4){
                    new AlertDialog.Builder(MainActvity.this).setTitle("选择操作").setSingleChoiceItems(new String[]{"系统方法","第三方库"},0,new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           Intent mIntent=new Intent(MainActvity.this,TranslucentActivity_.class);
                            mIntent.putExtra("mode",which);
                            openActivityByIntent(mIntent);
                            dialog.dismiss();
                        }
                    }).create().show();
                }else{
                    openActivity(mClassItems[position]);
                }
        }
    }
    class MainAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Hondler _Honlder=null;
            if(convertView==null){
                convertView=mInflater.inflate(R.layout.lv_main_item,null);
                _Honlder=new Hondler();
                _Honlder.tv_item=(TextView)convertView.findViewById(R.id.tv_item);
                convertView.setTag(_Honlder);
            }else
            {
                _Honlder=(Hondler)convertView.getTag();
            }
            _Honlder.tv_item.setText(mItems[position]);
            return convertView;
        }



    }

    static class Hondler{
        TextView tv_item;
    }


}
