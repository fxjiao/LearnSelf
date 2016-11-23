package com.ihere.learnself.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihere.learnself.R;
import com.ihere.learnself.bean.MayEssayBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fxjiao on 16/2/26.
 */
public class MayEssayAdapter extends PagerAdapter{

    private Context mContext;
    private List<MayEssayBean> listMayEssay;
    private LayoutInflater mInflater;
    private boolean isFromNote = false;

    public MayEssayAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        listMayEssay = new LinkedList<>();
    }

    public void setData(List<MayEssayBean> listMayEssay) {
        this.listMayEssay = listMayEssay;
        notifyDataSetChanged();
    }

    public void setIsFromNote (Boolean isFromNote){
        this.isFromNote  = isFromNote;
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return listMayEssay.size();
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
        RelativeLayout layout = (RelativeLayout) mInflater.inflate(R.layout.view_may_essay, null);
        TextView txNumGuard = (TextView) layout.findViewById(R.id.tx_may_essay_num_guard);
        TextView txTitle = (TextView) layout.findViewById(R.id.tx_may_essay_title);
        TextView txContent = (TextView) layout.findViewById(R.id.tx_may_essay_content);

        MayEssayBean mayEssayBean = listMayEssay.get(position);
        txNumGuard.setText((position + 1) + " / " + listMayEssay.size());

        txTitle.setText(mayEssayBean.getTile());
        txContent.setText(mayEssayBean.getContent());

        view.addView(layout);
        return layout;
    }


}
