package ru.harrier55.project.filmography.data

import KinopoiskBase
import android.util.Log

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GetCallbackWebConnectionRetrofit {
    fun onSuccess(kinopoiskBase: KinopoiskBase)
    fun onError()
}

interface RetrofitService {
    @GET("review?search=325&field=movieId&page=5&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS")
    fun getFilmList(): Call<KinopoiskBase>
}

class WebConnectionRetrofit {
    private val TAG: String = "@@@"
    private val baseURl: String = "https://api.kinopoisk.dev/"

    fun getDataKinopoiskfromRetrofit(onRequestCompleteListener: OnRequestCompleteListener) {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)
        val call = retrofitService.getFilmList()

        call.enqueue(object : Callback<KinopoiskBase> {
            override fun onResponse(call: Call<KinopoiskBase>, response: Response<KinopoiskBase>) {
                if (response.isSuccessful && response.code() == 200) {
                    val kinopoiskBase: KinopoiskBase = response.body()!!
                    Log.d(TAG, "onResponse Retrofit: " + kinopoiskBase.pages)
                    MyApp.instance.generateRepoFromWeb(kinopoiskBase)
                    onRequestCompleteListener.onSuccess()
                }
            }

            override fun onFailure(call: Call<KinopoiskBase>, t: Throwable) {
                onRequestCompleteListener.onError()
            }
        })


    }


}