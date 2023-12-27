package com.amira.bostaapp.ui.image_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.amira.bostaapp.R
import com.amira.bostaapp.databinding.FragmentZoomingImageBinding
import com.amira.bostaapp.ui.album_images_fragment.AlbumImagesFragmentArgs
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso


class ZoomingImageFragment : Fragment() {
    private var _binding: FragmentZoomingImageBinding? = null
    private val binding get() = _binding!!
    private val args: ZoomingImageFragmentArgs by navArgs()
    private var imageUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        imageUrl = args.imageUrl
        Log.d("ImageUrl1",imageUrl)


        _binding = FragmentZoomingImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("ImageUrl",imageUrl)
        Glide.with(requireContext()).load(imageUrl).into(binding.imageZooming)
    }
}