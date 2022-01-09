package ru.harrier55.project.filmography.ui.favorit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harrier55.project.filmography.data.room.CardFilmEntityFavoriteDb
import ru.harrier55.project.filmography.domain.repo.CardFilmRepoFavoriteDataBaseImpl

class FavoriteFragmentViewModel: ViewModel() {

    private val filmList: List<CardFilmEntityFavoriteDb> = mutableListOf()
    private val cardFilmRepoDb by lazy { CardFilmRepoFavoriteDataBaseImpl() }
    var myListFavoriteFilm = MutableLiveData<List<CardFilmEntityFavoriteDb>>()

     fun getData(){
        cardFilmRepoDb.getCardFilmListFromDataBase(onRequestCompleteDataBaseListener)
    }

    fun deleteCardFilm(cardFilmEntityFavoriteDb: CardFilmEntityFavoriteDb){
        cardFilmRepoDb.deleteCardFilm(cardFilmEntityFavoriteDb)
        getData()
    }

    /** инициализация интерфейса onRequestCompleteDataBaseListener, он сообщит,
     * что данные с Data Base пришли, т.к запрос асинхронный*/
    private val onRequestCompleteDataBaseListener = object: OnRequestCompleteDataBaseListener{
        override fun getDataFromDb(cardFilms: List<CardFilmEntityFavoriteDb>) {
            myListFavoriteFilm.postValue(cardFilms)
        }
    }
}

