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
public class MayWordsShowActivity extends _AbstractActivity {
    public final static String BUNDLE_KEY_WORD_TYPE = "word_type";
    public final static int BUNDLE_VALUE_WORD_TYPE_LEVEL = 0;//根据等级显示
    public final static int BUNDLE_VALUE_WORD_TYPE_FIRST_LETTER = 1;//根据首字母显示
    public final static String BUNDLE_KEY_WORD_CONTENT = "content";
    private ViewPager vpMayWords;
    private MayWordsAdapter mayWordsAdapter;
    private LinearLayout layGuard;
    private EditText editFrom;
    private EditText editTo;
    private Button button;
    int from = 0, to = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_words);
        InitData.initAllWords(this);
        initView();
        initData();
    }

    private void initView() {

        layGuard = (LinearLayout) findViewById(R.id.lay_guard);
        editFrom = (EditText) findViewById(R.id.edit_from);
        editTo = (EditText) findViewById(R.id.edit_to);
        button = (Button) findViewById(R.id.btn_confirm);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (editFrom.getText() != null) {
//                    from = Integer.valueOf(editFrom.getText().toString());
//                }
//                if (editTo.getText() != null) {
//                    to = Integer.valueOf(editTo.getText().toString());
//                }
//                mayWordsAdapter = new MayWordsAdapter(MayWordsShowActivity.this);
//                vpMayWords.setAdapter(mayWordsAdapter);
////                mayWordsAdapter.setData(DatabaseHelper.getHelper(MayWordsShowActivity.this).getOrderMayWords(MayWordsShowActivity.this, from, to));
//                layGuard.setVisibility(View.GONE);
//            }
//        });

        vpMayWords = (ViewPager) findViewById(R.id.vp_may_words);
        layGuard.setVisibility(View.GONE);
    }

    private void initData() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null ) {
            mayWordsAdapter = new MayWordsAdapter(MayWordsShowActivity.this);

            int key = bundle.getInt(BUNDLE_KEY_WORD_TYPE, -1);
            if (key == BUNDLE_VALUE_WORD_TYPE_LEVEL) {
                String level = bundle.getString(BUNDLE_KEY_WORD_CONTENT);
                mayWordsAdapter.setData(DatabaseHelper.getHelper(MayWordsShowActivity.this).getMayWordsByLevel(MayWordsShowActivity.this, level));
            } else if (key == BUNDLE_VALUE_WORD_TYPE_FIRST_LETTER) {
                String firstLetter = bundle.getString(BUNDLE_KEY_WORD_CONTENT);
                mayWordsAdapter.setData(DatabaseHelper.getHelper(MayWordsShowActivity.this).getMayWordsByFirstLetter(MayWordsShowActivity.this, firstLetter));
            }

            vpMayWords.setAdapter(mayWordsAdapter);
        }
    }

}
