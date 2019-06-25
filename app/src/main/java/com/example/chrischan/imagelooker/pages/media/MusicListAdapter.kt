package com.example.chrischan.imagelooker.pages.media

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.chrischan.imagelooker.R
import com.example.chrischan.imagelooker.bean.MusicItem

/**
 * @author by Lucifer on 2019-06-25.
 */
class MusicListAdapter(private val musicData: ArrayList<MusicItem> = ArrayList(), private val itemClickedListener:(MusicItem)->Unit): RecyclerView.Adapter<MusicListAdapter.MusicItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false) as ConstraintLayout
        return MusicItemViewHolder(itemView, itemClickedListener)
    }

    override fun getItemCount(): Int {
        return musicData.size
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.titleView.text = musicData[position].title
        holder.subtitleView.text = musicData[position].subtitle
        Glide.with(holder.albumArt)
                .load(musicData[position].albumArtUri)
                .into(holder.albumArt)
        holder.item = musicData[position]
    }

    fun updateData(musicData: List<MusicItem>) {
        this.musicData.clear()
        this.musicData.addAll(musicData)
        notifyDataSetChanged()
    }
    class MusicItemViewHolder(musicItemView: ConstraintLayout, itemClickedListener:(MusicItem)->Unit): RecyclerView.ViewHolder(musicItemView) {
        val titleView: TextView = musicItemView.findViewById(R.id.title)
        val subtitleView: TextView = musicItemView.findViewById(R.id.subtitle)
        val albumArt: ImageView = musicItemView.findViewById(R.id.albumArt)

        var item: MusicItem? = null

        init {
            musicItemView.setOnClickListener {
                item?.let {
                    itemClickedListener(it)
                }
            }
        }
    }

}