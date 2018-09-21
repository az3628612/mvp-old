package com.glh.tjfx.bean.pie;

import java.io.Serializable;
import java.util.List;

/**
 * 饼图实体
 */

public class SeriesEntity implements Serializable {
    private ItemStyleEntity  itemStyle;
    private String name;
    private List<SeriesDataEntity> data;
    private String type;

    public ItemStyleEntity getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyleEntity itemStyle) {
        this.itemStyle = itemStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeriesDataEntity> getData() {
        return data;
    }

    public void setData(List<SeriesDataEntity> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
