package com.glh.tjfx.service;

import com.glh.tjfx.app.URLs;
import com.glh.tjfx.base.JsonResponse;
import com.glh.tjfx.bean.QueryConditionCoalEntity;
import com.glh.tjfx.bean.QueryConditionSiteEntity;
import com.glh.tjfx.bean.QueryConditionWellEntity;
import com.glh.tjfx.bean.line.CurrentLineEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 分答 详细页 接口服务
 */

public interface QueryConditionService {

    @GET(URLs.QUERY_CONDITION_WELL)
    Observable<JsonResponse<List<QueryConditionWellEntity>>> queryConditionWell();

    @GET(URLs.QUERY_CONDITION_SITE)
    Observable<JsonResponse<List<QueryConditionSiteEntity>>> queryConditionSite();

    @GET(URLs.QUERY_CONDITION_COAL)
    Observable<JsonResponse<List<QueryConditionCoalEntity>>> queryConditionCoal();
}
