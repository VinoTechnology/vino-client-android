package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public class InteractionMode {

    public static final int SIZE = 4;

    private int mode;

    public InteractionMode() {
    }

    public InteractionMode(int mode) {
        this.mode = mode;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
