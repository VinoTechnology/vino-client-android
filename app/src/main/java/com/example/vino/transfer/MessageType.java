package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 15-Jul-16.
 */

public enum MessageType {

    CONTROL_START_MSG(0),
    CONTROL_STOP_MSG(1),
    CONTROL_SYN_MSG(2),


    INITIAL_SCENE_MSG(3),
    INITIAL_CAMERA_MSG(4),
    INITIAL_PERSPECTIVE_MSG(5),
    INITIAL_RESOLUTION_MSG(6),
    INITIAL_INTERACTION_MSG(7),


    RUNTIME_TOUCH_EVENT_MSG(8),
    RUNTIME_REF_FRAME_MSG(9),
    RUNTIME_VIEW_QUERY_MSG(10),
    RUNTIME_SCENE_RENDERING_MSG(11),
    RUNTIME_IMAGE_WARPING_MSG(12),
    RUNTIME_VIEW_UPDATE_MSG(13),
    RUNTIME_REF_ENCODING_MSG(14),
    RUNTIME_LOG_MSG(15),

    APPLICATIONMSG(16),
    CAMERAPOSITION(17),
    CAMERACENTER(18),
    CAMERAUP(19),

    NODE_NAME(20),
    NODE_TRANSLATION(21),
    NODE_RATATION_AXIS(22),
    NODE_ROTATION_DIRECTION(23);

    int value;
    MessageType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
