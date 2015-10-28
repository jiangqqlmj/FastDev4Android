package com.chinaztt.fda.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chinaztt.fda.entity.PersonBean;
import com.chinaztt.fda.presenter.LoginPresenter;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.ui.view.ILoginView;
import com.chinaztt.fda.utils.Log;

import org.androidannotations.annotations.EActivity;

/**
 * 当前类注释:MVP开发模式实例
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/10/27 13:38
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity
public class MVPTestActivity extends BaseActivity implements ILoginView{
    private static  final  String TAG="MVPTestActivity";

    private EditText ed_username;
    private EditText ed_password;
    private Button btn_login;
    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_test_layout);
        ed_username=(EditText)this.findViewById(R.id.ed_username);
        ed_password=(EditText)this.findViewById(R.id.ed_password);
        btn_login=(Button)this.findViewById(R.id.btn_login);
        mLoginPresenter=new LoginPresenter(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.loginSystem();

            }
        });
    }
    /**
     * 进行返回用户名信息
     * @return
     */
    @Override
    public String getUserName() {
        return ed_username.getText().toString().trim();
    }
    /**
     * 进行返回用户密码信息
     * @return
     */
    @Override
    public String getPassword() {
        return ed_password.getText().toString().trim();
    }
    /**
     * 登录成功 回调
     * @param personBean
     */
    @Override
    public void showSuccessInfo(PersonBean personBean) {
        Log.d(TAG,"showSuccessInfo:"+personBean.toString());
        showToastMsgShort("登录成功:"+personBean.toString());
    }
    /**
     * 登录失败 回调
     */
    @Override
    public void showFailedInfo() {
        Log.d(TAG,"showFailedInfo...");
        showToastMsgShort("登录失败...");
    }
}
