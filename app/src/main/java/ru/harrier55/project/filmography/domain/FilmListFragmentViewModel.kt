package ru.harrier55.project.filmography.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harrier55.project.filmography.data.CardFilmEntity
import ru.harrier55.project.filmography.data.MyApp

class FilmListFragmentViewModel : ViewModel(), FilmListContract.ViewModel {

    private var filmList: List<CardFilmEntity> = mutableListOf()
    private var myList: MutableLiveData<List<CardFilmEntity>> = MutableLiveData()

    init {
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
    }

    override fun getData(): MutableLiveData<List<CardFilmEntity>> {
        myList.postValue(filmList)
        return myList
    }
}



