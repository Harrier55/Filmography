package ru.harrier55.project.filmography.data.webconnection

import KinopoiskReview
import android.util.Log
import com.example.example.KinopoiskMovie

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.harrier55.project.filmography.data.webconnection.OnRequestCompleteListener

interface RetrofitService {
    @GET("review?search=325&field=movieId&page=5&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS")
    fun getFilmListReview(): Call<KinopoiskReview>
}

interface RetrofitServiceMovie{
    @GET("movie?search=2019&field=year&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS")
    fun getFilmListMovie(): Call<KinopoiskMovie>
}

class WebConnectionRetrofit {
    private val TAG: String = "@@@"
    private val baseURl: String = "https://api.kinopoisk.dev/"

    fun getDataKinopoiskReviewfromRetrofit(onRequestCompleteListener: OnRequestCompleteListener) {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitServiceMovie::class.java)
        val call = retrofitService.getFilmListMovie()

        call.enqueue(object : Callback<KinopoiskMovie> {
            override fun onResponse(call: Call<KinopoiskMovie>, response: Response<KinopoiskMovie>) {
                if (response.isSuccessful && response.code() == 200) {
                    val kinopoiskMovie: KinopoiskMovie = response.body()!!
                    Log.d(TAG, "onResponse Retrofit: " + kinopoiskMovie.pages)
                    onRequestCompleteListener.onSuccessMovie(kinopoiskMovie)
                }
            }
            override fun onFailure(call: Call<KinopoiskMovie>, t: Throwable) {
                onRequestCompleteListener.onError()
            }
        })
    }
}