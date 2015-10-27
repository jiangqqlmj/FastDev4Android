package com.chinaztt.fda.presenter;

import android.os.Handler;

import com.chinaztt.fda.biz.IPersonBiz;
import com.chinaztt.fda.biz.LoginRequestCallBack;
import com.chinaztt.fda.biz.imp.PersonBizImp;
import com.chinaztt.fda.entity.PersonBean;
import com.chinaztt.fda.ui.view.ILoginView;
import com.chinaztt.fda.utils.Log;

/**
 * 当前类注释:负责完成登录界面View于Model(IPersonBiz)间的交互
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.presenter
 * 作者：江清清 on 15/10/27 16:36
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class LoginPresenter {
    private static final  String TAG="LoginPresenter";
    private ILoginView mLoginView;
    private IPersonBiz mPersonBiz;

    private Handler mHandler=new Handler();

    public LoginPresenter(ILoginView view) {
        mLoginView = view;
        mPersonBiz = new PersonBizImp();
    }

    public void loginSystem(){
       mPersonBiz.login(mLoginView.getUserName(), mLoginView.getPassword(), new LoginRequestCallBack() {
           /**
            * 登录成功
            * @param personBean
            */
           @Override
           public void loginSuccess(final PersonBean personBean) {
               Log.d(TAG, "登录成功:" + personBean.toString());
               mHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       mLoginView.showSuccessInfo(personBean);
                   }
               });
           }
           /**
            * 登录失败
            */
           @Override
           public void loginFailed() {
               Log.d(TAG,"登录失败...");
               mHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       mLoginView.showFailedInfo();;
                   }
               });
           }
       });
    }
}
