package com.chinaztt.fda.ui.view;

import com.chinaztt.fda.entity.PersonBean;

/**
 * 当前类注释:登录页面 相关操作 功能接口
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.ui.view
 * 作者：江清清 on 15/10/27 16:35
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public interface ILoginView {
    //获取用户名
    String getUserName();
    //获取密码
    String getPassword();

    void showSuccessInfo(PersonBean personBean);
    void showFailedInfo();
}
