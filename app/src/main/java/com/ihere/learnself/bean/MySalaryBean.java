package com.ihere.learnself.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by fxjiao on 16/2/23.
 */
@DatabaseTable(tableName = "tb_may_words")
public class MySalaryBean {
    @DatabaseField(columnName = "id",generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "date")
    private String date;
    @DatabaseField(columnName = "week")
    private String week;
    @DatabaseField(columnName = "inTime")
    private String inTime;//签到时间
    @DatabaseField(columnName = "outTime")
    private String outTime;//签退时间
    @DatabaseField(columnName = "lateMin")
    private String lateMin;//迟到分钟
    @DatabaseField(columnName = "moreMin")
    private String moreMin;//加班时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getLateMin() {
        return lateMin;
    }

    public void setLateMin(String lateMin) {
        this.lateMin = lateMin;
    }

    public String getMoreMin() {
        return moreMin;
    }

    public void setMoreMin(String moreMin) {
        this.moreMin = moreMin;
    }
}
