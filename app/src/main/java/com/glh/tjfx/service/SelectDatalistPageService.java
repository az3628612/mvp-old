package com.glh.tjfx.service;

import com.glh.tjfx.app.URLs;
import com.glh.tjfx.base.JsonResponse;
import com.glh.tjfx.bean.DataEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 分答 详细页 接口服务
 */

public interface SelectDatalistPageService {
    /**
     * @param start     页数
     * @param length    每页条数
     * @param selectTimeType 时间 (currentDay,currentMonth,currentYear)
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.SELECT_DATA_LIST_PAGE)
    Observable<DataEntity> selectDataListPage(@Query("start") int start,
                                                            @Query("length") int length,
                                                            @Query("selectTimeType") String selectTimeType,
                                                            @Query("coalunit") String coalunit,
                                                            @Query("wellhead") String wellhead,
                                                            @Query("CoalLevel") String CoalLevel
    );
}
