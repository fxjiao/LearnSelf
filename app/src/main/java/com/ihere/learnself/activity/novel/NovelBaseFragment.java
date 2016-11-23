package com.ihere.learnself.activity.novel;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by fxjiao on 16/10/21.
 */

public class NovelBaseFragment extends Fragment implements NovelCatalogContract.View {
    protected NovelCatalogContract.Presenter mPresenter;

    @Override
    public void setPresenter(@Nullable NovelCatalogContract.Presenter presenter) {
        mPresenter  = presenter;
    }
}
