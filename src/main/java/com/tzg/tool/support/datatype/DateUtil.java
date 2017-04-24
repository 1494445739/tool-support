package com.tzg.tool.support.datatype;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关的工具函数
 *
 * @see #getCurrentTimeStamp: 根据默认日期格式（yyyyMMddHHmmss），返回当前服务器日期时间戳
 * @see #getDate: 返回指定日期对象的日期字符串。假如日期对象为null，则返回空字符串
 * @see #getDateByPattern: 根据指定的日期格式和日期对象，返回日期字符串，如果日期对象为null，则返回空字符串
 * @see #getDate(String, String): 把传入的日期字符串，转换成指定格式的日期对象
 * @see #getYearMonth(Date) : 根据传入的日期对象，获取年月字符串
 * @see #getTime(Date) : 根据传入的日期对象，获取时间字符串
 * @see #getYear(Date) : 获取年字符串
 * @see #getMonth(Date) : 获取月字符串
 * @see #getDay(Date) : 根据传入的日期对象，获取日字符串
 * @see #getHour(Date) : 返回小时
 * @see #getMinutes(Date) : 返回分钟
 * @see #getTimeByPattern(Date, int) : 根据日期对象和时间模式获取时间数值
 * @see #getSeconds(Date) : 返回秒
 * @see #getMillis(Date) : 返回距离1970年0时0分的毫秒数。这个函数经常用来做比较用。比如程序的执行效率等
 * @see #diffDate(Date, Date) : 日期相减
 * @see #addDate(Date, int) : 日期相加
 * @see #diffDate(Date, Date) : 日期相减
 * @see #diffHour(Date, Date) : 小时相减
 * @see #diffMinutes(Date, Date) : 分钟相减
 * @see #diffSeconds(Date, Date) : 秒相减
 * @see #diffMillis(Date, Date) : 毫秒相减
 * @see #isTimeIn(Date, Date, Date) : 判断是否在一个时间段内
 * @see #isCurrentYear(String) : 判断输入的时间字符串是否今年
 * @see #getPreMonth() : 返回当前时间的上个月
 * @see #getDayOfWeek(String) : 判断当前日期是星期几
 * @see #getDateForSunday(String) : 获取某日期所在周的星期天日期(以周一为一周的第一天)
 * @see #getWeekOfYear(String) : 获取某日期是一年中的第几周
 * @see #getIntervalTime(String, int) : 时间前推或后推分钟,其中minute表示分钟. +后推 -前推
 * @see #isDateBefore(String, String) : 判断时间before是否在时间end之前
 * @see #isEOM(Date) : 判断是否是月末
 * @see #isCurrentMonth(String) : 判断是否当月
 * @see #getStepDate(int, String) : 获得当前日期的前N天或后N天
 * @see #getDaysOfMonth() : 取得当月天数
 * @see #getDaysOfMonth(String, String) : 取得月天数
 * @see #getDayOfMonth() : 取得本日是本月的第几天
 * @see #compareDateTime(String, String, String) : 判断两个时间先后
 * @see #getDiffDays(Date, Date) : 获取相差的天数
 * @see #get1thOfMonth(Date) : 获取日期当月的第一天
 * @see #getLastDayOfMonth(Date, String) : 获取日期当月的最后一天
 */
public final class DateUtil {

    private static       String           DAY_PATTERN        = "dd";
    private static       String           TIME_PATTERN       = "HH:mm:ss";
    private static       String           DATE_PATTERN       = "yyyy-MM-dd";
    private static       String           YEAR_PATTERN       = "yyyy";
    private static       String           MONTH_PATTERN      = "MM";
    private static       String           YEAR_MONTH_PATTERN = "yyyy-MM";
    private static       String           DATETIME_PATTERN   = "yyyy-MM-dd HH:mm:ss";
    private final static SimpleDateFormat FORMAT             = new SimpleDateFormat( "yyyyMMddHHmmss" );

