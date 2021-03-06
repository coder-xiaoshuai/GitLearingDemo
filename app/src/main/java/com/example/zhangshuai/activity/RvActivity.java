package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RvActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        findViewById(R.id.btn_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RvActivity.this, "你点击了标题", Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerview);
        initData();
        MyAdapter myAdapter = new MyAdapter(R.layout.item_rv_text, mDatas);
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(RvActivity.this, "你点击了第" + (position + 1) + "个item", Toast.LENGTH_SHORT).show();

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    Log.e("zs", "最后一个可见的position" + linearLayoutManager.findLastVisibleItemPosition() + "header="
                            + myAdapter.getHeaderLayoutCount() + ",footer" + myAdapter.getFooterLayoutCount());
                }
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDatas.add("这是第" + (i + 1) + "个item");
        }
    }

    public static class MyAdapter extends BaseQuickAdapter<String, MyViewHolder> {
        //private List<String> mDatas;
        //private Context context;

        public MyAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        //@NonNull
        //@Override
        //public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //    return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_text, null, false));
        //}
        //
        //@Override
        //public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //    myViewHolder.text.setText(mDatas.get(i));
        //}

        @Override
        protected void convert(MyViewHolder helper, String item) {
            helper.text.setText(item);
        }
        //
        //@Override
        //public int getItemCount() {
        //    return mDatas != null ? mDatas.size() : 0;
        //}
    }

    public static class MyViewHolder extends BaseViewHolder {
        private TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
