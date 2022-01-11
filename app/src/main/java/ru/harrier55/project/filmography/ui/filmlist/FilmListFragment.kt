package ru.harrier55.project.filmography.ui.filmlist

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.harrier55.project.filmography.R

import ru.harrier55.project.filmography.databinding.FragmentListFilmBinding
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity
import ru.harrier55.project.filmography.util.MyAnalytic

class FilmListFragment : Fragment() {

    private val TAG: String = "@@@"

    private var _binding: FragmentListFilmBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this)[FilmListFragmentViewModel::class.java] }
    private val myCardFilmAdapter by lazy { NowPlayingListAdapter(onClickListenerCardFilm) }
    private val myNavigationButtonAdapter by lazy { FilmListNavigationButtonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: FilmListFragment")
        if (savedInstanceState == null) {
            viewModel.getData()
            viewModel.getDataKinopoisk()
        }
        MyAnalytic.writeLogFile(requireContext(), "start FilmListFragment")
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_film, container, false)
        _binding = FragmentListFilmBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nowPlayingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.nowPlayingRecyclerView.adapter = myCardFilmAdapter

        viewModel.myList.observe(viewLifecycleOwner, Observer {
            myCardFilmAdapter.refreshListFilm(it)
        })
        viewModel.errorList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                showMessageInSnackBar(view, it)
            }
        })

        /**навигация */

        binding.navigationFilmRecyclerView.layoutManager =
            GridLayoutManager(context, 4, RecyclerView.VERTICAL, false)
        binding.navigationFilmRecyclerView.adapter = myNavigationButtonAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    /**инициализируем интерфейс слушателя клика на карточку фильма*/
    private var onClickListenerCardFilm = object : OnClickListenerCardFilm {
        override fun onClickItemCardFilm(cardFilmEntity: CardFilmEntity) {
            Log.d(
                TAG,
                "FilmListFragment  onClickItemCardFilm:  myOnClickListener + " + cardFilmEntity.idKp.toString()
            )
            viewModel.addCardFilmToFavoriteFragment(cardFilmEntity)
        }
    }

    private fun showMessageInSnackBar(view: View, it: String) {
        Snackbar.make(view, it, Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.bottom_navigation)
            .setAction("Ok") {
                // todo возможное действие для кнопки ОК
            }
            .show()
    }

}