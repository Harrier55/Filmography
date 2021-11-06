package ru.harrier55.project.filmography.data

typealias CacheListFilmsListener = (listFilms: List<CardFilm>) -> Unit

class CardFilmRepoImpl(): CardFilmRepo {

    private val listeners: MutableSet<CacheListFilmsListener> = mutableSetOf<CacheListFilmsListener>()

    private val TAG: String = "@@@"

    private var cardFilm = CardFilm()

    private val cacheListFilms: ArrayList<CardFilm> = ArrayList()


     fun getCardFilmList(): List<CardFilm>{
        return ArrayList<CardFilm>(cacheListFilms)
    }

    override fun createdCardFilm(cardFilm: CardFilm) {
        cacheListFilms.add(cardFilm)
    }

    override fun updateCardFilm() {
        TODO("Not yet implemented")
        notifyChanges()
    }

    override fun deleteCardFilm(cardFilm: CardFilm) {

// вариант удаления элемента по индеку
//        val indexToDelete: Int = cacheListFilms.indexOfFirst { it.id == cardFilm.id }
//        if (indexToDelete!= -1){
//            cacheListFilms.removeAt(indexToDelete)
//        }
        notifyChanges()
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



}