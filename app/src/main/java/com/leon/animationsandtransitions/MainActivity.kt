package com.leon.animationsandtransitions

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import com.leon.animationsandtransitions.utils.*
import io.codetail.animation.arcanimator.Side



class MainActivity : AppCompatActivity() {
    
    val show by lazy { findViewById<Button>(R.id.show) }
    val hide by lazy { findViewById<Button>(R.id.hide) }
    val layout by lazy { findViewById<LinearLayout>(R.id.layout) }
    val startX by lazy { show.left + show.pivotX }
    val startY by lazy { show.top + show.pivotY }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        
        show.setOnClickListener {
            val arcAnimator = show.createArcAnimator( layout.pivotX + layout.left, layout.pivotY + layout.top, 70f, Side.RIGHT)
            arcAnimator.duration = 500
        
            arcAnimator.setInterpolator(AccelerateInterpolator())
            arcAnimator.start()
            
            arcAnimator.then {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    layout.revealInAnimator(startRadius = show.width.toFloat() / 2).start()
        
                } else {
                    layout.fadeInAnimation()
                }
                show.visibility = View.GONE
            }
        }
        
    
        hide.setOnClickListener {
            val rAnimator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                layout.revealOutAnimator(endRadius = show.width.toFloat() / 2)
            } else {
                layout.fadeOutAnimator()
            }
            
    
            rAnimator.then {
                show.visibility = View.VISIBLE
                val arcAnim = show.createArcAnimator(startX, startY, 70f, Side.LEFT)
                arcAnim.duration = 500
                arcAnim.setInterpolator(DecelerateInterpolator())
                arcAnim.start()
            }
            rAnimator.start()
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            0 -> startActivity(Intent(this@MainActivity, AlbumListActivity::class.java))
        }
        return true
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu.add(groupId, itemId, order, titleRes)
        menu?.add(0, 0, 0, "List Activity")
        return true
    }
}
