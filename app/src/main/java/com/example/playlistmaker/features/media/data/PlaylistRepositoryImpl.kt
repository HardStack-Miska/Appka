package com.example.playlistmaker.features.media.data

import com.example.playlistmaker.data.db.PlaylistsDao
import com.example.playlistmaker.features.media.domain.model.Playlist
import com.example.playlistmaker.features.media.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaylistRepositoryImpl(
    private val playlistsDao: PlaylistsDao
) : PlaylistRepository {

    override suspend fun createPlaylist(playlist: Playlist): Long {
        val entity = PlaylistMapper.mapToEntity(playlist)
        return playlistsDao.insertPlaylist(entity)
    }

    override suspend fun updatePlaylist(playlist: Playlist) {
        val entity = PlaylistMapper.mapToEntity(playlist)
        playlistsDao.updatePlaylist(entity)
    }

    override fun observePlaylists(): Flow<List<Playlist>> {
        return playlistsDao.observePlaylists().map { entities ->
            entities.map { entity -> PlaylistMapper.mapToDomain(entity) }
        }
    }

    override suspend fun getPlaylistByName(name: String): Playlist? {
        return playlistsDao.getPlaylistByName(name)?.let { entity ->
            PlaylistMapper.mapToDomain(entity)
        }
    }
}
