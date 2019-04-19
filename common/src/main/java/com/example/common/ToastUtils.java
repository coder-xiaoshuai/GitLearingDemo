package com.example.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * 统一封装 Toast
 *
 * @author zhangshuai
 */
public class ToastUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static long lastTime;//记录上一次的时间戳
    private ToastUtils() {}

    public static void init(Context context) {
        ToastUtils.context = context;
    }

    public static void show(String text) {
        ToastWrapper.INSTANCE.show(text);
    }

    public static void show(@StringRes int textResourceId) {
        String text = null;
        try {
            text = context.getString(textResourceId);
        } catch (Resources.NotFoundException exception) {
            exception.printStackTrace();
        }
        show(text);
    }




    public static void show(@StringRes int textResourceId, Object... args) {
        String text = null;
        try {
            text = context.getString(textResourceId, args);
        } catch (Resources.NotFoundException exception) {
            exception.printStackTrace();
        }
        show(text);
    }

    enum ToastWrapper {
        @SuppressLint("StaticFieldLeak")
        INSTANCE;

        static final String TAG = ToastWrapper.class.getSimpleName();
        // System value paired with the flag Toast.LENGTH_SHORT.
        static final int DURATION_IN_MILLISECONDS = 1500;
        private static final int DURATION = Toast.LENGTH_SHORT;

        private static final int MSG_WHAT_SHOW = 1;
        private static final int MSG_WHAT_DISMISS = 2;

        TextView textView;

        final Handler mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_WHAT_SHOW:
                        String text = (String) msg.obj;
                        // Ignore empty content or text equal to what is showing.
                        if (TextUtils.isEmpty(text) || (textView != null && text.equals(textView.getText()))) {
                            Log.d(TAG, "Ignore text: " + text);
                            return;
                        }
                        if (textView == null) {
                            createToastAndShow(text);
                            // Un-reference textView after toast dismisses.
                            mainHandler.sendEmptyMessageDelayed(MSG_WHAT_DISMISS, DURATION_IN_MILLISECONDS);
                            Log.d(TAG, "Show new toast: " + text);
                        } else {
                            textView.setText("");
                            textView.setText(text);
                            Log.d(TAG, "Update toast: " + text);
                        }
                        break;
                    case MSG_WHAT_DISMISS:
                        textView = null;
                        break;
                    default:
                        break;
                }
            }
        };

        void show(String text) {
            mainHandler.obtainMessage(MSG_WHAT_SHOW, text).sendToTarget();
        }

        void createToastAndShow(String text) {
            Toast toast = new Toast(context);
            toast.setDuration(DURATION);
            textView = (TextView) ViewUtils.newInstance(context, R.layout.toast_layout_view);
            textView.setText(text);
            toast.setView(textView);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
