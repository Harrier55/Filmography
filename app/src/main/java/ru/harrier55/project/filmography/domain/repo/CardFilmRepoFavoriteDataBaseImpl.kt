package ru.harrier55.project.filmography.domain.repo

import android.util.Log
import ru.harrier55.project.filmography.data.MyApp
import ru.harrier55.project.filmography.data.room.CardFilmEntityFavoriteDb
import ru.harrier55.project.filmography.ui.favorit.OnRequestCompleteDataBaseListener

class CardFilmRepoFavoriteDataBaseImpl : IFilmRepoDb {

    private val TAG: String = "@@@"

    private val favoriteDataBase = MyApp.instance.getFavoriteDataBaseDao()
    private var cardFilms: List<CardFilmEntityFavoriteDb> = mutableListOf()


    /**  в этот метод приходит CardFilmEntity и затем преобразовавается в формат для бвзы данных
     * это разные объекты*/
    override fun createdCardFilm() {

        /** TEST  */
        Thread {
            favoriteDataBase.insertCardFilmEntity(
                CardFilmEntityFavoriteDb(
                    null,
                    12345,
                    "poster",
                    "Name",
                    2025,
                    "deskriptions",
                    2.1,
                    "alternative"
                )
            )
        }.start()
    }

    override fun updateCardFilm() {
        // TODO("Not yet implemented")
    }

    override fun deleteCardFilm() {
        // TODO("Not yet implemented")
    }

    override fun getCardFilmFromDataBase(onRequestCompleteDataBaseListener: OnRequestCompleteDataBaseListener) {
        Thread {
            cardFilms = favoriteDataBase.getListCardFilmEntityFavorite()
            Log.d(TAG, "CardFilmRepoFavoriteDataBaseImpl getCardFilmFromDataBase: " + cardFilms.toString())
            onRequestCompleteDataBaseListener.getDataFromDb(cardFilms)
        }.start()

    }
}