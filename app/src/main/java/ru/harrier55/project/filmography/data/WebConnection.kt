package ru.harrier55.project.filmography.data

import KinopoiskBase
import android.util.Log
import android.view.OnReceiveContentListener
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import okhttp3.*
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.domain.FilmListFragmentViewModel
import java.io.IOException


interface OnRequestCompleteListener {
    fun onSuccess()
    fun onError()
}

class WebConnection() {

    private val TAG: String = "@@@"
    var TESTURL: String =
        "https://api.kinopoisk.dev/review?search=326&field=movieId&page=5&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
    private val gson by lazy { Gson() }
    private val okHttpClient by lazy { OkHttpClient() }

    fun getDataKinopoisk(onRequestCompleteListener: OnRequestCompleteListener) {

        lateinit var resultJsonString: String

        val request = Request.Builder()
            .url(TESTURL)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d(TAG, "onFailure: ", e)
                /**Callback Error in View Model**/
                onRequestCompleteListener.onError()
            }

            override fun onResponse(call: Call, response: Response) {

                if (response.code == 200) {
                    resultJsonString = response.body!!.string()
                }
                /**Парсинг JSON в класс Кинопоиск*/
                val kinopoiskBase: KinopoiskBase =
                    gson.fromJson(resultJsonString, KinopoiskBase::class.java)

                Log.d(TAG, "WebConnection  onResponse: вызвал  MyApp.generateRepoFromWeb")

                /**Заполнили репозиторий значениями из класса Кинопоиск*/
                MyApp.instance.generateRepoFromWeb(kinopoiskBase)

                /**Callback in View Model**/
                onRequestCompleteListener.onSuccess()
            }

        })

    }

}