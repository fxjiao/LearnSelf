package com.ihere.learnself.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by fxjiao on 16/10/14.
 */
@DatabaseTable(tableName = "tb_novel")
public class NovelBean {
    @DatabaseField(columnName = "id",generatedId = true)
    private int id;
    /** 下载顺序,只在下载过程中有用*/
    @DatabaseField(columnName = "num")
    private int num;
    /** 章节标题*/
    @DatabaseField(columnName = "title")
    private String title;
    /** 章节内容*/
    @DatabaseField(columnName = "Content")
    private String Content;
    /** 下载链接*/
    @DatabaseField(columnName = "link")
    private String link;
    /** 是否已读*/
    @DatabaseField(columnName = "state")
    private int state;//0.未下载  1.已下载  2.已读
    /** 上章链接*/
    @DatabaseField(columnName = "perLink")
    private String perLink;
    /** 下章链接*/
    @DatabaseField(columnName = "nextLink")
    private String nextLink;
    /** 书名*/
    @DatabaseField(columnName = "bookName")
    private String bookName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPerLink() {
        return perLink;
    }

    public void setPerLink(String perLink) {
        this.perLink = perLink;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
