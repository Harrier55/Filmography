package ru.harrier55.project.filmography.domain

import android.app.Activity
import android.view.View
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.data.CardFilmRepoImpl
import ru.harrier55.project.filmography.data.MyApp

class FilmListContract {


    interface View{
        fun setData(list: List<CardFilm>)


    }


    interface Presenter{
        fun attach(view:View)
        fun detach()
        fun setCardFilmRepoImpl()
    }


}