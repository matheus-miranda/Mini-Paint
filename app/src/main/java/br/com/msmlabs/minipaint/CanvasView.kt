package br.com.msmlabs.minipaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.core.content.res.ResourcesCompat

class CanvasView(context: Context) : View(context) {

    // Used for caching what has been drawn before
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        // Recycle Bitmap before creating a new one to prevent memory leak
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        // Bitmap with the screen size
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null)
    }
}