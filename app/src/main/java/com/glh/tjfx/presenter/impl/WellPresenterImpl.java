package com.glh.tjfx.presenter.impl;

import android.text.TextUtils;

import com.glh.tjfx.base.BasePresenter;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.bean.pie.CurrentPieEntity;
import com.glh.tjfx.presenter.IWellPresenter;
import com.glh.tjfx.rx.JsonResponseSubscriber;
import com.glh.tjfx.service.CurrentService;
import com.glh.tjfx.service.WellHeadCurrentService;
import com.glh.tjfx.service.WellHeadPieCurrentService;
import com.glh.tjfx.ui.activity.MainActivity;
import com.glh.tjfx.ui.interfaces.IWellView;

/**
 * 按站点显示 fragment 网络请求业务实现类
 */

public class WellPresenterImpl extends BasePresenter<IWellView> implements IWellPresenter {

    private CurrentService currentService;
    private WellHeadCurrentService wellHeadCurrentService;
    private WellHeadPieCurrentService wellHeadPieCurrentService;

    @Override
    protected void initService() {
        currentService = getService(CurrentService.class);
        wellHeadCurrentService = getService(WellHeadCurrentService.class);
        wellHeadPieCurrentService = getService(WellHeadPieCurrentService.class);
    }

    @Override
    public void queryContent(final String type, String coalunit, String wellhead, String coalLevel) {
        if (TextUtils.equals(type, MainActivity.QUERY_CONDITION_STATUS[0])) {
            getDataByDay(type, coalunit, wellhead, coalLevel);
        } else if (TextUtils.equals(type, MainActivity.QUERY_CONDITION_STATUS[1])) {
            getDataByMonth(type, coalunit, wellhead, coalLevel);
        } else if (TextUtils.equals(type, MainActivity.QUERY_CONDITION_STATUS[2])) {
            getDataByYear(type, coalunit, wellhead, coalLevel);
        }
    }

    /**
     * 获取当日数据
     */
    private void getDataByDay(final String type, String coalunit, String wellhead, String coalLevel) {
        getSameTimeDay(type, coalunit, wellhead, coalLevel);
        getLineDay(type, coalunit, wellhead, coalLevel);
        getPieDay(type, coalunit, wellhead, coalLevel);
    }

    /**
     * 获取当月数据
     */
    private void getDataByMonth(final String type, String coalunit, String wellhead, String coalLevel) {
        getSameTimeMonth(type, coalunit, wellhead, coalLevel);
        getLineMonth(type, coalunit, wellhead, coalLevel);
        getPieMonth(type, coalunit, wellhead, coalLevel);
    }

    /**
     * 获取当年数据
     */
    private void getDataByYear(final String type, String coalunit, String wellhead, String coalLevel) {
        getSameTimeYear(type, coalunit, wellhead, coalLevel);
        getLineYear(type, coalunit, wellhead, coalLevel);
        getPieYear(type, coalunit, wellhead, coalLevel);
    }

    /**
     * 获取当日 日期同比线形图
     */
    private void getSameTimeDay(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), currentService.statisticsSearchCurrentDay(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentLineEntity>(getView()) {
                    @Override
                    public void onNext(CurrentLineEntity entity) {
                        getView().setSameContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当月 日期同比线形图
     */
    private void getSameTimeMonth(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), currentService.statisticsSearchCurrentMonth(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentLineEntity>(getView()) {
                    @Override
                    public void onNext(CurrentLineEntity entity) {
                        getView().setSameContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当年 日期同比线形图
     */
    private void getSameTimeYear(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), currentService.statisticsSearchCurrentYear(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentLineEntity>(getView()) {
                    @Override
                    public void onNext(CurrentLineEntity entity) {
                        getView().setSameContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当日 井口同比线形图
     */
    private void getLineDay(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), wellHeadCurrentService.statisticsSearchWellHeadCurrentDay(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentLineEntity>(getView()) {
                    @Override
                    public void onNext(CurrentLineEntity entity) {
                        getView().setLineContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当月 井口同比线形图
     */
    private void getLineMonth(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), wellHeadCurrentService.statisticsSearchWellHeadCurrentMonth(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentLineEntity>(getView()) {
                    @Override
                    public void onNext(CurrentLineEntity entity) {
                        getView().setLineContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当年 井口同比线形图
     */
    private void getLineYear(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), wellHeadCurrentService.statisticsSearchWellHeadCurrentYear(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentLineEntity>(getView()) {
                    @Override
                    public void onNext(CurrentLineEntity entity) {
                        getView().setLineContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当日 井口同比饼形图
     */
    private void getPieDay(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), wellHeadPieCurrentService.statisticsSearchWellHeadPieCurrentDay(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentPieEntity>(getView()) {
                    @Override
                    public void onNext(CurrentPieEntity entity) {
                        getView().setPieContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当月 井口同比饼形图
     */
    private void getPieMonth(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), wellHeadPieCurrentService.statisticsSearchWellHeadPieCurrentMonth(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentPieEntity>(getView()) {
                    @Override
                    public void onNext(CurrentPieEntity entity) {
                        getView().setPieContent(entity, type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取当年 井口同比饼形图
     */
    private void getPieYear(final String type, String coalunit, String wellhead, String coalLevel) {
        subscribeMap(getView(), wellHeadPieCurrentService.statisticsSearchWellHeadPieCurrentYear(coalunit, wellhead, coalLevel),
                new JsonResponseSubscriber<CurrentPieEntity>(getView()) {
                    @Override
                    public void onNext(CurrentPieEntity entity) {
                        getView().setPieContent(entity, type);

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
