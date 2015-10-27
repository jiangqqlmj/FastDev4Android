package com.chinaztt.fda.biz.imp;

import com.chinaztt.fda.biz.IPersonBiz;
import com.chinaztt.fda.biz.LoginRequestCallBack;
import com.chinaztt.fda.entity.PersonBean;
import com.chinaztt.fda.utils.Log;

/**
 * 当前类注释:用户相关业务逻辑实现类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.biz.imp
 * 作者：江清清 on 15/10/27 16:33
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class PersonBizImp implements IPersonBiz{
    private static final  String TAG="PersonBizImp";
    @Override
    public void login(final String username, final String password, final LoginRequestCallBack valueCallBack) {
        Log.d(TAG,"username:"+username+",password:"+password);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //进行开始登录,这边应该进行请求服务器，进行数据验证
                if(username.equals("jiangqq")&&password.equals("12345")){
                    valueCallBack.loginSuccess(new PersonBean(username,password));
                }else{
                    valueCallBack.loginFailed();
                }
            }
        }).start();
    }
}
