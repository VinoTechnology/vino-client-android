package com.example.vino.transfer;

/**
 * Created by zhangyutong926 on 16-Jul-16.
 */

public enum InteractionType {
    TRACK_BALL_MANIPULATOR(0),
    FIRST_PERSON_MANIPULATOR(1),
    USER_DEFINED(2);

    int value;
    InteractionType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
