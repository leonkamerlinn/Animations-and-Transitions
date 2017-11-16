package com.leon.animationsandtransitions.transitions

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.transition.TransitionValues
import android.transition.Visibility
import android.view.View
import android.view.ViewGroup

/**
 * Created by Leon on 12.11.2017..
 */
class Scale : Visibility() {
    override fun onAppear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
        return createScaleAnimator(view, 0f, 1f)
    }
    
    override fun onDisappear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
        return createScaleAnimator(view, 1f, 0f)
    }
    
    private fun createScaleAnimator(view: View, fromScale: Float, toScale: Float): Animator {
        val set = AnimatorSet()
        val x = ObjectAnimator.ofFloat(view, View.SCALE_X, fromScale, toScale)
        val y = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromScale, toScale)
        set.playTogether(x, y)
        return set
    }
}