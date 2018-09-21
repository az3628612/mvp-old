package com.glh.tjfx.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.glh.tjfx.R;
import com.glh.tjfx.base.BaseActivity;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.presenter.impl.SameTimePresenterImpl;
import com.glh.tjfx.ui.interfaces.ISameTimeView;
import com.glh.tjfx.ui.widget.MyXFormatter;

import java.util.ArrayList;

/**
 * Created by Yang on 2017/10/26.
 */

public class SameTimeActivity extends BaseActivity<ISameTimeView, SameTimePresenterImpl> implements ISameTimeView {

    private ArrayList<Integer> colors;

    @Override
    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        // 返回按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        CurrentLineEntity currentLineEntity = (CurrentLineEntity) intent.getSerializableExtra("data");

        // 获取布局实例
        LineChart chart = findViewById(R.id.chart);

        // 初始化颜色
        getViewColor();

        String[] xStr = currentLineEntity.getxAxis().getData();

        //自定义x轴显示
        MyXFormatter formatter = new MyXFormatter(xStr);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        //显示个数
        xAxis.setLabelCount(currentLineEntity.getxAxis().getData().length);
        xAxis.setValueFormatter(formatter);
        xAxis.setAvoidFirstLastClipping(true);

        if(xStr[0].length() >2) {
            xAxis.setLabelRotationAngle(-90f);
        }
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setWordWrapEnabled(true);
        l.setDrawInside(false);
        l.setYEntrySpace(0f);
        l.setYEntrySpace(5f);
        l.setYOffset(5f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int j = 0; j < currentLineEntity.getSeries().size(); j++) {
            ArrayList<Entry> valsComp = new ArrayList<>();
            if (currentLineEntity.getSeries().get(j).getData() == null) break;
            for (int i = 0; i < currentLineEntity.getSeries().get(j).getData().length; i++) {
                valsComp.add(new Entry(i, currentLineEntity.getSeries().get(j).getData()[i]));
            }
            LineDataSet setComp = new LineDataSet(valsComp, currentLineEntity.getSeries().get(j).getName());
            setComp.setColor(colors.get(j));
            dataSets.add(setComp);
        }
        LineData data = new LineData(dataSets);
        data.setDrawValues(true);
        chart.setData(data);
        chart.invalidate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected SameTimePresenterImpl getPresenter() {
        return new SameTimePresenterImpl();
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_line;
    }

    public void getViewColor() {
        colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
    }
}
