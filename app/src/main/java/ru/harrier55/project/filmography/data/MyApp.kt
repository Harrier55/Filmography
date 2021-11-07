package ru.harrier55.project.filmography.data

import android.app.Application
import ru.harrier55.project.filmography.R

    class MyApp: Application() {
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


     fun generateTestRepo(cardFilm: CardFilm){
        cardFilmRepoImpl.createdCardFilm(CardFilm(R.drawable.star_wars,"Месть ситхов","1982","10"))
        cardFilmRepoImpl.createdCardFilm(CardFilm(R.drawable.fox,"Зверополис","2018","8,6"))
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(CardFilm(R.drawable.fox,"Зверополис","2018","8,6"))
    }

}