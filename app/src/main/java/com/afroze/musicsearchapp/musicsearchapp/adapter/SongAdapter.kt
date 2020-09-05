package com.afroze.musicsearchapp.musicsearchapp.adapter

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afroze.musicsearchapp.R
import com.afroze.musicsearchapp.musicsearchapp.model.Track
import com.afroze.musicsearchapp.musicsearchapp.ui.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.song_item.view.*

class SongAdapter(private val context: Context) : RecyclerView.Adapter<SongAdapter.MyViewHolder>() {

     var list: List<Track> = ArrayList()

    fun displayArtistList(list: List<Track>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.setData(current, position)
    }

    inner class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0
        private var current: Track? = null

        fun setData(current: Track, position: Int?){

            current?.let {

                val artworkUrl: String = current.artworkUrl100;
                Glide.with(context).load(artworkUrl).placeholder(R.drawable.ic_launcher_background).into(itemView.artwork);
                itemView.track_name.text = current.trackName
                itemView.artist_name_and_genre.text = current.artistName + current.primaryGenreName
                itemView.duration.text = current.trackTimeMillis.toString()
                itemView.price.text = String.format("US $ %s", current.trackPrice.toString())
            }
            this.pos = position!!
            this.current = current
        }
    }


}