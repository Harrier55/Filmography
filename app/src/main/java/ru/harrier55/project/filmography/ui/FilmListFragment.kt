package ru.harrier55.project.filmography.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilm
import ru.harrier55.project.filmography.databinding.FragmentListFilmBinding
import ru.harrier55.project.filmography.domain.FilmListContract
import ru.harrier55.project.filmography.domain.FilmListPresenter


class FilmListFragment : Fragment(), FilmListContract.View {
//    private lateinit var binding:  FilmListFragment //заменил инициализацию,чтобы исключить ошибки при пересоздании фрагмента

    private var _binding: FragmentListFilmBinding? = null
    private val binding get() = _binding!!

    private var dataList: List<CardFilm> = mutableListOf()
    private lateinit var presenter: FilmListContract.Presenter
//    private var cardFilmRepoImpl= CardFilmRepoImpl()  // перестал использовать,заменил на Application
//    private lateinit var cardFilmRepoImpl: CardFilmRepoImpl  // теперь используем синглтон в Application

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FilmListPresenter()
        presenter.attach(this)
        presenter.getDataForFilmList()  // получение данных через  презентера
//        initCardFilmRepoImpl() // получение данных из Арр напрямую
        retainInstance = true
    }

    override fun setData(list: List<CardFilm>) {
        dataList = list
    }

    override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?
                            ): View? {
        val view = inflater.inflate(R.layout.fragment_list_film, container, false)
        _binding = FragmentListFilmBinding.bind(view)

        binding.nowPlayingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.nowPlayingRecyclerView.adapter = NowPlayingAdapter(dataList)
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}