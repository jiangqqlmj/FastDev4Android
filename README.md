# FastDev4Android
本项目是Android快速开发框架，采用AndroidStudio进行开发。 
预想集成工具包,ORM,网络请求(HTTPClint,Volley,OkHttps),数据解析,依赖注入,xutils,图片异步加载，二维码扫描等等
后续会进行逐步添加
整体项目目录如下:
FastDev4Android
    --app--
          |--build
          |--libs 一些公共jar包库
          |- -src--main--androidTest
                       |-main---java-com.chinaztt.fda--|-apater        适配器
                                                       |-application   全局application
                       |-res                           |-base          基类包
                                                       |-cache         数据缓存相关处理
                                                       |-common        公共类,或者配置相关
                                                       |-db            数据库操作相关
                                                       |-event         事件处理相关
                                                       |-fragment      fragment操作管理相关
                                                       |-html5         webview处理,重写webview
                                                       |-json          json数据解析
                                                       |-listlogic     网络数据请求加载分发
                                                       |-location      位置相关
                                                       |-model         实体类
                                                       |-push          消息推送
                                                       |-sensor        设备传感器相关
                                                       |-spreference   SharedPerference管理
                                                       |-test          
                                                       |-ui            Activity UI相关
                                                       |-update        APP自动更新相关
                                                       |-utils         项目各种工具类
                                                       |-widget        自定义控件
                                                       
                       |-AndroidMainifest.xml

    --build
    --grade
    
V1.0_001版本功能如下:
一.Utils工具类加入(1.DataUtils 时间日期处理
              2.GuideUtils 是否启动引导处理标志管理
              3.IoUtils 网络请求工具类【特别注意】这边采用HTTPClient 由于Android 6.0已经删除该类，
                这边libs目录需要加入org.apache.http.legcy.jar依赖包
              4.JudgeNetWorker 网络状态判断工具类
              5.Log 日志自定义管理
              6.ManagerActivity Activity管理工具类
              7.StrUtils 字符串相关处理工具类，系统信息获取工具类)
二.sperferences加入SharePerferences加入封装工具可以快速使用SP进行数据保存配置文件
三.Activity基类简单封装BaseActivity和BaseFrameActivity 暂时主要为Toast,LayoutInFlater,打开指定的Activity工具类分装

后期会持续不断进行更新最新的框架功能，如果有一起合作把这个Android快速开发框架完善起来的~请联系我哦
QQ:781931404
              
