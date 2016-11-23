package com.ihere.learnself;

import java.util.List;

/**
 * Created by fxjiao on 16/10/21.
 */

public abstract class BaseListPresenter<T> implements BasePresenter {
    /**
     * 每页加载的数据量
     */
    protected int PAGE_SIZE = 10;
    /**
     * 起始页
     */
    protected int START_PAGE = 0;
    /**
     * 当前页
     */
    protected int currentPage = 0;
    @Override
    public void start() {

    }

    @Override
    public void loadData(boolean isShowDialog) {

    }

    /** 初始化每页加载的item数量,当前页,没有数据时显示的文字及图片等*/
    protected void initPageParams(){

    }
    protected void setListData(List<T> listData){

    }
    protected void loadMoreData(){

    }
    protected void refreshData(){
    }



}
