package com.ihere.learnself.activity.mayenglish;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ihere.learnself.R;
import com.ihere.learnself.activity._AbstractActivity;
import com.ihere.learnself.adapter.MayEssayAdapter;
import com.ihere.learnself.bean.MayEssayBean;
import com.ihere.learnself.util.InitData;
import com.ihere.learnself.util.ResourceUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fxjiao on 16/2/23.
 */
public class MayEssayShowActivity extends _AbstractActivity {
    private ViewPager vpMayEssay;
    private MayEssayAdapter mayEssayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_essay);
        InitData.initAllWords(this);
        initView();
        initData();
    }

    private void initView() {
        vpMayEssay = (ViewPager) findViewById(R.id.vp_may_essay);
        mayEssayAdapter = new MayEssayAdapter(this);

    }

    private void initData() {
        vpMayEssay.setAdapter(mayEssayAdapter);
        List<MayEssayBean> listMayEssay = new LinkedList<>();

        for(int i = 1; i <= 10; i ++){
            MayEssayBean   essayBean    = new MayEssayBean();
            int titleId = ResourceUtil.getStringId(this,"composition_"+i+"_title");
            int contentId = ResourceUtil.getStringId(this,"composition_"+i+"_content");
            if(titleId > 0 && contentId > 0){
                essayBean.setTile(getString(titleId));
                essayBean.setContent(getString(contentId));
                listMayEssay.add(essayBean);
            }
        }
        mayEssayAdapter.setData(listMayEssay);
    }

}
