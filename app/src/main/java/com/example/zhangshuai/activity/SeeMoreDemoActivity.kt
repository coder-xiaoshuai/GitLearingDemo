package com.example.zhangshuai.activity

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhangshuai.adapter.ListSeeMoreAdapter
import com.example.zhangshuai.gitlearingdemo.R
import com.example.zhangshuai.utils.ViewUtils

class SeeMoreDemoActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var data: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_see_more)
        recyclerView = findViewById(R.id.recyclerView)

        initData()

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView?.layoutManager = layoutManager

        recyclerView?.adapter = ListSeeMoreAdapter(data!!, this)

        addActionListener()
    }

    fun initData() {
        data = ArrayList()
        for (i in 0..19) {
            data?.add("String$i")
        }
    }

    private fun addActionListener() {
//        recyclerView?.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if (event?.action == MotionEvent.ACTION_UP || event?.action == MotionEvent.ACTION_CANCEL) {
//                    if ((recyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == 19){
//                        Toast.makeText(this@SeeMoreDemoActivity,"查看更多",Toast.LENGTH_SHORT).show()
//                    }
//                }
//                return false
//            }
//        })

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == data?.size?.minus(1)) {
                    Log.e("tag", "单位${ViewUtils.dpToPx(this@SeeMoreDemoActivity, 1f)}")
                    Log.e("tag", "总偏移量${recyclerView.computeHorizontalScrollOffset()}")
                    val rect=Rect()
                    (recyclerView.layoutManager as LinearLayoutManager).findViewByPosition(data?.size!!.minus(1))?.getLocalVisibleRect(rect)
                    var translationX = rect.width()
                            Log.e("tag", "translationX$translationX")
                    Toast.makeText(this@SeeMoreDemoActivity, "查看更多" + (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition(), Toast.LENGTH_SHORT).show()
                    recyclerView.scrollBy(-(translationX?.toInt() )!!, 0)
                }
            }
        })
    }

}