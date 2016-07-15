package com.vinotech.vino;

/**
 * Created by zhangyutong926 on 15-Jul-16.
 */

public class ProgmaSource {

    private String vertex;
    private String fragment;

    public ProgmaSource() {
    }

    public ProgmaSource(String vertex, String fragment) {
        this.vertex = vertex;
        this.fragment = fragment;
    }

    public String getVertex() {
        return vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
