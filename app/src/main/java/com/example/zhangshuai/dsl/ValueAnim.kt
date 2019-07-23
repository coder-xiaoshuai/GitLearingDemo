package com.example.zhangshuai.dsl

import android.animation.Animator
import android.animation.ValueAnimator

/**
 * An Animator just like [ValueAnimator]but original could reverse itself without the limitation of  Api level
 *
 * In addition,combing with [AnimSet], original is easy to build much more readable animation code. See at [AnimSet]
 */
class ValueAnim : Anim() {
    /**
     * the [ValueAnimator] is about to run
     */
    override var animator: Animator = ValueAnimator()
    private val valueAnimator
        get() = animator as ValueAnimator

    /**
     * the animation value
     */
    var values: Any? = null
        set(value) {
            field = value
            value?.let {
                when (it) {
                    is FloatArray -> {
                        valueAnimator.setFloatValues(*it)
                    }
                    is IntArray -> {
                        valueAnimator.setIntValues(*it)

                    }
                    else -> {
                        throw IllegalArgumentException("unsupported value type")
                    }
                }
            }
        }

    /**
     * [action] describe what to animate
     */
    var action: ((Any) -> Unit)? = null
        set(value) {
            field = value
            valueAnimator.animatedValue.let { value?.invoke(it) }
        }

    override fun reverse() {
        values?.let {
            when (it) {
                is FloatArray -> {
                    it.reverse()
                    valueAnimator.setFloatValues(*it)
                }
                is IntArray -> {
                    it.reverse()
                    valueAnimator.setIntValues(*it)
                }

                else -> {
                    throw IllegalArgumentException("unsupported type of value")
                }
            }
        }
    }

}