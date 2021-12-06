package ru.harrier55.project.filmography.data

import Docs
import KinopoiskBase
import android.app.Application
import ru.harrier55.project.filmography.R

class MyApp : Application() {
    private val cardFilmRepoImpl = CardFilmRepoImpl()


    fun getMyAppCardFilmRepoImpl(): CardFilmRepoImpl {
        return cardFilmRepoImpl
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }


    fun generateTestRepo(cardFilm: CardFilmEntity) {

        cardFilmRepoImpl.createdCardFilm(CardFilmEntity(
            null,
            null,
            R.drawable.fox,
            "jhjghkfjhkj",
            "2588",
            "dere",
            "589"

        ))


        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)

    }

    fun generateRepoFromWeb(kinopoiskBase: KinopoiskBase) {


        kinopoiskBase.docs.forEach{
            cardFilmRepoImpl.createdCardFilm(CardFilmEntity(
                it.id,
                it.movieId,
                R.drawable.star_wars,
                it.title,
                it.date,
                it.review,
                "777"
            ))
        }


    }

}