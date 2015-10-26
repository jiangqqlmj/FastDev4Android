package com.chinaztt.fda.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.chinaztt.fda.listlogic.RequestCallBack;
import com.chinaztt.fda.spreference.SharedPreferencesHelper;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * 当前类注释:网络请求工具类
 * 当前类还是采用了httpclient进行请求，由于在从Android L开始当前库已经被废弃了，Android M直接删除该工具类
 * 现在如果还需要使用的时候 建议编译在Android M之下编译 或者下载libs中org.apache.http.legacy.jar包进行关联使用
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.utils
 * 作者：江清清 on 15/10/22 09:50
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class IoUtils {

    //Log日志筛选标签
    private static final String TAG_LISTLOGIC="listlogic";

    // 网络连接超时时间
    private static final int DEF_CONN_TIMEOUT = 120 * 1000;
    // 网络sock通信超时时间
    private static final int DEF_SOCKET_TIMEOUT = 120 * 1000;

    // 网络连接超时时间
    private static final int DELIVER_CONN_TIMEOUT = 120 * 1000;
    // 网络sock通信超时时间
    private static final int DELIVER_SOCKET_TIMEOUT = 120 * 1000;
    // 缓冲的大小
    private static final int BUFF_SIZE = 1024 * 2;

    // 请求数据
    private static SharedPreferencesHelper mSharedPreferencesHelper;

    public static InputStream getInputStreamFromUrl(String url,
                                                    RequestCallBack requestCallBack) {
        if (url == null || !url.contains("http://")) {
            Log.e("listlogic", "列表下载地址异常");
            return null;
        }
        if(requestCallBack!=null){
            requestCallBack.onRequestStart();
        }
        if (requestCallBack != null) {
            requestCallBack.onRequestLoading();
        }

        URI encodedUri = null;
        HttpGet httpGet = null;
        try {
            encodedUri = new URI(url);
            httpGet = new HttpGet(encodedUri);
        } catch (URISyntaxException e) {
            // 清理一些空格
            String encodedUrl = url.replace(' ', '+');
            httpGet = new HttpGet(encodedUrl);
            e.printStackTrace();
        }
        // 创建httpclient对象
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DEF_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DEF_SOCKET_TIMEOUT);
        HttpResponse httpResponse = null;
        InputStream inputStream = null;
        try {
            try {
                // 执行请求
                httpResponse = httpClient.execute(httpGet);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            if (httpResponse != null) {
                int httpCode = httpResponse.getStatusLine().getStatusCode();
                if (httpCode == HttpStatus.SC_OK) {
                    // 请求数据
                    HttpEntity httpEntity = httpResponse.getEntity();
                    if (httpEntity != null) {
                        inputStream = httpEntity.getContent();
                        byte[] bytes = getByteArrayFromInputstream(inputStream);
                        if (bytes != null) {
                            InputStream inputStream2 = new ByteArrayInputStream(
                                    bytes);
                            if (requestCallBack != null) {
                                requestCallBack.onRequestSuccess(inputStream2);
                            }
                            return inputStream2;
                        }
                    }
                } else {
                    httpGet.abort();
                    if (requestCallBack != null) {
                        requestCallBack.onRequestError(
                                RequestCallBack.HTTPSTATUSERROR, "HTTP链接错误");
                    }

                }
            } else {
                httpGet.abort();
                if (requestCallBack != null) {
                    requestCallBack.onRequestError(
                            RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
            }
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCallBack != null) {
                requestCallBack.onCancel();
            }
        }
        return null;
    }

    // 获取字节数组
    public static byte[] getByteArrayFromInputstream(InputStream is) {
        ByteArrayOutputStream bot = new ByteArrayOutputStream();
        byte[] bytes = new byte[BUFF_SIZE];
        int rc = 0;
        try {
            while ((rc = is.read(bytes, 0, BUFF_SIZE)) != -1) {
                bot.write(bytes, 0, rc);
            }
            bot.flush();
            bot.close();
            return bot.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post投递
     */
    public static void sendMessageByPost(String url, HashMap<String, String> map) {
        if (url == null || url.equals("")) {
            return;
        }
        Log.d(TAG_LISTLOGIC, "post投递地址:" + url);
        HttpPost httpPost = null;
        URI encodedUri = getEncodingURI(url);
        httpPost = new HttpPost(encodedUri);
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DELIVER_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DELIVER_SOCKET_TIMEOUT);
        try {
            if (map != null) {
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey().toString();
                    String value = null;
                    if (entry.getValue() == null) {
                        value = "";
                    } else {
                        value = entry.getValue().toString();
                    }
                    Log.d(TAG_LISTLOGIC, "post投递参数" + key + "=" + value);
                    BasicNameValuePair basicNameValuePair = new BasicNameValuePair(
                            key, value);
                    nameValuePair.add(basicNameValuePair);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair,
                        HTTP.UTF_8));
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                int code = httpResponse.getStatusLine().getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    Log.d(TAG_LISTLOGIC, "post投递服务器返回200");
                    return;
                } else {
                    httpPost.abort();
                }
            } else {
                httpPost.abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        }
    }

    /**
     * get投递
     */
    public static void sendMessageByGet(String url,
                                        HashMap<String, String> map, boolean isEncode) {
        if (url == null || url.equals("")) {
            return;
        }
        if (map != null) {
            StringBuilder sb = new StringBuilder(url);
            if (url.contains("?")) {
                sb.append('&');
            } else {
                sb.append('?');
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String value = null;
                if (entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue().toString();
                }
                sb.append(key);
                sb.append('=');
                try {
                    Log.d(TAG_LISTLOGIC, "get投递的key:" + key);
                    if (isEncode) {
                        Log.d(TAG_LISTLOGIC, "get投递编码前value:" + value);
                        value = URLEncoder.encode(value, HTTP.UTF_8);
                        Log.d(TAG_LISTLOGIC, "get投递编码后value:" + value);
                    }
                    sb.append(value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append('&');
            }
            sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"
            url = sb.toString();
        }
        Log.d(TAG_LISTLOGIC, "get投递地址:" + url);
        HttpGet httpGet = null;
        URI encodedUri = getEncodingURI(url);
        if (encodedUri == null) {
            return;
        }
        httpGet = new HttpGet(encodedUri);
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DELIVER_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DELIVER_SOCKET_TIMEOUT);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                int code = httpResponse.getStatusLine().getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    Log.d(TAG_LISTLOGIC, "get投递服务器返回值200");
                    return;
                } else {
                    httpGet.abort();
                }
            } else {
                httpGet.abort();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }

        }
    }

    /**
     * get投递
     */
    public static void sendMessageByGet(String url) {
        if (url == null || url.equals("")) {
            return;
        }
        Log.d(TAG_LISTLOGIC, "get投递地址:" + url);
        HttpGet httpGet = null;
        URI encodedUri = getEncodingURI(url);
        if (encodedUri == null) {
            return;
        }
        httpGet = new HttpGet(encodedUri);
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DELIVER_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DELIVER_SOCKET_TIMEOUT);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                int httpCode = httpResponse.getStatusLine().getStatusCode();
                if (httpCode == HttpStatus.SC_OK) {
                    Log.d(TAG_LISTLOGIC, "get投递服务器返回值200");
                    return;
                } else {
                    httpGet.abort();
                }
            } else {
                httpGet.abort();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        }
    }
    public static String responseFromServiceByPost(String url,String encode,
                                                   HashMap<String, String> map, RequestCallBack requestCallBack) {
        if (url == null || url.equals("") || map == null) {
            return null;
        }
        if(requestCallBack!=null){
            requestCallBack.onRequestStart();
        }
        Log.d(TAG_LISTLOGIC, "post数据请求地址:" + url);
        if (requestCallBack != null) {
            requestCallBack.onRequestLoading();
        }
        HttpPost httpPost = null;
        URI encodedUri = null;
        try {
            encodedUri = new URI(url);
            httpPost = new HttpPost(encodedUri);
        } catch (URISyntaxException e) {
            // 清理一些空格
            String encodedUrl = url.replace(' ', '+');
            httpPost = new HttpPost(encodedUrl);
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DEF_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DEF_SOCKET_TIMEOUT);
        try {
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String value = null;
                if (entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue().toString();
                }
                Log.d(TAG_LISTLOGIC, "post数据请求参数" + key + "=" + value);
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(
                        key, value);
                nameValuePair.add(basicNameValuePair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, encode));
            // httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair,
            // "GBK"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                int code = httpResponse.getStatusLine().getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    HttpEntity entity = httpResponse.getEntity();
                    String result = EntityUtils.toString(entity).trim();
                    Log.d(TAG_LISTLOGIC, "post数据请求服务器返回值200");
                    Log.d(TAG_LISTLOGIC, "post返回值:" + result);
                    if (requestCallBack != null) {
                        requestCallBack.onRequestSuccess(result);
                    }
                    return result;
                } else {
                    httpPost.abort();
                    if (requestCallBack != null) {
                        requestCallBack.onRequestError(
                                RequestCallBack.HTTPSTATUSERROR, "HTTP链接错误");
                    }

                }
            } else {
                httpPost.abort();

                if (requestCallBack != null) {
                    requestCallBack.onRequestError(
                            RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
            }
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.OUTOFMEMORYERROR, "内存溢出");
            }

            return null;
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
            if(requestCallBack!=null){
                requestCallBack.onCancel();
            }
        }
        return null;
    }
    /**
     * post请求获取服务端数据
     */
    public static String responseFromServiceByPost(String url,
                                                   HashMap<String, String> map, RequestCallBack requestCallBack) {
        if (url == null || url.equals("") || map == null) {
            return null;
        }
        if(requestCallBack!=null){
            requestCallBack.onRequestStart();
        }
        Log.d(TAG_LISTLOGIC, "post数据请求地址:" + url);
        if (requestCallBack != null) {
            requestCallBack.onRequestLoading();
        }
        HttpPost httpPost = null;
        URI encodedUri = null;
        try {
            encodedUri = new URI(url);
            httpPost = new HttpPost(encodedUri);
        } catch (URISyntaxException e) {
            // 清理一些空格
            String encodedUrl = url.replace(' ', '+');
            httpPost = new HttpPost(encodedUrl);
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DEF_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DEF_SOCKET_TIMEOUT);
        try {
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String value = null;
                if (entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue().toString();
                }
                Log.d(TAG_LISTLOGIC, "post数据请求参数" + key + "=" + value);
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(
                        key, value);
                nameValuePair.add(basicNameValuePair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
            // httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair,
            // "GBK"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                int code = httpResponse.getStatusLine().getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    HttpEntity entity = httpResponse.getEntity();
                    String result = EntityUtils.toString(entity).trim();
                    Log.d(TAG_LISTLOGIC, "post数据请求服务器返回值200");
                    Log.d(TAG_LISTLOGIC, "post返回值:" + result);
                    if (requestCallBack != null) {
                        requestCallBack.onRequestSuccess(result);
                    }
                    return result;
                } else {
                    httpPost.abort();
                    if (requestCallBack != null) {
                        requestCallBack.onRequestError(
                                RequestCallBack.HTTPSTATUSERROR, "HTTP链接错误");
                    }

                }
            } else {
                httpPost.abort();

                if (requestCallBack != null) {
                    requestCallBack.onRequestError(
                            RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
            }
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.OUTOFMEMORYERROR, "内存溢出");
            }

            return null;
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
            if(requestCallBack!=null){
                requestCallBack.onCancel();
            }
        }
        return null;
    }

    /**
     * post请求获取服务端数据 编码为UTF-8
     */
    public static String responseFromServiceByPostUTF(String url,
                                                      HashMap<String, String> map) {
        if (url == null || url.equals("") || map == null) {
            return null;
        }
        Log.d(TAG_LISTLOGIC, "post数据请求地址:" + url);
        // 对密码进行加密处理

        HttpPost httpPost = null;
        URI encodedUri = null;
        try {
            encodedUri = new URI(url);
            httpPost = new HttpPost(encodedUri);
        } catch (URISyntaxException e) {
            // 清理一些空格
            String encodedUrl = url.replace(' ', '+');
            httpPost = new HttpPost(encodedUrl);
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DEF_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DEF_SOCKET_TIMEOUT);
        try {
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String value = null;
                if (entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue().toString();
                }
                Log.d(TAG_LISTLOGIC, "post数据请求参数" + key + "=" + value);
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(
                        key, value);
                nameValuePair.add(basicNameValuePair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                int code = httpResponse.getStatusLine().getStatusCode();
                if (code == HttpStatus.SC_OK) {
                    HttpEntity entity = httpResponse.getEntity();
                    String result = EntityUtils.toString(entity).trim();
                    Log.d(TAG_LISTLOGIC, "post数据请求服务器返回值200");
                    Log.d(TAG_LISTLOGIC, "post返回值:" + result);
                    return result;
                } else {
                    httpPost.abort();
                }
            } else {
                httpPost.abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }

        }
        return null;
    }

    /**
     * get请求获取服务端数据
     */
    public static String responseFromServiceByGet(String url,
                                                  HashMap<String, String> map, RequestCallBack requestCallBack) {
        if (url == null || url.equals("")) {
            return null;
        }
        if(requestCallBack!=null){
            requestCallBack.onRequestStart();
        }
        if (map != null) {
            StringBuilder sb = new StringBuilder(url);
            sb.append('?');
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String value = null;
                if (entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue().toString();
                }
                sb.append(key);
                sb.append('=');
                try {
                    Log.d(TAG_LISTLOGIC, "get获取数据的key:" + key);
                    Log.d(TAG_LISTLOGIC, "get获取数据的value:" + value);
                    value = URLEncoder.encode(value, HTTP.UTF_8);
                    Log.d(TAG_LISTLOGIC, "get投递编码后value:" + value);
                    sb.append(value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append('&');
            }
            sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"
            url = sb.toString();
        }
        Log.d(TAG_LISTLOGIC, "get请求地址" + url);

        if (requestCallBack != null) {
            requestCallBack.onRequestLoading();
        }
        HttpGet httpGet = null;
        URI encodedUri = null;
        InputStream is = null;
        try {
            encodedUri = new URI(url);
            httpGet = new HttpGet(encodedUri);
        } catch (URISyntaxException e) {
            // 清理一些空格
            String encodedUrl = url.replace(' ', '+');
            httpGet = new HttpGet(encodedUrl);
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, DEF_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                DEF_SOCKET_TIMEOUT);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                int httpCode = httpResponse.getStatusLine().getStatusCode();
                if (httpCode == HttpStatus.SC_OK) {
                    HttpEntity entity = httpResponse.getEntity();
                    Header header = httpResponse
                            .getFirstHeader("Content-Encoding");
                    if (header != null && header.getValue().equals("gzip")) {
                        Log.d(TAG_LISTLOGIC, "数据已做gzip压缩...");
                        // gzip压缩
                        byte[] resultstream = EntityUtils.toByteArray(entity);
                        resultstream = unGZip(resultstream);
                        String resultString = new String(resultstream, "UTF-8");
                        if (requestCallBack != null) {
                            requestCallBack.onRequestSuccess(resultString);
                        }
                        return resultString;
                    } else {
                        Log.d(TAG_LISTLOGIC, "数据无Gzip压缩...");
                        // 无压缩
                        is = entity.getContent();
                        if (is != null) {
                            String resultString = new String(
                                    getByteArrayFromInputstream(is), "utf-8");
                            if (requestCallBack != null) {
                                requestCallBack.onRequestSuccess(resultString);
                            }
                            return resultString;
                        }
                    }
                    Log.d(TAG_LISTLOGIC, "get请求服务器返回值200");
                } else {
                    httpGet.abort();
                    if (requestCallBack != null) {
                        requestCallBack.onRequestError(
                                RequestCallBack.HTTPSTATUSERROR, "HTTP链接错误");
                    }
                }
            } else {
                httpGet.abort();
                if (requestCallBack != null) {
                    requestCallBack.onRequestError(
                            RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.HTTPRESPONSEERROR, "数据获取异常");
            }
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (requestCallBack != null) {
                requestCallBack.onRequestError(
                        RequestCallBack.OUTOFMEMORYERROR, "内存溢出");
            }
            return null;
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
            if (requestCallBack != null) {
                requestCallBack.onCancel();
            }

        }
        return null;
    }

    /**
     * get请求获取服务端数据 不适用账号密码验证
     */
    public static String responseFromServiceByGetNo(String url,
                                                    HashMap<String, String> map) {
        if (url == null || url.equals("")) {
            return null;
        }
        if (map != null) {
            StringBuilder sb = new StringBuilder(url);
            sb.append('?');
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                String value = null;
                if (entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue().toString();
                }
                sb.append(key);
                sb.append('=');
                try {
                    Log.d(TAG_LISTLOGIC, "get获取数据的key:" + key);
                    Log.d(TAG_LISTLOGIC, "get获取数据的value:" + value);
                    value = URLEncoder.encode(value, HTTP.UTF_8);
                    Log.d(TAG_LISTLOGIC, "get投递编码后value:" + value);
                    sb.append(value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append('&');
            }
            sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"
            url = sb.toString();
        }
        Log.d(TAG_LISTLOGIC, "get请求地址" + url);
        HttpGet httpGet = null;
        URI encodedUri = null;
        InputStream is = null;
        try {
            encodedUri = new URI(url);
            httpGet = new HttpGet(encodedUri);

        } catch (URISyntaxException e) {
            // 清理一些空格
            String encodedUrl = url.replace(' ', '+');
            httpGet = new HttpGet(encodedUrl);
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 4000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                4000);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                int httpCode = httpResponse.getStatusLine().getStatusCode();
                if (httpCode == HttpStatus.SC_OK) {
                    HttpEntity entity = httpResponse.getEntity();
                    Header header = httpResponse
                            .getFirstHeader("Content-Encoding");
                    if (header != null && header.getValue().equals("gzip")) {
                        Log.d(TAG_LISTLOGIC, "数据已做gzip压缩...");
                        // gzip压缩
                        byte[] resultstream = EntityUtils.toByteArray(entity);
                        resultstream = unGZip(resultstream);
                        return new String(resultstream, "UTF-8");
                    } else {
                        Log.d(TAG_LISTLOGIC, "数据无Gzip压缩...");
                        // 无压缩
                        is = entity.getContent();
                        if (is != null) {
                            return new String(getByteArrayFromInputstream(is),
                                    "utf-8");
                        }
                    }
                    Log.d(TAG_LISTLOGIC, "get请求服务器返回值200");
                } else {
                    httpGet.abort();
                }
            } else {
                httpGet.abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }

        }
        return null;
    }

    /**
     * 判断网络线路状态
     */
    public static boolean checkNetworkIsGood(String url) {
        if (url == null || url.equals("")) {
            return false;
        }
        HttpGet httpGet = null;
        URI encodedUri = null;
        try {
            encodedUri = new URI(url);
            httpGet = new HttpGet(encodedUri);
        } catch (URISyntaxException e) {
            // 清理一些空格
            String encodedUrl = url.replace(' ', '+');
            httpGet = new HttpGet(encodedUrl);
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                5000);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                int uRC = httpResponse.getStatusLine().getStatusCode();
                if (uRC == HttpStatus.SC_OK) {
                    return true;
                } else {
                    httpGet.abort();
                }
            } else {
                httpGet.abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        }
        return false;
    }

    /**
     * 获取编码URI
     * @param url
     * @return
     */
    private static URI getEncodingURI(String url) {
        String encodedUrl = url;
        URI encodedUri = null;
        if (encodedUrl != null) {
            int count = 0, len = url.length();
            while (true && count < len) {
                try {
                    encodedUri = new URI(encodedUrl);
                    break;
                } catch (URISyntaxException e) {
                    Log.i(TAG_LISTLOGIC, "url编码异常");
                    e.printStackTrace();
                    count++;
                    int index = e.getIndex();
                    String enstr = "";
                    try {
                        enstr = URLEncoder.encode(
                                String.valueOf(encodedUrl.charAt(index)),
                                HTTP.UTF_8);
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    String newUrl = encodedUrl.substring(0, index);
                    newUrl += enstr;
                    if (index < newUrl.length() - 1) {
                        newUrl += encodedUrl.substring(index + 1);
                    }
                    encodedUrl = newUrl;
                }
            }
        }
        return encodedUri;
    }

    public static String getHttpData(String baseUrl) {
        return getHttpData(baseUrl, "GET", "", null);
    }

    public static String postHttpData(String baseUrl, String reqData) {
        return getHttpData(baseUrl, "POST", reqData, null);
    }

    public static String postHttpData(String baseUrl, String reqData,
                                      HashMap<String, String> propertys) {
        return getHttpData(baseUrl, "POST", reqData, propertys);
    }

    /**
     * 获取数据信息
     *
     * @return
     */
    public static String getHttpData(String baseUrl, String method,
                                     String reqData, HashMap<String, String> propertys) {
        String data = "", str;
        PrintWriter outWrite = null;
        InputStream inpStream = null;
        BufferedReader reader = null;

        HttpURLConnection urlConn = null;
        try {
            URL url = new URL(baseUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            // 启用gzip压缩
            urlConn.addRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConn.setRequestMethod(method);
            urlConn.setDoOutput(true);
            urlConn.setConnectTimeout(3000);

            if (propertys != null && !propertys.isEmpty()) {
                Iterator<Map.Entry<String, String>> props = propertys
                        .entrySet().iterator();
                Map.Entry<String, String> entry;
                while (props.hasNext()) {
                    entry = props.next();
                    urlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            outWrite = new PrintWriter(urlConn.getOutputStream());
            outWrite.print(reqData);
            outWrite.flush();

            urlConn.connect();

            // 获取数据流
            inpStream = urlConn.getInputStream();
            String encode = urlConn.getHeaderField("Content-Encoding");

            // 如果通过gzip
            if (encode != null && encode.indexOf("gzip") != -1) {
                Log.v(TAG_LISTLOGIC, "get data :" + encode);
                inpStream = new GZIPInputStream(inpStream);
            } else if (encode != null && encode.indexOf("deflate") != -1) {
                inpStream = new InflaterInputStream(inpStream);
            }

            reader = new BufferedReader(new InputStreamReader(inpStream));

            while ((str = reader.readLine()) != null) {
                data += str;
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null && urlConn != null) {
                try {
                    outWrite.close();
                    inpStream.close();
                    reader.close();
                    urlConn.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        Log.d(TAG_LISTLOGIC, "[Http data][" + baseUrl + "]:" + data);
        return data;
    }

    /**
     * 获取Image图片信息
     *
     * @return bitmap
     */
    public static Bitmap getBitmapData(String imgUrl) {
        Bitmap bmp = null;
        Log.d(TAG_LISTLOGIC, "get imgage:" + imgUrl);
        InputStream inpStream = null;
        try {
            HttpGet http = new HttpGet(imgUrl);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = (HttpResponse) client.execute(http);
            HttpEntity httpEntity = response.getEntity();
            BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(
                    httpEntity);

            // 获取数据流
            inpStream = bufferedHttpEntity.getContent();
            bmp = BitmapFactory.decodeStream(inpStream);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (inpStream != null) {
                try {
                    inpStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return bmp;
    }

    /**
     * 获取url的InputStream
     *
     * @param urlStr
     * @return
     */
    public static InputStream getInputStream(String urlStr) {
        Log.d(TAG_LISTLOGIC, "get http input:" + urlStr);
        InputStream inpStream = null;
        try {
            HttpGet http = new HttpGet(urlStr);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = (HttpResponse) client.execute(http);
            HttpEntity httpEntity = response.getEntity();
            BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(
                    httpEntity);

            // 获取数据流
            inpStream = bufferedHttpEntity.getContent();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (inpStream != null) {
                try {
                    inpStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return inpStream;
    }

    /**
     * GZip解压
     *
     * @param bContent
     * @return
     */
    public static byte[] unGZip(byte[] bContent) {
        byte[] data = new byte[1024];
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bContent);
            GZIPInputStream pIn = new GZIPInputStream(in);
            DataInputStream objIn = new DataInputStream(pIn);
            int len = 0;
            int count = 0;
            while ((count = objIn.read(data, len, len + 1024)) != -1) {
                len = len + count;
            }
            byte[] trueData = new byte[len];
            System.arraycopy(data, 0, trueData, 0, len);
            objIn.close();
            pIn.close();
            in.close();

            return trueData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
