package com.glh.tjfx.bean.line;

import java.io.Serializable;

/**
 * 线图实体
 */

public class LegendEntity implements Serializable {
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
}
