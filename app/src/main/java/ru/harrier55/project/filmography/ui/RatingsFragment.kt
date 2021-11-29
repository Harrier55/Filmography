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
import java.io.IOException

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

                    activity?.runOnUiThread {
                        binding.loadResultTV.text = response.toString()
                    }
                }


            })
        }

        return view
    }


}