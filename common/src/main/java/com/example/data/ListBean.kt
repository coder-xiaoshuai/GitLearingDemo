package com.example.data

data class ListBean(
    var auth_info: String,
    var avatar: String,
    var desc: Any,
    var distance: Double,
    var followRelation: Any,
    var hasFollowed: Boolean,
    var photos: List<String>,
    var recoLogInfo: Any,
    var schoolSimpleInfo: Any,
    var sex: String,
    var signature: String,
    var userId: Int,
    var userName: String,
    var vipFlag: Int
)