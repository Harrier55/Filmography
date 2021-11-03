package ru.harrier55.project.filmography.data

import android.app.Application

class MyApp: Application() {
    private val cardFilmRepoImpl = CardFilmRepoImpl()

    public fun getCardFilmRepoImpl(): CardFilmRepoImpl {

        return cardFilmRepoImpl
    }

}