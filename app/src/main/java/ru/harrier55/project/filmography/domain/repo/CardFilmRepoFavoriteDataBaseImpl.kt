package ru.harrier55.project.filmography.domain.repo

import android.util.Log
import ru.harrier55.project.filmography.data.MyApp
import ru.harrier55.project.filmography.data.room.CardFilmEntityFavoriteDb
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.ui.favorit.OnRequestCompleteDataBaseListener

class CardFilmRepoFavoriteDataBaseImpl : IFilmRepoDb {

    private val TAG: String = "@@@"

    private val favoriteDataBase = MyApp.instance.getFavoriteDataBaseDao()
    private var cardFilms: List<CardFilmEntityFavoriteDb> = mutableListOf()


    /**  в этот метод приходит CardFilmEntity и затем преобразовавается в формат для бвзы данных
     * так как это разные объекты*/
    override fun createdCardFilm(cardFilmEntity: CardFilmEntity) {
        Thread {
            favoriteDataBase.insertCardFilmEntity(
                CardFilmEntityFavoriteDb(
                    null,
                    cardFilmEntity.idKp,
                    cardFilmEntity.filmPoster,
                    cardFilmEntity.filmName,
                    cardFilmEntity.filmYear_premiere,
                    cardFilmEntity.description,
                    cardFilmEntity.filmRating,
                    cardFilmEntity.alternativeName
                )
            )

        }.start()
    }

    override fun updateCardFilm() {
    }

    override fun deleteCardFilm(cardFilmEntityFavoriteDb: CardFilmEntityFavoriteDb) {
        Thread {
            favoriteDataBase.deleteCardFilmEntity(cardFilmEntityFavoriteDb)
        }.start()
    }

    override fun getCardFilmListFromDataBase(onRequestCompleteDataBaseListener: OnRequestCompleteDataBaseListener) {
        Thread {
            cardFilms = favoriteDataBase.getListCardFilmEntityFavorite()
            Log.d(
                TAG,
                "CardFilmRepoFavoriteDataBaseImpl getCardFilmFromDataBase: " + cardFilms.toString()
            )
            onRequestCompleteDataBaseListener.getDataFromDb(cardFilms)
        }.start()

    }
}