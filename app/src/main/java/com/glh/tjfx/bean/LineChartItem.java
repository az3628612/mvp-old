
package com.glh.tjfx.bean;

import android.content.Context;

import com.github.mikephil.charting.data.ChartData;

import static com.glh.tjfx.app.Constants.TYPE_LINE_CHART;

public class LineChartItem extends ChartItem {



    public LineChartItem(ChartData<?> cd, Context c, int contentType) {
        super(cd);

        super.contentType = contentType;
    }

    @Override
    public int getItemChartType() {
        return TYPE_LINE_CHART;
    }

    @Override
    public int getItemContentType() {
        return contentType;
    }

}
