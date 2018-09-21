package com.glh.tjfx.presenter.impl;

import com.glh.tjfx.base.BasePresenter;
import com.glh.tjfx.bean.QueryConditionCoalEntity;
import com.glh.tjfx.bean.QueryConditionSiteEntity;
import com.glh.tjfx.bean.QueryConditionWellEntity;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.presenter.IDataPresenter;
import com.glh.tjfx.presenter.IMainPresenter;
import com.glh.tjfx.rx.JsonResponseSubscriber;
import com.glh.tjfx.service.QueryConditionService;
import com.glh.tjfx.ui.interfaces.IDataView;
import com.glh.tjfx.ui.interfaces.IMainView;

import java.util.List;

/**
 * 主页面 网络请求业务实现
 */

public class MainPresenterImpl extends BasePresenter<IMainView> implements IMainPresenter {
    private QueryConditionService queryConditionService;
    @Override
    protected void initService() {
        queryConditionService = getService(QueryConditionService.class);
    }


    @Override
    public void getQueryCondition() {
        getWell();
        getSite();
        getCoal();
    }

    /**
     * 获取井口筛选条件
     */
    private void getWell() {
        subscribeMap(getView(), queryConditionService.queryConditionWell(),
                new JsonResponseSubscriber<List<QueryConditionWellEntity>>(getView()) {
                    @Override
                    public void onNext(List<QueryConditionWellEntity> list) {
                        getView().setQueryConditionWell(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取井口筛选条件
     */
    private void getSite() {
        subscribeMap(getView(), queryConditionService.queryConditionSite(),
                new JsonResponseSubscriber<List<QueryConditionSiteEntity>>(getView()) {
                    @Override
                    public void onNext(List<QueryConditionSiteEntity> list) {
                        getView().setQueryConditionSite(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取井口筛选条件
     */
    private void getCoal() {
        subscribeMap(getView(), queryConditionService.queryConditionCoal(),
                new JsonResponseSubscriber<List<QueryConditionCoalEntity>>(getView()) {
                    @Override
                    public void onNext(List<QueryConditionCoalEntity> list) {
                        getView().setQueryConditionCoal(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
