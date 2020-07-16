package com.example.zhangshuai.kotlin

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.example.zhangshuai.activity.*
import com.example.zhangshuai.dialog.JoinTeamGuideDialog
import com.example.zhangshuai.gitlearingdemo.R
import com.example.zhangshuai.mvp.PresenterTestActivity
import com.tencent.tinker.lib.tinker.TinkerInstaller
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_image_video_demo.setOnClickListener {
            var intent = Intent(this@MainActivity, ImageVideoDemoActivity::class.java)
            startActivity(intent)
        }
        btn_nine_image_preview.setOnClickListener {
            var intent = Intent(this@MainActivity, NineImageActivity::class.java)
            startActivity(intent)
        }

        btn_auto_text.setOnClickListener {
            var intent = Intent(this@MainActivity, AutoTextActivity::class.java)
            startActivity(intent)
        }
        btn_clickable.setOnClickListener {
            var intent = Intent(this@MainActivity, ClickableSpanActivity::class.java)
            startActivity(intent)
        }

        btn_path_demo.setOnClickListener {
            var intent = Intent(this@MainActivity, PathDemoActivity::class.java)
            startActivity(intent)
        }

        btn_rv.setOnClickListener {
            var intent = Intent(this@MainActivity, RvActivity::class.java)
            startActivity(intent)
        }

        btn_praise.setOnClickListener {
            var intent = Intent(this@MainActivity, PraiseDemoActivity::class.java)
            startActivity(intent)
        }

        btn_nine_grid.setOnClickListener {
            var intent = Intent(this@MainActivity, NineGridLayoutActivity::class.java)
            startActivity(intent)
        }

        btn_gif.setOnClickListener {
            var intent = Intent(this@MainActivity, GifActivity::class.java)
            startActivity(intent)
        }

        btn_event.setOnClickListener {
            var intent = Intent(this@MainActivity, EventTestActivity::class.java)
            startActivity(intent)
        }

        btn_my_butter_knife.setOnClickListener {
            var intent = Intent(this@MainActivity, MyButterKnifeActivity::class.java)
            startActivity(intent)
        }

        btn_profiler.setOnClickListener {
            var intent = Intent(this@MainActivity, ProfilerTestActivity::class.java)
            startActivity(intent)
        }

        btn_pre_download.setOnClickListener {
            var intent = Intent(this@MainActivity, PreDownLoadActivity::class.java)
            startActivity(intent)
        }

        btn_viewpager_demo.setOnClickListener {
            var intent = Intent(this@MainActivity, ViewPagerDemoActivity::class.java)
            startActivity(intent)
        }

        btn_set_test.setOnClickListener {
            var intent = Intent(this@MainActivity, SetTestActivity::class.java)
            startActivity(intent)
        }

        btn_praise2.setOnClickListener {
            var intent = Intent(this@MainActivity, PraiseDemo2Activity::class.java)
            startActivity(intent)
        }

        btn_verification_code.setOnClickListener {
            var intent = Intent(this@MainActivity, VerificationCodeActivity::class.java)
            startActivity(intent)
        }

        btn_border_image.setOnClickListener {
            var intent = Intent(this@MainActivity, BorderImageViewActivity::class.java)
            startActivity(intent)
        }

        btn_age_choice.setOnClickListener {
            var intent = Intent(this@MainActivity, AgeChoiceActivity::class.java)
            startActivity(intent)
        }

        btn_show_dialog.setOnClickListener {
            val dialog = JoinTeamGuideDialog(this@MainActivity)
            dialog.show()
        }

        btn_null_test.setOnClickListener {
//            nullTest(null!!)
        }

        btn_presenter_test.setOnClickListener {
            val intent = Intent(MainActivity@ this, PresenterTestActivity::class.java)
            startActivity(intent)
        }

        btn_final_test.setOnClickListener {
            val intent = Intent(MainActivity@ this, FinalTestActivity::class.java)
            startActivity(intent)
        }

        btn_human_test.setOnClickListener {
            val intent = Intent(MainActivity@ this, HumanbenchmarkTestActivity::class.java)
            startActivity(intent)
        }

        btn_tinker_test.setOnClickListener {
            val  intent = Intent(MainActivity@this,TinkerTestActivity::class.java)
            startActivity(intent)
        }

        loadPatch()
        Log.i("zs",Environment.getExternalStorageDirectory().absolutePath.toString())

    }

    fun nullTest(string: String) {
        Log.i("zs", string)
    }


    private fun loadPatch() {
        val path = "${Environment.getExternalStorageDirectory().absolutePath}/Tinker/";
        val dir = File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //patch_signed_7zip.apk为我们要打的补丁包
        val file = File(path, "patch_signed_7zip.apk");
        if (file.exists()) {
            if (file.length() > 0) {
                Log.e("我就想看看路径", file.absolutePath)
                TinkerInstaller.onReceiveUpgradePatch(MainActivity@ this, file.absolutePath)
            }
        }
    }

}
