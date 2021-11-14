package ru.harrier55.project.filmography.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilm

import ru.harrier55.project.filmography.databinding.FragmentListFilmBinding

import ru.harrier55.project.filmography.domain.FilmListFragmentViewModel


class FilmListFragment : Fragment() {

    private var _binding: FragmentListFilmBinding? = null
    private val binding get() = _binding!!
    private var dataListFilm: List<CardFilm> = mutableListOf()
    private lateinit var liveDataList: LiveData<List<CardFilm>>
    private val viewModelFilmListFragment by lazy { ViewModelProvider(this).get(FilmListFragmentViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        dataListFilm= MyApp.instance.getMyAppCardFilmRepoImpl().getCardFilmList()  // для тестового запуска
        retainInstance = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

         val view = inflater.inflate(R.layout.fragment_list_film, container, false)

        _binding = FragmentListFilmBinding.bind(view)

        liveDataList = viewModelFilmListFragment.getData() // получили LiveData

        binding.nowPlayingRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.nowPlayingRecyclerView.adapter = NowPlayingListAdapter(dataListFilm)

        liveDataList.observe(viewLifecycleOwner, Observer {

            binding.nowPlayingRecyclerView.adapter = NowPlayingListAdapter(it)         // где it - это список dataListFilm приходящий из LiveData
        })
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    // демонстрация жизненного цикла из урока 3 (время 1,54 )
//        lifecycle.addObserver(object: LifecycleObserver{
//            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//            fun showLifeCycle(lifecycleOwner: LifecycleOwner,event: Lifecycle.Event){
//                Toast.makeText(context,"Цикл"+event.name,Toast.LENGTH_SHORT).show()
//            }
//        })

}