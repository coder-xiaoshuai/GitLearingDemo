package com.example.zhangshuai.kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.activity.*
import com.example.zhangshuai.entity.IntentDataBean
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_main.*

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

        btn_rv_see_more.setOnClickListener {
            var intent = Intent(this@MainActivity, SeeMoreDemoActivity::class.java)
            startActivity(intent)
        }

        btn_request_layout_test.setOnClickListener {
            var intent = Intent(this@MainActivity, RequestLayoutActivity::class.java)
            startActivity(intent)
        }

        btn_drag_test.setOnClickListener {
            var intent = Intent(this@MainActivity, HoDragActivity::class.java)
            startActivity(intent)
        }

        btn_drag_test_rv.setOnClickListener {
            var intent = Intent(this@MainActivity, DragTestActivity::class.java)
            startActivity(intent)
        }

        btn_event_view.setOnClickListener {
            var intent = Intent(this@MainActivity, EventViewActivity::class.java)
            startActivity(intent)
        }

        btn_dsl_test.setOnClickListener {
            var intent = Intent(this@MainActivity, DslTestActivity::class.java)
            startActivity(intent)
        }

        btn_custom_show.setOnClickListener {
            var intent = Intent(this@MainActivity, CustomShowActivity::class.java)
            startActivity(intent)
        }

        btn_intent_test.setOnClickListener {
            var intent = Intent(this@MainActivity, BActivity::class.java)
            var bundle = Bundle()
            arrayList.clear()
            arrayList.add("哈哈")
            arrayList.add("嘻嘻")
            Log.e("zs", "传递list:$arrayList")
            bundle.putSerializable(INTENT_KEY_LIST, arrayList)

            Log.e("zs", "传递bean:$intentDataBean")
            bundle.putSerializable(INTENT_KEY_BEAN, intentDataBean)
            Log.e("zs", "传递bundle:${bundle.hashCode()}")
            bundle.putInt(INTENT_KEY_ID, pageId)
            bundle.putString(INTENT_KEY_STRING, string)
            intent.putExtras(bundle)
            Log.e("zs", "传递intent:${intent.hashCode()}")
            startActivity(intent)
        }

        btn_get_context.setOnClickListener {
            var intent = Intent(this@MainActivity, ContextTestActivity::class.java)
            startActivity(intent)
        }

        btn_get_width.setOnClickListener {
            var intent = Intent(this@MainActivity, ScrollViewActivity::class.java)
            startActivity(intent)
        }
    }

    private var arrayList = ArrayList<String>()
    private var intentDataBean = IntentDataBean("张三", 18)
    private var pageId = 100
    private var string = "1111111"

    companion object {
        const val INTENT_KEY_LIST = "intent_key_list"
        const val INTENT_KEY_BEAN = "intent_key_bean"
        const val INTENT_KEY_ID = "intent_key_id"
        const val INTENT_KEY_STRING = "intent_key_string"
    }

    override fun onResume() {
        super.onResume()
        Log.e("zs", "onResume:$arrayList")
        Log.e("zs", "onResume:$intentDataBean")
        Log.e("zs", "onResume:$pageId")
        Log.e("zs", "onResume:$string")
    }

}
