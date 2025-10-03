package com.example.playlistmaker.features.media.domain.interactor

import com.example.playlistmaker.features.media.domain.model.Playlist
import com.example.playlistmaker.features.media.domain.repository.PlaylistCoverStorage
import com.example.playlistmaker.features.media.domain.repository.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaylistInteractorImpl(
    private val playlistRepository: PlaylistRepository,
    private val coverStorage: PlaylistCoverStorage
) : PlaylistInteractor {

    override suspend fun createPlaylist(
        name: String,
        description: String?,
        coverUri: String?
    ): Long {
        val trimmedName = name.trim()
        val sanitizedDescription = description?.trim().takeUnless { it.isNullOrEmpty() }
        val storedCoverPath = coverUri
            ?.takeIf { it.isNotBlank() }
            ?.let { uri ->
                withContext(Dispatchers.IO) {
                    coverStorage.copyCoverToStorage(uri, trimmedName)
                }
            }
            ?.takeIf { it.isNotBlank() }

        val playlist = Playlist(
            name = trimmedName,
            description = sanitizedDescription,
            coverPath = storedCoverPath,
            trackIds = emptyList(),
            trackCount = 0
        )
        return playlistRepository.createPlaylist(playlist)
    }

    override fun observePlaylists() = playlistRepository.observePlaylists()

    override suspend fun updatePlaylist(playlist: Playlist) {
        playlistRepository.updatePlaylist(playlist)
    }

    override suspend fun getPlaylistByName(name: String): Playlist? {
        return playlistRepository.getPlaylistByName(name.trim())
    }
}
