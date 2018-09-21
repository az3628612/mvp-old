package com.glh.tjfx.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.glh.tjfx.app.BaseApplication;
import com.glh.tjfx.app.Constants;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.bean.LineChartItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yang on 2017/10/25.
 */

public class PackagingData {



    private String[] wells = {"露采", "二分区", "徐殿阁", "李恒", "赵辉", "七分区", "山推基地", "十一作业组", "洗煤厂", "矸石煤", "分选厂"};
    private String[] hours = {"0:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
    private String[] times = {"今天", "昨天", "七天前", "30天前"};


    /**
     * generates a random ChartData object with just one DataSet
     */
    private LineChartItem generateDataLine(CurrentLineEntity entity) {

        ArrayList<ILineDataSet> sets = new ArrayList<>();

        for (int a = 0; a < entity.getLegend().getData().length; a++) {
            ArrayList<Entry> e1 = new ArrayList<>();

            for (int i = 0; i < entity.getxAxis().getData().length; i++) {
                e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
            }

            LineDataSet d1 = new LineDataSet(e1, times[a]);
            d1.setLineWidth(2.5f);
            d1.setCircleRadius(4.5f);
            d1.setHighLightColor(Color.rgb(244, 117, 117));
            d1.setDrawValues(false);
            sets.add(d1);
        }
        LineData cd = new LineData(sets);
        return new LineChartItem(cd, BaseApplication.getInstance(), Constants.FORM_SAME_TIME);
    }


    public void packagingIconData() {
        List list = new ArrayList();
        list.add(new Object());
        //list.add(new LineChartItem(generateDataLine(), BaseApplication.getInstance(), Constants.FORM_SAME_TIME));
        //list.add(new LineChartItem(generateDataLine(wells), BaseApplication.getInstance(), Constants.FORM_LINE));
        //list.add(new PieChartItem(generateDataPie(), BaseApplication.getInstance(), Constants.FORM_PIE, generateCenterText()));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("list", list);
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("MPAndroidChart\ncreated by\nPhilipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.6f), 0, 14, 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[0]), 0, 14, 0);
        s.setSpan(new RelativeSizeSpan(.9f), 14, 25, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, 25, 0);
        s.setSpan(new RelativeSizeSpan(1.4f), 25, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 25, s.length(), 0);
        return s;
    }

    /**
     * generates a random ChartData object with just one DataSet
     * 使用一个数据集生成一个随机的ChartData对象
     *
     * @return lineData
     */
    private LineData generateDataLine(String[] cnt) {
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        ArrayList<LineDataSet> d1 = new ArrayList<>();

        for (int j = 0; j < cnt.length; j++) {

            for (int i = 0; i < hours.length; i++) {
                e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
            }

            LineDataSet d = new LineDataSet(e1, cnt[j]);
            d.setLineWidth(2.5f);
            d.setCircleRadius(4.5f);
            d.setHighLightColor(Color.rgb(244, 117, 117));
            d.setDrawValues(false);
            d1.add(d);
        }

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.addAll(d1);

        LineData cd = new LineData(sets);
        return cd;
    }




    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e2.add(new Entry(i, e1.get(i).getY() - 30));
        }

        LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);

        LineData cd = new LineData(sets);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     * 使用一个数据集生成一个随机的ChartData对象
     *
     * @return
     */
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < wells.length; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), wells[i]));
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

}
