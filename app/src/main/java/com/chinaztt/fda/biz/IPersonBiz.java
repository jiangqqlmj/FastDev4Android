package com.chinaztt.fda.biz;

/**
 * 当前类注释:用户相关业务操作接口
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.biz
 * 作者：江清清 on 15/10/27 16:32
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public interface IPersonBiz {
    void login(String username,String password,LoginRequestCallBack valueCallBack);
}
