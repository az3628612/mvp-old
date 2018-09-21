package com.glh.tjfx.bean;

/**
 * Created by Yang on 2017/10/31.
 */

public class QueryConditionWellEntity {
    private int SortId;
    private int WellHeadId;
    private String WellHeadName;

    public int getSortId() {
        return SortId;
    }

    public void setSortId(int sortId) {
        SortId = sortId;
    }

    public int getWellHeadId() {
        return WellHeadId;
    }

    public void setWellHeadId(int wellHeadId) {
        WellHeadId = wellHeadId;
    }

    public String getWellHeadName() {
        return WellHeadName;
    }

    public void setWellHeadName(String wellHeadName) {
        WellHeadName = wellHeadName;
    }
}
