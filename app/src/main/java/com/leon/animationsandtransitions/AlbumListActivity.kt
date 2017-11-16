package com.leon.animationsandtransitions

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AlbumListActivity : AppCompatActivity() {
    
    val albumListRecyclerView by lazy { findViewById<RecyclerView>(R.id.albumListRecyclerView) }
   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
   
        val albumArts = arrayOf<Track>(
            Track(R.drawable.mean_something_kinder_than_wolves, "Title 1", "Artist 1", 100),
            Track(R.drawable.cylinders_chris_zabriskie, "Title 2", "Artist 2", 120),
            Track(R.drawable.broken_distance_sutro, "Title 3", "Artist 3", 400),
            Track(R.drawable.playing_with_scratches_ruckus_roboticus, "Title 4", "Artist 4", 250),
            Track(R.drawable.keep_it_together_guster, "Title 5", "Artist 5", 300),
            Track(R.drawable.the_carpenter_avett_brothers, "Title 6", "Artist 6", 260),
            Track(R.drawable.please_sondre_lerche, "Title 7", "Artist 7", 180),
            Track(R.drawable.direct_to_video_chris_zabriskie, "Title 8", "Artist 8", 290)
        )

        
    
        
        val adapter = object : RecyclerView.Adapter<AlbumViewHolder>() {
            
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
                
                val itemView = layoutInflater.inflate(R.layout.album_grid_item, parent, false)
                
                return AlbumViewHolder(itemView, object : OnViewHolderClickedListener {
                    
                    
                    override fun onViewHolderClick(viewHolder: AlbumViewHolder) {
                        val position = viewHolder.adapterPosition % albumArts.size
                        val track = albumArts[position]
                       
                        val intent = Intent(this@AlbumListActivity, AlbumDetailActivity::class.java)
                        intent.putExtra(AlbumDetailActivity.EXTRA_IMAGE_RESID, track.imageResource)
                        intent.putExtra(AlbumDetailActivity.EXTRA_TITLE, track.title)
                        intent.putExtra(AlbumDetailActivity.EXTRA_ARTIST, track.artist)
                        intent.putExtra(AlbumDetailActivity.EXTRA_TIME, track.time)
    
                     
                        val p1 = Pair.create(viewHolder.albumArt as View, "albumArt")
                        val p2 =  Pair.create(viewHolder.titleTextView as View, "title")
                        
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@AlbumListActivity, p1, p2)
                        startActivity(intent, options.toBundle())
                    }
                })
                
            }
        
            override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
                val track = albumArts[position % albumArts.size]
                holder.albumArt.setImageResource(track.imageResource)
                holder.titleTextView.text = track.title
            }
        
        
            override fun getItemCount(): Int {
                return albumArts.size * 4
            }
        }
        
        albumListRecyclerView.adapter = adapter
        albumListRecyclerView.layoutManager = GridLayoutManager(this, 2)
        
    }
    
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu.add(groupId, itemId, order, titleRes)
        menu?.add(0, 0, 0, "Main Activity")
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            0 -> startActivity(Intent(this@AlbumListActivity, MainActivity::class.java))
        }
        return true
    }
    
    
    internal interface OnViewHolderClickedListener {
        fun onViewHolderClick(vh: AlbumViewHolder)
    }
    
    internal class AlbumViewHolder(itemView: View, private val mListener: OnViewHolderClickedListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        
        val albumArt by lazy { itemView.findViewById<ImageView>(R.id.album_art) }
        val titleTextView by lazy { itemView.findViewById<TextView>(R.id.titleTextView) }
        
        init {
            itemView.setOnClickListener(this)
        }
        
        override fun onClick(v: View) {
            mListener.onViewHolderClick(this)
        }
    }
}
