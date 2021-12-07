package ru.harrier55.project.filmography.data

import KinopoiskBase
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import okhttp3.*
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.domain.FilmListFragmentViewModel
import java.io.IOException

class WebConnection {

    private val TAG: String = "@@@"
    var TESTURL: String =
        "https://api.kinopoisk.dev/review?search=325&field=movieId&page=5&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
    private val gson by lazy { Gson() }
    private val okHttpClient by lazy { OkHttpClient() }
    private val viewModel = FilmListFragmentViewModel()


    fun getDataKinopoisk() {

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

                Log.d(TAG, "class WebConnection  onResponse: ")
                Log.d(TAG, "class WebConnection  onResponse: вызвал  MyApp.instance.generateRepoFromWeb")
                MyApp.instance.generateRepoFromWeb(kinopoiskBase)


                Log.d(TAG, "class WebConnection  onResponse: вызвал  viewModel.getData() ")
                Log.d(TAG, "onResponse: viewModel_hashcode= " + viewModel.hashCode())

                viewModel.getData()

            }

        })

    }

}