package com.example.vino.cppimpl.support;

public class IntRef {

    private int value;

    public IntRef() {
        value = 0;
    }

    public IntRef(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
