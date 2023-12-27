package com.amira.bostaapp.utils

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.amira.bostaapp.R
import com.amira.bostaapp.databinding.AlbumListBinding
import com.amira.bostaapp.domain.model.Album
import java.util.*

class AlbumsAdapter(
    private var albumList: List<Album>? = null,
    private val onItemClicked: (Int, Int, String) -> Unit,
) : RecyclerView.Adapter<AlbumsAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.album_list, parent, false)
        return Holder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentAlbum = albumList?.get(position)
        holder.binding.apply {
            albumName.text = currentAlbum!!.title
        }
    }

    override fun getItemCount(): Int {
        return albumList?.size ?: 0
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = AlbumListBinding.bind(itemView)

        init {
            binding.albumName.setOnClickListener {
                val currentAlbum = albumList?.get(adapterPosition)
                currentAlbum?.let { it1 -> onItemClicked(adapterPosition, it1.albumId, it1.title) }
            }
        }
    }
}