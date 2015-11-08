package com.chinaztt.fda.test;

import com.chinaztt.fda.entity.ModuleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/8 17:53
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class DataUtils {
    /**
     * 进行构造相关数据
     * @return
     */
    public static List<ModuleBean> getAdapterData(){
        List<ModuleBean> moduleBeans=new ArrayList<ModuleBean>();
        ModuleBean moduleBean=new ModuleBean();
        moduleBean.setModulename("完美“价”给你");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150325/20150325083110_0898.jpg");
        moduleBean.setDescription("标题1的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("探路者，旅行记");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150325/20150325083214_8280.jpg");
        moduleBean.setDescription("标题2的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("进口商品，彻底放价");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150328/20150328105404_2392.jpg");
        moduleBean.setDescription("标题3的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("鲜果季");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150325/20150325083611_0644.jpg");
        moduleBean.setDescription("标题4的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("盼盼 法式小面包");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150312/20150312100454_8837.jpg");
        moduleBean.setDescription("标题5的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("雀巢 脆脆鲨 威化 480g/盒");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150312/20150312100617_0693.jpg");
        moduleBean.setDescription("标题6的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("主题馆1");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150129/20150129163540_6179.jpg");
        moduleBean.setDescription("标题7的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("主题馆2");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150129/20150129163615_1774.jpg");
        moduleBean.setDescription("标题8的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("主题馆3");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150129/20150129163635_1130.jpg");
        moduleBean.setDescription("标题9的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("主题馆4");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150129/20150129163840_0270.jpg");
        moduleBean.setDescription("标题10的简要说明");
        moduleBeans.add(moduleBean);

        moduleBean=new ModuleBean();
        moduleBean.setModulename("主题馆5");
        moduleBean.setImgurl("http://interface.zttmall.com/Images/upload/image/20150129/20150129163849_4099.jpg");
        moduleBean.setDescription("标题11的简要说明");
        moduleBeans.add(moduleBean);
        return moduleBeans;
    }
}
