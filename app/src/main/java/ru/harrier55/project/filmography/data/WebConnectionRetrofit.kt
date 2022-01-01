package ru.harrier55.project.filmography.data

import KinopoiskReview
import android.util.Log

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("review?search=325&field=movieId&page=5&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS")
    fun getFilmListReview(): Call<KinopoiskReview>
}

//interface RetrofitServiceMovie{
//    @GET("movie?search=2020&field=year&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS")
//    fun getFilmListMovie(): Callback<KinopoiskMovie>
//}

class WebConnectionRetrofit {
    private val TAG: String = "@@@"
    private val baseURl: String = "https://api.kinopoisk.dev/"

    fun getDataKinopoiskReviewfromRetrofit(onRequestCompleteListener: OnRequestCompleteListener) {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)
        val callReview = retrofitService.getFilmListReview()

        callReview.enqueue(object : Callback<KinopoiskReview> {
            override fun onResponse(call: Call<KinopoiskReview>, response: Response<KinopoiskReview>) {
                if (response.isSuccessful && response.code() == 200) {
                    val kinopoiskReview: KinopoiskReview = response.body()!!
                    Log.d(TAG, "onResponse Retrofit: " + kinopoiskReview.pages)
                    onRequestCompleteListener.onSuccess(kinopoiskReview)
                }
            }
            override fun onFailure(call: Call<KinopoiskReview>, t: Throwable) {
                onRequestCompleteListener.onError()
            }
        })
    }

    fun getDataKinopoiskMoviefromRetrofit(onRequestCompleteListener: OnRequestCompleteListener){
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//        val retrofitService = retrofit.create(RetrofitServiceMovie::class.java)
//        val callMovie = retrofitService.getFilmListMovie()


    }


}