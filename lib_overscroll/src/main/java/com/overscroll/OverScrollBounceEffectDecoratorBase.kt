package com.overscroll

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Property
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.drawerlayout.widget.DrawerLayout.STATE_IDLE
import com.overscroll.IOverScrollState.Companion.STATE_BOUNCE_BACK
import com.overscroll.IOverScrollState.Companion.STATE_DRAG_END_SIDE
import com.overscroll.IOverScrollState.Companion.STATE_DRAG_START_SIDE
import com.overscroll.adapters.IOverScrollDecoratorAdapter


abstract class OverScrollBounceEffectDecoratorBase : IOverScrollDecor, View.OnTouchListener {

    companion object {
        const val DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD = 3F
        const val DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK = 1f
        const val DEFAULT_DECELERATE_FACTOR = -2f

        const val MAX_BOUNCE_BACK_DURATION_MS = 800
        const val MIN_BOUNCE_BACK_DURATION_MS = 200
    }

    val mStartAttr = OverScrollStartAttributes()
    var mViewAdapter: IOverScrollDecoratorAdapter? = null
    var mIdleState: IdleState? = null
    lateinit var mCurrentState: IDecoratorState
    val mStateListener: IOverScrollStateListener = ListenerStubs.OverScrollStateListenerStub()
    val mUpdateListener: IOverScrollUpdateListener = ListenerStubs.OverScrollUpdateListenerStub()
    protected val mOverScrollingState: OverScorllingState? = null
    protected val mBounceBackState: BounceBackState? = null
    var mVelocity = 0f


    abstract class MotionAttributes {
        var mAbsOffset = 0f
        var mDeltaOffset = 0f
        var mDir = false

        abstract fun init(view: View, event: MotionEvent): Boolean

    }

    open class OverScrollStartAttributes {
        var mPointerId = 0
        var mAbsOffset = 0f
        var mDir = false
    }

    abstract class AnimationAttributes {
        var mProperty: Property<View, Float>? = null
        var mAbsOffset = 0f
        var mMaxOffset = 0f
        abstract fun init(view: View)
    }

    interface IDecoratorState {
        fun handleMoveTouchEvent(event: MotionEvent): Boolean

        fun handleUpOrCancelTouchEvent(event: MotionEvent): Boolean

        fun handleEntryTransition(fromState: IDecoratorState)

        fun getStateId(): Int

    }

    inner class IdleState : IDecoratorState {

        val mMoveAttr: MotionAttributes? = null

        override fun handleMoveTouchEvent(event: MotionEvent): Boolean {
            val view = mViewAdapter?.getView()
            if (!mMoveAttr?.init(view!!, event)!!) {
                return false
            }
            if ((mViewAdapter?.isInAbsoluteStart()!! && mMoveAttr.mDir) || (mViewAdapter?.isInAbsoluteEnd()!! && !mMoveAttr.mDir)) {
                mStartAttr.mPointerId = event.getPointerId(0)
                mStartAttr.mAbsOffset = mMoveAttr.mAbsOffset
                mStartAttr.mDir = mMoveAttr.mDir
//                issueStateTransition(mOverScr)

            }
            return false
        }

        override fun handleUpOrCancelTouchEvent(event: MotionEvent): Boolean {
            return false
        }

        override fun handleEntryTransition(fromState: IDecoratorState) {
            mStateListener.onOverScrollStateChange(this@OverScrollBounceEffectDecoratorBase, fromState.getStateId(), this.getStateId())
        }

        override fun getStateId(): Int {
            return STATE_IDLE
        }

    }

    inner class OverScorllingState(val mTouchDragRatioFwd: Float = 0f, val mTouchDragRatioBck: Float = 0f) : IDecoratorState {

        lateinit var mMoveAttr: MotionAttributes
        var mCurrDragState = 0

        init {
            mMoveAttr = createMotionAttributes()

        }

        override fun handleMoveTouchEvent(event: MotionEvent): Boolean {
            if (mStartAttr.mPointerId != event.getPointerId(0)) {
                issueStateTransition(mBounceBackState!!)
                return true
            }

            val view = mViewAdapter!!.getView()
            if (!mMoveAttr.init(view, event)) {
                return true
            }

            val deltaOffset = mMoveAttr.mDeltaOffset / if (mMoveAttr.mDir === mStartAttr.mDir) mTouchDragRatioFwd else mTouchDragRatioBck
            val newOffset = mMoveAttr.mAbsOffset + deltaOffset
            if (mStartAttr.mDir && !mMoveAttr.mDir && newOffset <= mStartAttr.mAbsOffset || !mStartAttr.mDir && mMoveAttr.mDir && newOffset >= mStartAttr.mAbsOffset) {
                translateViewAndEvent(view, mStartAttr.mAbsOffset, event)
                mUpdateListener.onOverScrollUpdate(this@OverScrollBounceEffectDecoratorBase, mCurrDragState, 0f)

                issueStateTransition(mIdleState!!)
                return true
            }

            if (view.parent != null) {
                view.parent.requestDisallowInterceptTouchEvent(true)
            }

            val dt = event.eventTime - event.getHistoricalEventTime(0)
            if (dt > 0) { // Sometimes (though rarely) dt==0 cause originally timing is in nanos, but is presented in millis.
                mVelocity = deltaOffset / dt
            }

            translateView(view, newOffset)
            mUpdateListener.onOverScrollUpdate(this@OverScrollBounceEffectDecoratorBase, mCurrDragState, newOffset)

            return true
        }

        override fun handleUpOrCancelTouchEvent(event: MotionEvent): Boolean {
            issueStateTransition(mBounceBackState!!)
            return false
        }

        override fun handleEntryTransition(fromState: IDecoratorState) {
            mCurrDragState = if (mStartAttr.mDir) STATE_DRAG_START_SIDE else STATE_DRAG_END_SIDE
            mStateListener.onOverScrollStateChange(this@OverScrollBounceEffectDecoratorBase, fromState.getStateId(), this.getStateId())
        }

        override fun getStateId(): Int {
            return mCurrDragState
        }

    }


