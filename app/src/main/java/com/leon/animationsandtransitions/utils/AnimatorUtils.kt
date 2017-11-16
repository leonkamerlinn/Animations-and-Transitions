package com.leon.animationsandtransitions.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Path
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.LinearInterpolator
import io.codetail.animation.arcanimator.ArcAnimator
import io.codetail.animation.arcanimator.Side


/**
 * Created by Leon on 12.11.2017..
 */
fun View.translationXAnimator(deltaX: Float, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "translationX", deltaX)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}


fun View.translationYAnimator(deltaY: Float, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "translationY", deltaY)
    anim.duration = duration
    anim.startDelay = startDelay
    
    return anim
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.moveAnimator(path: Path, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "x", "y", path)
    anim.duration = duration
    anim.startDelay = startDelay
    
    return anim
}


fun View.blinkAnimator(fromAlpha: Float = 0f, toAlpha: Float = 1f, duration: Long = 1000, startDelay: Long = 0, repeatMode: Int = ValueAnimator.REVERSE, repeatCount: Int = ValueAnimator.INFINITE): Animator {
    val anim = ObjectAnimator.ofFloat(this, "alpha", fromAlpha, toAlpha)
    anim.interpolator = LinearInterpolator()
    anim.duration = duration
    anim.startDelay = startDelay
    anim.repeatMode = repeatMode
    anim.repeatCount = repeatCount
    return anim
}

fun View.alphaAnimator(fromAlpha: Float, toAlpha: Float, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "alpha", fromAlpha, toAlpha)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.fadeOutAnimator(fromAlpha: Float = 1f, toAlpha: Float = 0f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "alpha", fromAlpha, toAlpha)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.fadeInAnimator(fromAlpha: Float = 0f, toAlpha: Float = 1f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "alpha", fromAlpha, toAlpha)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.rotatationAnimator(fromDegrees: Float = 0f, toDegrees: Float = 90f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "rotation", fromDegrees, toDegrees)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.rotationXAnimator(fromDegrees: Float = 0f, toDegrees: Float = 90f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "rotationX", fromDegrees, toDegrees)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.rotationYAnimator(fromDegrees: Float = 0f, toDegrees: Float = 90f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "rotationY", fromDegrees, toDegrees)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.scaleXAnimator(from: Float = 1f, to: Float = 1.5f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "scaleX", from, to)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.scaleYAnimator(from: Float = 1f, to: Float = 1.5f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "scaleY", from, to)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.pivotXAnimator(deltaX: Float = 1f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "pivotX", deltaX)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.pivotYAnimator(deltaY: Float = 1f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "pivotY", deltaY)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.xAnimator(x: Float = 1f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "x", x)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

fun View.yAnimator(y: Float = 1f, duration: Long = 1000, startDelay: Long = 0): Animator {
    val anim = ObjectAnimator.ofFloat(this, "y", y)
    anim.duration = duration
    anim.startDelay = startDelay
    return anim
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.revealInAnimator(cx: Int = width / 2, cy: Int = height / 2, startRadius: Float = 0f, endRadius: Float = Math.hypot(width.toDouble(), height.toDouble()).toFloat(), startDelay: Long = 0, duration: Long = 500): Animator {
    
    // create the animator for this view (the start radius is zero)
    val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, startRadius, endRadius)
    anim.startDelay = startDelay
    anim.duration = duration
    // make the view visible and start the animation
    visibility = View.VISIBLE
    
    return anim
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.revealOutAnimator(cx: Int = width / 2, cy: Int = height / 2, startRadius: Float = Math.max(width, height).toFloat(), endRadius: Float = 0f, startDelay: Long = 0, duration: Long = 500): Animator {
    val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, startRadius, endRadius)
    anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            visibility = View.GONE
        }
    })
    
    anim.startDelay = startDelay
    anim.duration = duration
    
    return anim
}


fun View.bottomAnimator(startValue: Int = top, endValue: Int = bottom, duration: Long = 1000): Animator {
    val anim = ObjectAnimator.ofInt(this, "bottom", startValue, endValue)
    anim.duration = duration
    return anim
}

fun View.topAnimator(startValue: Int = top, endValue: Int = bottom, duration: Long = 1000): Animator {
    val anim = ObjectAnimator.ofInt(this, "top", startValue, endValue)
    anim.duration = 1000
    return anim
}



fun Animator.then(func: (animator: Animator?) -> Unit): Animator {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        
        }
        
        override fun onAnimationEnd(animation: Animator?) {
            func(animation)
        }
        
        override fun onAnimationCancel(animation: Animator?) {
            func(animation)
        }
        
        override fun onAnimationStart(animation: Animator?) {
        
        }
    })
    
    return this
}

fun com.nineoldandroids.animation.Animator.then(func: (animator: com.nineoldandroids.animation.Animator?) -> Unit): com.nineoldandroids.animation.Animator {
    addListener(object : com.nineoldandroids.animation.Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: com.nineoldandroids.animation.Animator?) {
        
        }
    
        override fun onAnimationEnd(animation: com.nineoldandroids.animation.Animator?) {
            func(animation)
        }
    
        override fun onAnimationCancel(animation: com.nineoldandroids.animation.Animator?) {
            func(animation)
        }
    
        override fun onAnimationStart(animation: com.nineoldandroids.animation.Animator?) {
        
        }
    })
    
    return this
}

fun View.createArcAnimator(endX: Float, endY: Float, degree: Float = 70f, side: Side = Side.RIGHT): com.nineoldandroids.animation.Animator {
    val anim = ArcAnimator.createArcAnimator(this, endX, endY, degree, side)
    return anim
}

fun View.createArcAnimator(nestView: View, degree: Float = 70f, side: Side = Side.RIGHT): com.nineoldandroids.animation.Animator {
    val anim = ArcAnimator.createArcAnimator(this, nestView, degree, side)
    return anim
}