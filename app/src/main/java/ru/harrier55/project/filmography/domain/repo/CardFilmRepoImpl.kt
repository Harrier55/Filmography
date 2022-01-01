package ru.harrier55.project.filmography.domain.repo

import ru.harrier55.project.filmography.data.*
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity

typealias CacheListFilmsListener = (listFilms: List<CardFilmEntity>) -> Unit

class CardFilmRepoImpl(): IFilmRepo {

    private val TAG: String = "@@@"
    private val listeners: MutableSet<CacheListFilmsListener> = mutableSetOf<CacheListFilmsListener>()
    private val cacheListFilms: ArrayList<CardFilmEntity> = ArrayList()
    private val webConnectionOkHttp by lazy { WebConnectionOkHttp() }
    private val webConnectionRetrofit by lazy { WebConnectionRetrofit() }

    override fun createdCardFilm(cardFilm: CardFilmEntity) {
        cacheListFilms.add(cardFilm)
    }

    override fun updateCardFilm() {
        TODO("Not yet implemented")
        notifyChanges()
    }

    override fun deleteCardFilm(cardFilm: CardFilmEntity) {

// вариант удаления элемента по индеку
//        val indexToDelete: Int = cacheListFilms.indexOfFirst { it.id == cardFilm.id }
//        if (indexToDelete!= -1){
//            cacheListFilms.removeAt(indexToDelete)
//        }
        notifyChanges()
    }

    fun getCardFilmList(): List<CardFilmEntity>{
        return ArrayList<CardFilmEntity>(cacheListFilms)
    }

    fun addListener(listener: CacheListFilmsListener){  // реализация паттерна Наблюдатель
        listeners.add(listener)
    }

    fun removeListener(listener: CacheListFilmsListener){ // реализация паттерна Наблюдатель
        listeners.remove(listener)
        listener.invoke(cacheListFilms)
    }

    private fun notifyChanges(){
        listeners.forEach { it.invoke(cacheListFilms) }
    }

    override fun getDataKinopoisk(onRequestCompleteListener: OnRequestCompleteListener) {
//        webConnectionRetrofit.getDataKinopoiskReviewfromRetrofit(onRequestCompleteListener)  // реализация для Retrofit
        webConnectionOkHttp.getDataKinopoiskfromOkHTTP(onRequestCompleteListener)  // реализация для OkHTTP

    }

}

