package ru.harrier55.project.filmography.data.webconnection

import android.util.Log
import com.example.example.KinopoiskMovie
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class WebConnectionOkHttp() {

    private val TAG: String = "@@@"
    var movieUrl: String =
        "https://api.kinopoisk.dev/movie?search=2019&field=year&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
    val reviewUrl:String = "https://api.kinopoisk.dev/review?search=325&field=movieId&page=10&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
    private val gson by lazy { Gson() }
    private val okHttpClient by lazy { OkHttpClient() }

    fun getDataKinopoiskfromOkHTTP(onRequestCompleteListener: OnRequestCompleteListener) {

        lateinit var resultJsonString: String

        val request = Request.Builder()
            .url(movieUrl)
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
                val kinopoiskMovie:KinopoiskMovie = gson.fromJson(resultJsonString, KinopoiskMovie::class.java)

                Log.d(TAG, "WebConnectionOkHTTP  onResponse: " + kinopoiskMovie.docs[0].alternativeName)

                /**Заполнили репозиторий значениями из класса Кинопоиск*/
//                MyApp.instance.generateRepoFromWebKinopoiskMovie(kinopoiskMovie)

                /**Callback onResult in the View Model**/
                onRequestCompleteListener.onSuccessMovie(kinopoiskMovie)
            }
        })
    }



}