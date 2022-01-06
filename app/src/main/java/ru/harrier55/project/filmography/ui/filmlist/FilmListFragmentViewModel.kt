package ru.harrier55.project.filmography.ui.filmlist

import KinopoiskReview
import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.KinopoiskMovie

import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.data.MyApp
import ru.harrier55.project.filmography.data.OnRequestCompleteListener

class FilmListFragmentViewModel : ViewModel() {

    private val TAG: String = "@@@"

    private var filmList: List<CardFilmEntity> = mutableListOf()
    val myList = MutableLiveData<List<CardFilmEntity>>()
    val errorList = MutableLiveData<String?>()


    init {
        Log.d(TAG, "ViewModel_ init: ")
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
    }

    /** Обновление данных*/
    fun getData() {
        Log.d(TAG, "ViewModel getData  Start")
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
        myList.postValue(filmList)
    }
    /** Запрос : Репозиторий,дай данные из Кинопоиска*/
    fun getDataKinopoisk() {
        MyApp.instance.getMyAppCardFilmRepoImpl().getDataKinopoisk(onRequestCompleteListener)
    }

    /** инициализация интерфейса onRequestCompleteListener, он сообщит,что данные с Web пришли, т.к запрос асинхронный*/
    private var onRequestCompleteListener = object : OnRequestCompleteListener {
        override fun onSuccessReview(kinopoiskReview: KinopoiskReview) {
            Log.d(TAG, "onSuccess: start")
            errorList.postValue(null)
            /**заполняем репозиторий значениями из Web**/
            MyApp.instance.generateRepoFromWebReview(kinopoiskReview)
            getData()
        }

        override fun onSuccessMovie(kinopoiskMovie: KinopoiskMovie) {
            errorList.postValue(null)
             /**заполняем репозиторий значениями из Web**/
            MyApp.instance.generateRepoFromWebKinopoiskMovie(kinopoiskMovie)
            getData()
        }

        override fun onError() {
            Log.d(TAG, "onError:")
            errorList.postValue("Отсутствует подключение к интернету")
        }
    }


}