    inner class BounceBackState(val mDecelerateFactor: Float) : IDecoratorState, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        val mBounceBackInterpolator = DecelerateInterpolator()
        var mDoubleDecelerateFactor: Float = 0.0f
        lateinit var mAnimAttributes: AnimationAttributes

        init {
            mDoubleDecelerateFactor = mDecelerateFactor * 2f
            mAnimAttributes = createAnimationAttributes()
        }

        override fun handleMoveTouchEvent(event: MotionEvent): Boolean {
            return true
        }

        override fun handleUpOrCancelTouchEvent(event: MotionEvent): Boolean {
            return true
        }

        override fun handleEntryTransition(fromState: IDecoratorState) {
            mStateListener.onOverScrollStateChange(this@OverScrollBounceEffectDecoratorBase, fromState.getStateId(), this.getStateId())
            val bounceBackAnim = createAnimator()

        }

        fun createAnimator(): Animator {
            val view = mViewAdapter!!.getView()
            mAnimAttributes.init(view)
            if (mVelocity == 0f || (mVelocity < 0 && mStartAttr.mDir) || (mVelocity > 0 && !mStartAttr.mDir)) {
                return createBounceBackAnimator(mAnimAttributes.mAbsOffset)
            }

            var slowdownDuration = -mVelocity / mDecelerateFactor
            slowdownDuration = if (slowdownDuration < 0) 0f else slowdownDuration

            val slowdownDistance = -mVelocity * mVelocity / mDoubleDecelerateFactor
            val slowdownEndOffset = mAnimAttributes.mAbsOffset + slowdownDistance
            val slowdownAnim = createSlowdownAnimator(view, slowdownDuration.toInt(), slowdownEndOffset)
            val bounceBackAnim = createBounceBackAnimator(slowdownEndOffset)

            val wholeAnim = AnimatorSet()
            wholeAnim.playSequentially(slowdownAnim, bounceBackAnim)
            return wholeAnim
        }

        fun createSlowdownAnimator(view: View, slowdownDuration: Int, slowdownEndOffset: Float): ObjectAnimator {
            val slowdownAnim = ObjectAnimator.ofFloat<View>(view, mAnimAttributes.mProperty, slowdownEndOffset)
            slowdownAnim.duration = slowdownDuration.toLong()
            slowdownAnim.interpolator = mBounceBackInterpolator
            slowdownAnim.addUpdateListener(this)
            return slowdownAnim
        }

        fun createBounceBackAnimator(startOffset: Float): ObjectAnimator {

            val view = mViewAdapter!!.getView()

            // Duration is proportional to the view's size.
            val bounceBackDuration = Math.abs(startOffset) / mAnimAttributes.mMaxOffset * MAX_BOUNCE_BACK_DURATION_MS
            val bounceBackAnim = ObjectAnimator.ofFloat<View>(view, mAnimAttributes.mProperty, mStartAttr.mAbsOffset)
            bounceBackAnim.setDuration(Math.max(bounceBackDuration.toInt(), MIN_BOUNCE_BACK_DURATION_MS).toLong())
            bounceBackAnim.interpolator = mBounceBackInterpolator
            bounceBackAnim.addUpdateListener(this)
            return bounceBackAnim
        }

        override fun getStateId(): Int {
            return STATE_BOUNCE_BACK
        }

        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            issueStateTransition(mIdleState!!)
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }

        override fun onAnimationUpdate(animation: ValueAnimator?) {
        }

    }

    fun issueStateTransition(state: IDecoratorState) {
        var oldState = mCurrentState
        mCurrentState = state
        mCurrentState.handleEntryTransition(oldState)
    }

    fun attach() {
        getView().setOnTouchListener(this)
        getView().overScrollMode = View.OVER_SCROLL_NEVER
    }

    abstract fun createMotionAttributes(): MotionAttributes
    abstract fun createAnimationAttributes(): AnimationAttributes
    abstract fun translateView(view: View, offset: Float)
    abstract fun translateViewAndEvent(view: View, offset: Float, event: MotionEvent)

}