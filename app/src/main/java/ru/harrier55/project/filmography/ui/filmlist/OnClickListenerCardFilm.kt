package ru.harrier55.project.filmography.ui.filmlist

import ru.harrier55.project.filmography.domain.entities.CardFilmEntity

/**интерфейс инициализирован в FilmListFragment и реализует callback NowPlayingListAdapter*/

interface OnClickListenerCardFilm {
    fun onClickItemCardFilm(cardFilmEntity: CardFilmEntity)
}