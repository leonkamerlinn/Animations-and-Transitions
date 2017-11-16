package com.leon.animationsandtransitions

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.transition.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.leon.animationsandtransitions.transitions.Fold
import com.leon.animationsandtransitions.transitions.Scale

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AlbumDetailActivity : AppCompatActivity(), View.OnClickListener {
    
    
    companion object {
        val EXTRA_IMAGE_RESID = "extra_image_resid"
        val EXTRA_TITLE = "extra_title"
        val EXTRA_ARTIST = "extra_artist"
        val EXTRA_TIME = "extra_duration"
    }
    
    val albumArtImageView by lazy { findViewById<ImageView>(R.id.album_art) }
    val fab by lazy { findViewById<ImageButton>(R.id.fab) }
    val titlePanel by lazy { findViewById<ViewGroup>(R.id.title_panel) }
    val trackPanel by lazy { findViewById<ViewGroup>(R.id.track_panel) }
    val detailContainer by lazy { findViewById<ViewGroup>(R.id.detail_container) }
    val titleTextView by lazy { findViewById<TextView>(R.id.album_title) }
    val artistTextView by lazy { findViewById<TextView>(R.id.artist) }
    val trackNameTextView by lazy { findViewById<TextView>(R.id.track_name) }
    val lengthTextView by lazy { findViewById<TextView>(R.id.length) }
    
    
    private lateinit var mTransitionManager: TransitionManager
    private var mExpandedScene: Scene? = null
    private var mCurrentScene: Scene? = null
    private var mCollapsedScene: Scene? = null
    
 
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
    
       
    
        populate()
        setupTransitions()
     
    }
    
 
    
    
    
    private fun setupTransitions() {
        
        /*Slide slide = new Slide(Gravity.BOTTOM);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        getWindow().setEnterTransition(slide);
        getWindow().setSharedElementsUseOverlay(false);*/
    
        mTransitionManager = TransitionManager()
        val transitionRoot = detailContainer
    
        // Expanded scene
        mExpandedScene = Scene.getSceneForLayout(transitionRoot, R.layout.activity_album_detail_expanded, this)
    
        mExpandedScene!!.setEnterAction({
            populate()
            mCurrentScene = mExpandedScene
        })
    
        val expandTransitionSet = TransitionSet()
        expandTransitionSet.ordering = TransitionSet.ORDERING_SEQUENTIAL
        val changeBounds = ChangeBounds()
        changeBounds.duration = 200
        expandTransitionSet.addTransition(changeBounds)
    
        val fadeLyrics = Fade()
        fadeLyrics.addTarget(R.id.lyrics)
        fadeLyrics.duration = 150
        expandTransitionSet.addTransition(fadeLyrics)
    
        // Collapsed scene
        mCollapsedScene = Scene.getSceneForLayout(transitionRoot, R.layout.activity_album_detail, this)
    
        mCollapsedScene!!.setEnterAction({
            populate()
            mCurrentScene = mCollapsedScene
        })
    
        val collapseTransitionSet = TransitionSet()
        collapseTransitionSet.ordering = TransitionSet.ORDERING_SEQUENTIAL
    
        val fadeOutLyrics = Fade()
        fadeOutLyrics.addTarget(R.id.lyrics)
        fadeOutLyrics.duration = 150
        collapseTransitionSet.addTransition(fadeOutLyrics)
    
        val resetBounds = ChangeBounds()
        resetBounds.duration = 200
        collapseTransitionSet.addTransition(resetBounds)
    
        mTransitionManager.setTransition(mExpandedScene, mCollapsedScene, collapseTransitionSet)
        mTransitionManager.setTransition(mCollapsedScene, mExpandedScene, expandTransitionSet)
        mCollapsedScene!!.enter()
    
        //postponeEnterTransition();
    }
    
    
    
    private fun populate() {
        val imageRestId = intent.getIntExtra(EXTRA_IMAGE_RESID, R.drawable.mean_something_kinder_than_wolves)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val artist = intent.getStringExtra(EXTRA_ARTIST)
        val time = intent.getIntExtra(EXTRA_TIME, 0)
    
    
        titleTextView.text = title
        artistTextView.text = artist
        trackNameTextView.text = "$title - $artist"
        lengthTextView.text = intToTime(time)
    
    
        albumArtImageView.setImageResource(imageRestId)
    
        val albumBitmap = getReducedBitmap(imageRestId)
        colorizeFromImage(albumBitmap)
    
    
        albumArtImageView.setOnClickListener(this)
        titlePanel.setOnClickListener(this)
        trackPanel.setOnClickListener(this)
        
        
        
    }
    
    private fun getReducedBitmap(albumArtResId: Int): Bitmap {
        // reduce image size in memory to avoid memory errors
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        options.inSampleSize = 8
        return BitmapFactory.decodeResource(resources, albumArtResId, options)
    }
    
    private fun intToTime(time: Int): String {
        val minutes = Math.floor(time / 60.toDouble())
        
        return "${minutes.toInt()} : ${time % 60}"
    }
    
    private fun colorizeFromImage(image: Bitmap) {
        val palette = Palette.from(image).generate()
        
        // set panel colors
        val defaultPanelColor = -0x7f7f80
        val defaultFabColor = -0x111112
        titlePanel.setBackgroundColor(palette.getDarkVibrantColor(defaultPanelColor))
        trackPanel.setBackgroundColor(palette.getLightMutedColor(defaultPanelColor))
        
        // set fab colors
        val states = arrayOf(intArrayOf(android.R.attr.state_enabled), intArrayOf(android.R.attr.state_pressed))
        
        val colors = intArrayOf(palette.getVibrantColor(defaultFabColor), palette.getLightVibrantColor(defaultFabColor))
       
        fab.backgroundTintList = ColorStateList(states, colors)
        
    }
    
    private fun createTransition(visibility: Int): Transition {
        val set = TransitionSet()
        set.ordering = TransitionSet.ORDERING_SEQUENTIAL
        
        val tFab = Scale()
        tFab.duration = 150
        tFab.addTarget(fab)
        
        val tTitle = Fold()
        tTitle.duration = 150
        tTitle.addTarget(titlePanel)
        
        val tTrack = Fold()
        tTrack.duration = 150
        tTrack.addTarget(trackPanel)
        
        when(visibility) {
            View.VISIBLE -> {
                set.addTransition(tTitle)
                set.addTransition(tTrack)
                set.addTransition(tFab)
            }
            
            else -> {
                set.addTransition(tTrack)
                set.addTransition(tTitle)
                set.addTransition(tFab)
            }
        }
        
        
        return set
    }
    
    
    
    var counter = 0

    private fun hideTransition() {
        val transition = createTransition(View.INVISIBLE)
        TransitionManager.beginDelayedTransition(detailContainer, transition)
        fab.visibility = View.INVISIBLE
        titlePanel.visibility = View.INVISIBLE
        trackPanel.visibility = View.INVISIBLE
    }
    

    private fun showTransition() {
        val transition = createTransition(View.VISIBLE)
        TransitionManager.beginDelayedTransition(detailContainer, transition)
        fab.visibility = View.VISIBLE
        titlePanel.visibility = View.VISIBLE
        trackPanel.visibility = View.VISIBLE
    }
    
    override fun onClick(p0: View?) {
        when(p0?.id) {
            
            R.id.album_art -> {
               
                counter++
                if (counter % 2 == 1) {
                    hideTransition()
                } else {
                    showTransition()
                }
                    
                
                
            }
            
            R.id.title_panel -> {
            
            }
            
            R.id.track_panel -> {
                mCurrentScene = if (mCurrentScene == mExpandedScene) {
                    mExpandedScene
                } else {
                    mExpandedScene
                }
                mTransitionManager.transitionTo(mCurrentScene)
            }
        }
    }
}
