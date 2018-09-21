package com.glh.tjfx.bean.pie;

import java.io.Serializable;

/**
 * 饼图实体
 */

public class SeriesDataEntity implements Serializable {
    private String name;
    private float value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
