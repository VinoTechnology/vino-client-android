package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public enum ApplicationType {
    DEFAULT_VALUE(0),
    ENGINE_SHOW(1);

    int value;
    ApplicationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
