package com.ihere.learnself.activity.mayfinancial;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ihere.learnself.R;
import com.ihere.learnself.activity._AbstractActivity;

import java.io.File;

/**
 * Created by fxjiao on 16/3/7.
 */
public class MayFinancialMainActivity extends _AbstractActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_financial);
        initView();
    }

    private void initView() {
        (findViewById(R.id.financial_one)).setOnClickListener(this);
        (findViewById(R.id.financial_two)).setOnClickListener(this);
        (findViewById(R.id.financial_three)).setOnClickListener(this);
        (findViewById(R.id.financial_four)).setOnClickListener(this);
        (findViewById(R.id.financial_five)).setOnClickListener(this);
        (findViewById(R.id.financial_six)).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        test();
//        String filePath = "file:///android_asset/financial/test.pdf" ;
//
//        switch (view.getId()) {
//            case R.id.financial_one:
//                filePath = "http://IP/android_asset/";
//                File file   = new File(filePath);
//                if(file.exists()){
//                    Log.i("","--- onclick --- exists "+filePath);
//                }else {
//                    Log.i("","--- onclick --- noe exists "+filePath);
//                }
//                break;
//           case R.id.financial_two:
//               filePath = "file:///android_asset/financial/test";
//               File file1   = new File(filePath);
//               if(file1.exists()){
//                   Log.i("","--- onclick --- exists"+filePath);
//               }else {
//                   Log.i("","--- onclick --- noe exists"+filePath);
//               }
//                break;
//           case R.id.financial_three:
////               filePath = "file:///assets/financial/partthree.pdf";
//               filePath = "file:///android_asset/financial/test.pdf";
//               File file2   = new File(filePath);
//               if(file2.exists()){
//                   Log.i("","--- onclick --- exists "+filePath);
//               }else {
//                   Log.i("","--- onclick --- noe exists "+filePath);
//               }
//                break;
//           case R.id.financial_four:
////               filePath = "file:///android_asset/financial/partfour.pdf";
//               filePath = "file:///android_asset/financial/yahoo.txt";
//               File file3   = new File(filePath);
//               if(file3.exists()){
//                   Log.i("","--- onclick --- exists "+filePath);
//               }else {
//                   Log.i("","--- onclick --- noe exists "+filePath);
//               }
//                break;
//           case R.id.financial_five:
//               filePath = "file:///android_asset/financial/partfive";
//               filePath = "file:///android_asset/lessons.txt";
//               File file4   = new File(filePath);
//               if(file4.exists()){
//                   Log.i("","--- onclick --- exists "+filePath);
//               }else {
//                   Log.i("","--- onclick --- noe exists "+filePath);
//               }
//                break;
//            case R.id.financial_six:
////                filePath = "file:///android_asset/financial/partsix.pdf";
//                filePath = "file:///android_asset/financial/yahoo.txt";
//                File file5   = new File(filePath);
//                if(file5.exists()){
//                    Log.i("","--- onclick --- exists "+filePath);
//                }else {
//                    Log.i("","--- onclick --- noe exists "+filePath);
//                }
//                break;
//
//            default:
//                break;
//        }
//        try {
////            startActivity(getPdfFileIntent(filePath));
////            openPdf();
//        }catch (ActivityNotFoundException ex){
//            Toast.makeText(this,"没有找到文件",Toast.LENGTH_SHORT).show();
//        }

    }

    public  Intent getPdfFileIntent(String param)
    {
        Log.i("","----- url ----"+param);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri localUri = Uri.fromFile(new File(param));
        intent.setDataAndType(localUri, "application/pdf");
        return intent;
    }


    private final String[][] MIME_MapTable={
            //{后缀名，MIME类型}
            {".3gp",    "video/3gpp"},
            {".apk",    "application/vnd.android.package-archive"},
            {".asf",    "video/x-ms-asf"},
            {".avi",    "video/x-msvideo"},
            {".bin",    "application/octet-stream"},
            {".bmp",    "image/bmp"},
            {".c",  "text/plain"},
            {".class",  "application/octet-stream"},
            {".conf",   "text/plain"},
            {".cpp",    "text/plain"},
            {".doc",    "application/msword"},
            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls",    "application/vnd.ms-excel"},
            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe",    "application/octet-stream"},
            {".gif",    "image/gif"},
            {".gtar",   "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h",  "text/plain"},
            {".htm",    "text/html"},
            {".html",   "text/html"},
            {".jar",    "application/java-archive"},
            {".java",   "text/plain"},
            {".jpeg",   "image/jpeg"},
            {".jpg",    "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log",    "text/plain"},
            {".m3u",    "audio/x-mpegurl"},
            {".m4a",    "audio/mp4a-latm"},
            {".m4b",    "audio/mp4a-latm"},
            {".m4p",    "audio/mp4a-latm"},
            {".m4u",    "video/vnd.mpegurl"},
            {".m4v",    "video/x-m4v"},
            {".mov",    "video/quicktime"},
            {".mp2",    "audio/x-mpeg"},
            {".mp3",    "audio/x-mpeg"},
            {".mp4",    "video/mp4"},
            {".mpc",    "application/vnd.mpohun.certificate"},
            {".mpe",    "video/mpeg"},
            {".mpeg",   "video/mpeg"},
            {".mpg",    "video/mpeg"},
            {".mpg4",   "video/mp4"},
            {".mpga",   "audio/mpeg"},
            {".msg",    "application/vnd.ms-outlook"},
            {".ogg",    "audio/ogg"},
            {".pdf",    "application/pdf"},
            {".png",    "image/png"},
            {".pps",    "application/vnd.ms-powerpoint"},
            {".ppt",    "application/vnd.ms-powerpoint"},
            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop",   "text/plain"},
            {".rc", "text/plain"},
            {".rmvb",   "audio/x-pn-realaudio"},
            {".rtf",    "application/rtf"},
            {".sh", "text/plain"},
            {".tar",    "application/x-tar"},
            {".tgz",    "application/x-compressed"},
            {".txt",    "text/plain"},
            {".wav",    "audio/x-wav"},
            {".wma",    "audio/x-ms-wma"},
            {".wmv",    "audio/x-ms-wmv"},
            {".wps",    "application/vnd.ms-works"},
            {".xml",    "text/plain"},
            {".z",  "application/x-compress"},
            {".zip",    "application/x-zip-compressed"},
            {"",        "*/*"}
    };

    /**
     * 根据文件后缀名获得对应的MIME类型。
     * @param file
     */
    private String getMIMEType(File file) {

        String type="*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if(dotIndex < 0){
            return type;
        }
    /* 获取文件的后缀名*/
        String end=fName.substring(dotIndex,fName.length()).toLowerCase();
        if(end=="")return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for(int i=0;i<MIME_MapTable.length;i++){ //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if(end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    /**
     * 打开文件
     * @param file
     */
    private void openFile(File file){

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
        //跳转
        startActivity(intent);     //这里最好try一下，有可能会报错。 //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
    }

    private void test(){
        AssetManager am = getAssets();//获得该应用的AssetManager
//        am.open();
        String[] locales = am.getLocales();
        if(locales != null && locales.length > 0){
            for (String str : locales){
                Log.i("","--- onclick --- local : "+str);
            }
        }
    }

}
