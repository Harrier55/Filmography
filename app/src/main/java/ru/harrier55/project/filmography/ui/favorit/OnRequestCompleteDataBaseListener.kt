package ru.harrier55.project.filmography.ui.favorit

import ru.harrier55.project.filmography.data.room.CardFilmEntityFavoriteDb

interface OnRequestCompleteDataBaseListener {
    fun getDataFromDb(myList: List<CardFilmEntityFavoriteDb>)
}