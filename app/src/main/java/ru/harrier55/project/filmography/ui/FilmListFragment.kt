package ru.harrier55.project.filmography.ui


import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilmEntity

import ru.harrier55.project.filmography.databinding.FragmentListFilmBinding

import ru.harrier55.project.filmography.domain.FilmListFragmentViewModel


class FilmListFragment : Fragment() {

    private val TAG: String = "@@@"

    private var _binding: FragmentListFilmBinding? = null
    private val binding get() = _binding!!

    private lateinit var liveData: LiveData<List<CardFilmEntity>>

    private val viewModel by lazy { ViewModelProvider(this)[FilmListFragmentViewModel::class.java] }

    private lateinit var myAdapter: NowPlayingListAdapter

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
        myAdapter = NowPlayingListAdapter(myOnClickListener)
        _binding = FragmentListFilmBinding.bind(view)
        liveData = viewModel.getData() // получили LiveData


        
        binding.nowPlayingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.nowPlayingRecyclerView.adapter = myAdapter

        liveData.observe(viewLifecycleOwner, Observer {
            myAdapter.refreshListFilm(it) // где it - это список dataListFilm приходящий из LiveData

        })
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private var myOnClickListener = object : MyOnClickListener {
        override fun onClickItem() {
            Log.d(TAG, "Сработал обратный вызов из адаптера ")
        }
    }


    /**  демонстрация жизненного цикла из урока 3 (время 1,54 )

    lifecycle.addObserver(object: LifecycleObserver{
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun showLifeCycle(lifecycleOwner: LifecycleOwner,event: Lifecycle.Event){
    Toast.makeText(context,"Цикл"+event.name,Toast.LENGTH_SHORT).show()
    }
    })
     **/
}