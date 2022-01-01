package ru.harrier55.project.filmography.data

import KinopoiskReview

interface OnRequestCompleteListener {
    fun onSuccess(kinopoiskReview: KinopoiskReview)
    fun onError()
}