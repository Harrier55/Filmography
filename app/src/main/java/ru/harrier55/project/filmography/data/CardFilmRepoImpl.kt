package ru.harrier55.project.filmography.data

class CardFilmRepoImpl(): CardFilmRepo {

    private val TAG: String = "@@@"

    private val cardFilm = CardFilm()

    private val cacheFilms: ArrayList<CardFilm> = ArrayList()

    fun getCardFilmList(): List<CardFilm>{
        return ArrayList<CardFilm>(cacheFilms)
    }


    override fun createdCardFilm(cardFilm: CardFilm) {
        cacheFilms.add(cardFilm)
    }

    override fun updateCardFilm() {
        TODO("Not yet implemented")
    }

    override fun deleteCardFilm() {
        TODO("Not yet implemented")
    }


}