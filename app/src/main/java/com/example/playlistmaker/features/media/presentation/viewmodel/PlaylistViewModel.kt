package com.example.playlistmaker.features.media.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playlistmaker.features.media.domain.interactor.PlaylistInteractor
import com.example.playlistmaker.features.media.domain.model.Playlist
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val playlistInteractor: PlaylistInteractor
) : ViewModel() {

    private val _playlists = MutableLiveData<List<Playlist>>(emptyList())
    val playlists: LiveData<List<Playlist>> = _playlists

    init {
        observePlaylists()
    }

    private fun observePlaylists() {
        viewModelScope.launch {
            playlistInteractor.observePlaylists().collect { playlists ->
                _playlists.postValue(playlists)
            }
        }
    }
}