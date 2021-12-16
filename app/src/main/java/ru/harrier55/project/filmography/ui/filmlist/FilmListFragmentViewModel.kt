package ru.harrier55.project.filmography.ui.filmlist

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.data.MyApp
import ru.harrier55.project.filmography.data.OnRequestCompleteListener
import ru.harrier55.project.filmography.data.WebConnection

class FilmListFragmentViewModel : ViewModel(){

    private val TAG: String = "@@@"

    private val webConnection by lazy { WebConnection() }
    private var filmList: List<CardFilmEntity> = mutableListOf()
    val myList = MutableLiveData<List<CardFilmEntity>>()
    val errorList = MutableLiveData<String?>()

    init {
        Log.d(TAG, "ViewModel_ init: ")
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
    }

    fun getData() {
        Log.d(TAG, "ViewModel getData  Start")
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
        myList.postValue(filmList)
    }

    fun getDataKinopoisk() {
        webConnection.getDataKinopoisk(onRequestCompleteListener)
    }

/** имплементация интерфейса callbabck, он сообщает,что данные с Web пришли*/
    private var onRequestCompleteListener = object : OnRequestCompleteListener {
        override fun onSuccess() {
            Log.d(TAG, "onSuccess: start")
            errorList.postValue(null)
            getData()

        }

        override fun onError() {
            Log.d(TAG, "onError:")
            errorList.postValue("Отсутствует подключение к интернету")
        }

    }



}



