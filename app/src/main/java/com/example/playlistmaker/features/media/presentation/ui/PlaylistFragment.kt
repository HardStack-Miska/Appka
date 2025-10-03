package com.example.playlistmaker.features.media.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.playlistmaker.features.media.presentation.viewmodel.PlaylistViewModel
import com.example.playlistmaker.R
import com.google.android.material.button.MaterialButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistFragment : Fragment() {
    private val viewModel: PlaylistViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newPlaylistButton: MaterialButton = view.findViewById(R.id.newPlaylistButton)
        newPlaylistButton.setOnClickListener {
            findNavController().navigate(R.id.action_mediaLibraryFragment_to_createPlaylistFragment)
        }
    }

    companion object {
        fun newInstance(): PlaylistFragment = PlaylistFragment()
    }
}