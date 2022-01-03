package ru.harrier55.project.filmography.data

import KinopoiskReview
import com.example.example.KinopoiskMovie

interface OnRequestCompleteListener {
    fun onSuccessReview(kinopoiskReview: KinopoiskReview)
    fun onSuccessMovie(kinopoiskMovie: KinopoiskMovie)
    fun onError()
}