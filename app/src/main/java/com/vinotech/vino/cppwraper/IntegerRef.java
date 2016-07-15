package com.vinotech.vino.cppwraper;

/**
 * Created by zhangyutong926 on 15-Jul-16.
 */

public class IntegerRef {

    private int value = 0;

    public IntegerRef() {

    }

    public IntegerRef(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