    /**
     * 根据默认日期格式（yyyyMMddHHmmss），返回当前服务器日期时间戳
     *
     * @return String "年月日时分秒"日期字符串
     */
    public static String getCurrentTimeStamp() {
        return FORMAT.format( new Date() );
    }

    /**
     * 返回指定日期对象的日期字符串。假如日期对象为null，则返回空字符串
     *
     * @param date 日期对象
     * @return String "年月日"日期字符串
     */
    public static String getDate( Date date ) {
        return getDateByPattern( DATE_PATTERN, date );
    }

    /**
     * 根据传入日期返回指定日期的日期字符串
     *
     * @param date 日期对象
     * @return String 格式化后的'年月日时分秒'日期字符串
     */
    public static String getDateTime( Date date ) {
        return getDateByPattern( DATETIME_PATTERN, date );
    }

    /**
     * 根据指定的日期格式和日期对象，返回日期字符串，如果日期对象为null，则返回空字符串
     */
    public static String getDateByPattern( String pattern, Date date ) {
        String result = "";

        if ( date != null ) {
            SimpleDateFormat df = new SimpleDateFormat( pattern );
            result = df.format( date );
        }

        return result;
    }

    /**
     * 把传入的日期字符串，转换成指定格式的日期对象
     *
     * @param dateStr 日期字符串
     * @param pattern 指定转换格式
     * @return Date  日期对象
     */
    public static Date getDate( String dateStr, String pattern ) throws ParseException {
        Date date = null;

        if ( !StringUtils.isEmpty( dateStr ) ) {
            SimpleDateFormat sdf = new SimpleDateFormat( pattern );
            date = sdf.parse( dateStr );
        }

        return date;
    }

    /**
     * 获取年字符串
     *
     * @param date 日期对象
     * @return String 年
     */
    public static String getYear( Date date ) {
        return getDateByPattern( YEAR_PATTERN, date );
    }

    /**
     * 获取月字符串
     *
     * @param date 日期对象
     * @return String 月
     */
    public static String getMonth( Date date ) {
        return getDateByPattern( MONTH_PATTERN, date );
    }

    /**
     * 根据传入的日期对象，获取年月字符串
     *
     * @param date 传入的日期对象
     * @return String  年-月
     */
    public static String getYearMonth( Date date ) {
        return getDateByPattern( YEAR_MONTH_PATTERN, date );
    }

    /**
     * 根据传入的日期对象，获取日字符串
     *
     * @param date 日期对象
     * @return String 日
     */
    public static String getDay( Date date ) {
        return getDateByPattern( DAY_PATTERN, date );
    }

    /**
     * 根据传入的日期对象，获取时间字符串
     *
     * @param date 日期对象
     * @return String 时:分:秒
     */
    public static String getTime( Date date ) {
        return getDateByPattern( TIME_PATTERN, date );
    }

    /**
     * 返回小时
     *
     * @param date 日期对象
     * @return int 小时
     */
    public static int getHour( Date date ) {
        return getTimeByPattern( date, Calendar.HOUR_OF_DAY );
    }

    /**
     * 返回分钟
     *
     * @param date 日期对象
     * @return int 分钟
     */
    public static int getMinutes( Date date ) {
        return getTimeByPattern( date, Calendar.MINUTE );
    }

    /**
     * 返回秒
     *
     * @param date 日期对象
     * @return int 秒
     */
    public static int getSeconds( Date date ) {
        return getTimeByPattern( date, Calendar.SECOND );
    }

    /**
     * 返回距离1970年0时0分的毫秒数。这个函数经常用来做比较用。比如程序的执行效率等。
     * <example>
     * long start = getMillis( new Date() );
     * doSomething();
     * long end   = getMillis( new Date() );
     * </example>
     *
     * @param date 日期对象
     * @return long 毫秒
     */
    public static long getMillis( Date date ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        return calendar.getTimeInMillis();
    }

