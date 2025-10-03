package com.example.playlistmaker.features.media.domain.interactor

import com.example.playlistmaker.features.media.domain.model.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistInteractor {
    suspend fun createPlaylist(name: String, description: String?, coverUri: String?): Long
    fun observePlaylists(): Flow<List<Playlist>>
    suspend fun updatePlaylist(playlist: Playlist)
    suspend fun getPlaylistByName(name: String): Playlist?
    suspend fun getPlaylists(): List<Playlist>
}
