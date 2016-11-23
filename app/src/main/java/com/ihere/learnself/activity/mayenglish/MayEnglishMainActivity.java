package com.ihere.learnself.activity.mayenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ihere.learnself.R;
import com.ihere.learnself.activity._AbstractActivity;

/**
 * Created by fxjiao on 16/3/7.
 */
public class MayEnglishMainActivity extends _AbstractActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_english);
        initView();
    }

    private void initView() {
        findViewById(R.id.word_note).setOnClickListener(this);
        findViewById(R.id.tx_essay).setOnClickListener(this);

        findViewById(R.id.word_high).setOnClickListener(this);
        findViewById(R.id.word_middle).setOnClickListener(this);
        findViewById(R.id.word_low).setOnClickListener(this);
        findViewById(R.id.word_high).setTag("high");
        findViewById(R.id.word_middle).setTag("middle");
        findViewById(R.id.word_low).setTag("low");

        findViewById(R.id.letter_a).setOnClickListener(this);
        findViewById(R.id.letter_b).setOnClickListener(this);
        findViewById(R.id.letter_c).setOnClickListener(this);
        findViewById(R.id.letter_d).setOnClickListener(this);
        findViewById(R.id.letter_e).setOnClickListener(this);
        findViewById(R.id.letter_f).setOnClickListener(this);
        findViewById(R.id.letter_g).setOnClickListener(this);
        findViewById(R.id.letter_h).setOnClickListener(this);
        findViewById(R.id.letter_i).setOnClickListener(this);
        findViewById(R.id.letter_j).setOnClickListener(this);
        findViewById(R.id.letter_k).setOnClickListener(this);
        findViewById(R.id.letter_l).setOnClickListener(this);
        findViewById(R.id.letter_m).setOnClickListener(this);
        findViewById(R.id.letter_n).setOnClickListener(this);
        findViewById(R.id.letter_o).setOnClickListener(this);
        findViewById(R.id.letter_p).setOnClickListener(this);
        findViewById(R.id.letter_q).setOnClickListener(this);
        findViewById(R.id.letter_r).setOnClickListener(this);
        findViewById(R.id.letter_s).setOnClickListener(this);
        findViewById(R.id.letter_t).setOnClickListener(this);
        findViewById(R.id.letter_u).setOnClickListener(this);
        findViewById(R.id.letter_v).setOnClickListener(this);
        findViewById(R.id.letter_w).setOnClickListener(this);
        findViewById(R.id.letter_x).setOnClickListener(this);
        findViewById(R.id.letter_y).setOnClickListener(this);
        findViewById(R.id.letter_z).setOnClickListener(this);

        findViewById(R.id.letter_a).setTag("a");
        findViewById(R.id.letter_b).setTag("b");
        findViewById(R.id.letter_c).setTag("c");
        findViewById(R.id.letter_d).setTag("d");
        findViewById(R.id.letter_e).setTag("e");
        findViewById(R.id.letter_f).setTag("f");
        findViewById(R.id.letter_g).setTag("g");
        findViewById(R.id.letter_h).setTag("h");
        findViewById(R.id.letter_i).setTag("i");
        findViewById(R.id.letter_j).setTag("j");
        findViewById(R.id.letter_k).setTag("k");
        findViewById(R.id.letter_l).setTag("l");
        findViewById(R.id.letter_m).setTag("m");
        findViewById(R.id.letter_n).setTag("n");
        findViewById(R.id.letter_o).setTag("o");
        findViewById(R.id.letter_p).setTag("p");
        findViewById(R.id.letter_q).setTag("q");
        findViewById(R.id.letter_r).setTag("r");
        findViewById(R.id.letter_s).setTag("s");
        findViewById(R.id.letter_t).setTag("t");
        findViewById(R.id.letter_u).setTag("u");
        findViewById(R.id.letter_v).setTag("v");
        findViewById(R.id.letter_w).setTag("w");
        findViewById(R.id.letter_x).setTag("x");
        findViewById(R.id.letter_y).setTag("y");
        findViewById(R.id.letter_z).setTag("z");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.word_note:
                startActivity(new Intent(MayEnglishMainActivity.this,MayWordsShowActivity.class));
                break;
            case R.id.word_high:
            case R.id.word_middle:
            case R.id.word_low:
                Bundle bundleLevel = new Bundle();
                Intent intentLevel = new Intent(MayEnglishMainActivity.this, MayWordsShowActivity.class);
                String level = (String) view.getTag();
                bundleLevel.putInt(MayWordsShowActivity.BUNDLE_KEY_WORD_TYPE, MayWordsShowActivity.BUNDLE_VALUE_WORD_TYPE_LEVEL);
                bundleLevel.putString(MayWordsShowActivity.BUNDLE_KEY_WORD_CONTENT, level);
                intentLevel.putExtra("bundle",bundleLevel);
                startActivity(intentLevel);
                break;
            case R.id.letter_a:
            case R.id.letter_b:
            case R.id.letter_c:
            case R.id.letter_d:
            case R.id.letter_e:
            case R.id.letter_f:
            case R.id.letter_g:
            case R.id.letter_h:
            case R.id.letter_i:
            case R.id.letter_j:
            case R.id.letter_k:
            case R.id.letter_l:
            case R.id.letter_m:
            case R.id.letter_n:
            case R.id.letter_o:
            case R.id.letter_p:
            case R.id.letter_q:
            case R.id.letter_r:
            case R.id.letter_s:
            case R.id.letter_t:
            case R.id.letter_u:
            case R.id.letter_v:
            case R.id.letter_w:
            case R.id.letter_x:
            case R.id.letter_y:
            case R.id.letter_z:
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MayEnglishMainActivity.this, MayWordsShowActivity.class);
                String letter = (String) view.getTag();
                bundle.putInt(MayWordsShowActivity.BUNDLE_KEY_WORD_TYPE, MayWordsShowActivity.BUNDLE_VALUE_WORD_TYPE_FIRST_LETTER);
                bundle.putString(MayWordsShowActivity.BUNDLE_KEY_WORD_CONTENT, letter);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
                break;
            case R.id.tx_essay:
                startActivity(new Intent(MayEnglishMainActivity.this, MayEssayShowActivity.class));
                break;
            default:
                break;

        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
