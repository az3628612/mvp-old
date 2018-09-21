package com.glh.tjfx.service;

import com.glh.tjfx.app.URLs;
import com.glh.tjfx.base.JsonResponse;
import com.glh.tjfx.bean.pie.CurrentPieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 分答 详细页 接口服务
 */

public interface SitePieCurrentService {

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.SITE_PIE_CURRENT_DAY)
    Observable<JsonResponse<CurrentPieEntity>> statisticsSearchSitePieCurrentDay(@Query("coalunit") String coalunit,
                                                                                 @Query("wellhead") String wellhead,
                                                                                 @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.SITE_PIE_CURRENT_MONTH)
    Observable<JsonResponse<CurrentPieEntity>> statisticsSearchSitePieCurrentMonth(@Query("coalunit") String coalunit,
                                                                                    @Query("wellhead") String wellhead,
                                                                                    @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.SITE_PIE_CURRENT_YEAR)
    Observable<JsonResponse<CurrentPieEntity>> statisticsSearchSitePieCurrentYear(@Query("coalunit") String coalunit,
                                                                                   @Query("wellhead") String wellhead,
                                                                                   @Query("CoalLevel") String CoalLevel);
}
