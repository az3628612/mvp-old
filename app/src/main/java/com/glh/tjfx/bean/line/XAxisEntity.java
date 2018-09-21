package com.glh.tjfx.bean.line;

import java.io.Serializable;

/**
 * 线图实体
 */

public class XAxisEntity implements Serializable {
    private String[] data;
    private boolean boundaryGap;
    private String type;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public boolean isBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
