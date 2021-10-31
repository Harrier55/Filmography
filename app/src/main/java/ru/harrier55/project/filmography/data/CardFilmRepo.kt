package ru.harrier55.project.filmography.data

interface CardFilmRepo {

    fun createdCardFilm():Boolean
    fun updateCardFilm(): Boolean
    fun deleteCardFilm(): Boolean

}