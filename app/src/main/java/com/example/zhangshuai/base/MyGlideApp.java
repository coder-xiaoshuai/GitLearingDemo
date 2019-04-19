package com.example.zhangshuai.base;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public class MyGlideApp extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);

        // 默认全局使用 565
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
    }

    //@Override
    //public void registerComponents(Context context, Registry registry) {
    //    super.registerComponents(context, registry);
    //        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(GlideProgressManager.getOkHttpClient());
    //        registry.replace(GlideUrl.class, InputStream.class, factory);
    //}

    //@Override
    //public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    //    super.registerComponents(context, glide, registry);
    //    OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(GlideProgressManager.getOkHttpClient());
    //    registry.replace(GlideUrl.class, InputStream.class, factory);
    //}

}
