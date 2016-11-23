package com.ihere.learnself.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ihere.learnself.R;
import com.ihere.learnself.activity.mayenglish.MayWordsNoteActivity;
import com.ihere.learnself.bean.MayWordBean;
import com.ihere.learnself.helper.DatabaseHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fxjiao on 16/2/26.
 */
public class MayWordsAdapter extends PagerAdapter implements View.OnClickListener {

    private Context mContext;
    private List<MayWordBean> listMayWord;
    private LayoutInflater mInflater;
    private boolean isFromNote = false;

    public MayWordsAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        listMayWord = new LinkedList<>();
    }

    public void setData(List<MayWordBean> listMayWord) {
        this.listMayWord = listMayWord;
        notifyDataSetChanged();
    }

    public void setIsFromNote (Boolean isFromNote){
        this.isFromNote  = isFromNote;
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return listMayWord.size();
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        RelativeLayout layout = (RelativeLayout) mInflater.inflate(R.layout.view_may_word, null);
        TextView txNumGuard = (TextView) layout.findViewById(R.id.tx_num_guard);
        TextView txWord = (TextView) layout.findViewById(R.id.tx_word);
        TextView txChinese = (TextView) layout.findViewById(R.id.tx_chinese);
        TextView txProgram = (TextView) layout.findViewById(R.id.tx_program);
        TextView txSentence = (TextView) layout.findViewById(R.id.tx_sentence);
        TextView txSentenceChinese = (TextView) layout.findViewById(R.id.tx_sentence_chinese);
        Button btnAdd = (Button) layout.findViewById(R.id.btn_add_new);
        Button btnOpenNote = (Button) layout.findViewById(R.id.btn_open_new);

        MayWordBean mayWordBean = listMayWord.get(position);
        txNumGuard.setText(position + " / " + listMayWord.size());
        txWord.setText(mayWordBean.getWord());
        txChinese.setText(mayWordBean.getWordTranslate());
        txProgram.setText(mayWordBean.getPhonogram());
        txSentence.setText(mayWordBean.getSentence());
        txSentenceChinese.setText(mayWordBean.getSentenceTranslate());

        if(isFromNote){
            btnAdd.setText("移出生词本");
            btnOpenNote.setVisibility(View.GONE);
        }
        btnAdd.setOnClickListener(this);
        btnAdd.setTag(mayWordBean);
        btnOpenNote.setOnClickListener(this);

        view.addView(layout);
        return layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_new:
                MayWordBean wordBean = (MayWordBean) view.getTag();
                if(isFromNote){
                    wordBean.setIsNewWord(false);
                    if ((DatabaseHelper.getHelper(mContext).updateMayWord(mContext, wordBean))) {
                        listMayWord.remove(wordBean);
                        Toast.makeText(mContext,"已移除",Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }else {
                        Toast.makeText(mContext,"移除失败",Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                if(!wordBean.isNewWord() ){
                    wordBean.setIsNewWord(true);
                    if ((DatabaseHelper.getHelper(mContext).updateMayWord(mContext, wordBean))) {
                        Toast.makeText(mContext,"已添加",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mContext,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(mContext,"已存在",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_open_new:
                Intent intent   = new Intent(mContext, MayWordsNoteActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }
}
