package ru.harrier55.project.filmography.data

interface IFilmRepo {

    fun createdCardFilm(cardFilm: CardFilm)
    fun updateCardFilm()
    fun deleteCardFilm(cardFilm: CardFilm)

}