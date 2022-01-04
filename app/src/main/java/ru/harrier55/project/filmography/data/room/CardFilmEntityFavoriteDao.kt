package ru.harrier55.project.filmography.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity

@Dao
interface CardFilmEntityFavoriteDao {
    @Query("SELECT*FROM favorite_films")
    fun getListCardFilmEntityFavorite(): LiveData<MutableList<CardFilmEntityFavoriteDb>>
    @Insert
    fun insertCardFilmEntity(cardFilmEntity: CardFilmEntity)
    @Delete
    fun deleteCardFilmEntity(cardFilmEntityFavoriteDb: CardFilmEntityFavoriteDb)
}