package com.example.zhangshuai.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.zhangshuai.gitlearingdemo.R;
import com.example.zhangshuai.utils.ViewUtils;

public class AutoTextActivity extends AppCompatActivity {

    private Paint mPaint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_text);
        mPaint = new Paint();


        final String text = "是奇偶覅王嘉尔噢诶放假哦啊了设计费咯啊哦来大家";
        final TextView textAuto = findViewById(R.id.text_auto);
        mPaint.setTextSize(textAuto.getTextSize());
        textAuto.post(new Runnable() {
            @Override
            public void run() {
                Log.e("zs","字的长度"+mPaint.measureText(text + "三分钟前")+",TextView的长度"+textAuto.getWidth());
                if (mPaint.measureText(text + "三分钟前") > textAuto.getWidth()) {
                    String newText = text + "\n 三分钟前";
                    textAuto.setText(newText);
                } else {
                    textAuto.setText(text + "三分钟前");
                }
            }
        });

        TextView commontText = findViewById(R.id.text_common);
        commontText.setText(text + "三分钟前");
    }
}
