package ru.harrier55.project.filmography.data


import ru.harrier55.project.filmography.R

data class CardFilmEntity(
    var filmPoster: Int = R.drawable.star_wars,
    var filmName: String = "Звездные войны",
    var filmYear_premiere: String = "1980",
    var filmRating: String = "10.0"
) {


}