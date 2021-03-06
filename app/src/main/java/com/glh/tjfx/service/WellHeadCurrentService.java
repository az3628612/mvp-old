package com.glh.tjfx.service;

import com.glh.tjfx.app.URLs;
import com.glh.tjfx.base.JsonResponse;
import com.glh.tjfx.bean.line.CurrentLineEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 分答 详细页 接口服务
 */

public interface WellHeadCurrentService {

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.WELL_HEAD_CURRENT_DAY)
    Observable<JsonResponse<CurrentLineEntity>> statisticsSearchWellHeadCurrentDay(@Query("coalunit") String coalunit,
                                                                                   @Query("wellhead") String wellhead,
                                                                                   @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.WELL_HEAD_CURRENT_MONTH)
    Observable<JsonResponse<CurrentLineEntity>> statisticsSearchWellHeadCurrentMonth(@Query("coalunit") String coalunit,
                                                                                     @Query("wellhead") String wellhead,
                                                                                     @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.WELL_HEAD_CURRENT_YEAR)
    Observable<JsonResponse<CurrentLineEntity>> statisticsSearchWellHeadCurrentYear(@Query("coalunit") String coalunit,
                                                                                    @Query("wellhead") String wellhead,
                                                                                    @Query("CoalLevel") String CoalLevel);
}
