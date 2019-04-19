package com.example.zhangshuai.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhangshuai.gitlearingdemo.R;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MediaPlayerActivity extends AppCompatActivity {
    private EditText et_path;
    private Button btn_play, btn_pause, btn_replay, btn_stop;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);

        et_path = findViewById(R.id.et_path);
        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_replay = findViewById(R.id.btn_replay);
        btn_stop = findViewById(R.id.btn_stop);

        btn_play.setOnClickListener(click);
        btn_pause.setOnClickListener(click);
        btn_replay.setOnClickListener(click);
        btn_stop.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_play:
                    play();
                    break;
                case R.id.btn_pause:
                    pause();
                    break;
                case R.id.btn_replay:
                    replay();
                    break;
                case R.id.btn_stop:
                    stop();
                    break;
            }
        }
    };

    /**
     * 播放音乐
     */
    protected void play() {
        String path = et_path.getText().toString().trim();
        File file = new File(path);
        if (file.exists() && file.length() != 0) {
            try {
                mediaPlayer = new MediaPlayer();
                //设置指定的流媒体地址
                mediaPlayer.setDataSource(path);
                //设置音频流的类型
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //通过异步的方式装载媒体资源
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(mp -> {
                    //装载完毕 开始播放流媒体
                    mediaPlayer.start();
                    Toast.makeText(MediaPlayerActivity.this, "播放开始", Toast.LENGTH_SHORT).show();
                    //避免重复播放，把播放按钮置为不可用
                    btn_play.setEnabled(false);
                });
                //设置循环播放
                //mediaPlayer.setLooping(true);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btn_play.setEnabled(true);
                    }
                });

                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        replay();
                        return false;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MediaPlayerActivity.this, "播放失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 暂停
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Toast.makeText(this, "暂停播放", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 重新播放
     */
    private void replay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
        }
        play();
    }

    /**
     * 停止播放
     */
    private void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            btn_play.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
