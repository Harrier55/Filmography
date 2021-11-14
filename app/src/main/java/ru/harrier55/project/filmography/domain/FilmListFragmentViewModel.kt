package ru.harrier55.project.filmography.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.data.MyApp

class FilmListFragmentViewModel: ViewModel() {

    private var filmList:List<CardFilm>  = mutableListOf()
     var myList : MutableLiveData<List<CardFilm>> = MutableLiveData()

//    var myList : MutableLiveData<List<CardFilm>> = MutableLiveData()

    fun getData(): MutableLiveData<List<CardFilm>> {

//        if(myList == null){
//            myList = MutableLiveData()
//            loadData()
//        }
        loadData()
        return myList
    }

    private fun loadData(){
        filmList= MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
        myList.postValue(filmList)
    }
}



