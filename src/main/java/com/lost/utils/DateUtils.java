package com.lost.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";
    
    public static String MMDD = "MMdd";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        String v = str.toString();
        try
        {
            return v.indexOf('-') > 0 ? parseDate(v, "yyyy-MM-dd") : parseDate(v, "yyyyMMdd");
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
    
    /**
     * 判断时间是否在时间段内 
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Boolean isEffectiveDate(Date nowTime, Date beginTime, Date endTime) {
    	Calendar date = Calendar.getInstance();
    	date.setTime(nowTime);
    	Calendar begin = Calendar.getInstance();
    	begin.setTime(beginTime);
    	Calendar end = Calendar.getInstance();
    	end.setTime(endTime);
    	if (date.after(begin) && date.before(end)) {
    		return true;
    	} else if(nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
    		return true;
    	} 
    	return false;
    }
    
    /**
     * 
     * Description：用于当前日期，是否在超始日期与截止日期的设定范围内
     * @param    startDate 起始日期 
     *           endDate  截止日期 
     * @return   boolean  
     * @exception
     */
    public static boolean compareDate(String startDate,String endDate)throws Exception{
    	boolean flag = true;
    	Date currDate = new Date();//当前日期
		try{
			if(StringUtils.isNotBlank(startDate)){
	    		flag = flag && compareStartDate(currDate,parseDate(startDate));
	    	}
	    	if(StringUtils.isNotBlank(endDate )){
	    		flag = flag && compareEndDate(currDate,parseDate(endDate));
	    	}
	    	return flag;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("传入日期参数不正确!");
		}
    }
    //与开始日期比较
    public static boolean compareStartDate(Date d,Date startDate)throws Exception{
    	
    	if(startDate == null){//没有开始日期
    		return true;
    	}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try{
			cal1.setTime(d);
			cal2.setTime(startDate);
			
			cal2.set(Calendar.HOUR, 0);
			cal2.set(Calendar.MINUTE, 0);
			cal2.set(Calendar.MILLISECOND, 0);
			
			long i =cal1.getTimeInMillis() - cal2.getTimeInMillis();
            if( i<0){ //还没有到起始日期
            	return false;
            }
            return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("传入日期参数不正确!");
		}
    }
    
    //与截止日期比较
    public static boolean compareEndDate(Date d,Date endDate)throws Exception{
    	
    	if(endDate == null){//没有截止日期
    		return true;
    	}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try{
			cal1.setTime(d);
			cal2.setTime(endDate);
			
			cal2.set(Calendar.HOUR, 23);
			cal2.set(Calendar.MINUTE, 59);
			cal2.set(Calendar.MILLISECOND,59);
			
			long i  =cal1.getTimeInMillis() - cal2.getTimeInMillis();
			if(i>0){
				return false;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("传入日期参数不正确!");
		}
    }
    
	/**
     * 读取当前时间的上一个年份 
     * @return
     */
	public static String getLastYear() {
		Calendar date = Calendar.getInstance();
		date.setTime(new Date());
		date.add(Calendar.YEAR, -1);
		Date y = date.getTime();
		String year = parseDateToStr("yyyy", y);
		return year;
	}
        
    public static int getAgeByBirth(Date birthDay) throws Exception {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }
    
    public static int getAgeByBirth(Date birthDay, String year) throws Exception {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        if(StringUtils.isNotBlank(year) ) {
        	 Date queryYear = dateTime(YYYY,year);
             cal.setTime(queryYear);
             int yearQuery = cal.get(Calendar.YEAR);  //时间查询年份
             int differYear = yearNow - yearQuery;
             age = age - differYear;
             if(age < 0) age = 0;
        }
       
        return age;
    }
    
    //取得当前年
    public static String getCurrentYear() {
    	Calendar cal = Calendar.getInstance();
    	return cal.get(Calendar.YEAR)+"";
	}
    
    public static int getYearOrMonthOrDay(String str){
		Calendar c = Calendar.getInstance();
		if("Y".equals(str)){
			return c.get(Calendar.YEAR);
		}else if("M".equals(str)){
			return (c.get(Calendar.MONTH)+1);
		}else if("D".equals(str)){
			return c.get(Calendar.DAY_OF_MONTH);
		}
		return 0;
	}
    
    
}
