package com.example.mynavigation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynavigation.R
import com.example.mynavigation.adapter.MovieAdapter
import com.example.mynavigation.retrofit.Endpoint
import com.example.mynavigation.viewmodel.MovieModel
import kotlinx.coroutines.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
    lateinit var movieModel: MovieModel
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieModel = ViewModelProvider(requireActivity()).get(MovieModel::class.java)
        val button = view.findViewById(R.id.button2) as Button
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        recyclerView = view.findViewById(R.id.recyclerView2) as RecyclerView
        val layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = layoutManager

        button.setOnClickListener {
            if(movieModel.data.isEmpty()) {
                loadMovies()
            }
            else {
                recyclerView.adapter = MovieAdapter(requireActivity(),movieModel.data)
            }
        }


    }

    fun loadMovies() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), "Une erreur s'est  ", Toast.LENGTH_SHORT).show()
            }
        }

        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch(exceptionHandler) {
            try {
                val response = Endpoint.createEndpoint().getAllMovies()

                if (response.isSuccessful && response.body() != null) {
                    val movieResponse = response.body()!!
                    val movies = movieResponse.data
                    // Process the movies as needed
                    movieModel.data = movies.toMutableList()
                    recyclerView.adapter = MovieAdapter(requireActivity(), movieModel.data)
                    progressBar.visibility = View.INVISIBLE
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                progressBar.visibility = View.INVISIBLE
                val errorMessage = "Une erreur s'est produite: ${e.message}"
                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }


    }


}