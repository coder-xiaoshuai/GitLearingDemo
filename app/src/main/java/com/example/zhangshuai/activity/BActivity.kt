package com.example.zhangshuai.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.entity.IntentDataBean
import com.example.zhangshuai.kotlin.MainActivity

class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("zs", "接收intent:${intent.hashCode()}")
        var bundle = intent.extras.hashCode()

        var id = intent.getIntExtra(MainActivity.INTENT_KEY_ID, 0)

        var string = intent.getStringExtra(MainActivity.INTENT_KEY_STRING) + "22222222222"

        var arrayList = intent.getSerializableExtra(MainActivity.INTENT_KEY_LIST) as ArrayList<String>
        arrayList.add("呵呵")
        var intentDataBean = intent.getSerializableExtra(MainActivity.INTENT_KEY_BEAN) as IntentDataBean
        intentDataBean.name = "李四"
        intentDataBean.age = 25
        Log.e("zs", "接收bundle:$bundle")
        Log.e("zs", "接收list:$arrayList")
        Log.e("zs", "接收bean:$intentDataBean")
        Log.e("zs", "接收id:$id")
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun animatorTest(){
        TextView(this@BActivity).animate().withEndAction({})
    }
}