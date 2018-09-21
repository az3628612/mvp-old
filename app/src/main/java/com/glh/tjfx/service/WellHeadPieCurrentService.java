package com.glh.tjfx.service;

import com.glh.tjfx.app.URLs;
import com.glh.tjfx.base.JsonResponse;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.bean.pie.CurrentPieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 分答 详细页 接口服务
 */

public interface WellHeadPieCurrentService {

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.WELL_HEAD_PIE_CURRENT_DAY)
    Observable<JsonResponse<CurrentPieEntity>> statisticsSearchWellHeadPieCurrentDay(@Query("coalunit") String coalunit,
                                                                                     @Query("wellhead") String wellhead,
                                                                                     @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.WELL_HEAD_PIE_CURRENT_MONTH)
    Observable<JsonResponse<CurrentPieEntity>> statisticsSearchWellHeadPieCurrentMonth(@Query("coalunit") String coalunit,
                                                                                        @Query("wellhead") String wellhead,
                                                                                        @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.WELL_HEAD_PIE_CURRENT_YEAR)
    Observable<JsonResponse<CurrentPieEntity>> statisticsSearchWellHeadPieCurrentYear(@Query("coalunit") String coalunit,
                                                                                       @Query("wellhead") String wellhead,
                                                                                       @Query("CoalLevel") String CoalLevel);
}
