package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhangshuai.adapter.PreDownLoadAdapter;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private List<String> imageUrls;
    private RecyclerView mRecyclerView;
    private PreDownLoadAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_download);
        imageUrls = new ArrayList<>();
        initRecyclerView();
        //initImageUrls();
        findViewById(R.id.btn_pre_download).setVisibility(View.GONE);

        findViewById(R.id.btn_show).setOnClickListener(v -> {
            addData();
            mAdapter.notifyDataSetChanged();});
    }

    private void addData() {
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301896&di=85011eb0483311922fc73314b46a3a82&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-04%2F5a24c0dfc3c59.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=80b25022e8e345545d92da59329fff5d&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-13%2F5a30f76471b61.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=b0514d6c40533aca68619685075d8d36&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-06-07%2F5b18f37614638.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=71448a471e1877fb5518d47b206c3778&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F7%2F587483dedc055.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=e4b3df389d555a2bcd550edac7b07961&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F14%2F171453hgtw64npx6aoa6ax.jpeg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551787800328&di=6ebda714776515db33449be4ab7cc5ee&imgtype=0&src=http%3A%2F%2Fcms-bucket.nosdn.127.net%2Fcc19e6e73e6e4c9a8c3430a87ffe781020161001110859.gif");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551787821798&di=fb81d4eb86484b92ae5a46e2e5f2513f&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160206%2Fmp58312418_1454765249938_3.gif");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=07ddf9399234ed8f9f84999e25c4971e&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fb%2F599d258903051.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=a281a70935bac7dcb07ef10e81f4e520&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-11-16%2F5a0cece424a3a.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=18d19dddfe16328c195f4b1da1d43fe3&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-01-25%2F5a699c89baaea.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301895&di=4948dcb4b319794648db953b36c61a02&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fb%2F599d258c44b97.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301894&di=6cae51c1c40980ba6057c702106fd9a5&imgtype=0&src=http%3A%2F%2Ftu.simei8.com%3A7788%2Fpic163%2F16385-16.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301894&di=138d8fb71e4085f5d3242b0913ecce74&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F4%2F58a2766a5fdbe.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301894&di=56cd0cc8f8c0031f5e81c51b05eb7f0c&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201703%2F04%2Fa9ebe62e899a0e5e0eb9414b4357a401.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301894&di=9ff545505390d4db5eca735162c39550&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-11-04%2F59fd79f1c5fe6.jpg%3Fdown");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301894&di=4ea294ded20841384da2e9030f34f3fa&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fa%2F58a11b17e0997.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301894&di=cd91be60eb38bcb90f4cef307dfd6064&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170925%2F20170925144653_d41d8cd98f00b204e9800998ecf8427e_4.jpeg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301893&di=91d7e63761f2362925895a9012bf2c0d&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-11-30%2F5a1f70f1a51a9.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301893&di=3199088fdb126390eb5ae2730fd8bfa3&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20171011%2F20171011095832_49d23dd458b7446249d84fda3d4ea1c1_7.jpeg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301893&di=7a6268205788e7f8bd79d376ffb2ead6&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-01-19%2F5a61656077ec1.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301893&di=4712ead7592b79cdc462d2b4bb3cacb0&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-29%2F5a45b879c17e3.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301892&di=d72a33819b7f66fcc0e63c961dd3be87&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F3%2F56d7e2dd4d996.jpg%3Fdown");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301892&di=a79ba52f920aa2dd697654f2c2e68359&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2%2F57982722d8309.jpg");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551854301892&di=5cf36c104b9515c100c2e354039b50e8&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F5%2F58802e1e5d476.jpg");
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new PreDownLoadAdapter(this, imageUrls);
        mRecyclerView.setAdapter(mAdapter);
    }
}
