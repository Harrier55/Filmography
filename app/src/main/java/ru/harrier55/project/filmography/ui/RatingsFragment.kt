package ru.harrier55.project.filmography.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.*
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.databinding.FragmentListFilmBinding
import ru.harrier55.project.filmography.databinding.FragmentRatingsBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

import java.net.URL
import java.sql.Connection


class RatingsFragment : Fragment() {

    private val TAG: String = "@@@"


    // Готовый пример запроса для получения информации о фильме "Джентельмены
    var TESTURL:String = " https://cloud-api.kinopoisk.dev/movies/1143242/token/7d59f07c9c5ce970ffd275a2a7962a0f"


    private var _binding: FragmentRatingsBinding? = null
    private val binding get() = _binding!!

    private val okHttpClient = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_ratings, container, false)
        _binding = FragmentRatingsBinding.bind(view)

        binding.loadButton.setOnClickListener{

//      Реализация HttpURLConnection в потоке

//            Thread{
//                var connection:HttpURLConnection? = null
//
//                val url = URL(TESTURL)
//                connection = url.openConnection() as HttpURLConnection
//                connection.requestMethod = "GET"
//                connection.connectTimeout = 5_000
//
//                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
//                val result = bufferedReader.readLines().toString()
//
//                activity?.runOnUiThread {
//                        binding.loadResultTV.text = result
//                    }
//
//
//            }
//                .start()

//      Реализация OKHTTP
            val request = Request.Builder()
                .url(TESTURL)
                .build()

            okHttpClient.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.d(TAG, "onFailure: ")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d(TAG, "onResponse: ")

                    val mediaType = "application/json; charset=utf-8".toMediaType()

                    val requestBody = response.toString().toRequestBody(mediaType)

                    activity?.runOnUiThread {
                        binding.loadResultTV.text = response.body!!.string()
                    }
                }

            })
        }

        return view
    }


}