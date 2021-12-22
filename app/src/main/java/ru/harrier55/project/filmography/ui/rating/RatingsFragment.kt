package ru.harrier55.project.filmography.ui.rating

import KinopoiskBase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.*

import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.databinding.FragmentRatingsBinding
import ru.harrier55.project.filmography.util.MyAnalytic
import java.io.File
import java.io.IOException
import java.lang.Exception


class RatingsFragment : Fragment() {

    private val TAG = "@@@"
    private val fileName = "log_application.txt"
    private var _binding: FragmentRatingsBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_ratings, container, false)
        _binding = FragmentRatingsBinding.bind(view)

        binding.testLoggerButton.setOnClickListener{
            MyAnalytic.writeLogFile(requireContext(),"Test event")
       }

        binding.readLogFileButton.setOnClickListener {
            val fileObject = File(requireContext().filesDir,fileName)
            try {
                val content = fileObject.readText()
                binding.loadResultTV.text = content
            } catch (e: Exception) {
                binding.loadResultTV.text = "file is empty"
            }
        }

        binding.deleteLogFileButton.setOnClickListener {
            val fileObj = MyAnalytic.getFileObject(requireContext())
            MyAnalytic.deleteLogFile(fileObj)
        }

        return view
    }


}

