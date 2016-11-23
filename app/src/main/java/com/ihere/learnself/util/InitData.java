package com.ihere.learnself.util;

import android.content.Context;
import android.util.Log;

import com.ihere.learnself.bean.MayWordBean;
import com.ihere.learnself.helper.DatabaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by fxjiao on 16/2/23.
 */
public class InitData {

    public static void initAllWords(Context context) {
        if (!DatabaseHelper.getHelper(context).isMayWordDatasExist(context)) {
            InputStream stream = context.getClass().getClassLoader().getResourceAsStream("assets/lessons/" + "high.xls");
            try {
                Workbook book = Workbook.getWorkbook(stream);//原文件文件
                System.out.println(">>>>>>number of sheet " + book.getNumberOfSheets());
                // 获得第一个工作表对象
                Sheet sheet = book.getSheet(0);
                int Rows = sheet.getRows();
                int Cols = sheet.getColumns();
                System.out.println("当前工作表的名字:" + sheet.getName());
                System.out.println("总行数:" + Rows);
                System.out.println("总列数:" + Cols);
                for (int i = 1; i < Rows; ++i) { //从第二行开始
                    MayWordBean wordBean = new MayWordBean();
                    wordBean.setWord(sheet.getCell(0, i).getContents());
                    wordBean.setPhonogram(sheet.getCell(1, i).getContents());
                    wordBean.setWordTranslate(sheet.getCell(2, i).getContents());
                    wordBean.setSentence(sheet.getCell(3, i).getContents());
                    wordBean.setSentenceTranslate(sheet.getCell(4, i).getContents());
                    wordBean.setLevel("high");
                    wordBean.setIsNewWord(false);
                    DatabaseHelper.getHelper(context).addMayWords(context, wordBean);
//                    for (int j = 0; j < Cols; ++j) {
//                        // getCell(Col,Row)获得单元格的值
//                        System.out.print((sheet.getCell(j, i)).getContents() + "\t");
//                    }
                    System.out.print("\n");
                }
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }

            InputStream streamM = context.getClass().getClassLoader().getResourceAsStream("assets/lessons/" + "middle.xls");
            try {
                Workbook book = Workbook.getWorkbook(streamM);//原文件文件
                System.out.println(">>>>>>number of sheet " + book.getNumberOfSheets());
                // 获得第一个工作表对象
                Sheet sheet = book.getSheet(0);
                int Rows = sheet.getRows();
                int Cols = sheet.getColumns();
                System.out.println("当前工作表的名字:" + sheet.getName());
                System.out.println("总行数:" + Rows);
                System.out.println("总列数:" + Cols);
                for (int i = 1; i < Rows; ++i) { //从第二行开始
                    MayWordBean wordBean = new MayWordBean();
                    wordBean.setWord(sheet.getCell(0, i).getContents());
                    wordBean.setPhonogram(sheet.getCell(1, i).getContents());
                    wordBean.setWordTranslate(sheet.getCell(2, i).getContents());
                    wordBean.setSentence(sheet.getCell(3, i).getContents());
                    wordBean.setSentenceTranslate(sheet.getCell(4, i).getContents());
                    wordBean.setLevel("middle");
                    wordBean.setIsNewWord(false);
                    DatabaseHelper.getHelper(context).addMayWords(context, wordBean);
//                    for (int j = 0; j < Cols; ++j) {
//                        // getCell(Col,Row)获得单元格的值
//                        System.out.print((sheet.getCell(j, i)).getContents() + "\t");
//                    }
                    System.out.print("\n");
                }
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }

            InputStream streamL = context.getClass().getClassLoader().getResourceAsStream("assets/lessons/" + "low.xls");
            try {
                Workbook book = Workbook.getWorkbook(streamL);//原文件文件
                System.out.println(">>>>>>number of sheet " + book.getNumberOfSheets());
                // 获得第一个工作表对象
                Sheet sheet = book.getSheet(0);
                int Rows = sheet.getRows();
                int Cols = sheet.getColumns();
                System.out.println("当前工作表的名字:" + sheet.getName());
                System.out.println("总行数:" + Rows);
                System.out.println("总列数:" + Cols);
                for (int i = 1; i < Rows; ++i) { //从第二行开始
                    MayWordBean wordBean = new MayWordBean();
                    wordBean.setWord(sheet.getCell(0, i).getContents());
                    wordBean.setPhonogram(sheet.getCell(1, i).getContents());
                    wordBean.setWordTranslate(sheet.getCell(2, i).getContents());
                    wordBean.setSentence(sheet.getCell(3, i).getContents());
                    wordBean.setSentenceTranslate(sheet.getCell(4, i).getContents());
                    wordBean.setLevel("low");
                    wordBean.setIsNewWord(false);
                    DatabaseHelper.getHelper(context).addMayWords(context, wordBean);
//                    for (int j = 0; j < Cols; ++j) {
//                        // getCell(Col,Row)获得单元格的值
//                        System.out.print((sheet.getCell(j, i)).getContents() + "\t");
//                    }
                    System.out.print("\n");
                }
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }
        }

    }

