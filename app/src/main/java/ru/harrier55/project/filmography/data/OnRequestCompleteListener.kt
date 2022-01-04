package ru.harrier55.project.filmography.data

import KinopoiskReview
import com.example.example.KinopoiskMovie

/**интерфейс инициализирован в FilmListFragmentViewModel и реализован в WebConnectionOkHttp
 * и WebConnectionRetrofit для уведомления, что данные с Web пришли, т.к. запрос асинхронный**/

interface OnRequestCompleteListener {
    fun onSuccessReview(kinopoiskReview: KinopoiskReview)
    fun onSuccessMovie(kinopoiskMovie: KinopoiskMovie)
    fun onError()
}