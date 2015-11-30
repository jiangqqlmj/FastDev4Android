package com.chinaztt.fda.entity;

/**
 * 当前类注释:模拟Gallery提供数据的实体类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.entity
 * 作者：江清清 on 15/11/19 20:55
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class GalleryModel {
    private int imgurl;
    private String title;

    public GalleryModel() {
    }

    public GalleryModel(int imgurl, String title) {
        this.imgurl = imgurl;
        this.title = title;
    }

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GalleryModel{" +
                "imgurl=" + imgurl +
                ", title='" + title + '\'' +
                '}';
    }
}
