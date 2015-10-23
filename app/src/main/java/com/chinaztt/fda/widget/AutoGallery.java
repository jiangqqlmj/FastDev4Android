package com.chinaztt.fda.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Gallery;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 当前类注释:重写Gallery对画廊控件进行重写，扩展成可以自动切换图片Gallery
 * 这个图片轮播控件还是比较之前封装的，现在一般采用viewpager进行封装，后面我这边也会介绍
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.widget
 * 作者：江清清 on 15/10/23 08:41
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class AutoGallery extends Gallery implements View.OnTouchListener {
    //画廊图片的数量
    private int length;
    //自动切换图片的时间
    private long delayMillis = 5000;
    //定时器
    private Timer timer = null;

    public AutoGallery(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public AutoGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public AutoGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    /**
     * 重写Galler中手指滑动的手势方法
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        int kEvent;
        if (isScrollingLeft(e1, e2)) {
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT; //设置手势滑动的方法 --向左
        } else {
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT; //设置手势滑动的方法--向右
        }
        onKeyDown(kEvent, null);  //进行设置galler切换图片
        if (this.getSelectedItemPosition() == 0) {
            this.setSelection(length);
        }
        return false;
    }

    /**
     * 进行判断滑动方向
     * @param e1
     * @param e2
     * @return
     */
    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > e1.getX();
    }

    /**
     * 开启定时器
     */
    public void start() {
        if (length > 0&&timer == null) {
            timer = new Timer();
            //进行每个delayMillis时间gallery切换一张图片
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (length > 0) {
                        onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
                    }
                }
            }, delayMillis, delayMillis);
        }
    }

    /**
     * 关闭定时器
     */
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 重写手指触摸的事件，当手指按下的时候，需要关闭gallery自动切换
     * 当手指抬开得时候 需要打开gallery自动切换功能
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stop();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                start();
                break;
        }
        return false;
    }
}
