package com.example.zhangshuai.activity;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.widget.TextView;

import com.example.zhangshuai.gitlearingdemo.R;
import com.example.zhangshuai.utils.ViewUtils;

public class AutoTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_text);
        final String text =
            "是奇偶覅王嘉尔噢诶放假哦啊了设计费咯啊哦来大家我奇偶发神经了空间阿拉基奥s金斧加上来了小垃圾哦我加剧了新空间哦我间我弗利萨 偶奇偶偶精神分裂就爱上了升级了可是奇偶奇偶司机佛啊发骄傲我金佛安抚";
        //String text2="奥斯卡积分拉是奇偶覅酒叟阿斯利康金佛我按实际佛教爱上了房间爱上了发窘按实际佛教啊奥is放假哦啊接发了肯定是骄傲覅奇偶啊拉丝机佛碍手离开最先进的瑟吉欧爱福家空间大搜if就哦啊是否家搜房嗲是奇偶覅就爱搜房隆盛科技佛爱睡觉佛";
        final TextView textAuto = findViewById(R.id.text_auto);
        textAuto.setText(text);
        Paint paint = textAuto.getPaint();
        Rect bound = new Rect();
        paint.getTextBounds("hahaha \n   hahahah", 0, "hahaha \n   hahahah".length(), bound);

        float length = paint.measureText("hahaha \n   hahahah");
        long start = System.currentTimeMillis();
        int screenWidth = ViewUtils.getScreenWidthPx(this);
        float lineWith = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            Log.e("zs", "字符" + text.charAt(i));
            lineWith = lineWith + paint.measureText(text.charAt(i) + "");
            if (lineWith > screenWidth) {
                Log.e("zs", "换行" + "-------" + text.charAt(i));
                lineWith = paint.measureText(text.charAt(i) + "");
                stringBuilder = new StringBuilder();
                stringBuilder.append(text.charAt(i));
            } else {
                stringBuilder.append(text.charAt(i));
            }

            //paint.measureText(text);
        }
        Log.e("zs", "最后一行" + stringBuilder.toString());
        long end = System.currentTimeMillis();
        Log.e("zs", "耗时：" + (end - start));
        Log.e("zs", "文字长度1:" + length + "bound width" + bound.width() + ",height" + bound.height());
        float length2 = paint.measureText("hahaha      hahahah");
        Rect bound2 = new Rect();
        paint.getTextBounds("hahaha      hahahah", 0, "hahaha      hahahah".length(), bound2);
        Log.e("zs", "文字长度2:" + length2 + "bound width" + bound2.width() + ",height" + bound2.height());
        TextView commonText = findViewById(R.id.text_common);
        commonText.setText(text + "三分钟前");

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(20);
        Paint paint1 =new Paint();
        paint1.setTextSize(20);
        float length1 = textPaint.measureText("哈哈哈哈                        哈哈哈哈");
        float length3 = textPaint.measureText("哈哈哈哈哈哈哈哈");
        float length5 = textPaint.measureText("哈哈哈哈                        哈哈哈哈");
        float length6 = textPaint.measureText("哈哈哈哈哈哈哈哈");
        Log.e("zs", "length1:" + length1 + ",length3:" + length3);
        Log.e("zs", "length5:" + length5 + ",length6:" + length6);
        TextView text3 = findViewById(R.id.text3);
        text3.setTextSize(20);
        text3.setText("哈哈哈哈                        哈哈哈哈");
        TextView text4 = findViewById(R.id.text4);
        text4.setTextSize(20);
        text4.setText("哈哈哈哈哈哈哈哈");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zs", "onDestroy");
    }
}
