package com.vinotech.vino;

import com.vinotech.vino.cppwraper.IntegerRef;

/**
 * Created by zhangyutong926 on 15-Jul-16.
 */

public class JavaViewResolutionImpl {

    private static JavaViewResolutionImpl instance;

    public static JavaViewResolutionImpl getInstance() {
        if (instance == null) {
            instance = new JavaViewResolutionImpl();
        }
        return instance;
    }

    protected JavaViewResolutionImpl() {

    }

    public void get(ViewResolution targetObj, IntegerRef width, IntegerRef height) {
        width.setValue(targetObj.width);
        height.setValue(targetObj.height);
    }
}
