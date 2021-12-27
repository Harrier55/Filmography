package ru.harrier55.project.filmography.data

import KinopoiskBase
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException


interface OnRequestCompleteListener {
    fun onSuccess()
    fun onError()
}

class WebConnectionOkHttp() {

    private val TAG: String = "@@@"
    var TESTURL: String =
        "https://api.kinopoisk.dev/review?search=325&field=movieId&page=5&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
    private val gson by lazy { Gson() }
    private val okHttpClient by lazy { OkHttpClient() }

    fun getDataKinopoiskfromOkHTTP(onRequestCompleteListener: OnRequestCompleteListener) {

        lateinit var resultJsonString: String

        val request = Request.Builder()
            .url(TESTURL)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d(TAG, "onFailure: ", e)
                /**Callback Error in the View Model**/
                onRequestCompleteListener.onError()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful&&response.code == 200) {
                    resultJsonString = response.body!!.string()
                }
                /**Парсинг JSON в класс Кинопоиск*/
                val kinopoiskBase: KinopoiskBase =
                    gson.fromJson(resultJsonString, KinopoiskBase::class.java)

                Log.d(TAG, "WebConnection  onResponse: вызвал  MyApp.generateRepoFromWeb")

                /**Заполнили репозиторий значениями из класса Кинопоиск*/
                MyApp.instance.generateRepoFromWeb(kinopoiskBase)

                /**Callback onResult in the View Model**/
                onRequestCompleteListener.onSuccess()
            }
        })
    }



}