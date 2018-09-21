package com.glh.tjfx.ui.widget;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Yang on 2017/10/25.
 */

public class MyXFormatter implements IAxisValueFormatter {

    private String[] mValues;

    public MyXFormatter(String[] values) {
        this.mValues = values;
    }

    private static final String TAG = "MyXFormatter";

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        return mValues[(int) value];
    }
}