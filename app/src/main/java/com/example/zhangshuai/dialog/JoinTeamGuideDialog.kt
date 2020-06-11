package com.example.zhangshuai.dialog


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.example.zhangshuai.gitlearingdemo.R


class JoinTeamGuideDialog(private val mContext: Context?) : AlertDialog(mContext) {

//    private var currentMember: CurrentMember? = null
    private var isRequestEnd = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_join_team_view)
        initTheme()
//        initView()
    }

    private fun initTheme() {
        //设置dialog在屏幕底部
        window?.setGravity(Gravity.BOTTOM)
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
//        window?.setWindowAnimations(R.style.dialogStyle)
        window?.decorView?.setPadding(0, 0, 0, 0)
        //获得window窗口的属性
        window?.setWindowAnimations(R.style.bottom_in_out_dialog_anim)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = window?.attributes

        //设置窗口宽度为充满全屏
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        //设置窗口高度为包裹内容
        lp?.height = WindowManager.LayoutParams.WRAP_CONTENT
        //将设置好的属性set回去
        window?.attributes = lp
    }

//    private fun initView() {
//        currentMember = CurrentMember.mine(mContext)
//        if (member != null) {
//            ImageDownloader.getInstance().loadCirCle(mContext, avatarImage, member.avatar_url, R.drawable.yidui_img_avatar_bg)
//            nicknameText?.text = member.nickname
//            if (!TextUtils.isEmpty(member.location)) {
//                locationText?.visibility = View.VISIBLE
//                locationText?.text = member.location
//            }
//            if (member.sex == 0) {
//                roleText?.text = "伊对月老"
//            }
//        }
//        joinTeamBtn?.setOnClickListener {
//            joinTeam()
//            SensorsStatUtils.trackEvent(SensorsEvent.COMMON_POPUP_CLICK,
//                    SensorsModel.build().common_popup_position(SensorsValue.BOTTOM)
//                            .common_popup_type(SensorsValue.COMMON_POPUP_TYPE_JOIN_TEAM_GUIDE)
//                            .common_popup_button_content(SensorsValue.COMMON_POPUP_BUTTON_CONTENT_OK)
//                            .title(SensorsStatUtils.getCurrTitle()))
//        }
//        closeBtn?.setOnClickListener {
//            dismiss()
//            SensorsStatUtils.trackEvent(SensorsEvent.COMMON_POPUP_CLICK,
//                    SensorsModel.build().common_popup_position(SensorsValue.BOTTOM)
//                            .common_popup_type(SensorsValue.COMMON_POPUP_TYPE_JOIN_TEAM_GUIDE)
//                            .common_popup_button_content(SensorsValue.COMMON_POPUP_BUTTON_CONTENT_CLOSE)
//                            .title(SensorsStatUtils.getCurrTitle()))
//        }
//        mHandler?.postDelayed(mRunnable, CommonDefine.TimeExtra.ONE_SECOND * 15L)
//
//        SensorsStatUtils.trackEvent(SensorsEvent.COMMON_POPUP_EXPOSE,
//                SensorsModel.build().common_popup_position(SensorsValue.BOTTOM)
//                        .common_popup_type(SensorsValue.COMMON_POPUP_TYPE_JOIN_TEAM_GUIDE)
//                        .common_popup_expose_refer_event(SensorsStatUtils.getsetRefreEvent())
//                        .title(SensorsStatUtils.getCurrTitle()))
//
//    }
//
//    private var mRunnable = Runnable {
//        if (AppUtils.contextExist(mContext)) {
//            dismiss()
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mHandler?.removeCallbacks(mRunnable)
//    }
//
//    /***
//     * 加入单身团
//     */
//    private fun joinTeam() {
//        if (TextUtils.isEmpty(currentMember?.id) || TextUtils.isEmpty(roomId) || TextUtils.isEmpty(member?.member_id)) {
//            ToastUtil.show(R.string.live_video_join_single_fail)
//            return
//        }
//        if (!isRequestEnd)
//            return
//        isRequestEnd = false
//        MiApi.getInstance().joinSingleTeam(currentMember?.id, roomId, member?.member_id).enqueue(object : Callback<SingleTeamStatus> {
//            override fun onResponse(call: Call<SingleTeamStatus>, response: Response<SingleTeamStatus>) {
//                if (!AppUtils.contextExist(mContext)) return
//                isRequestEnd = true
//                if (response.isSuccessful) {
//                    val singleTeamStatus = response.body()
//                    if (null == singleTeamStatus) {
//                        ToastUtil.show(R.string.live_video_join_single_fail)
//                        return
//                    }
//                    if (singleTeamStatus.status == 1) {
//                        ToastUtil.show(singleTeamStatus.msg)
//                        listener?.onJoinTeamSuccess()
//                    } else {
//                        ToastUtil.show(singleTeamStatus.msg)
//                    }
//                    dismiss()
//                } else {
//                    MiApi.makeErrorText(mContext, response)
//                }
//            }
//
//            override fun onFailure(call: Call<SingleTeamStatus>, t: Throwable) {
//                if (!AppUtils.contextExist(mContext)) return
//                isRequestEnd = true
//                MiApi.makeExceptionText(mContext, "请求失败", t)
//            }
//        })
//    }
//
//    interface OnJoinTeamListener {
//        fun onJoinTeamSuccess()
//
//    }


}