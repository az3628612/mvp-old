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

public interface SiteLineCurrentService {

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.SITE_LINE_CURRENT_DAY)
    Observable<JsonResponse<CurrentLineEntity>> statisticsSearchSiteLineCurrentDay(@Query("coalunit") String coalunit,
                                                                                   @Query("wellhead") String wellhead,
                                                                                   @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.SITE_LINE_CURRENT_MONTH)
    Observable<JsonResponse<CurrentLineEntity>> statisticsSearchSiteLineCurrentMonth(@Query("coalunit") String coalunit,
                                                                                     @Query("wellhead") String wellhead,
                                                                                     @Query("CoalLevel") String CoalLevel);

    /**
     * @param coalunit  站点
     * @param wellhead  井口
     * @param CoalLevel 煤等
     */
    @GET(URLs.SITE_LINE_CURRENT_YEAR)
    Observable<JsonResponse<CurrentLineEntity>> statisticsSearchSiteLineCurrentYear(@Query("coalunit") String coalunit,
                                                                                    @Query("wellhead") String wellhead,
                                                                                    @Query("CoalLevel") String CoalLevel);
}
