package ru.harrier55.project.filmography.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardFilmEntityFavoriteDb::class], version = 1)
abstract class FavoriteDataBase: RoomDatabase() {
    abstract fun cardFilmEntityFavoriteDao(): CardFilmFavoriteDao
}