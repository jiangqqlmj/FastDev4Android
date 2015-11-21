package com.chinaztt.fda.test.RecyclerViewAA;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test.RecyclerViewAA
 * 作者：江清清 on 15/11/21 09:36
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EBean
public class ImMemoryUserFinder implements AAUserFinder{
    @Override
    public List<TestUserBean> findAll() {
        List<TestUserBean> userModels=new ArrayList<TestUserBean>();
        for(int i=1;i<=45;i++){
            TestUserBean model=new TestUserBean();
            model.setFirstName("First 张三:"+i);
            model.setLastName("Last 李四:" + i);
            userModels.add(model);
        }
        return userModels;
    }
}
