package com.chinaztt.fda.test.ComInstance;

/**
 * 当前类注释:实例模拟数据bean
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.ComInstance
 * 作者：江清清 on 15/11/27 21:56
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class InstanceBean {
    private int img;
    private String title;
    public InstanceBean() {
    }

    public InstanceBean(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "InstanceBean{" +
                "img='" + img + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
