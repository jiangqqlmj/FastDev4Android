package com.chinaztt.fda.entity;

/**
 * 当前类注释:更新信息bean
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.entity
 * 作者：江清清 on 15/11/10 21:36
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class UpdateBean{
    private String appname;
    private String serverVersion;
    private String serverFlag;
    private String lastForce;
    private String updateurl;
    private String upgradeinfo;
    private String serverName;

    public UpdateBean() {
    }

    public UpdateBean(String appname, String serverVersion, String serverFlag, String lastForce, String updateurl, String upgradeinfo, String serverName) {
        this.appname = appname;
        this.serverVersion = serverVersion;
        this.serverFlag = serverFlag;
        this.lastForce = lastForce;
        this.updateurl = updateurl;
        this.upgradeinfo = upgradeinfo;
        this.serverName = serverName;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(String serverFlag) {
        this.serverFlag = serverFlag;
    }

    public String getLastForce() {
        return lastForce;
    }

    public void setLastForce(String lastForce) {
        this.lastForce = lastForce;
    }

    public String getUpdateurl() {
        return updateurl;
    }

    public void setUpdateurl(String updateurl) {
        this.updateurl = updateurl;
    }

    public String getUpgradeinfo() {
        return upgradeinfo;
    }

    public void setUpgradeinfo(String upgradeinfo) {
        this.upgradeinfo = upgradeinfo;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        return "UpdateBean{" +
                "appname='" + appname + '\'' +
                ", serverVersion='" + serverVersion + '\'' +
                ", serverFlag='" + serverFlag + '\'' +
                ", lastForce='" + lastForce + '\'' +
                ", updateurl='" + updateurl + '\'' +
                ", upgradeinfo='" + upgradeinfo + '\'' +
                ", serverName='" + serverName + '\'' +
                '}';
    }
}
