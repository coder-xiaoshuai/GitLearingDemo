package com.example.network.event;

public class BaseActionEvent extends BaseEvent {

    public static final int SHOW_LOADING_DIALOG = 0x01;

    public static final int DISMISS_LOADING_DIALOG = 0x02;

    public static final int SHOW_TOAST = 0x03;

    public static final int FINISH = 0x04;

    public static final int FINISH_WITH_RESULT_OK = 0x05;

    private String message;

    public BaseActionEvent(int action) {
        super(action);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
