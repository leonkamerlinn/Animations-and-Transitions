package com.leon.animationsandtransitions.utils

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View

/**
 * Created by Leon on 12.11.2017..
 */



fun Activity.display(): DisplayMetrics {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    
    return metrics
}

fun View.measure(): View {
    measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    return this
}
