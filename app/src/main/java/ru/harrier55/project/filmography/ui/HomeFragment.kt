package ru.harrier55.project.filmography.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilmRepoImpl
import ru.harrier55.project.filmography.data.MyApp
import ru.harrier55.project.filmography.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//    private var cardFilmRepoImpl= CardFilmRepoImpl()  // перестал использовать
    private lateinit var cardFilmRepoImpl: CardFilmRepoImpl  // используем синглтон в Application
    private lateinit var adapter: NowPlayingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCardFilmRepoImpl()
        retainInstance = true
    }

    private fun initCardFilmRepoImpl(){
        cardFilmRepoImpl= (requireActivity().applicationContext as MyApp).getMyAppCardFilmRepoImpl()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)

        binding.nowPlayingRecyclerView.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.nowPlayingRecyclerView.adapter = NowPlayingAdapter(cardFilmRepoImpl.getCardFilmList())

//        val recyclerView: RecyclerView = view.findViewById(R.id.now_playing_recycler_view)  // используем биндинг
//        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//        recyclerView.adapter = NowPlayingAdapter(cardFilmRepoImpl.getCardFilmList())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
    }

    override fun onDestroyView() {
//        _binding = null
        super.onDestroyView()
    }

}