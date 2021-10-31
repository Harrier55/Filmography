package ru.harrier55.project.filmography.data

import android.graphics.Bitmap
import android.graphics.Picture
import android.view.View

data class CardFilm(
    var poster: Bitmap,
    var film_name: String = "Звездные войны",
    var film_year_premiere: String = "1980",
    var film_rating: String = "10.0"
) {


}