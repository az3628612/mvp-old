package com.glh.tjfx.bean.line;

import java.io.Serializable;

/**
 * 线图实体
 */

public class StatisticsInfoEntity implements Serializable {
    private String proportion;
    private String trend;
    private int weightCount;
    private String timeType;

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public int getWeightCount() {
        return weightCount;
    }

    public void setWeightCount(int weightCount) {
        this.weightCount = weightCount;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }
}