    /**
     * 根据日期对象和时间模式获取时间数值
     *
     * @param date    日期对象
     * @param pattern 时间模式
     */
    public static int getTimeByPattern( Date date, int pattern ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        return calendar.get( pattern );
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param day  天数，可为负
     * @return 返回相加后的日期对象
     */
    public static Date addDate( Date date, int day ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( getMillis( date ) + day * 24 * 60 * 60 * 1000 );
        return calendar.getTime();
    }

    /**
     * 日期相减
     *
     * @param minuend    被减数
     * @param subtrahend 减数
     * @return 返回相减后的日期
     */
    public static int diffDate( Date minuend, Date subtrahend ) {
        return ( int ) ( ( getMillis( minuend ) - getMillis( subtrahend ) ) / ( 1000 * 60 * 60 * 24 ) );
    }

    /**
     * 小时相减
     *
     * @param minuend    被减数
     * @param subtrahend 减数
     * @return int 返回相减后的小时
     */
    public static int diffHour( Date minuend, Date subtrahend ) {
        return ( int ) ( ( getMillis( minuend ) - getMillis( subtrahend ) ) / ( 1000 * 60 * 60 ) );
    }

    /**
     * 分钟相减
     *
     * @param minuend    被减数
     * @param subtrahend 减数
     * @return int 返回相减后的分钟
     */
    public static int diffMinutes( Date minuend, Date subtrahend ) {
        return ( int ) ( ( getMillis( minuend ) - getMillis( subtrahend ) ) / ( 1000 * 60 ) );
    }

    /**
     * 秒相减
     *
     * @param minuend    被减数
     * @param subtrahend 减数
     * @return int 返回相减后的秒
     */
    public static int diffSeconds( Date minuend, Date subtrahend ) {
        return ( int ) ( ( getMillis( minuend ) - getMillis( subtrahend ) ) / ( 1000 ) );
    }

    /**
     * 毫秒相减
     *
     * @param minuend    被减数
     * @param subtrahend 减数
     * @return int 返回相减后的毫秒
     */
    public static long diffMillis( Date minuend, Date subtrahend ) {
        return ( getMillis( minuend ) - getMillis( subtrahend ) );
    }

    /**
     * 判断是否在一个时间段内
     *
     * @param time  要判断的时间
     * @param begin 开始时间
     * @param end   结束时间
     * @return boolean true：在开始和结束范围内，false:不在开始和结束范围内
     */
    public static boolean isTimeIn( Date time, Date begin, Date end ) {
        return time.getTime() >= begin.getTime() && time.getTime() <= end.getTime();
    }

    /**
     * 判断输入的时间字符串是否今年
     *
     * @param date 日期字符串
     * @return boolean  true：今年，false：不是今年
     */
    public static boolean isCurrentYear( String date ) {
        Calendar calendar = Calendar.getInstance();
        int      tar      = calendar.get( Calendar.YEAR );
        int      src      = Integer.parseInt( date.substring( 0, 4 ) );
        return src == tar;
    }

    /**
     * 返回当前时间的上个月
     *
     * @return String 日期格式yyyyMM格式的日期字符串
     */
    public static String getPreMonth() {
        Calendar calendar = Calendar.getInstance();
        int      year     = calendar.get( Calendar.YEAR );
        int      month    = calendar.get( Calendar.MONTH );
        return month >= 10 ? year + "-" + month : year + "-0" + month;
    }

    /**
     * 判断当前日期是星期几
     *
     * @param date 要判断的时间
     * @return int 当前是星期几
     */
    public static int getDayOfWeek( String date ) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( getDate( date, "yyyy-MM-dd" ) );
        int day = calendar.get( Calendar.DAY_OF_WEEK );
        return day == 1 ? 7 : day - 1;
    }

