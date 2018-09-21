package com.glh.tjfx.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.glh.tjfx.R;
import com.glh.tjfx.app.BaseApplication;
import com.glh.tjfx.app.Constants;
import com.glh.tjfx.bean.PieChartItem;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.bean.line.SeriesEntity;
import com.glh.tjfx.bean.line.StatisticsInfoEntity;
import com.glh.tjfx.bean.pie.CurrentPieEntity;
import com.glh.tjfx.bean.pie.SeriesDataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/9/28.
 */

public class StationAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private LayoutInflater mLayoutInfater;
    private List mList;
    private ArrayList<Integer> colors;
    private StationListner listner;

    public StationAdapter(Context context, List list) {
        mContext = context;
        mLayoutInfater = LayoutInflater.from(context);
        mList = list;
        getViewColor();
    }

    public void setOnWellListner(StationListner listner) {
        this.listner = listner;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new KeyIndexViewHolder(mLayoutInfater.inflate(R.layout.item_key_index, parent, false));
        } else if (viewType == 1 || viewType == 2) {
            return new LineViewHolder(mLayoutInfater.inflate(R.layout.item_line_chart, parent, false));
        } else if (viewType == 3) {
            return new PieViewHolder(mLayoutInfater.inflate(R.layout.item_pie_chart, parent, false));
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mList.get(position) == null) {
            return;
        }
        if (position == 0) {
            KeyIndexViewHolder keyIndexViewHolder = (KeyIndexViewHolder) holder;
            StatisticsInfoEntity entity = (StatisticsInfoEntity) mList.get(0);

            keyIndexViewHolder.txt3.setText(entity.getWeightCount() + "");
            SpannableStringBuilder style;
            if (TextUtils.equals(entity.getTrend(), "up")) {
                style = new SpannableStringBuilder("日" + "↑" + entity.getProportion());
                style.setSpan(new ForegroundColorSpan(Color.RED), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                style = new SpannableStringBuilder("日" + "↓" + entity.getProportion());
                style.setSpan(new ForegroundColorSpan(Color.GREEN), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            keyIndexViewHolder.txt5.setText(style);
        } else if (position == 1) {
            LineViewHolder lineViewHolder = (LineViewHolder) holder;
            CurrentLineEntity currentLineEntity = (CurrentLineEntity) mList.get(1);
            List<SeriesEntity> seriesEntities = currentLineEntity.getSeries();

            lineViewHolder.chart.getDescription().setEnabled(false);

            //自定义x轴显示
            XAxis xAxis = lineViewHolder.chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setEnabled(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            for (int j = 0; j < seriesEntities.size(); j++) {
                ArrayList<Entry> valsComp = new ArrayList<>();
                if (seriesEntities.get(j).getData() == null) break;
                for (int i = 0; i < seriesEntities.get(j).getData().length; i++) {
                    valsComp.add(new Entry(i, seriesEntities.get(j).getData()[i]));
                }
                LineDataSet setComp = new LineDataSet(valsComp, seriesEntities.get(j).getName());
                setComp.setColor(colors.get(j));
                dataSets.add(setComp);
            }
            LineData data = new LineData(dataSets);
            data.setDrawValues(true);
            lineViewHolder.chart.setData(data);
            lineViewHolder.chart.invalidate();
            lineViewHolder.txtShowBig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.goActivity(Constants.FORM_SAME_TIME);
                }
            });
        } else if (position == 2) {
            LineViewHolder lineViewHolder = (LineViewHolder) holder;
            CurrentLineEntity currentLineEntity = (CurrentLineEntity) mList.get(2);
            List<SeriesEntity> seriesEntities = currentLineEntity.getSeries();

            lineViewHolder.chart.getDescription().setEnabled(false);

            //自定义x轴显示
            XAxis xAxis = lineViewHolder.chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setEnabled(false);

            // 获取pieCahrt图列
            Legend l = lineViewHolder.chart.getLegend();
            l.setEnabled(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            for (int j = 0; j < seriesEntities.size(); j++) {
                ArrayList<Entry> valsComp = new ArrayList<>();
                if (seriesEntities.get(j).getData() == null) break;
                for (int i = 0; i < seriesEntities.get(j).getData().length; i++) {
                    valsComp.add(new Entry(i, seriesEntities.get(j).getData()[i]));
                }
                LineDataSet setComp = new LineDataSet(valsComp, seriesEntities.get(j).getName());
                if (j < colors.size()) {
                    setComp.setColor(colors.get(j));
                } else {
                    setComp.setColor(colors.get(j-colors.size()));
                }
                dataSets.add(setComp);
            }
            LineData data = new LineData(dataSets);
            data.setDrawValues(true);
            lineViewHolder.chart.setData(data);
            lineViewHolder.chart.invalidate();

            lineViewHolder.txtShowBig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.goActivity(Constants.FORM_LINE);
                }
            });
        } else if (position == 3) {
            PieViewHolder pieViewHolder = (PieViewHolder) holder;

            CurrentPieEntity currentPieEntity = (CurrentPieEntity) mList.get(3);

            List<SeriesDataEntity> seriesDataEntities = currentPieEntity.getSeries().get(0).getData();
            PieChartItem pieChartItem = new PieChartItem(generateDataPie(seriesDataEntities),
                    BaseApplication.getInstance(), Constants.FORM_PIE, generateCenterText());

            // apply styling
            pieViewHolder.chart.getDescription().setEnabled(false);
            pieViewHolder.chart.setHoleRadius(52f);
            pieViewHolder.chart.setTransparentCircleRadius(57f);
            pieViewHolder.chart.setCenterText(pieChartItem.getmCenterText());
            pieViewHolder.chart.setCenterTextSize(9f);
            pieViewHolder.chart.setUsePercentValues(true);
            pieViewHolder.chart.setExtraOffsets(5, 10, 50, 10);

            pieChartItem.getmChartData().setValueFormatter(new PercentFormatter());
            pieChartItem.getmChartData().setValueTextSize(11f);
            pieChartItem.getmChartData().setValueTextColor(Color.WHITE);
            // set data
            pieViewHolder.chart.setData((PieData) pieChartItem.getmChartData());

            Legend l = pieViewHolder.chart.getLegend();
            l.setEnabled(false);

            // do not forget to refresh the chart
            // holder.chart.invalidate();
            pieViewHolder.chart.animateY(900);

            pieViewHolder.txtShowBig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.goActivity(Constants.FORM_PIE);
                }
            });
        }
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
        SpannableString s = new SpannableString("分站点统计");
