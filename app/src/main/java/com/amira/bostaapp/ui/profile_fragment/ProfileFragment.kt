package com.amira.bostaapp.ui.profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amira.bostaapp.databinding.FragmentProfileBinding
import com.amira.bostaapp.domain.model.Album
import com.amira.bostaapp.domain.model.User
import com.amira.bostaapp.utils.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private var albumList = arrayListOf<Album>()
    private var albumAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(context)
        binding.albumRecycler.layoutManager = linearLayoutManager
        binding.albumRecycler.setHasFixedSize(true)

        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.albumsRemoteLiveData.observe(viewLifecycleOwner) {
            viewModel.getLocalAlbum()
        }
        viewModel.albumsLocalLiveData.observe(viewLifecycleOwner) { response ->
            if (response.data != null) {
                albumList.clear()
                for (item in response.data) {
                    albumList.add(item)
                }
                setUpAlbumAdapter()
            }
        }
        viewModel.usersRemoteLiveData.observe(viewLifecycleOwner) { response ->
            if (response.data == true) {
                val randomId = (0..10).random()
                viewModel.getRemoteAlbums(userId = randomId)
                viewModel.getLocalUsers(userId = randomId)
            }
        }
        viewModel.usersLocalLiveData.observe(viewLifecycleOwner) { response ->
            if (response.data != null) {
                setUserData(response.data)
            }
        }
    }

    private fun setUpAlbumAdapter() {
        albumAdapter = AlbumsAdapter(albumList,
            onItemClicked = { _, albumId, albumTitle ->
                viewModel.getRemoteImages(albumId = albumId)
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToAlbumFragment(
                        albumId = albumId.toString(),
                        albumTitle = albumTitle
                    )
                )
            })
        binding.albumRecycler.adapter = albumAdapter
    }

    private fun setUserData(data: List<User>) {
        binding.userName.text = data[0].userName
        binding.address.text =
            "${data[0].address.street}, ${data[0].address.suite}, ${data[0].address.city},\n${data[0].address.zipcode}"
    }
}