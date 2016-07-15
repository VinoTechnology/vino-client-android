package com.vinotech.vino;

/**
 * Created by zhangyutong926 on 15-Jul-16.
 */

public interface IManipulator {

    public void setByLookAt(osg::Vec3f& eye, osg::Vec3f& center, const osg::Vec3f& up);
}
