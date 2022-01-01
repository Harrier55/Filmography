package ru.harrier55.project.filmography.data

import KinopoiskReview
import android.app.Application
import android.util.Log
import com.example.example.KinopoiskMovie
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.domain.repo.CardFilmRepoImpl

class MyApp : Application() {
    private val TAG:String = "@@@"
    
    private val cardFilmRepoImpl = CardFilmRepoImpl()


/**  Этот метод вернет мне синглтон моего репозитория   */
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

    fun generateRepoFromWebReview(kinopoiskBase: KinopoiskReview) {
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

    fun generateRepoFromWebKinopoiskMovie(kinopoiskMovie: KinopoiskMovie){
        kinopoiskMovie.docs.forEach {
            cardFilmRepoImpl.createdCardFilm(
                CardFilmEntity(
                    it.id,
                    it.id,
                    R.drawable.star_wars,
                    it.alternativeName,
                    "2020",
                    it.description,
                    "555"
                )
            )
        }
        getMyAppCardFilmRepoImpl()

    }

}