package ru.harrier55.project.filmography.domain.entities



import ru.harrier55.project.filmography.R

data class CardFilmEntity(
    val id: Int? = null,
    val idKp: Int? = null,
    val filmPoster: String? = null,
    val filmName: String? = "Название фильма - test",
    val filmYear_premiere: Int? = 2002,
    val description: String? = "описание",
    val filmRating: Double? = 5.5,
    val alternativeName:String? = null

) {


}



