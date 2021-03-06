package ru.harrier55.project.filmography.ui.favorit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.room.CardFilmEntityFavoriteDb
import ru.harrier55.project.filmography.databinding.FragmentFavoriteBinding
import ru.harrier55.project.filmography.domain.repo.CardFilmRepoFavoriteDataBaseImpl


class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private val myAdapter by lazy { FavoriteListAdapter(onClickItemFavoriteFragment) }
    private var cardFilms: List<CardFilmEntityFavoriteDb> = mutableListOf()
    private val cardFilmRepo by lazy { CardFilmRepoFavoriteDataBaseImpl() }
    private val viewModel by lazy { ViewModelProvider(this)[FavoriteFragmentViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        binding = FragmentFavoriteBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.favoriteRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding!!.favoriteRecyclerView.adapter = myAdapter

        viewModel.myListFavoriteFilm.observe(viewLifecycleOwner, Observer {
            myAdapter.refreshList(it)
        })
    }

    /**Инициализация интерфейса слушателя */
    private val onClickItemFavoriteFragment = object : OnClickItemFavoriteFragment {
        override fun onClickDeleteButton(cardFilmEntityFavoriteDb: CardFilmEntityFavoriteDb) {
            viewModel.deleteCardFilm(cardFilmEntityFavoriteDb)
        }
    }


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}

