package com.chinaztt.fda.test.ComInstance;

import com.chinaztt.fda.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.ComInstance
 * 作者：江清清 on 15/11/28 10:46
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class AdvanceIntanceDataUtils {
    public static List<AdvanceInstanceBean> getAdvanceInstanceBeans(){
        List<AdvanceInstanceBean> advanceInstanceBeans=new ArrayList<AdvanceInstanceBean>();
        for (int i=0;i<10;i++){
            AdvanceInstanceBean advanceInstanceBean=new AdvanceInstanceBean();
            List<InstanceBean> instanceBeans=new ArrayList<InstanceBean>();
            for(int j=0;j<2;j++){
                InstanceBean bean=null;
                if(j==0){
                    bean=new InstanceBean("我是左边标题Item"+i, R.drawable.liuyan);
                }else {
                    bean=new InstanceBean("我是右边标题Item"+i, R.drawable.liuyan);
                }
                instanceBeans.add(bean);
            }
            advanceInstanceBean.setInstanceBeans(instanceBeans);
            advanceInstanceBeans.add(advanceInstanceBean);
        }
        return  advanceInstanceBeans;
    }
}
