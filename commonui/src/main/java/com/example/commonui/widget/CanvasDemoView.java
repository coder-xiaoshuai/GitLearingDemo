package com.example.commonui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasDemoView extends View {
    public CanvasDemoView(Context context) {
        super(context);
    }

    public CanvasDemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布向(100,50)方向平移
        canvas.translate(100, 50);
        int saveTranslate = canvas.save();
        //canvas缩放  中心默认是左上角
        canvas.scale(2, 4);
        //canvas缩放 缩放中心是 100，100
        canvas.scale(2, 4, 100, 100);
        //canvas旋转 (顺时针为正方向)
        canvas.rotate(30);
        //canvas旋转 旋转中心为 100,100
        canvas.rotate(30, 100, 100);

        canvas.restore();//返回最新的save的状态
        canvas.restoreToCount(saveTranslate);//手动指定返回到哪个状态

        //画文字
        canvas.drawText("要写的文字", 50, 20, new Paint(Paint.ANTI_ALIAS_FLAG));
        //画圆形
        canvas.drawCircle(200, 200, 100, new Paint(Paint.ANTI_ALIAS_FLAG));
        //画线
        canvas.drawLine(100, 100, 300, 300, new Paint(Paint.ANTI_ALIAS_FLAG));
        //画椭圆
        RectF rectF = new RectF(150, 200, 500, 400);
        canvas.drawOval(rectF, new Paint(Paint.ANTI_ALIAS_FLAG));
        //画弧度
        canvas.drawArc(rectF, 20, 180, false, new Paint(Paint.ANTI_ALIAS_FLAG));
        //矩形
        canvas.drawRect(100, 100, 200, 200, new Paint(Paint.ANTI_ALIAS_FLAG));
        //多边形
        Path path = new Path();  // 路径对象
        path.moveTo(80, 200);// 此点为多边形的起点
        path.lineTo(120, 250);
        path.lineTo(80, 250);
        //....  可以添加多个点。构成多边形
        path.close(); // 使终点和起点链接，构成封闭图形
        canvas.drawPath(path, new Paint(Paint.ANTI_ALIAS_FLAG));
        //画贝塞尔曲线
        Path path2 = new Path();
        path2.moveTo(100, 100);//设置Path的起点
        /**
         * 参数1、2：x1，y1为控制点的坐标值
         * 参数3、4：x2，y2为终点的坐标值
         */
        path2.quadTo(300, 100, 400, 400); //设置贝塞尔曲线的控制点坐标和终点坐标
        path2.quadTo(500, 700, 800, 800);
        canvas.drawPath(path2, new Paint(Paint.ANTI_ALIAS_FLAG));//画出贝塞尔曲线
    }
}
