package com.leon.animationsandtransitions.utils

import android.view.View
import android.view.animation.*

/**
 * Created by Leon on 12.11.2017..
 */
fun View.scaleAnimation(fromX: Float = 0f, toX: Float = 1f, fromY: Float = 0f, toY: Float = 1f, duration: Long = 1000, pivotXValue: Float = 0.5f, pivotYValue: Float = 0.5f, startOffset: Long = 0): Animation {
    val anim = ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, pivotXValue, Animation.RELATIVE_TO_SELF, pivotYValue)
    anim.fillAfter = true
    anim.duration = duration
    anim.startOffset = startOffset
    startAnimation(anim)
    
    return anim
}

fun View.rotationAnimation(fromDegrees: Float = 0f, toDegrees: Float = 90f, duration: Long = 1000, pivotXValue: Float = 0.5f, pivotYValue: Float = 0.5f, startOffset: Long = 0): Animation {
    
    val anim = RotateAnimation(fromDegrees, toDegrees, RotateAnimation.RELATIVE_TO_SELF, pivotXValue, RotateAnimation.RELATIVE_TO_SELF, pivotYValue)
    anim.duration = duration
    anim.fillAfter = true
    anim.startOffset = startOffset
    startAnimation(anim)
    return anim
}



fun View.fadeInAnimation(fromAlpha: Float = 0f, toAlpha: Float = 1f, duration: Long = 1000, startOffset: Long = 0): Animation {
    val anim = AlphaAnimation(fromAlpha, toAlpha)
    anim.interpolator = DecelerateInterpolator() //add this
    anim.duration = duration
    anim.startOffset = startOffset
    startAnimation(anim)
    return anim
}



fun View.fadeOutAnimation(fromAlpha: Float = 1f, toAlpha: Float = 0f, duration: Long = 1000, startOffset: Long = 0): Animation {
    val anim = AlphaAnimation(fromAlpha, toAlpha)
    anim.interpolator = DecelerateInterpolator() //add this
    anim.duration = duration
    anim.startOffset = startOffset
    startAnimation(anim)
    return anim
}



fun View.alphaAnimation(fromAlpha: Float, toAlpha: Float, duration: Long = 1000, startOffset: Long = 0): Animation {
    val anim = AlphaAnimation(fromAlpha, toAlpha)
    anim.interpolator = DecelerateInterpolator() //add this
    anim.duration = duration
    anim.startOffset = startOffset
    startAnimation(anim)
    return anim
}



fun View.blinkAnimation(fromAlpha: Float = 0f, toAlpha: Float = 1f, duration: Long = 1000, startOffset: Long = 0, repeatMode: Int = Animation.REVERSE, repeatCount: Int = Animation.INFINITE): Animation {
    val anim = AlphaAnimation(fromAlpha, toAlpha)
    anim.interpolator = LinearInterpolator() //add this
    anim.duration = duration
    anim.startOffset = startOffset
    anim.repeatMode = repeatMode
    anim.repeatCount = repeatCount
    startAnimation(anim)
    return anim
}


fun View.moveAnimation(startX: Float, startY: Float, endX: Float, endY: Float, duration: Long = 1000): Animation {
    val anim = TranslateAnimation(startX, startY, endX, endY)
    anim.duration = duration // duartion in ms
    anim.fillAfter = false
    startAnimation(anim)
    return anim
}