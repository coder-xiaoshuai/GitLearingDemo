package com.example.zhangshuai.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.EmptyUtils;
import com.example.common.ViewUtils;
import com.example.zhangshuai.gitlearingdemo.R;
import com.lchad.gifflen.Gifflen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GifActivity extends AppCompatActivity {
    private String imageCachePath;
    private String imageSavePath;
    private List<String> imagePaths;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        initSdcardFolders(this);

        imagePaths = new ArrayList<>();

        findViewById(R.id.btn_gif).setOnClickListener(v -> {
            imagePaths.clear();
            String staticImagePath = createBitmap(500,500);
            for (int i = 0; i < 20; i++) {
                imagePaths.add(createBitmapWithBlack(500, 500, i + 1, 20));
            }
            List<File> fileList = new ArrayList<>();
            for (int i = 0; i <10 ; i++) {
                fileList.add(new File(staticImagePath));
            }
            for (int i = 0; i < imagePaths.size(); i++) {
                fileList.add(new File(imagePaths.get(i)));
            }
            for (int i = 0; i <10 ; i++) {
                fileList.add(new File(staticImagePath));
            }
            Gifflen mGiffle = new Gifflen.Builder().delay(20) //每相邻两帧之间播放的时间间隔.
                .width(500)    //生成Gif文件的宽度(像素).
                .height(500)    //生成Gif文件的高度(像素).
                .listener(new Gifflen.OnEncodeFinishListener() {  //创建完毕的回调
                    @Override
                    public void onEncodeFinish(String path) {
                        Toast.makeText(GifActivity.this, "生成成功", Toast.LENGTH_SHORT).show();
                    }
                }).build();

            mGiffle.encode(500, 500, imageSavePath + "test.gif", fileList);
        });
    }

    private String createBitmap(int videoWidth, int videoHeight) {
        List<String> brands = new ArrayList<>();
        brands.add("品牌标签1");
        brands.add("品牌标签2");
        brands.add("品牌标签3");
        View rootView = ViewUtils.newInstance(this, R.layout.item_brands);
        LinearLayout layoutBrand = rootView.findViewById(R.id.ll_brand);
        if (EmptyUtils.isNotEmpty(brands)) {
            addBrandChild(layoutBrand, brands);
        }
        rootView.measure(View.MeasureSpec.makeMeasureSpec(videoWidth, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(videoHeight, View.MeasureSpec.EXACTLY));
        rootView.layout(0, 0, videoWidth, videoHeight);

        Bitmap result = Bitmap.createBitmap(videoWidth, videoHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        rootView.draw(canvas);

        return bitmap2File(result, UUID.randomUUID().toString(), imageCachePath);
    }

    private String createBitmapWithBlack(int videoWidth, int videoHeight, int m, int total) {
        List<String> brands = new ArrayList<>();
        brands.add("品牌标签1");
        brands.add("品牌标签2");
        brands.add("品牌标签3");
        View rootView = ViewUtils.newInstance(this, R.layout.item_brands);
        LinearLayout layoutBrand = rootView.findViewById(R.id.ll_brand);
        if (EmptyUtils.isNotEmpty(brands)) {
            addBrandChildWithBlack(layoutBrand, brands, m, total);
        }
        rootView.measure(View.MeasureSpec.makeMeasureSpec(videoWidth, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(videoHeight, View.MeasureSpec.EXACTLY));
        rootView.layout(0, 0, videoWidth, videoHeight);

        Bitmap result = Bitmap.createBitmap(videoWidth, videoHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        rootView.draw(canvas);

        return bitmap2File(result, UUID.randomUUID().toString(), imageCachePath);
    }

    private void addBrandChild(LinearLayout layoutBrand, List<String> brandDetails) {
        if (EmptyUtils.isEmpty(brandDetails)) {
            return;
        }
        for (String brandName : brandDetails) {

            TextView childView = (TextView) View.inflate(layoutBrand.getContext(), R.layout.item_text_brand, null);

            setBrandPadding(childView);
            childView.setText(brandName);
            LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = ViewUtils.dpToPx(4);
            layoutBrand.addView(childView, params);
        }
    }

    private void addBrandChildWithBlack(LinearLayout layoutBrand, List<String> brandDetails, int m, int total) {
        if (EmptyUtils.isEmpty(brandDetails)) {
            return;
        }
        for (String brandName : brandDetails) {
            FrameLayout childView =
                (FrameLayout) View.inflate(layoutBrand.getContext(), R.layout.item_text_brand_with_black, null);

            setBrandPaddingWithLighting(childView, m, total);
            ((TextView) childView.findViewById(R.id.text_brand_name)).setText(brandName);
            LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = ViewUtils.dpToPx(4);
            layoutBrand.addView(childView, params);
        }
    }

    private void setBrandPadding(TextView childView) {
        //if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
        childView.setLineSpacing(3.0f, 1.0f);
        childView.setPadding(ViewUtils.dpToPx(12), ViewUtils.dpToPx(7), ViewUtils.dpToPx(12), ViewUtils.dpToPx(7));
        //} else {
        //    childView.setPadding(ViewUtils.dpToPx(12), ViewUtils.dpToPx(-5), ViewUtils.dpToPx(12),
        //        ViewUtils.dpToPx(-5));
        //}
    }

    private void setBrandPaddingWithLighting(FrameLayout frameLayout, int m, int total) {
        //if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
        TextView textView = frameLayout.findViewById(R.id.text_brand_name);
        ImageView lighting = frameLayout.findViewById(R.id.image_lighting);
        textView.setLineSpacing(3.0f, 1.0f);
        textView.setPadding(ViewUtils.dpToPx(12), ViewUtils.dpToPx(7), ViewUtils.dpToPx(12), ViewUtils.dpToPx(7));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) lighting.getLayoutParams();
        layoutParams.leftMargin = (int) (100 / (float) total * m);
        lighting.setLayoutParams(layoutParams);

        //} else {
        //    frameLayout.setPadding(ViewUtils.dpToPx(12), ViewUtils.dpToPx(-5), ViewUtils.dpToPx(12),
        //        ViewUtils.dpToPx(-5));
        //}
    }

    public static String bitmap2File(Bitmap bitmap, String name, String dirPath) {
        String targetFilePath = dirPath + name + ".png";
        File f = new File(targetFilePath);
        if (f.exists()) {
            f.delete();
        }
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException var7) {
            return null;
        }
        return f.getAbsolutePath();
    }

    public void initSdcardFolders(Context context) {
        File sdDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sdDir = new File(Environment.getExternalStorageDirectory(), "");
        } else {
            if (android.os.Build.MODEL.equals("ZTE U930HD")) {
                sdDir = new File("/mnt/sdcard2");//ZTE U930HD 不装外置SD卡 内置的sd卡路径是这个 (modify by jackrex)
            } else {
                //使用/data/data 目录吧
                sdDir = context.getFilesDir();
            }
        }
        initPaths(sdDir);
    }

    private void initPaths(File sdDir) {
        imageCachePath = sdDir.toString() + "/GitLearningImageCache/";
        imageSavePath = sdDir.toString() + "/GifSaveCache/";
        File file = new File(imageCachePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(imageSavePath);
        if (!file2.exists()) {
            file2.mkdirs();
        }
    }
}
