package com.example.zhangshuai.kotlin

interface NetCallback {
    fun onSuccess(json: String)
    fun onFail(errorCode: Int, msg: String)
    fun onError(errorCode: Int, msg: String)
}