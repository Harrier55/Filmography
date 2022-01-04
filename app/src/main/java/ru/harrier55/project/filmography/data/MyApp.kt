package ru.harrier55.project.filmography.data

import KinopoiskReview
import android.app.Application
import android.util.Log
import com.example.example.KinopoiskMovie
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.domain.repo.CardFilmRepoImpl

class MyApp : Application() {
    private val TAG: String = "@@@"

    private val cardFilmRepoImpl = CardFilmRepoImpl()


    /**  Этот метод вернет мне синглтон моего репозитория   */
    fun getMyAppCardFilmRepoImpl(): CardFilmRepoImpl {
        Log.d(TAG, "My App __getMyAppCardFilmRepoImpl: в репозитоии  cardFilmRepoImpl лежат = ")
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
                null,
                "Зверополис",
                2018,
                "Описание",
                9.8

            )
        )
//        cardFilmRepoImpl.createdCardFilm(cardFilm)
//        cardFilmRepoImpl.createdCardFilm(cardFilm)
    }

    fun generateRepoFromWebReview(kinopoiskBase: KinopoiskReview) {
        kinopoiskBase.docs.forEach {
            cardFilmRepoImpl.createdCardFilm(
                CardFilmEntity(
                    it.id,
                    it.movieId,
                    null,
                    it.title,
                    2018,
                    it.review,
                    5.9
                )
            )
        }
        getMyAppCardFilmRepoImpl()

    }

    fun generateRepoFromWebKinopoiskMovie(kinopoiskMovie: KinopoiskMovie) {
        kinopoiskMovie.docs.forEach {
            cardFilmRepoImpl.createdCardFilm(
                CardFilmEntity(
                    null,
                    it.id,
                    it.poster?.previewUrl,
                    it.name,
                    it.year,
                    it.description,
                    it.rating?.imdb,
                    it.alternativeName
                )
            )
        }
        getMyAppCardFilmRepoImpl()

    }

}