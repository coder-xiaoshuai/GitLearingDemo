package com.example.zhangshuai.base;

import android.net.Uri;
import android.text.TextUtils;

import com.example.common.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * glide 加载图片，获取加载进度的管理类
 *
 * @author panxiangxing
 */
public class GlideProgressManager {

    private static Map<String, OnProgressListener> listenersMap = Collections.synchronizedMap(new HashMap<>());
    private static OkHttpClient okHttpClient;

    private GlideProgressManager() {}

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    return response.newBuilder()
                        .body(new ProgressResponseBody(request.url().toString(), LISTENER, response.body()))
                        .build();
                })
                .build();
        }
        return okHttpClient;
    }

    public static void addListener(String url, OnProgressListener listener) {
        if (!TextUtils.isEmpty(url) && listener != null) {
            listenersMap.put(getKeyByUrl(url), listener);
            listener.onProgress(false, 1, 0, 0);
        }
    }

    public static void removeListener(String url) {
        if (!TextUtils.isEmpty(url)) {
            listenersMap.remove(getKeyByUrl(url));
        }
    }

    public static void removeAllListener() {
        if (!CollectionUtils.isNotEmpty(listenersMap)) {
            return;
        }
        listenersMap.clear();
    }

    private static final ProgressResponseBody.InternalProgressListener LISTENER = (url, bytesRead, totalBytes) -> {
        OnProgressListener onProgressListener = getProgressListener(url);
        if (onProgressListener != null) {
            int percentage = (int) ((bytesRead * 1f / totalBytes) * 100f);
            boolean isComplete = percentage >= 100;
            onProgressListener.onProgress(isComplete, percentage, bytesRead, totalBytes);
            if (isComplete) {
                removeListener(url);
            }
        }
    };

    private static String getKeyByUrl(String url) {
        Uri uri = Uri.parse(url);
        return "http://" + uri.getHost() + uri.getPath();
    }

    private static OnProgressListener getProgressListener(String url) {
        if (TextUtils.isEmpty(url) || listenersMap == null || listenersMap.size() == 0) {
            return null;
        }
        OnProgressListener listenerWeakReference = listenersMap.get(getKeyByUrl(url));
        if (listenerWeakReference != null) {
            return listenerWeakReference;
        }
        return null;
    }
}
