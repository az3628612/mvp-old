package com.glh.tjfx.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.glh.tjfx.R;
import com.glh.tjfx.app.BaseApplication;
import com.glh.tjfx.app.Constants;
import com.glh.tjfx.base.BaseActivity;
import com.glh.tjfx.bean.PieChartItem;
import com.glh.tjfx.bean.pie.CurrentPieEntity;
import com.glh.tjfx.bean.pie.SeriesDataEntity;
import com.glh.tjfx.presenter.impl.SameTimePresenterImpl;
import com.glh.tjfx.ui.interfaces.ISameTimeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/10/26.
 */

public class PieActivity extends BaseActivity<ISameTimeView,SameTimePresenterImpl> implements ISameTimeView{

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
        CurrentPieEntity currentPieEntity = (CurrentPieEntity) intent.getSerializableExtra("data");

        // 获取布局实例
        PieChart chart = findViewById(R.id.chart);

        // 初始化颜色
        getViewColor();

        List<SeriesDataEntity> seriesDataEntities = currentPieEntity.getSeries().get(0).getData();
        PieChartItem pieChartItem = new PieChartItem(generateDataPie(seriesDataEntities),
                BaseApplication.getInstance(), Constants.FORM_PIE, generateCenterText());

        // apply styling
        chart.getDescription().setEnabled(false);
        chart.setHoleRadius(52f);
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText(pieChartItem.getmCenterText());
        chart.setCenterTextSize(9f);
        chart.setUsePercentValues(true);
        chart.setExtraOffsets(5, 10, 50, 10);

        pieChartItem.getmChartData().setValueFormatter(new PercentFormatter());
        pieChartItem.getmChartData().setValueTextSize(11f);
        pieChartItem.getmChartData().setValueTextColor(Color.WHITE);
        // set data
        chart.setData((PieData) pieChartItem.getmChartData());

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setWordWrapEnabled(true);
        l.setDrawInside(false);
        l.setYEntrySpace(0f);
        l.setYEntrySpace(5f);
        l.setYOffset(5f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateY(900);
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
        return R.layout.activity_pie;
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

    private PieData generateDataPie(List<SeriesDataEntity> seriesDataEntities) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < seriesDataEntities.size(); i++) {
            entries.add(new PieEntry(seriesDataEntities.get(i).getValue(), seriesDataEntities.get(i).getName()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
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
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        return data;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("分井口统计");
//        s.setSpan(new RelativeSizeSpan(1.6f), 0, 14, 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[0]), 0, 14, 0);
//        s.setSpan(new RelativeSizeSpan(.9f), 14, 25, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, 25, 0);
        s.setSpan(new RelativeSizeSpan(1.4f), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0, s.length(), 0);
        return s;
    }
}
