
package com.glh.tjfx.bean;

import android.content.Context;
import android.text.SpannableString;

import com.github.mikephil.charting.data.ChartData;

import static com.glh.tjfx.app.Constants.TYPE_PIE_CHART;

public class PieChartItem extends ChartItem {

    private SpannableString mCenterText;

    public PieChartItem(ChartData<?> cd, Context c, int contentType, SpannableString centerText) {
        super(cd);
        mCenterText = centerText;
        super.contentType = contentType;
    }

    @Override
    public int getItemChartType() {
        return TYPE_PIE_CHART;
    }

    @Override
    public int getItemContentType() {
        return contentType;
    }

    public SpannableString getmCenterText() {
        return mCenterText;
    }
}