//        s.setSpan(new RelativeSizeSpan(1.6f), 0, 14, 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[0]), 0, 14, 0);
//        s.setSpan(new RelativeSizeSpan(.9f), 14, 25, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, 25, 0);
        s.setSpan(new RelativeSizeSpan(1.4f), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0, s.length(), 0);
        return s;
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

    public interface StationListner {
        void goActivity(int type);
    }

    class KeyIndexViewHolder extends RecyclerView.ViewHolder {


        TextView txt3;
        TextView txt4;
        TextView txt5;
        TextView txt6;

        public KeyIndexViewHolder(View itemView) {
            super(itemView);
            txt3 = itemView.findViewById(R.id.txt_3);
            txt4 = itemView.findViewById(R.id.txt_4);
            txt5 = itemView.findViewById(R.id.txt_5);
            txt6 = itemView.findViewById(R.id.txt_6);
        }
    }

    class LineViewHolder extends RecyclerView.ViewHolder {
        LineChart chart;
        TextView txtShowBig;

        LineViewHolder(View itemView) {
            super(itemView);
            chart = itemView.findViewById(R.id.chart);
            txtShowBig = itemView.findViewById(R.id.txt_show_big);
        }
    }

    class PieViewHolder extends RecyclerView.ViewHolder {
        PieChart chart;
        TextView txtShowBig;

        PieViewHolder(View itemView) {
            super(itemView);
            chart = itemView.findViewById(R.id.chart);
            txtShowBig = itemView.findViewById(R.id.txt_show_big);
        }
    }
}
