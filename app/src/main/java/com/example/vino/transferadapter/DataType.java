package com.example.vino.transferadapter;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public enum DataType {

    VINO_CONFIG_SYN((byte) 0),
    VINO_CONFIG_VIEW_FRUSTUM((byte) 0x08),
    VINO_CONFIG_VIEW_PERSPECTIVE((byte) 0x09),
    VINO_CONFIG_VIEW_RESOLUTION((byte) 0x0A),
    VINO_CONFIG_CAMERA_STANDARD((byte) 0x10),
    VINO_CONFIG_QUALITY_UPSAMPLE((byte) 0x18),
    VINO_MODEL_3DIMAGE((byte) 0x40),
    VINO_MOTION_TOUCH_STANDARD((byte) 0x80);

    byte value;
    DataType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
