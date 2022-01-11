package ru.harrier55.project.filmography.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity

@Dao
interface CardFilmFavoriteDao {
    @Query("SELECT*FROM favorite_films")
    fun getListCardFilmEntityFavorite(): List<CardFilmEntityFavoriteDb>
    @Insert
    fun insertCardFilmEntity(cardFilmEntityFavoriteDb: CardFilmEntityFavoriteDb)
    @Delete
    fun deleteCardFilmEntity(cardFilmEntityFavoriteDb: CardFilmEntityFavoriteDb)
    @Query("DELETE FROM favorite_films" )
    fun deleteTable()
}