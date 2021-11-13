package ru.harrier55.project.filmography.data

import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.drawable.Drawable
import android.view.View
import ru.harrier55.project.filmography.R

data class CardFilm(
    var filmPoster: Int = R.drawable.star_wars,
    var filmName: String = "Звездные войны",
    var filmYear_premiere: String = "1980",
    var filmRating: String = "10.0"
) {


}