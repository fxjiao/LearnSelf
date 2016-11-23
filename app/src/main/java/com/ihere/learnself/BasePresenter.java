package com.ihere.learnself;

/**
 * Created by fxjiao on 16/10/21.
 */
public interface BasePresenter {
    void start();

    /**
     * 加载数据
     *
     * @param isShowDialog 是否需要弹框
     */
    void loadData(boolean isShowDialog);
}
