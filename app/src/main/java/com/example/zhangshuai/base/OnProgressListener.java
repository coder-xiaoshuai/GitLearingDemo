package com.example.zhangshuai.base;

/**
 * 图片加载进度接口
 *
 * @author panxiangxing
 */
public interface OnProgressListener {

    void onProgress(boolean isComplete, int percentage, long bytesRead, long totalBytes);
}
