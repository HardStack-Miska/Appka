package com.example.playlistmaker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [FavoriteTrackEntity::class, PlaylistEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(TrackIdConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    abstract fun playlistsDao(): PlaylistsDao
}
