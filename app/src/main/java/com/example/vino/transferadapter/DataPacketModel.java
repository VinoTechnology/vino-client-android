package com.example.vino.transferadapter;

public class DataPacketModel {
    public int _cLen;
    public int _dSrcLen;
    public int _dDstLen;
    public float[] _mat;
    public byte[] _cData;
    public byte[] _dData;

    DataPacketModel(int len1, int len2, int len3) {
        _cLen = len1;
        _dSrcLen = len2;
        _dDstLen = len3;
        _mat = new float[16];
        _cData = new byte[len1];
        _dData = new byte[len3];
    }
}