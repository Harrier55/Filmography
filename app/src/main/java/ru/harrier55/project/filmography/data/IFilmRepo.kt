package ru.harrier55.project.filmography.data

interface IFilmRepo {

    fun createdCardFilm(cardFilm: CardFilmEntity)
    fun updateCardFilm()
    fun deleteCardFilm(cardFilm: CardFilmEntity)

}