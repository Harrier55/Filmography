package ru.harrier55.project.filmography.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.data.CardFilmRepoImpl
import ru.harrier55.project.filmography.domain.NowPlayingAdapter


class HomeFragment : Fragment() {

    private  var cardFilm = CardFilm()
    private var cardFilmRepoImpl= CardFilmRepoImpl()


    private lateinit var binding: HomeFragment
    private lateinit var adapter: NowPlayingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        generateTestRepo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.now_playing_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = NowPlayingAdapter(cardFilmRepoImpl.getCardFilmList())
        return view
    }


    private fun generateTestRepo(){

        cardFilmRepoImpl.createdCardFilm(CardFilm(R.drawable.star_wars,"Что то иное","hjfhjk","jhfjkfhk"))
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(CardFilm(R.drawable.fox,"Зверополис","2018","8,6"))
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)
        cardFilmRepoImpl.createdCardFilm(cardFilm)


    }


}