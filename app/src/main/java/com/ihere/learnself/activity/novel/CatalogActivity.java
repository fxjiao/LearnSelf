package com.ihere.learnself.activity.novel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.ihere.learnself.R;
import com.ihere.learnself.bean.NovelBean;
import com.ihere.learnself.hdjob.ExcelUtil;
import com.ihere.learnself.helper.DatabaseHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by fxjiao on 16/10/14.
 */

public class CatalogActivity extends Activity {
//    private static String BASE_URL = "http://m.biquku.com";//择天记
    private static String BASE_URL = "http://www.00ksw.com/html/0/71/";//将夜
    private AQuery aQuery;
    private int downTimesCount = 0;
    private Handler handler;
    private ListView listView;
    private CheckBox cboxIsNight;
    private CatalogAdapter catalogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        cboxIsNight = (CheckBox)findViewById(R.id.cbox_day_night);
        listView = (ListView) findViewById(R.id.lv_catalog);
        aQuery = new AQuery(this);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
//                getData();
                getData("将夜");
                return false;
            }
        });

        if (DatabaseHelper.getHelper(CatalogActivity.this).getAllChapters(CatalogActivity.this) == null ||
                DatabaseHelper.getHelper(CatalogActivity.this).getAllChapters(CatalogActivity.this).size() == 0) {
//            getData();
            getData("将夜");
        } else {
            catalogAdapter = new CatalogAdapter();
            listView.setAdapter(catalogAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getTag() != null && view.getTag() instanceof NovelBean) {
                    NovelBean novelBean = (NovelBean) view.getTag();
                    if (novelBean.getContent() == null || novelBean.getContent().equals("")) {
                        getContent(novelBean);
                    }else {
                        Intent intent   = new Intent(CatalogActivity.this,ReadContent.class);
                        intent.putExtra("link",novelBean.getLink());
                        intent.putExtra("isNight",cboxIsNight.isChecked());
                        CatalogActivity.this.startActivity(intent);
                    }
                }
            }
        });

        cboxIsNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ExcelUtil.readExcel(CatalogActivity.this);
                }
            }
        });
    }

    private void getData() {
        if (downTimesCount > 20) {
            return;
        }
        downTimesCount++;
        aQuery.ajax("http://m.biquku.com/3/3226/", InputStream.class, new AjaxCallback<InputStream>() {
            @Override
            public void callback(String url, InputStream object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.i("", "--- url ---" + downTimesCount + "  " + url);
                if (object != null) {
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
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        });
    }

    private void getData(String name) {
        if (downTimesCount > 20) {
            return;
        }
        downTimesCount++;
        aQuery.ajax("http://www.00ksw.com/html/0/71/", InputStream.class, new AjaxCallback<InputStream>() {
            @Override
            public void callback(String url, InputStream object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.i("", "--- url ---" + downTimesCount + "  " + url);
                if (object != null) {
                    BufferedReader in = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(object, "GBK"));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = in.readLine()) != null) {
                            buffer.append(line);
                        }
                        String result = buffer.toString();
                        parse(result,"将夜");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        });
    }

    private void parse(String strContent) {
        int num = 0;
        Document doc = Jsoup.parse(strContent);
        Elements catalogs = doc.getElementsByClass("chapter");
        if (catalogs != null) {
            for (Element catalog : catalogs) {
                Elements elements = catalog.getElementsByTag("a");
                if (elements != null) {
                    for (Element element : elements) {
                        NovelBean novelBean = new NovelBean();
                        novelBean.setBookName("择天记");
                        novelBean.setLink(element.attr("href"));
                        novelBean.setTitle(element.ownText());
                        novelBean.setNum(num);
                        DatabaseHelper.getHelper(CatalogActivity.this).addChapter(CatalogActivity.this, novelBean);
                        num++;
                    }
                }
            }
        }
        if (catalogAdapter == null) {
            catalogAdapter = new CatalogAdapter();
            listView.setAdapter(catalogAdapter);
        } else {
            catalogAdapter.notifyDataSetChanged();
        }
    }


    private void parse(String strContent,String bookName) {
        int num = 0;
        Document doc = Jsoup.parse(strContent);
        Element catalogsElement = doc.getElementById("list");
        if (catalogsElement != null) {
                Elements elements = catalogsElement.getElementsByTag("a");
                if (elements != null) {
                    for (Element element : elements) {
                        NovelBean novelBean = new NovelBean();
                        novelBean.setBookName(bookName);
                        novelBean.setLink(element.attr("href"));
                        novelBean.setTitle(element.ownText());
                        novelBean.setNum(num);
                        DatabaseHelper.getHelper(CatalogActivity.this).addChapter(CatalogActivity.this, novelBean);
                        num++;
                    }
                }
            }
        if (catalogAdapter == null) {
            catalogAdapter = new CatalogAdapter();
            listView.setAdapter(catalogAdapter);
        } else {
            catalogAdapter.notifyDataSetChanged();
        }
    }


    private void getContent(final NovelBean novelBean) {
        final String targetUrl = BASE_URL + novelBean.getLink();
        aQuery.ajax(targetUrl, InputStream.class, new AjaxCallback<InputStream>() {
            @Override
            public void callback(String url, InputStream object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.i("", "--- url ---" + downTimesCount + "  " + url);
                if (object != null) {
                    BufferedReader in = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(object, "GBK"));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = in.readLine()) != null) {
                            buffer.append(line);
                        }
                        String result = buffer.toString();
//                        parseContent(result,novelBean);
                        parseContent(result,novelBean,"将夜");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void parseContent(String strContent, NovelBean novelBean) {
        Document doc = Jsoup.parse(strContent);
        Element title = doc.getElementById("nr_title");
        Element content = doc.getElementById("nr1");
        if (title != null && content != null) {
            novelBean.setContent(content.toString());
            novelBean.setState(1);
            Element next    = doc.getElementById("pb_next");
            novelBean.setNextLink(next.attr("href"));
            Element per    = doc.getElementById("pb_prev");
            novelBean.setPerLink(per.attr("href"));

            DatabaseHelper.getHelper(CatalogActivity.this).addChapter(CatalogActivity.this,novelBean);
        }
    }

    private void parseContent(String strContent, NovelBean novelBean,String bookName) {
        Document doc = Jsoup.parse(strContent);
        Element title = doc.getElementsByClass("bookname").get(0);
        Element content = doc.getElementById("content");
        Elements pres  =  doc.getElementsMatchingOwnText("上一章");
        Elements nexts  =  doc.getElementsMatchingOwnText("下一章");
        if (title != null && content != null) {
            novelBean.setContent(content.toString());
            novelBean.setState(1);

            if(nexts != null && nexts.size() > 0){
                Element next    = nexts.get(0);
                String nextLine = next.attr("href");
                novelBean.setNextLink(nextLine);
            }

            if(pres != null && pres.size() > 0){
                Element pre    = pres.get(0);
                String strPre = pre.attr("href");
                novelBean.setPerLink(strPre);
            }

            DatabaseHelper.getHelper(CatalogActivity.this).addChapter(CatalogActivity.this,novelBean);
        }
    }


    class CatalogAdapter extends BaseAdapter {
        private List<NovelBean> listNovel;

        public CatalogAdapter() {
            listNovel = DatabaseHelper.getHelper(CatalogActivity.this).getAllChapters(CatalogActivity.this);
        }

        @Override
        public int getCount() {
            return listNovel.size();
        }

        @Override
        public Object getItem(int position) {
            return listNovel.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null || convertView instanceof TextView) {
                convertView = new TextView(CatalogActivity.this);
                convertView.setPadding(15, 15, 15, 15);
            }
            NovelBean novelBean = listNovel.get(position);
            ((TextView) convertView).setText(novelBean.getTitle());
            convertView.setTag(novelBean);
            if(novelBean.getState() == 1){
                convertView.setBackgroundColor(getResources().getColor(R.color.item_downed));
            }else if(novelBean.getState() == 2){
                convertView.setBackgroundColor(getResources().getColor(R.color.item_read));
            }else {
                convertView.setBackgroundColor(Color.WHITE);
            }
            return convertView;
        }
    }
}