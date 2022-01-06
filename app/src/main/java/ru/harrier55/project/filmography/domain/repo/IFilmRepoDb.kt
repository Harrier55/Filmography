package ru.harrier55.project.filmography.domain.repo

import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.ui.favorit.OnRequestCompleteDataBaseListener

interface IFilmRepoDb {
    fun createdCardFilm(cardFilmEntity: CardFilmEntity)
    fun updateCardFilm()
    fun deleteCardFilm()
    fun getCardFilmFromDataBase(onRequestCompleteDataBaseListener: OnRequestCompleteDataBaseListener)
}