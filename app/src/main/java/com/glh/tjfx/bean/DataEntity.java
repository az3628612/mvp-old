package com.glh.tjfx.bean;

import com.glh.tjfx.bean.DataListEntity;

import java.util.List;

/**
 * 数据列表实体类
 */

public class DataEntity {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<DataListEntity> data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<DataListEntity> getData() {
        return data;
    }

    public void setData(List<DataListEntity> data) {
        this.data = data;
    }
}
