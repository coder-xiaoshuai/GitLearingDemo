package com.example.common;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

/**
 * 版本升级 - 下载 apk 的工具类
 *
 * @author panxiangxing
 */
public class DownloadApkUtils {

    private static DownloadManager downloadManager;
    //下载的ID
    private static long downloadId;

    private DownloadApkUtils() {}

    public static void downloadApk(String apkUrl) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
        //移动网络情况下是否允许漫游
        request.setAllowedOverRoaming(false);
        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle(" ");
        request.setDescription(" ");
        request.setVisibleInDownloadsUi(true);
        //设置下载的路径
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "flow.apk");

        //获取DownloadManager
        downloadManager = (DownloadManager) GlobalConfig.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager == null) {
            return;
        }
        //将下载请求加入下载队列，加入下载队列后会给该任务返回一个long型的id，通过该id可以取消任务，重启任务、获取下载的文件等等
        downloadId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        GlobalConfig.getContext()
            .registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private static BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus(context);
        }
    };

    private static void checkDownloadStatus(Context context) {
        DownloadManager.Query query = new DownloadManager.Query();
        //通过下载的id查找
        query.setFilterById(downloadId);
        Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                String localFilename;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    localFilename = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                } else {
                    localFilename = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                }
                SystemUtils.installAPKBySystemAPI(context, localFilename);
                GlobalConfig.getContext().unregisterReceiver(receiver);
            }
            if (status == DownloadManager.STATUS_FAILED) {
                GlobalConfig.getContext().unregisterReceiver(receiver);
            }
        }
    }
}
