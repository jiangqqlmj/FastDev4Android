package com.chinaztt.fdv;
import android.graphics.Bitmap;
import android.util.LruCache;
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
    private LruCache<String, Bitmap> mCache=null;
    private static final int CACHE_MAX_SIZE = 8 * 1024 * 1024;  //默认缓存大小为8M
    public Fdv_ImageCache(){
        if(mCache==null){
            mCache = new LruCache<String, Bitmap>(CACHE_MAX_SIZE) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }
    }
    /**
     * 从缓存中获取图片
     * @param url  获取图片key  当然该key可以根据实际情况 使用url进行变换修改
     * @return
     */
    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }
    /**
     * 向缓存中添加图片
     * @param url  缓存图片key，当然该key可以根据实际情况 使用url进行变换修改 不过规格需要和上面方法的key保持一致
     * @param bitmap  需要缓存的图片
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
       mCache.put(url,bitmap);
    }
}
