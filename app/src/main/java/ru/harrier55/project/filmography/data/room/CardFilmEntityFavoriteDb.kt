package ru.harrier55.project.filmography.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_films")
data class CardFilmEntityFavoriteDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "id_kinopoisk")
    val idKp: Long? = null,
    @ColumnInfo(name = "poster")
    val filmPoster: String? = null,
    @ColumnInfo(name = "film_name")
    val filmName: String? = null,
    @ColumnInfo(name = "year_premiere")
    val filmYear_premiere: Int? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "rating")
    val filmRating: Double? =null,
    @ColumnInfo(name = "alternative-name")
    val alternativeName:String? = null
) {
}