package com.chinaztt.fdv;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

/**
 * 当前类注释:图片缓存器，实现ImageLoder.ImageCache实现其中的方法，具体图片怎么样缓存让我们自己来实现
 * 这样可以考虑到将来的扩展性
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fdv
 * 作者：江清清 on 15/11/12 12:31
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class Fdv_ImageCache  implements ImageLoader.ImageCache {
    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {

    }
}
