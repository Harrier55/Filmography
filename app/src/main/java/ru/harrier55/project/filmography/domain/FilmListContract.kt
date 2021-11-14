package ru.harrier55.project.filmography.domain

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.data.CardFilmRepoImpl
import ru.harrier55.project.filmography.data.MyApp

class FilmListContract {

    interface ViewModel{
        fun getData(): MutableLiveData<List<CardFilm>>
    }



}