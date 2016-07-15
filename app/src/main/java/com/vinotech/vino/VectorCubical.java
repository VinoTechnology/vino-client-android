package com.vinotech.vino;

public class VectorCubical {
    public float x, y, z;
    public final float ZERO = 1e-10f;

    VectorCubical() {
        x = y = z = 0.0f;
    }

    VectorCubical(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    VectorCubical(float[] a) {
        x = a[0];
        y = a[1];
        z = a[2];
    }

    public float length() {
        double dd = (double) x * x + (double) y * y + (double) z * z;
        return (float) (Math.sqrt(dd));
    }

    public void normalize() {
        if (isZero()) return;
        float l = length();
        x /= l;
        y /= l;
        z /= l;
    }

    public boolean isZero() {
        if (x <= ZERO && y <= ZERO && z <= ZERO) return true;
        return false;
    }

    public float dotProduct(VectorCubical a) {
        return (a.x * x + a.y * y + a.z * z);
    }

    public VectorCubical crossProduct(VectorCubical a) {
        VectorCubical tmp = new VectorCubical();
        tmp.x = y * a.z - z * a.y;
        tmp.y = z * a.x - x * a.z;
        tmp.z = x * a.y - y * a.x;
        return tmp;
    }
}