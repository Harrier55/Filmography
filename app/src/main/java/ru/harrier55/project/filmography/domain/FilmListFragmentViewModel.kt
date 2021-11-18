package ru.harrier55.project.filmography.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.data.MyApp

class FilmListFragmentViewModel : ViewModel(), FilmListContract.ViewModel {

    private var filmList: List<CardFilm> = mutableListOf()
    private var myList: MutableLiveData<List<CardFilm>> = MutableLiveData()

    init {
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
    }

    override fun getData(): MutableLiveData<List<CardFilm>> {
        myList.postValue(filmList)
        return myList
    }
}



