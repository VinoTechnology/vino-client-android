package com.example.vino.transfer;

import com.example.vino.*;
import com.example.vino.vinoglobal.ViewPerspective;
import com.example.vino.vinoglobal.ViewResolution;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public class Transfer {

    public static Transfer adapter = null;

    private Socket _socket;
    private BufferedInputStream _input;
    private BufferedOutputStream _output;
    boolean _isConnected;

    public static Transfer getInstance() {
        if (adapter == null) {
            adapter = new Transfer();
        }

        return adapter;
    }

    public void set_socket(Socket s) {
        _socket = s;
    }

    public void set_isConnected(boolean connected) {
        _isConnected = connected;
    }

    public void setInputstream(BufferedInputStream input) {
        _input = input;
    }

    public void setOutputstream(BufferedOutputStream out) {
        _output = out;
    }

    private Transfer() {
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
            e.printStackTrace();
        }
        return _isConnected;
    }

    public boolean isConnected() {
        return _isConnected;
    }

    //2014_8_3
    public void sendOnePacket(MessageType type, float a, float b, float c) {
        byte[] mType = toNetworkStream(type.getValue());
        byte[] mLength = toNetworkStream(29);
        byte[] mId = toNetworkStream(0);

        byte[] mx = toNetworkStream(a);
        byte[] my = toNetworkStream(b);
        byte[] mz = toNetworkStream(c);

        byte[] mf = new byte[1];
        mf[0] = (byte) 0;
        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mx);
            _output.write(my);
            _output.write(mz);

            for (int i = 0; i < 5; i++) {
                _output.write(mf);
            }
            _output.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    //204_7_7
    public void sendOnePacket(MessageType type, InteractionMode im) {
        byte[] mType = toNetworkStream(type.getValue());
        byte[] mLength = toNetworkStream(16);
        byte[] mId = toNetworkStream(0);
        byte[] mData = toNetworkStream(im.getMode().getValue());

        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void sendOnePacket(MessageType type, ViewResolution r) {
        byte[] mType = toNetworkStream(type.getValue());
        byte[] mLength = toNetworkStream(20);
        byte[] mId = toNetworkStream(0);

        byte[] mWidth = toNetworkStream(r._width);
        byte[] mHeight = toNetworkStream(r._height);

        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mWidth);
            _output.write(mHeight);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void sendOnePacket(MessageType type, int size, ApplicationType apptype) {
        byte[] mType = toNetworkStream(type.getValue());
        // mType[0] = type;
        byte[] mLength = toNetworkStream(size);
        byte[] mApptype = toNetworkStream(apptype.getValue());
        try {
            //  Log.i("VIVO","!!!!!sendonePacket!!!!");
            _output.write(mLength);
            _output.write(mType);
            _output.write(mApptype);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2014_7_31
    public void sendOnePacket(MessageType type, int size, int partid, int op) {
        byte[] mType = toNetworkStream(type.getValue());
        byte[] mLength = toNetworkStream(size);
        byte[] mId = toNetworkStream(0);
        byte[] mPartid = toNetworkStream(partid);
        byte[] mOp = toNetworkStream(op);
        byte[] mFill = new byte[9];
        for (int i = 0; i <= 8; i++) {
            mFill[i] = ((byte) 0);
        }
        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mPartid);
            _output.write(mOp);
            _output.write(mFill);
            _output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendOnePacket(MessageType type, int size) {
        byte[] mType = toNetworkStream(type.getValue());
        // mType[0] = type;
        byte[] mLength = toNetworkStream(size);
        //byte[] mApptype=toNetworkStream(VinoActivity.ApplicationID);
        try {
            //  Log.i("VIVO","!!!!!sendonePacket!!!!");
            _output.write(mLength);
            _output.write(mType);
            //_output.write(mApptype);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    protected byte[] toNetworkStream(CameraModel pos) {
        byte[] a = toNetworkStream(pos._eyex);

        byte[] b = toNetworkStream(pos._eyey);

        byte[] c = toNetworkStream(pos._eyez);
        byte[] e = toNetworkStream(pos._centerx);
        byte[] f = toNetworkStream(pos._centery);
        byte[] g = toNetworkStream(pos._centerz);
        byte[] h = toNetworkStream(pos._upx);
        byte[] i = toNetworkStream(pos._upy);
        byte[] j = toNetworkStream(pos._upz);

        byte[] d = new byte[pos.SIZE];
        System.arraycopy(a, 0, d, 0, a.length);
        System.arraycopy(b, 0, d, 4, b.length);
        System.arraycopy(c, 0, d, 8, c.length);
        System.arraycopy(e, 0, d, 12, e.length);
        System.arraycopy(f, 0, d, 16, f.length);
        System.arraycopy(g, 0, d, 20, g.length);
        System.arraycopy(h, 0, d, 24, g.length);
        System.arraycopy(i, 0, d, 28, i.length);
        System.arraycopy(j, 0, d, 32, j.length);
        return d;
    }

    //2014_7_7
    public void sendOnePacket(MessageType type, CameraModel pos) {
        byte[] mLength = toNetworkStream(48);
        byte[] mType = toNetworkStream(type.getValue());
        byte[] mId = toNetworkStream(0);
        byte[] mData = toNetworkStream(pos);
        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    public void sendOnePacket(byte type, ViewFrustum vf)
    {
        byte[] mType = new byte[1];
        mType[0] = type;
        byte[] mData = toNetworkStream(vf);
        try
        {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }*/

    protected byte[] toNetworkStream(PerspectiveModel vp) {
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

    public void sendOnePacket(MessageType type, PerspectiveModel vp) {
        byte[] mLength = toNetworkStream(28);
        byte[] mType = toNetworkStream(type.getValue());
        byte[] mId = toNetworkStream(0);
        byte[] mData = toNetworkStream(vp);
        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public void sendOnePacket(byte type, ViewResolution vr)
    {
        byte[] mType = new byte[1];
        mType[0] = type;
        byte[] mData = toNetworkStream(vr);
        try
        {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }*/
    /*
    public void sendOnePacket(byte type, UpsampleFactor uf)
    {
        byte[] mType = new byte[1];
        mType[0] = type;
        byte[] mData = toNetworkStream(uf);
        try
        {
            _output.write(mType);
            _output.write(mData);
            _output.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }*/
    protected byte[] toNetworkStream(MotionModel m) {
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

    public void sendOnePacket(MessageType type, MotionModel md) {
        byte[] mLength = toNetworkStream(29);
        byte[] mType = toNetworkStream(type.getValue());
        byte[] mId = toNetworkStream(0);
        byte[] mData = toNetworkStream(md);
        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mData);
            _output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnePacket(MessageType initialSceneMsg, String scenename) {
        int _fillLen = 29 - (scenename.length() + 12);
        byte[] mLength = toNetworkStream(29);
        byte[] mType = toNetworkStream(initialSceneMsg.getValue());
        byte[] mId = toNetworkStream(0);
        byte[] mData = toNetworkStream(scenename);
        byte[] mFill = new byte[1];
        mFill[0] = (byte) (0);
        try {
            _output.write(mLength);
            _output.write(mType);
            _output.write(mId);
            _output.write(mData);
            for (int i = 0; i < _fillLen; i++) {
                _output.write(mFill);
            }
            _output.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private byte[] toNetworkStream(String scenename) {

        byte[] value = new byte[scenename.length()];
        for (int i = 0; i < scenename.length(); i++) {
            value[i] = (byte) ((scenename.charAt(i)) & 0xff);
            //value[2*i+1]=(byte) ((scenename.charAt(i)) & 0xff);
        }

        return value;
    }
}
