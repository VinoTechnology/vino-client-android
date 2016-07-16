package com.example.vino.transferadapter;

import android.util.Log;

import com.example.vino.Vector3;
import com.example.vino.transfer.MessageType;
import com.example.vino.vinoglobal.MotionData;
import com.example.vino.vinoglobal.StandardCameraPosition;
import com.example.vino.vinoglobal.UpsampleFactor;
import com.example.vino.vinoglobal.ViewFrustum;
import com.example.vino.vinoglobal.ViewPerspective;
import com.example.vino.vinoglobal.ViewResolution;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TransferAdapter {
    private Socket _socket;
    private BufferedInputStream _input;
    private BufferedOutputStream _output;
    public boolean _isConnected;

    public Socket getSocket() {
        return _socket;
    }

    public BufferedInputStream getInputstream() {
        return _input;
    }

    public BufferedOutputStream getOutputStrem() {
        return _output;
    }

    public TransferAdapter() {
        _socket = null;
        _input = null;
        _output = null;
        _isConnected = false;
    }

    public boolean connect(String addr, int port) {
        try {
            _socket = new Socket(addr, port);
            _isConnected = true;
            _input = new BufferedInputStream(_socket.getInputStream());
            _output = new BufferedOutputStream(_socket.getOutputStream());
        } catch (IOException e) {
            Log.i("SOCKET", e.toString());
            e.printStackTrace();
            // Log.i("VIVO","connection exception~~~~");
        }
        return _isConnected;
    }

    public boolean isConnected() {
        return _isConnected;
    }


    public DataPacketModel receiveOnePacket() {
        byte[] mType = new byte[1];
        byte[] mHead = new byte[76];
        DataPacketModel dp = null;
        int readSize;
        readSize = fullRead(mType, 0, mType.length);
        if (readSize != 1) return dp;
        if (mType[0] == DataType.VINO_MODEL_3DIMAGE.getValue()) {

            readSize = fullRead(mHead, 0, mHead.length);
            //Log.i( "VINO", "head:" + String.valueOf(readSize));

            dp = parse3DImageHead(mHead);

            readSize = fullRead(dp._cData, 0, dp._cData.length);
            //Log.i( "VINO", "color:" + String.valueOf(readSize));

            readSize = fullRead(dp._dData, 0, dp._dData.length);
            //Log.i( "VINO", "depth:" + String.valueOf(readSize));
        }
        return dp;
    }

    protected int fullRead(byte[] data, int off, int len) {
        try {
            int nextLen = len, pos = off, readLen;
            while (nextLen > 0) {
                readLen = _input.read(data, pos, nextLen);
                if (readLen >= 0) {
                    nextLen -= readLen;
                    pos += readLen;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return len;
    }

    protected DataPacketModel parse3DImageHead(byte[] head) {
        int len1 = toLocalInt(head, 0);
        int len2 = toLocalInt(head, 4);
        int len3 = toLocalInt(head, 8);

        //Log.i( "VINO", "Len:" + String.valueOf(len1) + "," + String.valueOf(len2) + "," +String.valueOf(len3));
        DataPacketModel dp = new DataPacketModel(len1, len2, len3);
        int i;
        for (i = 0; i < 16; ++i)
            dp._mat[i] = Float.intBitsToFloat(toLocalInt(head, 12 + i * 4));
        return dp;
    }

    public void sendOnePacket(MessageType type, int size) {
        byte[] mType = toNetworkStream(type.getValue());
        // mType[0] = type;
        byte[] mLength = toNetworkStream(size);
        try {
            //  Log.i("VIVO","!!!!!sendonePacket!!!!");
            _output.write(mLength);
            _output.write(mType);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType type) {
        byte[] mType = new byte[1];
        mType[0] = (byte) type.getValue();
        try {
            _output.write(mType);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType type, StandardCameraPosition pos) {
        byte[] mType = new byte[1];
        mType[0] = (byte) type.getValue();
        byte[] mData = toNetworkStream(pos);
        try {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType type, ViewFrustum vf) {
        byte[] mType = new byte[1];
        mType[0] = (byte) type.getValue();
        byte[] mData = toNetworkStream(vf);
        try {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType type, ViewPerspective vp) {
        byte[] mType = new byte[1];
        mType[0] = (byte) type.getValue();
        byte[] mData = toNetworkStream(vp);
        try {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType type, ViewResolution vr) {
        byte[] mType = new byte[1];
        mType[0] = (byte) type.getValue();
        byte[] mData = toNetworkStream(vr);
        try {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType type, UpsampleFactor uf) {
        byte[] mType = new byte[1];
        mType[0] = (byte) type.getValue();
        byte[] mData = toNetworkStream(uf);
        try {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType type, MotionData md) {
        byte[] mType = new byte[1];
        mType[0] = (byte) type.getValue();
        byte[] mData = toNetworkStream(md);
        try {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int toLocalInt(byte[] data, int pos) {
        int tmp = 0;
        tmp |= ((data[pos] << 24) & 0xff000000);
        tmp |= ((data[pos + 1] << 16) & 0xff0000);
        tmp |= ((data[pos + 2] << 8) & 0xff00);
        tmp |= (data[pos + 3] & 0xff);
        return tmp;
    }

    protected byte[] toNetworkStream(int n) {
        byte[] a = new byte[4];
        a[0] = (byte) ((n) & 0xff);
        a[1] = (byte) ((n >> 8) & 0xff);
        a[2] = (byte) ((n >> 16) & 0xff);
        a[3] = (byte) ((n >> 24) & 0xff);
        return a;
    }

    protected byte[] toNetworkStream(float t) {
        return toNetworkStream(Float.floatToIntBits(t));
    }

    protected byte[] toNetworkStream(Vector3 v) {
        byte[] a = toNetworkStream(v.x);
        byte[] b = toNetworkStream(v.y);
        byte[] c = toNetworkStream(v.z);
        byte[] d = new byte[12];
        System.arraycopy(a, 0, d, 0, a.length);
        System.arraycopy(b, 0, d, 4, b.length);
        System.arraycopy(c, 0, d, 8, c.length);
        return d;
    }

    protected byte[] toNetworkStream(MotionData m) {
        byte[] a1 = toNetworkStream(m._x1);
        byte[] b1 = toNetworkStream(m._y1);
        byte[] a2 = toNetworkStream(m._x2);
        byte[] b2 = toNetworkStream(m._y2);
        byte[] c = new byte[17];
        c[0] = m._type;
        System.arraycopy(a1, 0, c, 1, a1.length);
        System.arraycopy(b1, 0, c, 5, b1.length);
        System.arraycopy(a2, 0, c, 9, a2.length);
        System.arraycopy(b2, 0, c, 13, b2.length);
        return c;
    }

    protected byte[] toNetworkStream(StandardCameraPosition pos) {
        byte[] a = toNetworkStream(pos._eye);
        byte[] b = toNetworkStream(pos._center);
        byte[] c = toNetworkStream(pos._up);
        byte[] d = new byte[StandardCameraPosition.SIZE];
        System.arraycopy(a, 0, d, 0, a.length);
        System.arraycopy(b, 0, d, 12, b.length);
        System.arraycopy(c, 0, d, 24, c.length);
        return d;
    }

    protected byte[] toNetworkStream(ViewFrustum vf) {
        byte[][] a = new byte[6][];
        a[0] = toNetworkStream(vf._left);
        a[1] = toNetworkStream(vf._right);
        a[2] = toNetworkStream(vf._bottom);
        a[3] = toNetworkStream(vf._top);
        a[4] = toNetworkStream(vf._near);
        a[5] = toNetworkStream(vf._far);

        byte[] d = new byte[ViewFrustum.SIZE];
        int i;
        for (i = 0; i < 6; ++i)
            System.arraycopy(a[i], 0, d, i * 4, 4);
        return d;
    }

    protected byte[] toNetworkStream(ViewPerspective vp) {
        byte[][] a = new byte[4][0];
        a[0] = toNetworkStream(vp._fov);
        a[1] = toNetworkStream(vp._aspect);
        a[2] = toNetworkStream(vp._near);
        a[3] = toNetworkStream(vp._far);
        byte[] d = new byte[ViewPerspective.SIZE];
        int i;
        for (i = 0; i < 4; ++i)
            System.arraycopy(a[i], 0, d, i * 4, 4);
        return d;
    }

    protected byte[] toNetworkStream(ViewResolution vr) {
        byte[] a = toNetworkStream(vr._width);
        byte[] b = toNetworkStream(vr._height);
        byte[] d = new byte[ViewResolution.SIZE];
        System.arraycopy(a, 0, d, 0, a.length);
        System.arraycopy(b, 0, d, 4, b.length);
        return d;
    }

    protected byte[] toNetworkStream(UpsampleFactor uf) {
        byte[] a = toNetworkStream(uf._factX);
        byte[] b = toNetworkStream(uf._factY);
        byte[] d = new byte[UpsampleFactor.SIZE];
        System.arraycopy(a, 0, d, 0, a.length);
        System.arraycopy(b, 0, d, 4, b.length);
        return d;
    }
}