    public static void initMySalary(Context context) {
//        if(!DatabaseHelper.getHelper(context).isMayWordDatasExist(context)){
        InputStream stream = context.getClass().getClassLoader().getResourceAsStream("assets/lessons/" + "12.xls");
        try {
            Workbook book = Workbook.getWorkbook(stream);//原文件文件
            System.out.println(">>>>>>number of sheet " + book.getNumberOfSheets());
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            System.out.println("当前工作表的名字:" + sheet.getName());
            System.out.println("总行数:" + Rows);
            System.out.println("总列数:" + Cols);
            ArrayList<String> keys = new ArrayList<>();
            ArrayList<String> keysHolarDay = new ArrayList<>();
            HashMap<String, String> inMap = new HashMap<>();  //保存上班时间
            HashMap<String, String> outMap = new HashMap<>(); //保存下班时间
            for (int i = 1; i < Rows; ++i) { //从第二行开始
                if (sheet.getCell(1, i).getContents().equals("冯小娇")) {
                    String dateTime = sheet.getCell(2, i).getContents();
                    String key = toDate(dateTime);
                    if (getWeekOfDate(dateTime).equals("星期日") || getWeekOfDate(dateTime).equals("星期六")) {
                        if (!keysHolarDay.contains(key)) {
                            keysHolarDay.add(key);
                        }
                    } else {
                        if (!keys.contains(key)) {
                            keys.add(key);
                        }
                    }
                    if (sheet.getCell(4, i).getContents().equals("上班签到")) {
                        inMap.put(key, toHourAndMin(dateTime));
                    } else if (sheet.getCell(4, i).getContents().equals("下班签退")) {
                        outMap.put(key, toHourAndMin(dateTime));
                    }
                }
            }
            long more = 0;
            long more2 = 0;
            for (String key : keys) {
                if (inMap.get(key) == null) {
                    Log.i("上下班情况", key + "没有签到");
                } else if (outMap.get(key) == null) {
                    Log.i("上下班情况", key + " 没有签退");
                } else {
                    long m1 = diffTime("08:30", inMap.get(key));
                    long m2 = diffTime("18:00", outMap.get(key));
                    if (m1 < 0) {
                        m1 = 0;
                    }
                    if (m2 < 0) {
                        m2 = 0;
                    }
                    more = more + m2;
                    Log.i("上下班情况", key + "  " + inMap.get(key) + "  " + outMap.get(key) + "   迟到：" + m1 + " 加班：" + m2);
                }
            }

            Log.i("上下班情况", "----------- 周末加班 --------------");

            for (String key : keysHolarDay) {
                if (inMap.get(key) == null) {
                    Log.i("上下班情况", key + "没有签到");
                } else if (outMap.get(key) == null) {
                    Log.i("上下班情况", key + " 没有签退");
                } else {
                    long m = diffTime(inMap.get(key), outMap.get(key));
                    more2 = more2 + m;
                    Log.i("上下班情况", key + "  " + inMap.get(key) + "  " + outMap.get(key) + "   " + m + " min");
                }
            }
            Log.i("上下班情况", "=========== 加班合计 ============= 普通加班：" + more + " min 约 "+(more/60.0f)
                    +" h" + "  周末加班 " + more2 + " min 约 "+(more2/60.0f)+" h");
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("上下班情况", "");
        } catch (BiffException e) {
            e.printStackTrace();
        }
//        }
    }

