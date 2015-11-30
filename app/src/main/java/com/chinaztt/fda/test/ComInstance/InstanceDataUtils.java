package com.chinaztt.fda.test.ComInstance;

import com.chinaztt.fda.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.ComInstance
 * 作者：江清清 on 15/11/27 22:24
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class InstanceDataUtils {
    public static List<InstanceBean> getInstanceBeans(){
        List<InstanceBean> instanceBeans=new ArrayList<InstanceBean>();
        for (int i=0;i<10;i++){
            InstanceBean bean=new InstanceBean("我是标题Item"+i,R.drawable.liuyan);
            instanceBeans.add(bean);
        }
        return  instanceBeans;
    }
}
