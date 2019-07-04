package com.example.zhangshuai.views

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View

class CanvasTestView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null){
            return
        }
        canvas?.run {
            save()
            clipRect(0f, 0f, 100f, 100f)
            //需要画的内容

            restore()

            0
        }

        //几何变换 --平移
        canvas.save()
        canvas.translate(200f,0f)

        canvas.restore()

        //旋转
        canvas.save()
        canvas.rotate(45f)

        canvas.restore()


        //scale
        canvas.save()
        canvas.scale(1.5f,1.5f)

        canvas.restore()

        //错切
        canvas.save()
        canvas.skew(0f,0.5f)

        canvas.restore()

        //使用 Matrix 来做常见变换

        val matrix = Matrix()

        matrix.reset()
        matrix.postTranslate(100f,100f)
        matrix.postRotate(30f)

        canvas.save()
        canvas.concat(matrix)

        canvas.restore()


        //camera三维转化
        val camera = Camera()
        canvas.save()
        camera.save()
        camera.rotateX(30f)
        camera.applyToCanvas(canvas)

        camera.restore()
        canvas.restore()

        /*-----Canvas 的几何变换顺序是反的，-----*/
        canvas.save()
        camera.save()
        camera.rotateX(30f)
        canvas.translate(300f,300f)
        camera.applyToCanvas(canvas)
        canvas.translate(-300f,-300f)
        camera.restore()


        //Camera.setLocation(x, y, z) 设置虚拟相机的位置
        camera.setLocation(0f,100f,300f)
    }
}