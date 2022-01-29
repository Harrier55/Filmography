package ru.harrier55.project.filmography.data.webconnection

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import ru.harrier55.project.filmography.data.PlacesRootClass
import ru.harrier55.project.filmography.ui.googlemaps.OnCallbackFromGooglePlacesApi
import java.io.IOException

class WebConnectionOkHttp() {

    private val TAG: String = "@@@"
//    var movieUrl: String =
//        "https://api.kinopoisk.dev/movie?search=2019&field=year&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
//    val reviewUrl:String = "https://api.kinopoisk.dev/review?search=325&field=movieId&page=10&limit=10&token=68MMRD5-PBNMTR6-NREDMZQ-HDHYHYS"
    private val gson by lazy { Gson() }
    private val okHttpClient by lazy { OkHttpClient() }

    fun getDataFromWeb(myUrl: String,onCallbackFromGooglePlacesApi: OnCallbackFromGooglePlacesApi) {

        lateinit var resultJsonString: String

        val request = Request.Builder()
            .url(myUrl)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d(TAG, "onFailure: ", e)
                /**Callback Error in the View Model**/
                onCallbackFromGooglePlacesApi.errorPlacesFromGooglePlacesApi()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful&&response.code == 200) {
                    resultJsonString = response.body!!.string()
                    Log.d(TAG, "WebConnectionOkHTTP  onResponse: " +resultJsonString)
                    /**Callback onResult in the ......**/
                    onCallbackFromGooglePlacesApi.responsePlacesFromGooglePlacesApi(resultJsonString)
                }
            }

        })
    }



}