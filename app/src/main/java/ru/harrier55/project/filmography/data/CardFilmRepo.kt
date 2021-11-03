package ru.harrier55.project.filmography.data

interface CardFilmRepo {

    fun createdCardFilm(cardFilm: CardFilm)
    fun updateCardFilm()
    fun deleteCardFilm()

}