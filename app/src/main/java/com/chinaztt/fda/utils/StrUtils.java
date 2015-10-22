package com.chinaztt.fda.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chinaztt.fda.application.FDApplication;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 当前类注释:统一工具类 包括Android平台开发中需要用到的字符串判断,编码判断转化,字符字符串字节转换,
 * 系统各种信息的获取等等(屏幕分辨率,设备号,imei,imsi,系统版本,操作系统,cpu,IP,时间处理,域名,平台等等)
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.utils
 * 作者：江清清 on 15/10/22 09:40
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
public class StrUtils {
    // bt字节参考量
    private static final float SIZE_BT = 1024L;
    // KB字节参考量
    private static final float SIZE_KB = SIZE_BT * 1024.0f;
    // MB字节参考量
    private static final float SIZE_MB = SIZE_KB * 1024.0f;
    // GB字节参考量
    private static final float SIZE_GB = SIZE_MB * 1024.0f;
    // TB字节参考量
    // private static final float SIZE_TB=SIZE_GB * 1024.0f;
    // BigDecimal四舍五入精度为2
    private static final int SACLE = 2;
    // 缓冲的大小
    private static final int BUFF_SIZE = 1024;
    public static final String orange = "#EF8100";
    public static final String orange_dark = "#EA8918";
    public static final String green = "#1aa607";
    public static final String blue = "#770000ff";

    // 判断是否转int类型
    public static boolean isFormatInteger(String str) {
        if (str != null && !str.equals("") && isGigital(str)) {
            return true;
        }
        return false;
    }

