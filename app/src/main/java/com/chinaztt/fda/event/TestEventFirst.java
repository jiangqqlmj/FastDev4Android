package com.chinaztt.fda.event;

/**
 * 当前类注释:EventBus测试 First事件类
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.event
 * 作者：江清清 on 15/11/3 14:25
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class TestEventFirst {
     private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TestEventFirst(String msg){
         this.msg=msg;
     }
}
