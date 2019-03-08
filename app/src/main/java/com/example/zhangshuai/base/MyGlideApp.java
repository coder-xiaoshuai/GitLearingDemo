package com.example.zhangshuai.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

@GlideModule
public class MyGlideApp extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);

        // 默认全局使用 565
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
    }

    @Override
    public void registerComponents(Context context, Registry registry) {
        super.registerComponents(context, registry);
            OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(GlideProgressManager.getOkHttpClient());
            registry.replace(GlideUrl.class, InputStream.class, factory);
    }

    //@Override
    //public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    //    super.registerComponents(context, glide, registry);
    //    OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(GlideProgressManager.getOkHttpClient());
    //    registry.replace(GlideUrl.class, InputStream.class, factory);
    //}

}
