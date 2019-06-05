package com.example.zhangshuai.interfaces;

import androidx.recyclerview.widget.RecyclerView;

public interface StartDragListener {
    //触摸imageview，开启拖动的接口
    void startDragItem(RecyclerView.ViewHolder holder);

}
