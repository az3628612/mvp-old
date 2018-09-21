package com.glh.tjfx.bean.pie;

import java.io.Serializable;

/**
 * 饼图实体
 */

public class LegendEntity implements Serializable {
    private String orient;
    private String[] data;
    private String y;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }
}
