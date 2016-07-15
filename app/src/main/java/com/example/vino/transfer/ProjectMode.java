package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public enum ProjectMode {
    PERSPECTIVE(0),
    FRUSTRUM(1);

    int value;
    ProjectMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
