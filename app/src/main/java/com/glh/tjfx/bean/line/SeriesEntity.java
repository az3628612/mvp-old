package com.glh.tjfx.bean.line;

import java.io.Serializable;

/**
 * 线图实体
 */

public class SeriesEntity implements Serializable {
    private String name;
    private int[] data;
    private String stack;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
