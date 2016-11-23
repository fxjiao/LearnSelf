package com.ihere.learnself.util;

import android.app.Activity;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
//import jodd.util.Base64;

/**
 * Created by sundy on 15/7/9.
 */
public class TimeUtils {
    public static final int DATE_FORMAT_NORMAL = 1;//yyyy-MM-dd HH:mm:ss
    public static final int DATE_WITHOUT_SEC_FORMAT_NORMAL = 11;//yyyy-MM-dd
    public static final int DATE_FORMAT_CN = 2;    //yyyy年MM月dd日 HH:mm:ss
    public static final int DATE_WITHOUT_SEC_FORMAT_CN = 21;    //yyyy年MM月dd日
    public static final int DATE_WEEK = 3;    //yyyy年MM月dd日
    public static final int DATE_FORMAT_EN = 4;    //dd/MM/yyy
    public static final int DATE_FORMAT_MIN_SEC = 5;    //HH:mm

    public static final int SDCardMinCapacity = 10 * 1024;// 預設10M

    public static void hideSoftInputView(Activity activity) {
        if (activity.getWindow().getAttributes().softInputMode !=
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                manager.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    public static String decodeBase64String(String decryptString) {
        if (decryptString != null) {
            byte[] data = Base64.decode(decryptString, Base64.DEFAULT);
            try {
                String text = new String(data, "UTF-8");
                return text;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public static String encodeBase64String(String decryptString) {//暫不用加密
//        if (decryptString != null) {
//            byte[] data = new byte[0];
//            try {
//                data = decryptString.getBytes("UTF-8");
//                String base64 = Base64.encodeToString(data, Base64.DEFAULT);
////                if(base64.endsWith("\n")){
////                    return base64.replace(base64.substring(base64.lastIndexOf("\n")),"");
////                }
//                base64  = base64.replace("\n","");
//                return base64;
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }

//            try {
//                return new String(Base64.encode(decryptString.getBytes("UTF-8")));
//            } catch (UnsupportedEncodingException e) {
////                 TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        return decryptString;
    }


    public static Spannable getSpannableMust(String tx, int from, int to, int color) {

        String strEnd = tx;
//        int start = 0;
//        int end = start + tx.length();
        Spannable word = new SpannableString(strEnd);
        word.setSpan(new ForegroundColorSpan(color), from, to, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return word;
    }

    /**
     * @return date
     */
    public static String getCurrentWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat(getDataFormatType(DATE_WEEK), Locale.CHINESE);
        Date curDate = new Date(System.currentTimeMillis());
        String date = formatter.format(curDate);
        return date;
    }

    /**
     * @return date
     */
    public static String getCurrentDate(int formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(getDataFormatType(formatType));
        Date curDate = new Date(System.currentTimeMillis());
        String date = formatter.format(curDate);
        return date;
    }

    /**
     * @return hour and minus
     */
    public static String getCurrentTime(int formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(getDataFormatType(formatType));
        Date curDate = new Date(System.currentTimeMillis());
        String date = formatter.format(curDate);
        return date;
    }

    public static String tranMillSecToDate(Long sd, int formatType) {
        Date dat = new Date(sd);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format = new SimpleDateFormat(getDataFormatType(formatType));
        String sb = format.format(gc.getTime());
        System.out.println(sb);
        return sb;
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String getDifftime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (time == null || "".equals(time)) {
            return "";
        }
        java.util.Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();

        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);

        if (current.after(today)) {
            return "今日";
        } else if (current.before(today) && current.after(yesterday)) {

            return "昨日";
        } else if (current.get(Calendar.WEEK_OF_YEAR) == today.get(Calendar.WEEK_OF_YEAR)) {
            return "本週";
        } else if (current.get(Calendar.WEEK_OF_YEAR) == today.get(Calendar.WEEK_OF_YEAR) - 1) {
            return "上週前";
        } else if (current.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
            return "本月";
        } else {
            return "上個月前";
        }
    }

    private static String getDataFormatType(int formatType) {
        switch (formatType) {
            case DATE_FORMAT_CN:
                return "yyyy年MM月dd日 HH:mm:ss";
            case DATE_WITHOUT_SEC_FORMAT_CN:
                return "yyyy年MM月dd日";
            case DATE_FORMAT_NORMAL:
                return "yyyy-MM-dd HH:mm:ss";
            case DATE_WITHOUT_SEC_FORMAT_NORMAL:
                return "yyyy-MM-dd";
            case DATE_WEEK:
                return "EEEE";
            case DATE_FORMAT_EN:
                return "dd/MM/yyy";
            case DATE_FORMAT_MIN_SEC:
                return "HH:mm";
        }
        return "yyyy-MM-dd hh:mm:ss";
    }

    public static String timeToAmOrPm(String strTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (strTime == null || "".equals(strTime)) {
            return "";
        }
        java.util.Date date = null;
        try {
            date = format.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();
        current.setTime(date);
        int hour = current.get(Calendar.HOUR);
        int min = current.get(Calendar.MINUTE);
        current.get(Calendar.AM_PM);
        String time = "";
        if (min < 10) {
            time = hour + ":0" + min;
        } else {
            time = hour + ":" + min;
        }
        return current.get(Calendar.AM_PM) == Calendar.AM ? (time + "am") : (time + "pm");
    }

    public static String getRefreshTime(String strTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date curDate = new Date(System.currentTimeMillis());
            java.util.Date date = df.parse(strTime);
            if (!date.before(curDate)) {
                SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
                return "今日 " + df1.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    public static String adJustNewsDetailTime(String strTime) {
        if (strTime == null || strTime.trim().equals("")) {
            return "";
        }
        strTime = strTime.replace("2015", "15");
        if (strTime.contains(":")) {
            strTime = strTime.substring(0, strTime.lastIndexOf(":"));
        }
        return strTime;
    }

    public static String adJustSearchTime(String strTime) {
        if (strTime == null || strTime.trim().equals("")) {
            return "";
        }
        if (strTime.contains(":")) {
            strTime = strTime.substring(0, strTime.lastIndexOf(":"));
        }
        return strTime;
    }


    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
