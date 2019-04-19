package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.commonui.widget.NineGridLayout;
import com.example.zhangshuai.gitlearingdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NineGridLayoutActivity extends AppCompatActivity {
    private NineGridLayout nineGridLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninegrid);
        nineGridLayout = findViewById(R.id.nineGridLayout);
        findViewById(R.id.inner_view).setOnClickListener(
            v -> Toast.makeText(NineGridLayoutActivity.this, "点击了内部", Toast.LENGTH_SHORT).show());
        findViewById(R.id.inner_view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        nineGridLayout.setShowGridLine(true);
                        nineGridLayout.invalidate();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        nineGridLayout.setShowGridLine(false);
                        nineGridLayout.invalidate();
                        break;
                }
                return false;
            }
        });
    }
}
