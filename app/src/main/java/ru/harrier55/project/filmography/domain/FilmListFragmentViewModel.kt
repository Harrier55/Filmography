package ru.harrier55.project.filmography.domain

import KinopoiskBase
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.*
import ru.harrier55.project.filmography.data.CardFilmEntity
import ru.harrier55.project.filmography.data.MyApp
import java.io.IOException

class FilmListFragmentViewModel : ViewModel() {

    private val TAG: String = "@@@"

    var TESTURL: String =
        "https://api.kinopoisk.dev/review?search=326&field=movieId&page=5&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
    private val gson by lazy { Gson() }
    private val okHttpClient by lazy { OkHttpClient() }

    private var filmList: List<CardFilmEntity> = mutableListOf()

    val myList = MutableLiveData<List<CardFilmEntity>>()

    init {
        Log.d(TAG, "ViewModel_ init: ")
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
    }


    fun getData() {
        Log.d(TAG, "  ViewModel getData  Start")
        filmList = MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()
        myList.postValue(filmList)
    }

    fun getDataKinopoisk() {

        Log.d(TAG, "  ViewModel getDataKinopoisk  Start")
        lateinit var resultJsonString: String
        val request = Request.Builder()
            .url(TESTURL)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d(TAG, "onFailure: ", e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 200) {
                    resultJsonString = response.body!!.string()
                }

                val kinopoiskBase: KinopoiskBase =
                    gson.fromJson(resultJsonString, KinopoiskBase::class.java)

                MyApp.instance.generateRepoFromWeb(kinopoiskBase)

                getData()
            }
        })
    }

}



