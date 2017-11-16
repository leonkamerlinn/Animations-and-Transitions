package com.leon.animationsandtransitions.transitions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.transition.TransitionValues
import android.transition.Visibility
import android.view.View
import android.view.ViewGroup

/**
 * Created by Leon on 12.11.2017..
 */
class Fold : Visibility() {
    override fun onAppear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
        return createFoldAnimator(view, false)
    }
    
    override fun onDisappear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
        return createFoldAnimator(view, true)
    }
    
    fun createFoldAnimator(view: View, folding: Boolean): Animator {
        //if folding != true
        var start = view.top
        var end = view.top + view.measuredHeight - 1
        
        //if folding == true then reverse start and end
        if (folding) {
            val temp = start
            start = end
            end = temp
        }
        
        view.bottom = start
        return ObjectAnimator.ofInt(view, "bottom", start, end)
    }
}
