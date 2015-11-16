package com.chinaztt.fda.test;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

/**
 * 当前类注释:TextDrawables 效果实例演示
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/5 22:13
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.text_drawables_layout)
public class TextDrawablesTestActivity extends BaseActivity {
    @ViewById
    ListView lv_textdrawable;

    private String[] mTitles;
    private LayoutInflater mLayoutInflater;

    private ColorGenerator mGenerator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitles=new String[]{"SAMPLE_RECT"
                ,"SAMPLE_ROUND_RECT","SAMPLE_ROUND"
                ,"SAMPLE_RECT_BORDER","SAMPLE_ROUND_RECT_BORDER"
                ,"SAMPLE_ROUND_BORDER"
                ,"SAMPLE_MULTIPLE_LETTERS",
                "SAMPLE_FONT","SAMPLE_SIZE","SAMPLE_ANIMATION","SAMPLE_MISC"
        };
        mGenerator=ColorGenerator.DEFAULT;
        mLayoutInflater=getLayouInflater();

    }
    @AfterViews
    public void showLvDrawable(){
        lv_textdrawable.setAdapter(new TextAdapter());
    }

    class TextAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitles[position];
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
                convertView=mLayoutInflater.inflate(R.layout.text_drawables_item_layout,null);
                _Hondler.lv_item_img=(ImageView)convertView.findViewById(R.id.lv_item_img);
                _Hondler.lv_item_text=(TextView)convertView.findViewById(R.id.lv_item_text);
                convertView.setTag(_Hondler);
            }else
            {
                _Hondler=(Hondler)convertView.getTag();
            }
            _Hondler.lv_item_text.setText(mTitles[position]);
            Drawable drawable=null;
            switch (position){
                case 0:  //SAMPLE_RECT
                    drawable=TextDrawable.builder().buildRect("R",Color.BLUE);
                    break;
                case 1:  //SAMPLE_ROUND_RECT
                    drawable=TextDrawable.builder().buildRoundRect("S",Color.CYAN,10);
                    break;
                case 2:  //SAMPLE_ROUND
                    drawable=TextDrawable.builder().buildRound("圆",Color.LTGRAY);
                    break;
                case 3:  //SAMPLE_RECT_BORDER
                    drawable=TextDrawable.builder().beginConfig()
                            .withBorder(5)
                            .endConfig()
                            .buildRect("粗", Color.RED);
                    break;
                case 4:  //SAMPLE_ROUND_RECT_BORDER
                    drawable=TextDrawable.builder()
                            .beginConfig()
                            .withBorder(5)
                            .endConfig()
                            .buildRoundRect("S",Color.argb(220,122,122,1),10);
                    break;
                case 5:  //SAMPLE_ROUND_BORDER
                    drawable=TextDrawable.builder()
                            .beginConfig().withBorder(5).endConfig()
                            .buildRound("圆", Color.LTGRAY);
                    break;
                case 6:  //SAMPLE_MULTIPLE_LETTERS
                    drawable=TextDrawable.builder()
                            .beginConfig()
                            .fontSize(40)
                            .toUpperCase()
                            .endConfig()
                            .buildRect("AK", mGenerator.getColor("AK"));
                    break;
                case 7:  //SAMPLE_FONT
                    drawable = TextDrawable.builder()
                            .beginConfig()
                            .textColor(Color.BLACK)
                            .useFont(Typeface.SERIF)
                            .bold()
                            .toUpperCase()
                            .endConfig()
                            .buildRect("a", Color.RED);
                    break;
                case 8:  //SAMPLE_SIZE
                    drawable = TextDrawable.builder()
                            .beginConfig()
                            .textColor(Color.BLACK)
                            .fontSize(30) /* size in px */
                            .bold()
                            .toUpperCase()
                            .endConfig()
                            .buildRect("a", Color.RED);
                    break;
                case 9:  //SAMPLE_ANIMATION
                    TextDrawable.IBuilder builder = TextDrawable.builder()
                            .rect();
                    AnimationDrawable animationDrawable = new AnimationDrawable();
                    for (int i = 10; i > 0; i--) {
                        TextDrawable frame = builder.build(String.valueOf(i), mGenerator.getRandomColor());
                        animationDrawable.addFrame(frame, 1200);
                    }
                    animationDrawable.setOneShot(false);
                    animationDrawable.start();
                    drawable=(Drawable)animationDrawable;
                    break;
                case 10: //SAMPLE_MISC
                    drawable=TextDrawable.builder()
                            .buildRect("M", mGenerator.getColor("Misc"));
                    break;
            }
            if(drawable!=null){
                _Hondler.lv_item_img.setImageDrawable(drawable);
            }
            return convertView;
        }
    }

    final static class Hondler{
        ImageView lv_item_img;
        TextView lv_item_text;
    }

    @ItemClick(R.id.lv_textdrawable)
    public void lv_ItemClick(int position){
        showToastMsgShort("点击了TextDrawable列表...");
    }
}