    public static void initMySalaryNew(Context context) {
//        if(!DatabaseHelper.getHelper(context).isMayWordDatasExist(context)){
        InputStream stream = context.getClass().getClassLoader().getResourceAsStream("assets/lessons/" + "201602.xls");
        try {
            Workbook book = Workbook.getWorkbook(stream);//原文件文件
            System.out.println(">>>>>>number of sheet " + book.getNumberOfSheets());
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            System.out.println("当前工作表的名字:" + sheet.getName());
            System.out.println("总行数:" + Rows);
            System.out.println("总列数:" + Cols);
            ArrayList<String> keys = new ArrayList<>();
            ArrayList<String> keysHolarDay = new ArrayList<>();
            HashMap<String, String> inMap = new HashMap<>();  //保存上班时间
            HashMap<String, String> outMap = new HashMap<>(); //保存下班时间
            for (int i = 1; i < Rows; ++i) { //从第二行开始
                if (sheet.getCell(3, i).getContents().equals("梁倩霞")) {
                    String key = sheet.getCell(4, i).getContents();
//                    if (getWeekOfDate(key).equals("星期日") || getWeekOfDate(key).equals("星期六")) {
//                        if (!keysHolarDay.contains(key)) {
//                            keysHolarDay.add(key);
//                        }
//                    } else {
                        if (!keys.contains(key)) {
                            keys.add(key);
                        }
//                    }
                    String inAndOut = sheet.getCell(6, i).getContents()+",18:00:00";
                    String[] timeInOut = inAndOut.split(",");
                    if(timeInOut != null ){
                        inMap.put(key, timeInOut[0]);
                        outMap.put(key, timeInOut[1]);
                    }

                }
            }
            long more = 0;
            long more2 = 0;
            long late = 0;
            for (String key : keys) {
                if (inMap.get(key) == null|| inMap.get(key).equals("")) {
                    Log.i("上下班情况", key + "没有签到");
                } else if (outMap.get(key) == null || outMap.get(key).equals("")) {
                    Log.i("上下班情况", key + " 没有签退");
                } else {
                    long m1 = diffTime("08:35:00", inMap.get(key));
                    long m2 = diffTime("18:00:00", outMap.get(key));
                    Log.i("","---- m2 ----"+m2);
                    if (m1 < 0) {
                        m1 = 0;
                    }else if(m1 < 30){ //迟到不足半个小时 按半个小时算
                        m1 = ((m1 / 30) + 1)*30;
                    }
                    if (m2 < 0) {
                        m2 = 0;
                    }else { //加班不足半个小时 不算
                       m2 =  (m2 / 30) * 30;
                    }
                    late    = late + m1;
                    more = more + m2;
                    Log.i("上下班情况", key + "  " + inMap.get(key) + "  " + outMap.get(key) + "   迟到：" + m1 + " 加班：" + m2);
                }
            }

            Log.i("上下班情况", "----------- 周末加班 --------------");

            for (String key : keysHolarDay) {
                if (inMap.get(key) == null) {
                    Log.i("上下班情况", key + "没有签到");
                } else if (outMap.get(key) == null) {
                    Log.i("上下班情况", key + " 没有签退");
                } else {
                    long m = diffTime(inMap.get(key), outMap.get(key));
                    more2 = more2 + m;
                    Log.i("上下班情况", key + "  " + inMap.get(key) + "  " + outMap.get(key) + "   " + m + " wmin");
                }
            }
            Log.i("上下班情况", "=========== 加班合计 ============= 普通加班（已减迟到 ("+more +"- "+late+") ）：" + (more - late) + " min 约 "+((more - late)/60.0f)+" h"
                    + "  周末加班 " + more2 + " min 约 "+(more2/60.0f)+" h");
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("上下班情况", "");
        } catch (BiffException e) {
            e.printStackTrace();
        }
//        }
    }


    /**
     * 根据日期获得星期
     *
     * @param strTime
     * @return
     */
    public static String getWeekOfDate(String strTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        java.util.Date date = null;
        try {
            date = df.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static String toDate(String strTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = df.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df1.format(date);
    }

    public static String toHourAndMin(String strTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
        java.util.Date date = null;
        try {
            date = df.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df1.format(date);

    }

    public static long diffTime(String from, String to) {

        SimpleDateFormat dfs = new SimpleDateFormat("HH:mm:ss");
        java.util.Date begin = null;
        try {
            begin = dfs.parse(from);
            java.util.Date end = dfs.parse(to);
            long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒

//            long day1=between/(24*3600);
//            long hour1=between%(24*3600)/3600;
//            long minute1=between%3600/60;
//            long second1=between%60/60;
//            System.out.println(""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒");
            return ((end.getTime() - begin.getTime()) / 1000 / 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
