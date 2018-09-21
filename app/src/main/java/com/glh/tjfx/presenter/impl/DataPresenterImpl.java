package com.glh.tjfx.presenter.impl;

import com.glh.tjfx.base.BasePresenter;
import com.glh.tjfx.bean.DataEntity;
import com.glh.tjfx.presenter.IDataPresenter;
import com.glh.tjfx.rx.JsonResponseSubscriber;
import com.glh.tjfx.service.SelectDatalistPageService;
import com.glh.tjfx.ui.interfaces.IDataView;

/**
 * 数据列表 fragment 网络请求业务实现类
 */

public class DataPresenterImpl extends BasePresenter<IDataView> implements IDataPresenter {
    private SelectDatalistPageService selectDatalistPageService;

    @Override
    protected void initService() {
        selectDatalistPageService = getService(SelectDatalistPageService.class);
    }

    @Override
    public void queryContent(int start, int length, String selectTimeType, String coalunit, String wellhead, String coalLevel) {
        subscribeMap1(getView(), selectDatalistPageService.selectDataListPage(start, length, selectTimeType, coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<DataEntity>(getView()) {
                    @Override
                    public void onNext(DataEntity entity) {
                        getView().setContent(entity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });




    }
}
