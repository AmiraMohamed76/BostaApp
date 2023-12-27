package com.amira.bostaapp.ui.album_images_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.amira.bostaapp.databinding.FragmentAlbumImagesBinding
import com.amira.bostaapp.domain.model.Image
import com.amira.bostaapp.utils.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumImagesFragment : Fragment() {
    private var _binding: FragmentAlbumImagesBinding? = null
    private val args: AlbumImagesFragmentArgs by navArgs()
    private val viewModel: AlbumImagesViewModel by viewModels()
    private val binding get() = _binding!!
    private var imagesList = arrayListOf<Image>()
    private var imagesAdapter: ImagesAdapter? = null
    private var albumId: Int = 0
    private var albumTitle: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        getArgs()
        _binding = FragmentAlbumImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getArgs() {
        albumId = args.albumId.toInt()
        albumTitle = args.albumTitle
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(context, 3)
        binding.albumImageRecycler.layoutManager = gridLayoutManager
        binding.albumImageRecycler.setHasFixedSize(true)

        binding.albumNameText.text = albumTitle
        viewModel.imageLocalLiveData.observe(viewLifecycleOwner) { response ->
            if (response.data != null) {
                imagesList.clear()
                for (item in response.data) {
                    imagesList.add(item)
                }
                setUpImageAdapter()
            }
        }
        imageSearch()
    }

    private fun imageSearch() {
        binding.etSearch.apply {
            isIconified = true
            setOnClickListener { this.isIconified = false }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    if (imagesAdapter != null) {
                        imagesAdapter?.getFilter()?.filter(query)
                    }
                    return false
                }
            })
        }
    }

    private fun setUpImageAdapter() {
        imagesAdapter = ImagesAdapter(imagesList, imagesList,
            onImageClicked = { imageUrl ->
                findNavController().navigate(
                    AlbumImagesFragmentDirections.actionAlbumFragmentToZoomingImageFragment(
                        imageUrl = imageUrl,
                    )
                )
            })
        binding.albumImageRecycler.adapter = imagesAdapter
    }
}