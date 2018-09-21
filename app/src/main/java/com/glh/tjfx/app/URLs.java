package com.glh.tjfx.app;

/**
 * URL路径处理类
 *
 * @author Hunter
 */
public class URLs {
    public static final String BASE_URL = "";

    public static final String GLH_TRANSPORT = "GLH_Transport/";
    public static final String STATISTICS_CONTROLLER = "statisticsController/";

    public static final String STATISTICS_SEARCH = BASE_URL + GLH_TRANSPORT + STATISTICS_CONTROLLER;


    /**
     * 1.
     * 当日产量同线性图（井口）
     */
    public static final String CURRENT_DAY = STATISTICS_SEARCH + "statisticsSearchCurrentDay";
    /**
     * 4.
     * 当月产量同线性图（井口）
     */
    public static final String CURRENT_MONTH = STATISTICS_SEARCH + "statisticsSearchCurrentMonth";
    /**
     * 7.
     * 当年产量同线性图（井口）
     */
    public static final String CURRENT_YEAR = STATISTICS_SEARCH + "statisticsSearchCurrentYear";


    /**
     * 2.
     * 当日产量分井口同线性图（井口）
     */
    public static final String WELL_HEAD_CURRENT_DAY = STATISTICS_SEARCH + "statisticsSearchWellHeadCurrentDay";
    /**
     * 5.
     * 当月产量同线性图（井口）
     */
    public static final String WELL_HEAD_CURRENT_MONTH = STATISTICS_SEARCH + "statisticsSearchWellHeadCurrentMonth";
    /**
     * 8.
     * 当年产量分井口同线性图（井口）
     */
    public static final String WELL_HEAD_CURRENT_YEAR = STATISTICS_SEARCH + "statisticsSearchWellHeadCurrentYear";


    /**
     * 3.
     * 当日产量分井口饼状图（井口）
     */
    public static final String WELL_HEAD_PIE_CURRENT_DAY = STATISTICS_SEARCH + "statisticsSearchWellHeadPieCurrentDay";
    /**
     * 6.
     * 当月产量分井口饼状图（井口）
     */
    public static final String WELL_HEAD_PIE_CURRENT_MONTH = STATISTICS_SEARCH + "statisticsSearchWellHeadPieCurrentMonth";
    /**
     * 9.
     * 当年产量分井口饼状图（井口）
     */
    public static final String WELL_HEAD_PIE_CURRENT_YEAR = STATISTICS_SEARCH + "statisticsSearchWellHeadPieCurrentYear";


    /**
     * 10.
     * 当日产量同线性图（站点）
     */
    public static final String SITE_SAME_LINE_CURRENT_DAY = STATISTICS_SEARCH + "statisticsSearchSiteSameLineCurrentDay";
    /**
     * 13.
     * 当月产量同线性图（站点）
     */
    public static final String SITE_SAME_LINE_CURRENT_MONTH = STATISTICS_SEARCH + "statisticsSearchSiteSameLineCurrentMonth";
    /**
     * 16.
     * 当年产量同线性图（站点）
     */
    public static final String SITE_SAME_LINE_CURRENT_YEAR = STATISTICS_SEARCH + "statisticsSearchSiteSameLineCurrentYear";


    /**
     * 11.
     * 当日产量分站点同线性图（站点）
     */
    public static final String SITE_LINE_CURRENT_DAY = STATISTICS_SEARCH + "statisticsSearchSiteLineCurrentDay";
    /**
     * 14.
     * 当月产量分站点同线性图（站点）
     */
    public static final String SITE_LINE_CURRENT_MONTH = STATISTICS_SEARCH + "statisticsSearchSiteLineCurrentMonth";
    /**
     * 17.
     * 当年产量分站点同线性图（站点）
     */
    public static final String SITE_LINE_CURRENT_YEAR = STATISTICS_SEARCH + "statisticsSearchSiteLineCurrentYear";


    /**
     * 12.
     * 当日产量分站点饼状图（站点）
     */
    public static final String SITE_PIE_CURRENT_DAY = STATISTICS_SEARCH + "statisticsSearchSitePieCurrentDay";
    /**
     * 15.
     * 当月产量分站点饼状图（站点）
     */
    public static final String SITE_PIE_CURRENT_MONTH = STATISTICS_SEARCH + "statisticsSearchSitePieCurrentMonth";
    /**
     * 18.
     * 当年产量分站点饼状图（站点）
     */
    public static final String SITE_PIE_CURRENT_YEAR = STATISTICS_SEARCH + "statisticsSearchSitePieCurrentYear";


    /**
     * 19.
     * 数据列表
     */
    public static final String SELECT_DATA_LIST_PAGE = STATISTICS_SEARCH + "selectDataListPage";


    /**
     * 全部井口
     */
    public static final String QUERY_CONDITION_WELL = BASE_URL + GLH_TRANSPORT + "wellhead/queryAllWellHeadList";

    /**
     * 全部站点
     */
    public static final String QUERY_CONDITION_SITE = BASE_URL + GLH_TRANSPORT + "site/queryAllSiteList";

    /**
     * 全部煤等级
     */
    public static final String QUERY_CONDITION_COAL = BASE_URL + GLH_TRANSPORT + "coal/queryAllCoalList";


}
