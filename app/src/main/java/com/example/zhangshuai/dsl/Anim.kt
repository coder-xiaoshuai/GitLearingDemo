package com.example.zhangshuai.dsl

import android.animation.Animator
import android.animation.AnimatorSet
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

abstract class Anim {
    /**
     * the real Animator which is about to run
     */
    abstract var animator: Animator
    var builder: AnimatorSet.Builder? = null

    /**
     * the interpolator of Animator
     */
    var duration
        get() = 300L
        set(value) {
            animator.duration = value
        }

    /**
     * the interpolator of Animator
     */
    var interpolator
        get() = LinearInterpolator() as Interpolator
        set(value) {
            animator.interpolator = value
        }

    /**
     * start delay of Animator
     */
    var delay
        get() = 0L
        set(value) {
            animator.startDelay = value
        }

    /**
     * the callbacks describe the status of animation
     */
    var onStart: ((Animator) -> Unit)? = null
    var onRepeat: ((Animator) -> Unit)? = null
    var onEnd: ((Animator) -> Unit)? = null
    var onCancel: ((Animator) -> Unit)? = null

    /**
     * reverse the value of ValueAnimator
     */
    abstract fun reverse()

    internal fun addListener() {
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                animator?.let { onRepeat?.invoke(it) }
            }

            override fun onAnimationEnd(animation: Animator?) {
                animator?.let { onEnd?.invoke(it) }
            }

            override fun onAnimationCancel(animation: Animator?) {
                animator?.let { onCancel?.invoke(it) }
            }

            override fun onAnimationStart(animation: Animator?) {
                animator?.let { onStart?.invoke(it) }
            }

        })
    }

}