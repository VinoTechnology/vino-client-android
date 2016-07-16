package com.example.vino.cppimpl;

import com.example.vino.cppimpl.support.FloatArrayRef;
import com.example.vino.cppimpl.support.IntRef;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public class DepthImageBase {

    public static final int BSIZE = 32;
    public static final int MAX = (1 << 16) - 1;

    private float[] mMatrix = new float[16];

    private String mColor;
    private int mColorSize;

    private String mSampleCode;
    private int mSampleCodeSize;

    private float[] mSample;
    private int mSampleSize;
    private int mSampleCnt;

    private boolean mCflag;
    private boolean mDflag;
    private boolean mMflag;

    public boolean isWithData()
    {
        return (mMflag && mCflag && mDflag);
    }

    public void withoutData()
    {
        mMflag = false;
        mCflag = false;
        mDflag = false;
    }

    public void setMatrix(float[] mat)
    {
        for(int i = 0; i < 16; ++i)
            mMatrix[i] = mat[i];
        mMflag = true;
    }

    public float[] getMatixData()
    {
        return mMatrix;
    }

    public String getColorData()
    {
        return mColor;
    }

    public void getDepthData(FloatArrayRef data, IntRef cnt)
    {
        data.setValue(mSample);
        cnt.setValue(mSampleCnt);
    }

    public pthread_mutex_t& getMutex()
    {
        return mMutex;
    }
}
