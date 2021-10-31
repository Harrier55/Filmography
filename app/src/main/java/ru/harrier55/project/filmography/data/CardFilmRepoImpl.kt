package ru.harrier55.project.filmography.data

class CardFilmRepoImpl: CardFilmRepo {

    private val cacheFilms: ArrayList<CardFilm> = ArrayList()


    override fun createdCardFilm():Boolean {


        return true
    }

    override fun updateCardFilm(): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteCardFilm(): Boolean {
        TODO("Not yet implemented")
    }


}