    /**
     * 获取某日期所在周的星期天日期(以周一为一周的第一天)
     *
     * @param date 日期字符串
     * @return String 返回日期
     */
    public static String getDateForSunday( String date ) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( getDate( date, YEAR_MONTH_PATTERN ) );
        calendar.setFirstDayOfWeek( Calendar.MONDAY );
        calendar.set( Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6 );
        return getDay( calendar.getTime() );
    }

    /**
     * 获取某日期是一年中的第几周
     *
     * @param date 日期字符串
     * @return int 第几周
     */
    public static int getWeekOfYear( String date ) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( getDate( date, YEAR_MONTH_PATTERN ) );
        calendar.setFirstDayOfWeek( Calendar.MONDAY );
        return calendar.get( Calendar.WEEK_OF_YEAR );
    }

    /**
     * 时间前推或后推分钟,其中minute表示分钟. +后推 -前推
     *
     * @param date   日期字符串，格式年月日时分秒
     * @param minute 分钟
     * @return String
     */
    public static String getIntervalTime( String date, int minute ) throws ParseException {
        Date d      = getDate( date, DATETIME_PATTERN );
        long millis = d.getTime() + minute * 60 * 1000;
        d.setTime( millis );
        return getDateTime( d );
    }

    /**
     * 判断时间before是否在时间end之前
     *
     * @param before 时间字符串
     * @param end    时间字符串
     * @return boolean true:是，false：否
     */
    public static boolean isDateBefore( String before, String end ) throws ParseException {
        return getDate( before, DATETIME_PATTERN ).before( getDate( end, DATETIME_PATTERN ) );
    }

    /**
     * 判断指定日期对象是否是月末
     *
     * @param date 日期
     * @return boolean true月末,false不是月末
     */
    public static boolean isEOM( Date date ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        return ( calendar.get( Calendar.DATE ) == calendar.getActualMaximum( Calendar.DAY_OF_MONTH ) ) ? true : false;
    }

    /**
     * 判断是否当月
     *
     * @param date 日期
     * @return boolean 是返回true，否返回false
     */
    public static boolean isCurrentMonth( String date ) throws ParseException {
        String   src      = getMonth( getDate( date, YEAR_MONTH_PATTERN ) );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Date() );
        int    month = calendar.get( Calendar.MONTH ) + 1;
        String tar   = month > 9 ? String.valueOf( month ) : "0" + month;

        return src.equalsIgnoreCase( tar ) ? true : false;
    }

    /**
     * 获得当前日期的前N天或后N天
     *
     * @param step    day 整数是前推N天（即过去某一天），负数是向后推N天（即将来某一天）
     * @param pattern 返回格式
     * @return String 相加减后的日期字符串
     */
    public static String getStepDate( int step, String pattern ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Date() );
        int day = calendar.get( Calendar.DATE );
        calendar.set( Calendar.DATE, day - step );
        return getDateByPattern( pattern, calendar.getTime() );
    }

    /**
     * 当前时间往前几个月（month为负数），往后几个月（month为正）
     *
     * @param step    步长
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String getStepMonth( int step, String pattern ) {
        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.MONTH, step );
        return getDateByPattern( pattern, calendar.getTime() );
    }

    /**
     * 取得当月天数
     *
     * @return int 当月天数
     */
    public static int getDaysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set( Calendar.DATE, 1 );   //把日期设置为当月第一天
        calendar.roll( Calendar.DATE, -1 ); //日期回滚一天，也就是最后一天
        return calendar.get( Calendar.DATE );
    }

    /**
     * 取得指定日期的月天数
     *
     * @param date    日期字符串
     * @param pattern 日期显示模式
     * @return int 月天数
     */
    public static int getDaysOfMonth( String date, String pattern ) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( getDate( date, pattern ) );
        calendar.set( Calendar.DATE, 1 );
        calendar.roll( Calendar.DATE, -1 );
        return calendar.get( Calendar.DATE );
    }

    /**
     * 取得本日是本月的第几天
     *
     * @return int 当前月的第几天
     */
    public static int getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get( Calendar.DAY_OF_MONTH );
    }

    /**
     * 判断两个时间先后
     *
     * @param src
     * @param tar
     * @param pattern 日期格式
     * @return src在tar前返回-1，src在tar后返回1，src=tar返回0
     */
    public static int compareDateTime( String src, String tar, String pattern ) throws ParseException {
        Calendar before = Calendar.getInstance();
        Calendar end    = Calendar.getInstance();
        before.setTime( getDate( src, pattern ) );
        end.setTime( getDate( tar, pattern ) );
        return before.compareTo( end );
    }

    /**
     * 日期相减
     *
     * @param src
     * @param tar
     * @return 日期相差的天数
     * @throws ParseException
     */
    public static int getDiffDays( Date src, Date tar ) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( src );
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.SECOND, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
        long before = calendar.getTimeInMillis();

        calendar.setTime( tar );
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.SECOND, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
        long end = calendar.getTimeInMillis();

        return ( int ) ( ( before - end ) / ( 1000 * 3600 * 24 ) );
    }

    /**
     * 获取日期当月的第一天
     *
     * @param date
     * @return 日期当前月的第一天日期
     * @throws ParseException
     */
    public static Date get1thOfMonth( Date date ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( date );
        calendar.set( Calendar.DAY_OF_MONTH, 1 );
        return calendar.getTime();
    }

    /**
     * 获取日期当月的最后一天
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return 日期当月最后一天
     */
    public static String getLastDayOfMonth( Date date, String pattern ) {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MONTH, 1 );    //加一个月
        calendar.set( Calendar.DATE, 1 );   //设置为该月第一天
        calendar.add( Calendar.DATE, -1 );  //再减一天即为上个月最后一天
        return getDateByPattern( pattern, calendar.getTime() );
    }

    public static void main( String[] args ) throws ParseException {

        System.out.println( getCurrentTimeStamp() );
        System.out.println( getDate( new Date() ) );
        System.out.println( getDateTime( new Date() ) );
        System.out.println( getYearMonth( new Date() ) );
        System.out.println( getTime( new Date() ) );
        System.out.println( getYear( new Date() ) );
        System.out.println( getMonth( new Date() ) );
        System.out.println( getDay( new Date() ) );
        System.out.println( getHour( new Date() ) );
        System.out.println( getMinutes( new Date() ) );
        System.out.println( getSeconds( new Date() ) );
        System.out.println( getMillis( new Date() ) );
        System.out.println( getDateTime( addDate( new Date(), 10 ) ) );
        System.out.println( diffDate( new Date(), getDate( "2017-04-07", "yyyy-MM-dd" ) ) );
        System.out.println( diffHour( new Date(), getDate( "2017-04-07", "yyyy-MM-dd" ) ) );
        System.out.println( diffMinutes( new Date(), getDate( "2017-04-07", "yyyy-MM-dd" ) ) );
        System.out.println( isCurrentYear( "2017" ) );
        System.out.println( Calendar.getInstance().get( Calendar.DATE ) );
        System.out.println( getPreMonth() );
        System.out.println( getDayOfWeek( "2017-04-03" ) );
        System.out.println( getIntervalTime( "2017-04-06 12:00:00", 30 ) );
        System.out.println( isDateBefore( "2018-04-06 12:00:00", "2017-04-06 12:00:00" ) );
        System.out.println( isEOM( getDate( "2017-04-28", "yyyy-MM-dd" ) ) );
        System.out.println( isCurrentMonth( "2017-04-23" ) );
        System.out.println( getStepDate( 6, DATETIME_PATTERN ) );
        System.out.println( getDaysOfMonth() );
        System.out.println( getDaysOfMonth( "2017-02", YEAR_MONTH_PATTERN ) );
        System.out.println( getDayOfMonth() );
        System.out.println( compareDateTime( "2017-02-02", "2017-02-02", DATE_PATTERN ) );

    }

}
