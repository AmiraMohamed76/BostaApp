package com.amira.bostaapp.utils

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.amira.bostaapp.R
import com.amira.bostaapp.databinding.ImageListLayoutBinding
import com.amira.bostaapp.domain.model.Image
import com.bumptech.glide.Glide
import java.util.*

class ImagesAdapter(
    private var imagesList: List<Image>? = null,
    private var imageListFiltered: List<Image>? = null,
    private val onImageClicked: (String) -> Unit,
) : RecyclerView.Adapter<ImagesAdapter.Holder>() {

    fun addImageList(mImageList: List<Image>) {
        imagesList = mImageList
        imageListFiltered = mImageList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.image_list_layout, parent, false)
        return Holder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentImage = imageListFiltered?.get(position)
        holder.binding.apply {
            val context = albumImage.context
            Glide.with(context).load(currentImage!!.url).into(albumImage)

        }
    }

    override fun getItemCount(): Int {
        return imageListFiltered?.size ?: 0
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ImageListLayoutBinding.bind(itemView)
        init {
            binding.albumImage.setOnClickListener {
                val currentAlbum = imageListFiltered?.get(adapterPosition)
                currentAlbum?.let { image -> onImageClicked(image.url) }
            }
        }
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val query = charSequence.toString().trim()
                val filterResults = FilterResults()
                imagesList?.let { filteredImagesList ->
                    val filteredList: MutableList<Image> = ArrayList<Image>()
                    for (i in filteredImagesList.indices) {
                        val row: Image = filteredImagesList[i]
                        if ((row.title.toUpperCase()
                                .contains(query.uppercase(Locale.getDefault()))
                                    || row.title.toUpperCase()
                                .contains(query.uppercase(Locale.getDefault())))
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filterResults.values = filteredList
                }

                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                if (filterResults != null) {
                        val filteredList: List<Image> =
                            filterResults.values as List<Image>
                        imageListFiltered = filteredList
                        notifyDataSetChanged()
                }
            }
        }
    }
}