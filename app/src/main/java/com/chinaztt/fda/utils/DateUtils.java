package com.chinaztt.fda.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期操作工具类，主要实现了日期的常用操作。
 * <p>
 * 在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.
 * <p>
 * 格式的意义如下： 日期和时间模式 <br>
 * 日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
 * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
 * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
 * <p>
 * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
 * <table>
 * <tr>
 * <td>字母</td>
 * <td>日期或时间元素</td>
 * <td>表示</td>
 * <td>示例</td>
 * <td></tr>
 * <tr>
 * <td>G</td>
 * <td>Era</td>
 * <td>标志符</td>
 * <td>Text</td>
 * <td>AD</td>
 * <td></tr>
 * <tr>
 * <td>y</td>
 * <td>年</td>
 * <td>Year</td>
 * <td>1996;</td>
 * <td>96</td>
 * <td></tr>
 * <tr>
 * <td>M</td>
 * <td>年中的月份</td>
 * <td>Month</td>
 * <td>July;</td>
 * <td>Jul;</td>
 * <td>07</tr>
 * <tr>
 * <td>w</td>
 * <td>年中的周数</td>
 * <td>Number</td>
 * <td>27</td>
 * <td></tr>
 * <tr>
 * <td>W</td>
 * <td>月份中的周数</td>
 * <td>Number</td>
 * <td>2</td>
 * <td></tr>
 * <tr>
 * <td>D</td>
 * <td>年中的天数</td>
 * <td>Number</td>
 * <td>189</td>
 * <td></tr>
 * <tr>
 * <td>d</td>
 * <td>月份中的天数</td>
 * <td>Number</td>
 * <td>10</td>
 * <td></tr>
 * <tr>
 * <td>F</td>
 * <td>月份中的星期</td>
 * <td>Number</td>
 * <td>2</td>
 * <td></tr>
 * <tr>
 * <td>E</td>
 * <td>星期中的天数</td>
 * <td>Text</td>
 * <td>Tuesday;</td>
 * <td>Tue</tr>
 * <tr>
 * <td>a</td>
 * <td>Am/pm</td>
 * <td>标记</td>
 * <td>Text</td>
 * <td>PM</td>
 * <td></tr>
 * <tr>
 * <td>H</td>
 * <td>一天中的小时数（0-23）</td>
 * <td>Number</td>
 * <td>0</tr>
 * <tr>
 * <td>k</td>
 * <td>一天中的小时数（1-24）</td>
 * <td>Number</td>
 * <td>24</td>
 * <td></tr>
 * <tr>
 * <td>K</td>
 * <td>am/pm</td>
 * <td>中的小时数（0-11）</td>
 * <td>Number</td>
 * <td>0</td>
 * <td></tr>
 * <tr>
 * <td>h</td>
 * <td>am/pm</td>
 * <td>中的小时数（1-12）</td>
 * <td>Number</td>
 * <td>12</td>
 * <td></tr>
 * <tr>
 * <td>m</td>
 * <td>小时中的分钟数</td>
 * <td>Number</td>
 * <td>30</td>
 * <td></tr>
 * <tr>
 * <td>s</td>
 * <td>分钟中的秒数</td>
 * <td>Number</td>
 * <td>55</td>
 * <td></tr>
 * <tr>
 * <td>S</td>
 * <td>毫秒数</td>
 * <td>Number</td>
 * <td>978</td>
 * <td></tr>
 * <tr>
 * <td>z</td>
 * <td>时区</td>
 * <td>General</td>
 * <td>time</td>
 * <td>zone</td>
 * <td>Pacific</td>
 * <td>Standard</td>
 * <td>Time;</td>
 * <td>PST;</td>
 * <td>GMT-08:00</tr>
 * <tr>
 * <td>Z</td>
 * <td>时区</td>
 * <td>RFC</td>
 * <td>822</td>
 * <td>time</td>
 * <td>zone</td>
 * <td>-0800</td>
 * <td></tr>
 * </table>
 * [注] 当前时间日期处理工具类，本人是通过聚灵工作室提供的工具类，在此基础上面又新增若干个工具方法
 * 同时特别感谢聚灵工作室的开源奉献精神
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.utils
 * 作者：江清清 on 15/10/22 09:30
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
public class DateUtils implements  Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -3098985139095632110L;

    private DateUtils() {
    }

    /**
     * 格式化日期显示格式yyyy-MM-dd
     *
     * @param sdate
     *            原始日期格式
     * @return yyyy-MM-dd格式化后的日期显示
     */
    public static String dateFormat(String sdate) {
        return dateFormat(sdate, "yyyy-MM-dd");
    }

    /**
     * 格式化日期显示格式
     *
     * @param sdate
     *            原始日期格式
     * @param format
     *            格式化后日期格式
     * @return 格式化后的日期显示
     */
    public static String dateFormat(String sdate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        java.sql.Date date = java.sql.Date.valueOf(sdate);
        String dateString = formatter.format(date);

        return dateString;
    }

    /**
     * 求两个日期相差天数
     *
     * @param sd
     *            起始日期，格式yyyy-MM-dd
     * @param ed
     *            终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String sd, String ed) {
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date
                .valueOf(sd)).getTime())
                / (3600 * 24 * 1000);
    }

    /**
     * 起始年月yyyy-MM与终止月yyyy-MM之间跨度的月数
     *
     * @return int
     */
    public static int getInterval(String beginMonth, String endMonth) {
        int intBeginYear = Integer.parseInt(beginMonth.substring(0, 4));
        int intBeginMonth = Integer.parseInt(beginMonth.substring(beginMonth
                .indexOf("-") + 1));
        int intEndYear = Integer.parseInt(endMonth.substring(0, 4));
        int intEndMonth = Integer.parseInt(endMonth.substring(endMonth
                .indexOf("-") + 1));

        return ((intEndYear - intBeginYear) * 12)
                + (intEndMonth - intBeginMonth) + 1;
    }

    public static Date getDate(String sDate, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        ParsePosition pos = new ParsePosition(0);

        return fmt.parse(sDate, pos);
    }

    /**
     * 取得当前日期的年份，以yyyy格式返回.
     *
     * @return 当年 yyyy
     */
    public static String getCurrentYear() {
        return getFormatCurrentTime("yyyy");
    }

    /**
     * 自动返回上一年。例如当前年份是2007年，那么就自动返回2006
     *
     * @return 返回结果的格式为 yyyy
     */
    public static String getBeforeYear() {
        String currentYear = getFormatCurrentTime("yyyy");
        int beforeYear = Integer.parseInt(currentYear) - 1;
        return "" + beforeYear;
    }

    /**
     * 取得当前日期的月份，以MM格式返回.
     *
     * @return 当前月份 MM
     */
    public static String getCurrentMonth() {
        return getFormatCurrentTime("MM");
    }

    /**
     * 取得当前日期的天数，以格式"dd"返回.
     *
     * @return 当前月中的某天dd
     */
    public static String getCurrentDay() {
        return getFormatCurrentTime("dd");
    }

    /**
     * 返回当前时间字符串。
     * <p>
     * 格式：yyyy-MM-dd
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getCurrentDate() {
        return getFormatDateTime(new Date(), "yyyy-MM-dd");
    }

    /**
     * 返回给定时间字符串。
     * <p>
     * 格式：yyyy-MM-dd
     *
     * @param date
     *            日期
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDate(Date date) {
        return getFormatDateTime(date, "yyyy-MM-dd");
    }

    /**
     * 根据制定的格式，返回日期字符串。例如2007-05-05
     *
     * @param format
     *            "yyyy-MM-dd" 或者 "yyyy/MM/dd"
     * @return 指定格式的日期字符串。
     */
    public static String getFormatDate(String format) {
        return getFormatDateTime(new Date(), format);
    }

    /**
     * 返回当前时间字符串。
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getCurrentTime() {
        return getFormatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回给定时间字符串。
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     *            日期
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatTime(Date date) {
        return getFormatDateTime(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据给定的格式，返回时间字符串。
     * <p>
     * 格式参照类描绘中说明.
     *
     * @param format
     *            日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatCurrentTime(String format) {
        return getFormatDateTime(new Date(), format);
    }

    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串<br>
     *
     * @param date
     *            指定的日期
     * @param format
     *            日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 取得指定年月日的日期对象.
     *
     * @param year
     *            年
     * @param month
     *            月注意是从1到12
     * @param day
     *            日
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(int year, int month, int day) {
        Calendar c = new GregorianCalendar();
        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * 取得指定分隔符分割的年月日的日期对象.
     *
     * @param args
     *            格式为"yyyy-MM-dd"
     * @param split
     *            时间格式的间隔符，例如“-”，“/”
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(String args, String split) {
        String[] temp = args.split(split);
        int year = new Integer(temp[0]).intValue();
        int month = new Integer(temp[1]).intValue();
        int day = new Integer(temp[2]).intValue();
        return getDateObj(year, month, day);
    }

    /**
     * 取得给定字符串描述的日期对象，描述模式采用pattern指定的格式.
     *
     * @param dateStr
     *            日期描述
     * @param pattern
     *            日期模式
     * @return 给定字符串描述的日期对象。
     */
    public static Date getDateFromString(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date resDate = null;
        try {
            resDate = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }

    /**
     * 取得当前Date对象.
     *
     * @return Date 当前Date对象.
     */
    public static Date getDateObj() {
        Calendar c = new GregorianCalendar();
        return c.getTime();
    }

    /**
     *
     * @return 当前月份有多少天；
     */
    public static int getDaysOfCurMonth() {
        int curyear = new Integer(getCurrentYear()).intValue(); // 当前年份
        int curMonth = new Integer(getCurrentMonth()).intValue();// 当前月份
        int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 根据指定的年月 返回指定月份（yyyy-MM）有多少天。
     *
     * @param time yyyy-MM
     * @return 天数，指定月份的天数。
     */
    public static int getDaysOfCurMonth(final String time) {
        String[] timeArray = time.split("-");
        int curyear = new Integer(timeArray[0]).intValue(); // 当前年份
        int curMonth = new Integer(timeArray[1]).intValue();// 当前月份
        int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        if (curMonth == 12) {
            return mArray[0];
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 返回指定为年度为year月度为month的月份内，第weekOfMonth个星期的第dayOfWeek天。<br>
     * 00 00 00 01 02 03 04 <br>
     * 05 06 07 08 09 10 11<br>
     * 12 13 14 15 16 17 18<br>
     * 19 20 21 22 23 24 25<br>
     * 26 27 28 29 30 31 <br>
     * 2006年的第一个周的1到7天为：05 06 07 01 02 03 04 <br>
     * 2006年的第二个周的1到7天为：12 13 14 08 09 10 11 <br>
     * 2006年的第三个周的1到7天为：19 20 21 15 16 17 18 <br>
     * 2006年的第四个周的1到7天为：26 27 28 22 23 24 25 <br>
     * 2006年的第五个周的1到7天为：02 03 04 29 30 31 01 。本月没有就自动转到下个月了。
     *
     * @param year
     *            形式为yyyy <br>
     * @param month
     *            形式为MM,参数值在[1-12]。<br>
     * @param weekOfMonth
     *            在[1-6],因为一个月最多有6个周。<br>
     * @param dayOfWeek
     *            数字在1到7之间，包括1和7。1表示星期天，7表示星期六<br>
     *            -6为星期日-1为星期五，0为星期六 <br>
     * @return <type>int</type>
     */
    public static int getDayofWeekInMonth(String year, String month,
                                          String weekOfMonth, String dayOfWeek) {
        Calendar cal = new GregorianCalendar();
        // 在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。
        int y = new Integer(year).intValue();
        int m = new Integer(month).intValue();
        cal.clear();// 不保留以前的设置
        cal.set(y, m - 1, 1);// 将日期设置为本月的第一天。
        cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, new Integer(weekOfMonth)
                .intValue());
        cal.set(Calendar.DAY_OF_WEEK, new Integer(dayOfWeek).intValue());
        // System.out.print(cal.get(Calendar.MONTH)+" ");
        // System.out.print("当"+cal.get(Calendar.WEEK_OF_MONTH)+"\t");
        // WEEK_OF_MONTH表示当天在本月的第几个周。不管1号是星期几，都表示在本月的第一个周
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据指定的年月日小时分秒，返回一个java.Util.Date对象。
     *
     * @param year 年
     * @param month 月 0-11
     * @param date 日
     * @param hourOfDay 小时 0-23
     * @param minute 分 0-59
     * @param second 秒 0-59
     * @return 一个Date对象。
     */
    public static Date getDate(int year, int month, int date, int hourOfDay,
                               int minute, int second) {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month, date, hourOfDay, minute, second);
        return cal.getTime();
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param year
     * @param month
     *            month是从1开始的12结束
     * @param day
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String year, String month, String day) {
        Calendar cal = new GregorianCalendar(new Integer(year).intValue(),
                new Integer(month).intValue() - 1, new Integer(day).intValue());
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date
     *            "yyyy/MM/dd",或者"yyyy-MM-dd"
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String date) {
        String[] temp = null;
        if (date.indexOf("/") > 0) {
            temp = date.split("/");
        }
        if (date.indexOf("-") > 0) {
            temp = date.split("-");
        }
        return getDayOfWeek(temp[0], temp[1], temp[2]);
    }

    /**
     *  返回当前日期是星期几。例如：星期日、星期一、星期六等等。
     * @param date 格式为 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 返回当前日期是星期几
     */
    public static String getChinaDayOfWeek(String date){
        String[] weeks = new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int week = getDayOfWeek(date);
        return weeks[week-1];
    }
    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date
     *
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 返回制定日期所在的周是一年中的第几个周。<br>
     * created by wangmj at 20060324.<br>
     *
     * @param year
     * @param month
     *            范围1-12<br>
     * @param day
     * @return int
     */
    public static int getWeekOfYear(String year, String month, String day) {
        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(new Integer(year).intValue(),
                new Integer(month).intValue() - 1, new Integer(day).intValue());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date
     *            给定的日期对象
     * @param amount
     *            需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static Date getDateAdd(Date date, int amount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return cal.getTime();
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date
     *            给定的日期对象
     * @param amount
     *            需要添加的天数，如果是向前的天数，使用负数就可以.
     * @param format
     *            输出格式.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static String getFormatDateAdd(Date date, int amount, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDateTime(cal.getTime(), format);
    }

    /**
     * 获得当前日期固定间隔天数的日期，如前60天dateAdd(-60)
     *
     * @param amount
     *            距今天的间隔日期长度，向前为负，向后为正
     * @param format
     *            输出日期的格式.
     * @return java.lang.String 按照格式输出的间隔的日期字符串.
     */
    public static String getFormatCurrentAdd(int amount, String format) {

        Date d = getDateAdd(new Date(), amount);

        return getFormatDateTime(d, format);
    }

    /**
     * 取得给定格式的昨天的日期输出
     *
     * @param format
     *            日期输出的格式
     * @return String 给定格式的日期字符串.
     */
    public static String getFormatYestoday(String format) {
        return getFormatCurrentAdd(-1, format);
    }

    /**
     * 返回指定日期的前一天。<br>
     * @param sourceDate
     * @param format  yyyy MM  dd  hh mm  ss
     * @return  返回日期字符串，形式和formcat一致。
     */
    public static String getYestoday(String sourceDate, String format) {
        return getFormatDateAdd(getDateFromString(sourceDate, format), -1,
                format);
    }

    /**
     * 返回明天的日期，<br>
     * @param format
     * @return 返回日期字符串，形式和formcat一致。
     */
    public static String getFormatTomorrow(String format) {
        return getFormatCurrentAdd(1, format);
    }

    /**
     * 返回指定日期的后一天。<br>
     * @param sourceDate
     * @param format
     * @return 返回日期字符串，形式和formcat一致。
     */
    public static String getFormatDateTommorrow(String sourceDate, String format) {
        return getFormatDateAdd(getDateFromString(sourceDate, format), 1,
                format);
    }

    /**
     * 根据主机的默认 TimeZone，来获得指定形式的时间字符串。
     * @param dateFormat
     * @return  返回日期字符串，形式和formcat一致。
     */
    public static String getCurrentDateString(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(cal.getTime());
    }

    /**
     * @deprecated 不鼓励使用。
     * 返回当前时间串 格式:yyMMddhhmmss,在上传附件时使用
     *
     * @return String
     */
    public static String getCurDate() {
        GregorianCalendar gcDate = new GregorianCalendar();
        int year = gcDate.get(GregorianCalendar.YEAR);
        int month = gcDate.get(GregorianCalendar.MONTH) + 1;
        int day = gcDate.get(GregorianCalendar.DAY_OF_MONTH);
        int hour = gcDate.get(GregorianCalendar.HOUR_OF_DAY);
        int minute = gcDate.get(GregorianCalendar.MINUTE);
        int sen = gcDate.get(GregorianCalendar.SECOND);
        String y;
        String m;
        String d;
        String h;
        String n;
        String s;
        y = new Integer(year).toString();

        if (month < 10) {
            m = "0" + new Integer(month).toString();
        } else {
            m = new Integer(month).toString();
        }

        if (day < 10) {
            d = "0" + new Integer(day).toString();
        } else {
            d = new Integer(day).toString();
        }

        if (hour < 10) {
            h = "0" + new Integer(hour).toString();
        } else {
            h = new Integer(hour).toString();
        }

        if (minute < 10) {
            n = "0" + new Integer(minute).toString();
        } else {
            n = new Integer(minute).toString();
        }

        if (sen < 10) {
            s = "0" + new Integer(sen).toString();
        } else {
            s = new Integer(sen).toString();
        }

        return "" + y + m + d + h + n + s;
    }

    /**
     * 根据给定的格式，返回时间字符串。 和getFormatDate(String format)相似。
     *
     * @param format  yyyy  MM dd  hh mm ss
     * @return 返回一个时间字符串
     */
    public static String getCurTimeByFormat(String format) {
        Date newdate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(newdate);
    }

    /**
     * 获取两个时间串时间的差值，单位为秒
     *
     * @param startTime
     *            开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime
     *            结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static long getDiff(String startTime, String endTime) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = ft.parse(startTime);
            Date endDate = ft.parse(endTime);
            diff = startDate.getTime() - endDate.getTime();
            diff = diff / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 获取小时/分钟/秒
     *
     * @param second
     *            秒
     * @return 包含小时、分钟、秒的时间字符串，例如3小时23分钟13秒。
     */
    public static String getHour(long second) {
        long hour = second / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        long sec = (second - hour * 60 * 60) - minute * 60;

        return hour + "小时" + minute + "分钟" + sec + "秒";

    }

    /**
     * 返回指定时间字符串。
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getDateTime(long microsecond) {
        return getFormatDateTime(new Date(microsecond), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回当前时间加实数小时后的日期时间。
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return Float 加几实数小时.
     */
    public static String getDateByAddFltHour(float flt) {
        int addMinute = (int) (flt * 60);
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(GregorianCalendar.MINUTE, addMinute);
        return getFormatDateTime(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回指定时间加指定小时数后的日期时间。
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 时间.
     */
    public static String getDateByAddHour(String datetime, int minute) {
        String returnTime = null;
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = ft.parse(datetime);
            cal.setTime(date);
            cal.add(GregorianCalendar.MINUTE, minute);
            returnTime = getFormatDateTime(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnTime;

    }

    /**
     * 获取两个时间串时间的差值，单位为小时
     *
     * @param startTime
     *            开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime
     *            结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static int getDiffHour(String startTime, String endTime) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = ft.parse(startTime);
            Date endDate = ft.parse(endTime);
            diff = startDate.getTime() - endDate.getTime();
            diff = diff / (1000 * 60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Long(diff).intValue();
    }

    /**
     * 返回年份的下拉框。
     * @param selectName
     *            下拉框名称
     * @param value
     *            当前下拉框的值
     * @param startYear
     *            开始年份
     * @param endYear
     *            结束年份
     * @return 年份下拉框的html
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 返回年份的下拉框。
     * @param selectName
     *            下拉框名称
     * @param value
     *            当前下拉框的值
     * @param startYear
     *            开始年份
     * @param endYear
     *            结束年份
     *            例如开始年份为2001结束年份为2005那么下拉框就有五个值。（2001、2002、2003、2004、2005）。
     * @return 返回年份的下拉框的html。
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear, boolean hasBlank) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 返回年份的下拉框。
     * @param selectName
     *            下拉框名称
     * @param value
     *            当前下拉框的值
     * @param startYear
     *            开始年份
     * @param endYear
     *            结束年份
     * @param js
     *            这里的js为js字符串。例如 " onchange=\"changeYear()\" "
     *            ,这样任何js的方法就可以在jsp页面中编写，方便引入。
     * @return 返回年份的下拉框。
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear, boolean hasBlank,String js) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");

        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }
    /**
     * 返回年份的下拉框。
     * @param selectName
     *            下拉框名称
     * @param value
     *            当前下拉框的值
     * @param startYear
     *            开始年份
     * @param endYear
     *            结束年份
     * @param js
     *            这里的js为js字符串。例如 " onchange=\"changeYear()\" "
     *            ,这样任何js的方法就可以在jsp页面中编写，方便引入。
     * @return 返回年份的下拉框。
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear, String js) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }
    /**
     * 获取月份的下拉框
     *
     * @param selectName
     * @param value
     * @param hasBlank
     * @return 返回月份的下拉框。
     */
    public static String getMonthSelect(String selectName, String value,
                                        boolean hasBlank) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 12; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 获取月份的下拉框
     *
     * @param selectName
     * @param value
     * @param hasBlank
     * @param js
     * @return 返回月份的下拉框。
     */
    public static String getMonthSelect(String selectName, String value,
                                        boolean hasBlank, String js) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 12; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 获取天的下拉框，默认的为1-31。
     * 注意：此方法不能够和月份下拉框进行联动。
     * @param selectName
     * @param value
     * @param hasBlank
     * @return 获得天的下拉框
     */
    public static String getDaySelect(String selectName, String value,
                                      boolean hasBlank) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 31; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 获取天的下拉框，默认的为1-31
     *
     * @param selectName
     * @param value
     * @param hasBlank
     * @param js
     * @return 获取天的下拉框
     */
    public static String getDaySelect(String selectName, String value,
                                      boolean hasBlank, String js) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 31; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     *  计算两天之间有多少个周末（这个周末，指星期六和星期天，一个周末返回结果为2，两个为4，以此类推。）
     * （此方法目前用于统计司机用车记录。）
     * @param startDate
     *            开始日期 ，格式"yyyy/MM/dd"
     * @param endDate
     *            结束日期 ，格式"yyyy/MM/dd"
     * @return int
     */
    public static int countWeekend(String startDate, String endDate) {
        int result = 0;
        Date sdate = null;
        Date edate = null;
        sdate = getDateObj(startDate, "/"); // 开始日期
        edate = getDateObj(endDate, "/");// 结束日期
        // 首先计算出都有那些日期，然后找出星期六星期天的日期
        int sumDays = Math.abs(getDiffDays(startDate, endDate));
        int dayOfWeek = 0;
        for (int i = 0; i <= sumDays; i++) {
            dayOfWeek = getDayOfWeek(getDateAdd(sdate, i)); // 计算每过一天的日期
            if (dayOfWeek == 1 || dayOfWeek == 7) { // 1 星期天 7星期六
                result++;
            }
        }
        return result;
    }

    /**
     * 返回两个日期之间相差多少天。
     *
     * @param startDate
     *            格式"yyyy/MM/dd"
     * @param endDate
     *            格式"yyyy/MM/dd"
     * @return 整数。
     */
    public static int getDiffDays(String startDate, String endDate) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date sDate = ft.parse(startDate + " 00:00:00");
            Date eDate = ft.parse(endDate + " 00:00:00");
            diff = eDate.getTime() - sDate.getTime();
            diff = diff / 86400000;// 1000*60*60*24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) diff;

    }

    /**
     * 返回两个日期之间的详细日期数组(包括开始日期和结束日期)。
     * 例如：2007/07/01 到2007/07/03 ,那么返回数组
     * {"2007/07/01","2007/07/02","2007/07/03"}
     * @param startDate 格式"yyyy/MM/dd"
     * @param endDate  格式"yyyy/MM/dd"
     * @return 返回一个字符串数组对象
     */
    public static String[] getArrayDiffDays(String startDate,String endDate){
        int LEN = 0; //用来计算两天之间总共有多少天
        //如果结束日期和开始日期相同
        if(startDate.equals(endDate)){
            return new String[]{startDate};
        }
        Date sdate = null;
        sdate = getDateObj(startDate, "/"); // 开始日期
        LEN = getDiffDays(startDate,endDate);
        String[] dateResult = new String[LEN+1];
        dateResult[0]=startDate;
        for(int i=1;i<LEN+1;i++){
            dateResult[i] = getFormatDateTime( getDateAdd(sdate, i),"yyyy/MM/dd");
        }

        return dateResult;
    }

    /**
     * 根据传入的时间 和当前的时间进行比较.
     * @param microsecond
     * 1分钟=60*1000
     * 60分钟=1小时=60*60*1000
     * 10小时=24*60*60*1000
     * 5天=5*24*60*60*1000
     * @return
     */
    public static String getTimeConversion(long microsecond)
    {
        long mDurtionTime=System.currentTimeMillis()-microsecond;
        if(mDurtionTime<60*1000)
        {
            return String.valueOf(Math.abs(mDurtionTime/1000))+"秒前";
        }else if(mDurtionTime<60*60*1000){
            return String.valueOf(mDurtionTime/(60*1000))+"分钟前";
        }else if (mDurtionTime<24*60*60*1000) {
            return String.valueOf(mDurtionTime/(60*60*1000))+"小时前";
        }else if(mDurtionTime<10*24*60*60*1000)
        {
            return String.valueOf(mDurtionTime/(24*60*60*1000))+"天前";
        }else {
            return getDateTime(microsecond);
        }
    }
}
