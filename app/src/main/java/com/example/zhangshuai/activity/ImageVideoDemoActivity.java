package com.example.zhangshuai.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.zhangshuai.adapter.ImageVideoAdapter;
import com.example.zhangshuai.entity.ImageVideoEntity;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ImageVideoDemoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageVideoAdapter mAdapter;
    private List<ImageVideoEntity> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_video_demo);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mDatas = new ArrayList<>();
        mAdapter = new ImageVideoAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDatas.addAll(queryData());
                Log.e("zs", "mDatas的长度" + mDatas.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private List<ImageVideoEntity> queryData() {
        List<ImageVideoEntity> imageVideoEntities = new ArrayList<>();
        Uri uri = MediaStore.Files.getContentUri("external");
        String[] projection = {
            MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.DATE_ADDED, MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.MIME_TYPE, MediaStore.Files.FileColumns.TITLE,
        };
        String selection =
            MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE + " or "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
        //String selection =
        //    MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
        Cursor cursor = getContentResolver().query(uri, projection, selection, null,
            MediaStore.Files.FileColumns.DATE_ADDED + " DESC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String path = cursor.getString(1);
                ImageVideoEntity imageVideoEntity = new ImageVideoEntity();
                imageVideoEntity.setPath(path);
                imageVideoEntities.add(imageVideoEntity);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return imageVideoEntities;
    }
}
