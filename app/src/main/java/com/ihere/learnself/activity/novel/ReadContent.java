package com.ihere.learnself.activity.novel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.ihere.learnself.R;
import com.ihere.learnself.activity._AbstractActivity;
import com.ihere.learnself.bean.NovelBean;
import com.ihere.learnself.helper.DatabaseHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by fxjiao on 16/10/14.
 */

public class ReadContent extends _AbstractActivity {
    private static String BASE_URL    = "http://m.biquku.com";
    private static int MAX_DOWN_TIMES = 50;//每个链接每次下载失败后可以重复下载的次数

    private LinearLayout layWhole;
    private ScrollView scrollView;
    private  TextView txContent,txTitle,txNext,txPre;
    private NovelBean novelBean;

    private AQuery aQuery  ;
    private  int downTimesCount = 0;//同一个链接下载次数
    private  int downCount = 20;//下载章数
    private String nextLink;//下一章的链接
    private ArrayList<String> failedLinks = new ArrayList<>();//下载失败的链接
//    private LinkedHashMap<String,Element> lMapContents = new LinkedHashMap<>();
    LinkedList<Element> lListContent = new LinkedList<>();
    private Handler handler;
    private boolean isNight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_read_content);

        layWhole    = (LinearLayout)findViewById(R.id.lay_content);
        scrollView  = (ScrollView)findViewById(R.id.scroll_read_content);
        txContent = (TextView) findViewById(R.id.tx_read_content);
        txTitle = (TextView) findViewById(R.id.tx_title);
        txNext = (TextView) findViewById(R.id.tx_next);
        txPre = (TextView) findViewById(R.id.tx_pre);

        txPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(novelBean != null && novelBean .getPerLink() != null){
                    initData(novelBean .getPerLink());
                }
            }
        });

        txNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(novelBean != null && novelBean .getNextLink() != null){
                    initData(novelBean .getNextLink());
                }
            }
        });

        if(getIntent() != null){
            String link = getIntent().getStringExtra("link");
            isNight = getIntent().getBooleanExtra("isNight",false);
            initData(link);
        }
//        aQuery  = new AQuery(this);
//
//        getData("/3/3226/3522584.html");
//        handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                String url  = (String)msg.obj;
//                getData(url);
//                return false;
//            }
//        });
    }

    private void initData(String link){
        novelBean = DatabaseHelper.getHelper(ReadContent.this).getChapterByLink(ReadContent.this,link);
        if(novelBean != null ){
            txTitle.setText(Html.fromHtml(novelBean.getTitle()));
            txContent.setText(Html.fromHtml(novelBean.getContent()));
            novelBean.setState(2);
            scrollView.scrollTo(0,0);
            DatabaseHelper.getHelper(ReadContent.this).addChapter(ReadContent.this,novelBean);
        }

        if(isNight){
            layWhole.setBackgroundColor(getResources().getColor(R.color.black_tran));
        }else {
            layWhole.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }

    private void getData(final String url0){
        //注意判断:1. 超过每次最大重复次数 则停止该次下载,并保存至下载失败队列 最后再重新下载
        //2.如果下载到最大的下载章 则开始下载失败队列
        //3.如果下载的章数超过最新的章节(或者是最后一章),则开始下载失败队列
        //4.
        if(downTimesCount > MAX_DOWN_TIMES){
            if(!failedLinks.contains(url0)){
                failedLinks.add(url0);
            }
            nextLink    = "";
            downTimesCount  = 0;
        }
        if(lListContent.size() >= downCount){
            showData();
            return;
        }
        downTimesCount ++;
       final String targetUrl = BASE_URL +url0;
        aQuery.ajax(targetUrl, InputStream.class,new AjaxCallback<InputStream>(){
            @Override
            public void callback(String url, InputStream object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.i("","--- url ---"+downTimesCount+"  "+url);
                if(object != null ){
                    BufferedReader in = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(object, "GBK"));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = in.readLine()) != null) {
                            buffer.append(line);
                        }
                        String result = buffer.toString();
                        parse(result);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Message message = new Message();
                    message.what = 0;
                    message.obj = url0;
                    handler.sendMessageDelayed(message,1000);
                }
            }
        });
    }

    private void parse(String strContent) {
        Document doc = Jsoup.parse(strContent);
        Element title = doc.getElementById("nr_title");
        Element content = doc.getElementById("nr1");
        if (title != null && content != null) {
            lListContent.add(content);
            Element next    = doc.getElementById("pb_next");
            if(next != null){
                String linkNext = next.attr("href");
                nextLink    = linkNext;
                downTimesCount  = 0;
            }
            showData();
        }
        getData(nextLink);
    }

    private void showData(){
        String strContent   ="";
        int count = 0;
        if(lListContent != null){
            for(Element content : lListContent){
                strContent  = strContent + "\br\br --------------------------------------"+count+" " +content.toString() ;
                count++;
            }
        }
        txContent.setText(Html.fromHtml(strContent));
    }
}
