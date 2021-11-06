package ru.harrier55.project.filmography.data

import android.app.Application
import ru.harrier55.project.filmography.R

    class MyApp: Application() {
    private val cardFilmRepoImpl = CardFilmRepoImpl()

     fun getMyAppCardFilmRepoImpl(): CardFilmRepoImpl {
        return cardFilmRepoImpl
    }

     fun generateTestRepo(cardFilm: CardFilm){
        cardFilmRepoImpl.createdCardFilm(CardFilm(R.drawable.star_wars,"Империя наносит ответный удар","1982","10"))
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(CardFilm(R.drawable.fox,"Зверополис","2018","8,6"))
    }

}