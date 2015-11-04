<p># FastDev4Android</p>
本项目是Android快速开发框架，采用AndroidStudio进行开发。
预想集成工具包,ORM,网络请求(HTTPClint,Volley,OkHttps),数据解析,依赖注入,xutils,图片异步加载，二维码扫描等等<br>
同时会包括工作中自己封装的一些组件和控件.
后续会进行逐步添加
整体项目目录如下:
<p>FastDev4Android</p>
<table border="1" cellspacing="1" style="border-collapse:collapse">
    <tr>
        <th>包名</th><th>描述</th>
    </tr>
    <tr><td>libs</td> <td>一些公共jar包库</td></tr>
    <tr><td>adapter</td><td>适配器</td></tr>
    <tr><td>application</td><td>全局application</td></tr>
    <tr><td>base</td><td>基类包</td></tr>
    <tr><td>cache</td><td>数据缓存相关处理</td></tr>
    <tr><td>common</td><td>公共类,或者配置相关</td></tr>
    <tr><td>db</td><td>数据库操作相关</td></tr>
    <tr><td>event</td><td>事件处理相关</td></tr>
    <tr><td>fragment</td><td>fragment操作管理相关</td></tr>
    <tr><td>html5</td><td>webview处理,重写webview</td></tr>
    <tr><td>json</td><td>json数据解析</td></tr>
    <tr><td>listlogic</td><td>网络数据请求加载分发</td></tr>
    <tr><td>location</td><td>位置相关</td></tr>
    <tr><td>model</td><td>实体类</td></tr>
    <tr><td>push</td><td>消息推送</td></tr>
    <tr><td>sensor</td><td>设备传感器相关</td></tr>
    <tr><td>spreference</td><td>SharedPerference管理</td></tr>
    <tr><td>test</td><td>消息推送</td></tr>
    <tr><td>ui</td><td>Activity UI相关</td></tr>
    <tr><td>update</td><td>APP自动更新相关</td></tr>
    <tr><td>utils</td><td>项目各种工具类</td></tr>
    <tr><td>widget</td><td>自定义控件</td></tr>
    <tr><td>crash</td><td>自定义崩溃异常处理</td></tr>
    <tr><td>receiver</td><td>广播通知处理</td></tr>
</table>
<h3>广告一下:</h3>
Android在线学习网站(项目驱动学习)网站:<a href="http://www.cniao5.com/">菜鸟窝</a>欢迎大家前往免费学习!<br><br>
<a href="http://www.cniao5.com/clazz/yaya.html" target="_blank"><img src="http://img2.xxh.cc:8080/images/mall/yaya.png"/></a><br/>
<p><a href="http://blog.csdn.net/developer_jiangqq" style="text-decoration: none;"  target="_blank"><span style="color:blue;">V1.0_001版本功能如下:</span></a></p>
一.Utils工具类加入<br/>
&nbsp;&nbsp;&nbsp;&nbsp;1.DataUtils 时间日期处理<br/>
&nbsp;&nbsp;&nbsp;&nbsp;2.GuideUtils 是否启动引导处理标志管理<br/>
&nbsp;&nbsp;&nbsp;&nbsp;3.IoUtils 网络请求工具类【特别注意】这边采用HTTPClient 由于Android 6.0已经删除该类,
这边libs目录需要加入org.apache.http.legcy.jar依赖包<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.JudgeNetWorker 网络状态判断工具类<br/>
&nbsp;&nbsp;&nbsp;&nbsp;5.Log 日志自定义管理<br/>
&nbsp;&nbsp;&nbsp;&nbsp;6.ManagerActivity Activity管理工具类<br/>
&nbsp;&nbsp;&nbsp;&nbsp;7.StrUtils 字符串相关处理工具类，系统信息获取工具类)<br/>
二.sperferences加入SharePerferences加入封装工具可以快速使用SP进行数据保存配置文件<br/>
三.Activity基类简单封装BaseActivity和BaseFrameActivity 暂时主要为Toast,LayoutInFlater,打开指定的Activity工具类分装<br/>

<p><a href="http://blog.csdn.net/developer_jiangqq" style="text-decoration: none;"  target="_blank"><span style="color:blue;">V1.1_002版本功能如下:</span></a></p>
一.新增首页图片自动无限轮播组件和指示器(AutoGallery+FlowIndicator);<br/>
二.新增列表下拉刷新组件(PullToRefreshListView);<br/>
三.新增本地轻量级数据缓存组件(ACache);<br/>
四.新增应用自定义崩溃日志捕捉组件(CustomCrash);<br/>
以上该组件全部在MainActivity中有相应的使用实例;<br/>

<p><a href="http://blog.csdn.net/developer_jiangqq" style="text-decoration: none;"  target="_blank"><span style="color:blue;">V1.1.1_003版本功能如下:</span></a></p>
一.新增沉浸式状态栏功能实现;<br/>
二.新增MVP开发模式功能Demo;<br/>
以上该组件全部在MainActivity中有相应的使用实例;<br/>

<img src="http://img.blog.csdn.net/20151027203315049"/><br/><br/>

<p><a href="http://blog.csdn.net/developer_jiangqq" style="text-decoration: none;"  target="_blank"><span style="color:blue;">20151029注入框架更新:</span></a></p>
更新了AndroidAnnotations注入框架的使用详解:</br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49468923">AndroidAnnnotations注入框架介绍和Android Studios基本配置(一)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49471543">AndroidAnnnotations注入框架的工作原理(二)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49490083">AndroidAnnnotations注入框架使用之注入组件Components(三)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49497955">AndroidAnnnotations注入框架使用之Injection标签详解(四)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49512513">AndroidAnnnotations注入框架使用之事件绑定Event Binding(五)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49518299">AndroidAnnnotations注入框架使用之线程处理Threading(六)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49530307">AndroidAnnnotations注入框架使用之第三方框架集成RoboGuice(七)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49530731">AndroidAnnnotations注入框架使用之第三方框架集成Otto事件总线(八)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49530971">AndroidAnnnotations注入框架使用之第三方框架集成OrmLite(九)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49531669">AndroidAnnnotations注入框架使用之最佳实践之Adapters和lists(十)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49583487">AndroidAnnnotations注入框架使用之最佳实践SharedPreferences(十一)</a></br>
<br/>

<p><a href="http://blog.csdn.net/developer_jiangqq" style="text-decoration: none;"  target="_blank"><span style="color:blue;">20151101框架更新:</span></a></p>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49612399">非常漂亮的进度指示器AVLoadingIndicatorView的使用讲解(十八)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49613861">Android MVP开发模式详解(十九)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49617189">消息总线EventBus的基本使用(二十)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq/article/details/49640153">消息总线EventBus源码分析以及与Otto框架对比(二十一)</a></br>
<a href="http://blog.csdn.net/developer_jiangqq">更多项目内容请详见CSDN博客!</a></br>

后期会持续不断进行更新最新的框架功能，如果有一起合作把这个Android快速开发框架完善起来的~请联系我哦<br/>
<a href="http://blog.csdn.net/developer_jiangqq" style="text-decoration: none;"  target="_blank"><span style="color:blue;">QQ:781931404</span></a><br/>
<br/>
