package ru.harrier55.project.filmography.data

import KinopoiskBase
import android.app.Application
import android.util.Log
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.domain.repo.CardFilmRepoImpl

class MyApp : Application() {
    private val TAG:String = "@@@"
    
    private val cardFilmRepoImpl = CardFilmRepoImpl()

    fun getMyAppCardFilmRepoImpl(): CardFilmRepoImpl {
        Log.d(TAG, "My App __getMyAppCardFilmRepoImpl: в репозитоии  cardFilmRepoImpl лежат = " )
        return cardFilmRepoImpl
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "override fun onCreate in MyApp: ")
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }


    fun generateTestRepo(cardFilm: CardFilmEntity) {

        Log.d(TAG, "MyApp __generateRepoFromWeb: я сработал")

        cardFilmRepoImpl.createdCardFilm(
            CardFilmEntity(
            null,
            null,
            R.drawable.fox,
            "Зверополис",
            "2018",
            "Описание",
            "9"

        )
        )
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
    }

    fun generateRepoFromWeb(kinopoiskBase: KinopoiskBase) {
        kinopoiskBase.docs.forEach{
            cardFilmRepoImpl.createdCardFilm(
                CardFilmEntity(
                it.id,
                it.movieId,
                R.drawable.star_wars,
                it.title,
                it.date,
                it.review,
                "777"
            )
            )
        }
        Log.d(TAG, "MyApp __generateRepoFromWeb: я сработал")
        getMyAppCardFilmRepoImpl()

    }

}