    /**
     * @param str
     *            字符串
     * @return 如果字符串是数字返回ture，反正false
     */
    public static boolean isGigital(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isGigital = pattern.matcher(str);
        if (!isGigital.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断字符串是不是float型
     */
    public static boolean isFloat(String str) {
        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        Matcher isFloat = pattern.matcher(str);
        if (isFloat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param url
     *            保存文件的文字
     * @return 文件名
     */
    public static String getFileName(String url) {
        String fileName = null;
        if (url != null && url.contains("/")) {
            String[] data = url.split("/");
            fileName = data[data.length - 1];
        }
        return fileName;
    }

    /**
     * @param style
     *            类型
     * @return 用逗号，或者分号截取字符串前两个(这个方法用于类型的字符串截取)
     */
    public static String get2InString(String style) {
        Pattern pattern = Pattern.compile("[,;]");
        String[] actors = pattern.split(style);
        StringBuffer buffer = new StringBuffer();
        if (actors.length <= 1) {
            buffer.append(actors[0]);
        } else if (actors.length == 2) {
            buffer.append(actors[0]);
            buffer.append(",");
            buffer.append(actors[1]);
        } else if (actors.length >= 3) {
            buffer.append(actors[0]);
            buffer.append(",");
            buffer.append(actors[1]);
            buffer.append(",");
            buffer.append(actors[2]);
        }
        return buffer.toString();
    }

    /**
     * @param str
     *            字符串
     * @return 字符串转化MD5
     */
    public static String calcMd5(String str) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(str.getBytes());
            return toHexString(algorithm.digest(), "");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param file
     *            文件
     * @return 文件转换MD5
     */
    public static String calcMd5(File file) {
        FileInputStream in = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer;
            byteBuffer = ch
                    .map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            algorithm.update(byteBuffer);
            return toHexString(algorithm.digest(), "");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 上面的辅助类
    public static String toHexString(byte[] bytes, String separator) {
        StringBuilder hexString = new StringBuilder();
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        for (byte b : bytes) {
            hexString.append(hexDigits[b >> 4 & 0xf]);
            hexString.append(hexDigits[b & 0xf]);
        }
        return hexString.toString();
    }

    // 去掉字符串中的空格、回车、换行符、制表符
    public static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            String after = m.replaceAll("");
            return after;
        } else {
            return null;
        }
    }

    // 换域名
    public static String replaceRealmName(String newRealmName,
                                          String oldRealmName, String source) {
        if (oldRealmName == null) {
            return source;
        }
        StringBuffer bf = new StringBuffer("");
        int index = -1;
        while ((index = source.indexOf(oldRealmName)) != -1) {
            bf.append(source.substring(0, index) + newRealmName);
            source = source.substring(index + oldRealmName.length());
            index = source.indexOf(oldRealmName);
        }
        bf.append(source);
        return bf.toString();
    }

    // 详情页面综艺的时间
    public static String getDetailsDescriptionTime(String tm) {
        Long timestamp = Long.parseLong(tm) * 1000;
        String date = new SimpleDateFormat("yyyy年MM月")
                .format(new Date(timestamp));
        return date;
    }

    // 获取新闻的时间
    public static String getDescriptionTime(String tm) {
        Long timestamp = Long.parseLong(tm) * 1000;
        String date = new SimpleDateFormat("yyyy年MM月dd日")
                .format(new Date(timestamp));
        return date;
    }

    // 获取今天的时间
    public static String getTodayTime() {
        long todayDate = new Date().getTime();
        String date = new SimpleDateFormat("yyyy年MM月dd日")
                .format(new Date(todayDate));
        return date;
    }

    // 获取昨天的时间
    public static String getYesterdayTime() {
        long todayDate = new Date().getTime();
        long yesterdayDate = todayDate - 24 * 60 * 60 * 1000;
        String date = new SimpleDateFormat("yyyy年MM月dd日")
                .format(new Date(yesterdayDate));
        return date;
    }

    // 获取旧分辨率
    public static String getOldLcd(Activity activity) {
        StringBuffer buffer = new StringBuffer();
        int density = 0;
        int widthPixels = 0;
        int heightPixels = 0;
        DisplayMetrics dm = new DisplayMetrics();
        if (dm != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            density = dm.densityDpi;
            widthPixels = dm.widthPixels;
            heightPixels = dm.heightPixels;
        }
        buffer.append(heightPixels);
        buffer.append("*");
        buffer.append(widthPixels);
        buffer.append(",");
        buffer.append(density);
        buffer.append("dpi");
        return buffer.toString();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    // 获取屏幕长宽
    public static int[] getScreenSizeArray(Activity activity) {
        int[] size = new int[2];
        int widthPixels = 0;
        int heightPixels = 0;
        DisplayMetrics dm = new DisplayMetrics();
        if (dm != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            widthPixels = dm.widthPixels;
            heightPixels = dm.heightPixels;
            size[0] = widthPixels;
            size[1] = heightPixels;
        }
        return size;
    }

    // // 获取opudid
    // public static String getOpenUdid() {
    // OpenUDID_manager.sync(PPStvApp.getPPSInstance());
    // String openUDID = null;
    // if (OpenUDID_manager.isInitialized()) {
    // openUDID = OpenUDID_manager.getOpenUDID();
    // }
    // return openUDID;
    // }

    // 获取新分辨率
    public static String getNewLcd(Activity activity) {
        StringBuffer buffer = new StringBuffer();
        int widthPixels = 0;
        int heightPixels = 0;
        DisplayMetrics dm = new DisplayMetrics();
        if (dm != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            widthPixels = dm.widthPixels;
            heightPixels = dm.heightPixels;
        }
        buffer.append(heightPixels);
        buffer.append("*");
        buffer.append(widthPixels);
        return buffer.toString();
    }

    // 获取手机屏幕宽高乘积
    public static int getScreenSize(Activity activity) {
        int widthPixels = 0;
        int heightPixels = 0;
        DisplayMetrics dm = new DisplayMetrics();
        if (dm != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            widthPixels = dm.widthPixels;
            heightPixels = dm.heightPixels;
        }
        return widthPixels * heightPixels;
    }

    // 获取当前时间戳
    public static String getTimesTamp() {
        long timestamp = System.currentTimeMillis();
        return String.valueOf(timestamp);
    }

    // 客户端版本版本号
    public static String getVersionName(Activity activity) {
        String version = null;
        try {
            version = String.valueOf(activity.getPackageManager()
                    .getPackageInfo(activity.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    // 屏幕密度
    public static float getdensity(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        return density;
    }

    // 把字符串转换成UTF-8的格式
    public static String stringToUTF(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 把字符串转换成GBK的格式
    public static String stringToGBK(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLDecoder.decode(str, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 把字符串编码成GBK的格式
    public static String stringUTF8ToGBK(String str) {
        if (str != null && !str.equals("")) {
            try {
                return URLEncoder.encode(str, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 获取系统时间
    public static String getSystemTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(date);
        return result;

    }

    // 把文件转变字符串
    public static String file2String(File file, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file));
            } else {
                reader = new InputStreamReader(new FileInputStream(file),
                        encoding);
            }
            // 将输入流写入输出流
            char[] buffer = new char[BUFF_SIZE];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return writer.toString();
    }

    // 把inputstream转换为字符串（方法一）
    public static String getStr1FromInputstream(InputStream input) {
        String result = null;
        int i = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while ((i = input.read()) != -1) {
                baos.write(i);
            }
            result = baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 把inputstream转换为字符串（方法二）
    public static String getStr2FromInputstream(InputStream input) {
        int i = -1;
        String result = null;
        byte[] b = new byte[1024];
        StringBuffer sb = new StringBuffer();
        try {
            while ((i = input.read(b)) != -1) {
                sb.append(new String(b, 0, i));
            }
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 获取手机型号
    public static String getLocalModel() {
        String model = android.os.Build.MODEL;
        if (model == null) {
            model = "";
        }
        return model;
    }

    // 获取手机系统版本
    public static String getLocalSystemVersion() {
        String version = android.os.Build.VERSION.RELEASE;
        if (version == null) {
            version = "";
        }
        return version;

    }

    // 获取手机厂商
    public static String getLocalManufacturer() {
        String manufacturer = android.os.Build.MANUFACTURER;
        if (manufacturer == null) {
            manufacturer = "";
        }
        return manufacturer;
    }

    // 获取ip地区
    public static String getIpCountry() {
        String ipCountry = "460";
        try {
            TelephonyManager mTelephonyManager = (TelephonyManager) FDApplication
                    .getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephonyManager != null) {
                String IMSI = mTelephonyManager.getSubscriberId();
                if (IMSI != null && !IMSI.equals("") && IMSI.length() >= 3) {
                    // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
                    ipCountry = IMSI.substring(0, 3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipCountry;
    }

    // 获取ip运营商
    public static String getIpName() {
        String ipName = null;
        try {
            TelephonyManager mTelephonyManager = (TelephonyManager) FDApplication
                    .getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephonyManager != null) {
                String IMSI = mTelephonyManager.getSubscriberId();
                if (IMSI != null && !IMSI.equals("") && IMSI.length() >= 5) {
                    // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
                    ipName = IMSI.substring(3, 5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipName;
    }

    // 获取ip基站
    public static String getIpBaseStation() {
        TelephonyManager telMgr = (TelephonyManager) FDApplication
                .getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        int cid = 0;
        int lac = 0;
        try {
            if (telMgr != null) {
                GsmCellLocation gc = (GsmCellLocation) telMgr.getCellLocation();
                if (null == gc) {
                    return "0_0";
                }
                cid = gc.getCid();
                lac = gc.getLac();
            }
        } catch (Exception e) {
            if (telMgr != null) {
                CdmaCellLocation location = (CdmaCellLocation) telMgr
                        .getCellLocation();
                if (null == location) {
                    return "0_0";
                }
                lac = location.getNetworkId();
                cid = location.getBaseStationId();
                cid /= 16;
            }
        }
        return lac + "_" + cid;
    }

    // 获取包名
    public static String getPackageName(Activity activity) {

        String packageName = null;
        try {
            packageName = String.valueOf(activity.getPackageManager()
                    .getPackageInfo(activity.getPackageName(), 0).packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }

    // 获取Android_id
    public static String getAndroidId() {

        return Settings.Secure.getString(FDApplication.getInstance()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    // 判断设备是否越狱
    public static boolean getIsJailBreak() {
        for (String str : new String[] { "/system/bin/", "/system/xbin/",
                "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/" }) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    // 获取手机硬件属性
    public static String[] getTotalHardwareMessage() {
        String result[] = new String[3];
        String str1 = "/proc/cpuinfo";
        String str2 = null;
        FileReader localFileReader = null;
        BufferedReader localBufferedReader = null;
        try {
            localFileReader = new FileReader(str1);
            localBufferedReader = new BufferedReader(localFileReader);
            while ((str2 = localBufferedReader.readLine()) != null) {
                if (str2.contains("Processor")) {
                    if (str2.contains(":")) {
                        String[] arrayOfString = str2.split(":");
                        if (arrayOfString.length == 2) {
                            result[0] = arrayOfString[1];
                            if (result[0].length() > 32 && result[0] != null) {
                                result[0] = result[0].substring(0, 32);
                            }
                        }
                    }
                }
                if (str2.contains("Features")) {
                    if (str2.contains(":")) {
                        String[] arrayOfString = str2.split(":");
                        if (arrayOfString.length == 2) {
                            result[1] = arrayOfString[1];
                            if (result[1].length() > 50 && result[1] != null) {
                                result[1] = result[1].substring(0, 50);
                            }
                        }
                    }
                }
                if (str2.contains("Hardware")) {
                    if (str2.contains(":")) {
                        String[] arrayOfString = str2.split(":");
                        if (arrayOfString.length == 2) {
                            result[2] = arrayOfString[1];
                            if (result[2].length() > 32 && result[2] != null) {
                                result[2] = result[2].substring(0, 32);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (localBufferedReader != null) {
                try {
                    localBufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (localFileReader != null) {
                try {
                    localFileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 获取用户的IPd
    public static int getIpAddress() {
        int ipAddress = 0;
        WifiManager wifiManager = (WifiManager) FDApplication
                .getInstance().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo == null || wifiInfo.equals("")) {
            return ipAddress;
        } else {
            ipAddress = wifiInfo.getIpAddress();
        }
        return ipAddress;
    }

    // 获取用户设备IP
    public static String getUserIp() {
        int ipAddress = getIpAddress();
        return String.format("%d.%d.%d.%d", (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
    }

    // 获取时区
    public static String getTimeArea() {
        return String.valueOf(
                TimeZone.getDefault().getOffset(new Date().getTime() / 1000))
                .toString();
    }

    // 获取useragent
    public static String getUserAgent(Context context) {
        String userAgent = null;
        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            userAgent = settings.getUserAgentString();
        }
        return userAgent;
    }

    public static String getDetailsPromptMessage(int playPosition) {
        String message = "上次播放到：";
        playPosition /= 1000;
        int minute = playPosition / 60;
        int hour = minute / 60;
        int second = playPosition % 60;
        minute %= 60;
        if (hour == 0) {
            message = message + minute + "分" + second + "秒";
            return message;
        } else {
            message = message + hour + "小时" + minute + "分" + second + "秒";
            return message;
        }
    }

    public static String getHistoryPromptMessage(int playPosition,
                                                 int tvTotalTimes) {
        if (playPosition >= (tvTotalTimes - 1000 * 3)) {
            return "本片已播放完(100%)";
        } else if (playPosition >= 0 && (playPosition <= 1000 * 60)) {
            return "观看时间小于1分钟";
        } else {
            playPosition /= 1000;
            int minute = playPosition / 60;
            int hour = minute / 60;
            int second = playPosition % 60;
            minute %= 60;
            if (hour == 0) {
                String message = minute + "分" + second + "秒";
                return "观看至" + message;
            } else {
                String message = hour + "小时" + minute + "分" + second + "秒";
                return "观看至" + message;
            }
        }
    }

    public static String getDetailsPlayDuration(int playPosition,
                                                int tvTotalTimes) {
        float pos_percent = 0;
        String pospercent = "(0%)";
        String message;
        if ((playPosition != 0) && (tvTotalTimes != 0)) {
            pos_percent = ((float) playPosition / tvTotalTimes * 100);
            pospercent = "(" + String.format("%.1f", pos_percent) + "%" + ")";
        }
        if (playPosition >= (tvTotalTimes - 1000 * 3)) {
            message = "本片已播放完(100%)";
            return message;
        } else if (playPosition >= 0 && (playPosition <= 1000 * 60)) {
            message = "观看时间小于1分钟" + pospercent;
            return message;
        } else {
            playPosition /= 1000;
            int minute = playPosition / 60;
            int hour = minute / 60;
            int second = playPosition % 60;
            minute %= 60;
            if (hour == 0) {
                message = minute + "分" + second + "秒" + pospercent;
                return message;
            } else {
                message = hour + "小时" + minute + "分" + second + "秒"
                        + pospercent;
                return message;
            }
        }
    }

    // 计算UGC时长
    public static String getUgcDuration(int time) {
        StringBuffer buffer = new StringBuffer();
        int second = time / 1000;
        if (second > 60) {
            buffer.append(second / 60);
            buffer.append("分");
            if (second % 60 > 0) {
                buffer.append(second % 60);
                buffer.append("秒");
            }
        } else {
            buffer.append(second);
            buffer.append("秒");
        }
        return buffer.toString();
    }

    // 会员登录处理返回的用户名
    public static String convertAccountNmae(String string) {
        String result = "";
        try {
            result = new String(gbk2utf8(string), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] gbk2utf8(String string) {
        char c[] = string.toCharArray();
        byte[] fullByte = new byte[3 * c.length];
        for (int i = 0; i < c.length; i++) {
            int m = (int) c[i];
            String word = Integer.toBinaryString(m);

            StringBuffer sb = new StringBuffer();
            int len = 16 - word.length();
            for (int j = 0; j < len; j++) {
                sb.append("0");
            }
            sb.append(word);
            sb.insert(0, "1110");
            sb.insert(8, "10");
            sb.insert(16, "10");
            String s1 = sb.substring(0, 8);
            String s2 = sb.substring(8, 16);
            String s3 = sb.substring(16);
            byte b0 = Integer.valueOf(s1, 2).byteValue();
            byte b1 = Integer.valueOf(s2, 2).byteValue();
            byte b2 = Integer.valueOf(s3, 2).byteValue();
            byte[] bf = new byte[3];
            bf[0] = b0;
            fullByte[i * 3] = bf[0];
            bf[1] = b1;
            fullByte[i * 3 + 1] = bf[1];
            bf[2] = b2;
            fullByte[i * 3 + 2] = bf[2];

        }
        return fullByte;
    }

    // 通过下载地址得到fid
    public static String transPPSUrl2fid(String origUrl) {
        String toUrl = origUrl;
        String fid = "";
        if (origUrl == null) {
            return null;
        }
        if (toUrl.contains("pps://") || toUrl.contains("tvod://")) {
            int index = toUrl.lastIndexOf('?');
            if (index > 0 && index < toUrl.length() - 1) {
                fid = toUrl.substring(index + 1, toUrl.length());
                if (fid.contains("fid=") && fid.length() == 36/* fid=xxx */) {
                    index = fid.lastIndexOf('=');
                    if (index == 3) {
                        fid = fid.substring(4, 36);
                        return fid;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取有颜色的文字，这里默认为橙色
     *
     * @param str
     *            文字内容
     * @return
     */
    public static String getHtmlColorString(String color, String str) {
        StringBuffer sb = new StringBuffer();
        sb.append("<font color='" + color + "'>");
        sb.append(str);
        sb.append("</font>");
        return sb.toString();
    }

    /**
     * 根据分数不同返回不同颜色的文字
     *
     * @return
     */
    public static String getRatingColorString(String vote) {
        StringBuffer sb = new StringBuffer();
        if (vote == null || vote.equals("") || !StrUtils.isFloat(vote)) {
            vote = "0";
        }
        int score = (int) Float.parseFloat(vote);
        switch (score) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append("<font color='" + "#f3ad07" + "'>");
                break;
            case 7:
                sb.append("<font color='" + "#ef8203" + "'>");
                break;
            case 8:
                sb.append("<font color='" + "#ff7510" + "'>");
                break;
            default:
                sb.append("<font color='" + "#fe4223" + "'>");
                break;
        }
        sb.append(vote);
        sb.append("</font>");
        return sb.toString();
    }

    public static String getNewDetailsFn(String str, String name) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(str.substring(0, 2));
        buffer.append("年");
        buffer.append(str.substring(2, 4));
        buffer.append("月");
        buffer.append(str.substring(4, 6));
        buffer.append("日");
        buffer.append("-");
        buffer.append(name);
        return buffer.toString();
    }

    // 获取BP设置重试地址
    public static String getBpSetRetryUrl(String retryName, String sourceStr) {
        String retryUrl = StrUtils.replaceRealmName(retryName,
                "bip.ppstream.com", sourceStr);
        return retryUrl;
    }

    // 获取列表重试地址
    public static String getRetryUrl(String retryName, String sourceStr) {
        String retryUrl = StrUtils.replaceRealmName(retryName,
                "list1.ppstream.com", sourceStr);
        return retryUrl;
    }

    // 获取BP播放重试地址
    public static String getBpPlayRetryUrl(String retryName, String sourceStr) {
        String retryUrl = StrUtils.replaceRealmName(retryName, "dp.ugc.pps.tv",
                sourceStr);
        return retryUrl;
    }

    // 根据传入的字节数，返回对应的字符串
    public static String getReadableSize(long length) {
        if (length >= 0 && length < SIZE_BT) {
            // Math.round四舍五入
            return (double) (Math.round(length * 10) / 10.0) + "B";
        } else if (length >= SIZE_BT && length < SIZE_KB) {
            // //length/SIZE_BT+"KB";
            return (double) (Math.round((length / SIZE_BT) * 10) / 10.0) + "KB";
        } else if (length >= SIZE_KB && length < SIZE_MB) {
            // length/SIZE_KB+"MB";
            return (double) (Math.round((length / SIZE_KB) * 10) / 10.0) + "MB";
        } else if (length >= SIZE_MB && length < SIZE_GB) {
            // bigdecimal这个对象进行数据相互除
            BigDecimal longs = new BigDecimal(Double.valueOf(length + "")
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            return result + "GB";
        } else {
            // bigdecimal这个对象进行数据相互除
            BigDecimal longs = new BigDecimal(Double.valueOf(length + "")
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            return result + "TB";
        }
    }

    // 从url获取投递地址
    public static String getDeliverUrlFromUrl(String url) {
        if (url != null && !url.equals("")) {
            String[] array1 = url.split("[?]");
            if (array1.length >= 2) {
                return array1[0];
            }
        }
        return null;
    }

    // 从url获取投递参数
    public static HashMap<String, String> getDeliverMapFromUrl(String url) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (url != null && !url.equals("")) {
            String[] array1 = url.split("[?]");
            if (array1.length >= 2) {
                String content = array1[1];
                String[] array2 = content.split("[&]");
                for (int j = 0; j < array2.length; j++) {
                    String str2 = array2[j];
                    String[] array3 = str2.split("[=]");
                    String key = null;
                    String value = null;
                    for (int z = 0; z < array3.length; z++) {
                        if (z % 2 == 0) {
                            key = array3[z];
                        } else {
                            value = array3[z];
                            map.put(key, value);
                        }
                    }
                }
            }
        }
        return map;
    }

    // 获取string转int
    public static int string2Int(String str) {
        if (str != null && !str.equals("") && isGigital(str)) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    // 获取cup数目
    public static int getNumCores() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return files.length;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * @author jiangqq
     * 获取当前的网络状态  -1：没有网络  1：WIFI网络 2：wap网络3：net网络
     * @param context
     * @return
     */
    public static final int CMNET=3;
    public static final int CMWAP=2;
    public static final int WIFI=1;
    public static int getAPNType(Context context){
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo==null){
            return netType;
        }
        int nType = networkInfo.getType();
        if(nType==ConnectivityManager.TYPE_MOBILE){
            Log.e("networkInfo.getExtraInfo()", "networkInfo.getExtraInfo() is "+networkInfo.getExtraInfo());
            if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                netType = CMNET;
            }
            else{
                netType = CMWAP;
            }
        }
        else if(nType==ConnectivityManager.TYPE_WIFI){
            netType = WIFI;
        }
        return netType;
    }
}
