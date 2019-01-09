package com.example.zhangshuai.utils;

import android.media.MediaMetadataRetriever;

public class FileUtils {
    /**
     * 获取视频的时长
     */
    public static long getVideoDuration(String filePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
        } catch (Exception e) {
            return 0;
        }
        String time;
        try {
            time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            time = time == null ? "0" : time.isEmpty() ? "0" : time;
            return Integer.parseInt(time);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int getFileWidth(String filePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
        } catch (Exception e) {
        }
        String width;
        try {
            width = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_IMAGE_WIDTH);
            return Integer.parseInt(width);
        } catch (Exception e) {

        }
        return 0;
    }

}
