package com.ihere.learnself.activity.novel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ihere.learnself.R;
import com.ihere.learnself.bean.NovelBean;
import com.ihere.learnself.helper.DatabaseHelper;

import java.util.List;

/**
 * Created by fxjiao on 16/10/21.
 */

public class NovelCatalogFragment extends Fragment implements NovelCatalogContract.View{
    private NovelCatalogContract.Presenter mPresenter;
    private ListView listView;
    private CatalogAdapter catalogAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_catalog,container,false);
        listView = (ListView) root.findViewById(R.id.lv_catalog);
        return root;
    }

    @Override
    public void setPresenter(NovelCatalogContract.Presenter presenter) {
        mPresenter    = presenter;
    }



    private static class CatalogAdapter extends BaseAdapter {
        private List<NovelBean> listNovel ;

        public CatalogAdapter(List<NovelBean> listNovel) {
//            listNovel = DatabaseHelper.getHelper(CatalogActivity.this).getAllChapters(CatalogActivity.this);
            this.listNovel  = listNovel;
        }

        private void replaceData(List<NovelBean> listNovel){
            this.listNovel  = listNovel;
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
                convertView = new TextView(parent.getContext());
                convertView.setPadding(15, 15, 15, 15);
            }
            NovelBean novelBean = listNovel.get(position);
            ((TextView) convertView).setText(novelBean.getTitle());
            convertView.setTag(novelBean);
            if(novelBean.getState() == 1){
                convertView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.item_downed));
            }else if(novelBean.getState() == 2){
                convertView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.item_read));
            }else {
                convertView.setBackgroundColor(Color.WHITE);
            }
            return convertView;
        }
    }
}
