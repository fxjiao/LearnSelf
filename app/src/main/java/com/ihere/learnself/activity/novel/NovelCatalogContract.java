package com.ihere.learnself.activity.novel;

import com.ihere.learnself.BaseListPresenter;
import com.ihere.learnself.BasePresenter;
import com.ihere.learnself.BaseView;
import com.ihere.learnself.bean.NovelBean;

/**
 * Created by fxjiao on 16/10/21.
 */

public interface NovelCatalogContract {
    interface View extends BaseView<Presenter> {

    }

    abstract class Presenter extends BaseListPresenter<NovelBean> {
        public abstract void getData(String url);
    }
}
