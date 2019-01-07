package com.example.zhangshuai.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Utils to operate android views
 *
 * @author panxiangxing
 */
public class ViewUtils {

    private static final long SMALL_SCREEN_HEIGHT_DP = 570;

    private ViewUtils() {}

    /**
     * Creates a view.
     *
     * @param parent parent view
     * @param resId resource id
     * @return view
     */
    public static View newInstance(ViewGroup parent, @LayoutRes int resId) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    /**
     * Creates a view.
     *
     * @param parent parent view
     * @param resId resource id
     * @return view
     */
    public static View newInstance(ViewGroup parent, @LayoutRes int resId, boolean attachToRoot) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, attachToRoot);
    }

    /**
     * Creates a view.
     *
     * - 建议使用带 parent 的形式 { @see newInstance(ViewGroup parent, @LayoutRes int resId) }
     *
     * @param context context
     * @param resId resource id
     * @return view
     */
    public static View newInstance(Context context, @LayoutRes int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }

    public static void setTextWhenNoNull(TextView textView, String text) {
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }

    /**
     * 获取屏幕的高度，px
     */
    public static int getScreenHeightPx(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕的高度，dp
     */
    public static int getScreenHeightDp(Context context) {
        int heightPx = getScreenHeightPx(context);
        return (int) pxToDp(context, heightPx);
    }

    /**
     * 获取屏幕的宽度，px
     */
    public static int getScreenWidthPx(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的宽度，dp
     */
    public static int getScreenWidthDp(Context context) {
        int widthPx = getScreenWidthPx(context);
        return (int) pxToDp(context, widthPx);
    }

    /**
     * 将dip或dp值转换为px值
     */
    public static int dpToPx(Context context, float dipValue) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     */
    public static int spToPx(int spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
            Resources.getSystem().getDisplayMetrics());
    }

    public static int getScreenMinWidth(Context context) {
        int height = getScreenHeightPx(context);
        int width = getScreenWidthPx(context);
        return height < width ? height : width;
    }

    public static void setViewWidthDp(View view, int widthDp) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = dpToPx(view.getContext(), widthDp);
        view.setLayoutParams(layoutParams);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float getScale(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * for old scale , just return half of density
     */
    public static float getScaleForOldData(Context context) {
        return getScale(context) / 2;
    }

    public static boolean isTv(Context context) {
        return false;
    }

    public static void setBackgroundResourceAndKeepPadding(View view, int resId) {
        Drawable backgroundDrawable = ContextCompat.getDrawable(view.getContext(), resId);
        Rect drawablePadding = new Rect();
        backgroundDrawable.getPadding(drawablePadding);
        int top = view.getPaddingTop() + drawablePadding.top;
        int left = view.getPaddingLeft() + drawablePadding.left;
        int right = view.getPaddingRight() + drawablePadding.right;
        int bottom = view.getPaddingBottom() + drawablePadding.bottom;

        view.setBackgroundResource(resId);
        view.setPadding(left, top, right, bottom);
    }

    public static float getDimenDp(Context context, @DimenRes int dimenResId) {
        return context.getResources().getDimension(dimenResId);
    }

    public static boolean isSmallScreen(Context context) {
        return getScreenHeightDp(context) <= SMALL_SCREEN_HEIGHT_DP;
    }

    public static boolean hasVirtualKey(Activity activity) {
        return activity != null && getScreenOriginalHeight(activity) > 0
            && getScreenHeightPx(activity) - getScreenOriginalHeight(activity) != 0;
    }

    public static int getVirtualKeyHeight(Activity activity) {
        return getScreenOriginalHeight(activity) - getScreenHeightPx(activity);
    }

    public static int getRelativeLeft(View rootView, View myView) {
        if (myView.getParent() == rootView) {
            return myView.getLeft();
        } else {
            return myView.getLeft() + getRelativeLeft(rootView, (View) myView.getParent());
        }
    }

    public static int getRelativeTop(View rootView, View myView) {
        if (myView.getParent() == rootView) {
            return myView.getTop();
        } else {
            return myView.getTop() + getRelativeTop(rootView, (View) myView.getParent());
        }
    }

    /**
     * Converts dp unit to equivalent pixels, depending on device density.
     */
    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return 0;
        }
        return px / getScale(context);
    }

    /**
     * 关闭 RecyclerView 原生动画
     */
    public static void disableRecyclerViewAnimator(RecyclerView recyclerView) {
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
    }

    public static void viewFadeInFromY(View view, long duration, int translateFromYDp, long delay) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator totalTranslateYAnimator =
            ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, dpToPx(view.getContext(), translateFromYDp), 0);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1);
        animatorSet.play(totalTranslateYAnimator).with(alphaAnimator);
        animatorSet.setDuration(duration);
        animatorSet.setStartDelay(delay);
        animatorSet.start();
    }

    public static void viewFadeOutFromY(View view, long duration, int translateFromYDp, long delay) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator totalTranslateYAnimator =
            ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, dpToPx(view.getContext(), translateFromYDp));
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0);
        animatorSet.play(totalTranslateYAnimator).with(alphaAnimator);
        animatorSet.setDuration(duration);
        animatorSet.setStartDelay(delay);
        animatorSet.start();
    }

    public static int getScreenOriginalHeight(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        try {
            Class<?> clazz = Class.forName("android.view.Display");
            Method method = clazz.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(defaultDisplay, metrics);
            return metrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getScreenOriginalWidth(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        try {
            Class<?> clazz = Class.forName("android.view.Display");
            Method method = clazz.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(defaultDisplay, metrics);
            return metrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void setRoundRectangleBackground(View view, int radius, int color) {
        ShapeDrawable background = new ShapeDrawable();
        // The corners are ordered top-left, top-right, bottom-right,
        // bottom-left. For each corner, the array contains 2 values, [X_radius,
        // Y_radius]
        float[] radii = new float[8];
        radii[0] = radius;
        radii[1] = radius;
        radii[2] = radius;
        radii[3] = radius;
        radii[4] = radius;
        radii[5] = radius;
        radii[6] = radius;
        radii[7] = radius;
        background.setShape(new RoundRectShape(radii, null, null));
        background.getPaint().setColor(color);
        view.setBackgroundDrawable(background);
    }

    public static Rect getImageViewInsideSize(ImageView imageView) {
        if (imageView == null || imageView.getDrawable() == null || (
            imageView.getScaleType() != ImageView.ScaleType.CENTER_INSIDE
                && imageView.getScaleType() != ImageView.ScaleType.CENTER)) {
            return null;
        }
        Drawable drawable = imageView.getDrawable();
        int imgW = drawable.getIntrinsicWidth();
        int imgH = drawable.getIntrinsicHeight();
        float imgRatio = imgH != 0 ? (float) imgW / (float) imgH : 0;
        int viewW = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
        int viewH = imageView.getHeight() - imageView.getPaddingTop() - imageView.getPaddingBottom();
        float viewRatio = viewH != 0 ? (float) viewW / (float) viewH : 0;
        int finalW, finalH;
        if (imgRatio > viewRatio) {
            finalW = Math.min(imgW, viewW);
            finalH = imgRatio != 0 ? (int) (((float) finalW) / imgRatio) : 0;
        } else {
            finalH = Math.min(imgH, viewH);
            finalW = (int) (imgRatio * finalH);
        }

        int horizontalPadding = (viewW - finalW) / 2;
        int verticalPadding = (viewH - finalH) / 2;
        return new Rect(horizontalPadding + imageView.getPaddingLeft(), verticalPadding + imageView.getPaddingTop(),
            viewW - horizontalPadding - imageView.getPaddingBottom(),
            viewH - verticalPadding - imageView.getPaddingRight());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean isFitsSystemWindows(final Activity activity) {
        //noinspection SimplifiableIfStatement
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ViewGroup contentView = activity.findViewById(android.R.id.content);
            if (contentView == null) {
                return false;
            }
            View firstChild = contentView.getChildAt(0);
            if (firstChild == null) {
                return false;
            }
            return firstChild.getFitsSystemWindows();
        }
        return false;
    }

    /**
     * 判断view 是否可见，
     *
     * @param ratio 主观认为显示在屏幕内的比例，算作可见
     */
    public static boolean isViewVisibleFromWindow(float ratio, View view,
                                                  @LinearLayoutCompat.OrientationMode int orientation) {
        Rect localRect = new Rect();
        view.getLocalVisibleRect(localRect);
        boolean visible = false;
        if (localRect.height() == 0 || localRect.width() == 0) {
            visible = false;
        } else if (LinearLayoutCompat.VERTICAL == orientation) {
            //左上角出现在屏幕中 top = 0（向上滚动）; 右下角出现在屏幕中 bottom = height，（向下滚动）
            if (localRect.top == 0 || localRect.bottom == view.getHeight()) {
                visible = localRect.height() >= view.getHeight() * ratio;
            }
        } else if (LinearLayout.HORIZONTAL == orientation) {
            visible = localRect.width() >= view.getWidth() * ratio;
        }

        return visible;
    }

    public static ViewGroup.LayoutParams getLayoutMatchParent() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public static ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static boolean isFullScreen(final Activity activity) {
        return (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isTranslucentStatus(final Activity activity) {
        //noinspection SimplifiableIfStatement
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                != 0;
        }
        return false;
    }
}
