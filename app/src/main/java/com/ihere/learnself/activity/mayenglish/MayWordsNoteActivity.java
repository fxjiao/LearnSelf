package com.ihere.learnself.activity.mayenglish;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ihere.learnself.R;
import com.ihere.learnself.activity._AbstractActivity;
import com.ihere.learnself.adapter.MayWordsAdapter;
import com.ihere.learnself.helper.DatabaseHelper;
import com.ihere.learnself.util.InitData;

/**
 * Created by fxjiao on 16/2/23.
 */
public class MayWordsNoteActivity extends _AbstractActivity {
    private ViewPager vpMayWords;
    private MayWordsAdapter mayWordsAdapter;
    private LinearLayout layGuard;
    private EditText editFrom;
    private EditText editTo;
    private Button button;
    int from = 0,to = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_words);
        InitData.initAllWords(this);
        initView();
    }

    private void initView(){

        layGuard = (LinearLayout)findViewById(R.id.lay_guard);
        editFrom = (EditText)findViewById(R.id.edit_from);
        editTo = (EditText)findViewById(R.id.edit_to);
        button = (Button)findViewById(R.id.btn_confirm);
        vpMayWords  = (ViewPager)findViewById(R.id.vp_may_words);

        mayWordsAdapter = new MayWordsAdapter(MayWordsNoteActivity.this);
        mayWordsAdapter.setIsFromNote(true);
        vpMayWords.setAdapter(mayWordsAdapter);
        mayWordsAdapter.setData(DatabaseHelper.getHelper(MayWordsNoteActivity.this).getAllNoteMayWords(MayWordsNoteActivity.this));
        layGuard.setVisibility(View.GONE);
    }


}
