package ru.harrier55.project.filmography.domain

import androidx.lifecycle.MutableLiveData
import ru.harrier55.project.filmography.data.CardFilmEntity

class FilmListContract {

    interface ViewModel{
        fun getData(): MutableLiveData<List<CardFilmEntity>>
    }



}