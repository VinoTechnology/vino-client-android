package com.example.vino.cppimpl.support;

public class FloatArrayRef {

    private float[] value;

    public FloatArrayRef() {
        value = new float[0];
    }

    public FloatArrayRef(float[] value) {
        this.value = value;
    }

    public float[] getValue() {
        return value;
    }

    public void setValue(float[] value) {
        this.value = value;
    }
}
