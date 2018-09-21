package com.glh.tjfx.bean.line;

import java.io.Serializable;
import java.util.List;

/**
 * 线图实体
 */

public class CurrentLineEntity implements Serializable{


    private LegendEntity legend;
    private XAxisEntity xAxis;
    private List<SeriesEntity> series;
    private StatisticsInfoEntity statisticsInfo;

    public LegendEntity getLegend() {
        return legend;
    }

    public void setLegend(LegendEntity legend) {
        this.legend = legend;
    }

    public XAxisEntity getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxisEntity xAxis) {
        this.xAxis = xAxis;
    }

    public List<SeriesEntity> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesEntity> series) {
        this.series = series;
    }

    public StatisticsInfoEntity getStatisticsInfo() {
        return statisticsInfo;
    }

    public void setStatisticsInfo(StatisticsInfoEntity statisticsInfo) {
        this.statisticsInfo = statisticsInfo;
    }

}
