package ru.harrier55.project.filmography.ui.filmlist

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.harrier55.project.filmography.R

import ru.harrier55.project.filmography.databinding.FragmentListFilmBinding

import ru.harrier55.project.filmography.domain.FilmListFragmentViewModel
import ru.harrier55.project.filmography.ui.filmlist.MyOnClickListener
import ru.harrier55.project.filmography.ui.filmlist.NowPlayingListAdapter

class FilmListFragment : Fragment() {

    private val TAG: String = "@@@"

    private var _binding: FragmentListFilmBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[FilmListFragmentViewModel::class.java] }
    private var myOnClickListener = object : MyOnClickListener {
        override fun onClickItem() {
            Log.d(TAG, "FilmListFragment  onClickItem:  myOnClickListener")
            viewModel.getData()
        }
    }
    private val myAdapter by lazy { NowPlayingListAdapter(myOnClickListener) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: FilmListFragment")
        if(savedInstanceState == null) {
            viewModel.getData()
            viewModel.getDataKinopoisk()
        }
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: FilmListFragment ")

        val view = inflater.inflate(R.layout.fragment_list_film, container, false)

        _binding = FragmentListFilmBinding.bind(view)

        binding.nowPlayingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.nowPlayingRecyclerView.adapter = myAdapter

        viewModel.myList.observe(viewLifecycleOwner, Observer {
            myAdapter.refreshListFilm(it)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel.errorList.observe(viewLifecycleOwner, Observer {
             showMessageInSnackBar(view, it)
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showMessageInSnackBar(view: View, it:String){
        Snackbar.make(view,it,Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.bottom_navigation)
            .setAction("Ok") {
                // todo
            }
            .show()
    }

}