package ru.harrier55.project.filmography.data



import ru.harrier55.project.filmography.R

data class CardFilmEntity(
    val id: Int? = null,
    val movieId: Int? = null,
    var filmPoster: Int = R.drawable.star_wars,
    var filmName: String? = "Название фильма",
    var filmYear_premiere: String? = "дата премьеры",
    val description: String? = "описание",
    var filmRating: String? = "рейтинг"
) {


}



