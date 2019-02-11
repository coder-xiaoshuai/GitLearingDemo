package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangshuai.gitlearingdemo.R;

public class ClickableSpanActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickable);
        TextView textView = findViewById(R.id.text);
        String text = "aaaaaaaaaaaaaaaaaaaaaa";

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text);
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(ClickableSpanActivity.this, "点击了文字", Toast.LENGTH_SHORT).show();
            }
        }, 3, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setOnClickListener(v -> {
            Toast.makeText(ClickableSpanActivity.this, "点击了TextView", Toast.LENGTH_SHORT).show();
        });

        textView.setText(builder);
    }


}
