package com.example.zhangshuai.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PresenterTestActivity : AppCompatActivity(), TestView {

    private var presenter: TestPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = TestPresenter(this);
        presenter?.onbind(this)
    }

    override fun test() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestory()
    }

}