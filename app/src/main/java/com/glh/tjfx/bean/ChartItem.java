package com.glh.tjfx.bean;

import com.github.mikephil.charting.data.ChartData;

/**
 * baseclass of the chart-listview items
 *
 * @author philipp
 */
public abstract class ChartItem {

    public static final int TYPE_LINE_CHART = 0;
    public static final int TYPE_PIE_CHART = 1;

    public static final int TYPE_SAME_TIME = 0;
    public static final int TYPE_WELL = 1;
    public static final int TYPE_STATION = 2;

    protected ChartData<?> mChartData;
    protected int contentType;


    public ChartItem(ChartData<?> cd) {
        this.mChartData = cd;
    }

    public ChartData<?> getmChartData() {
        return mChartData;
    }

    public abstract int getItemChartType();

    public abstract int getItemContentType();
}
