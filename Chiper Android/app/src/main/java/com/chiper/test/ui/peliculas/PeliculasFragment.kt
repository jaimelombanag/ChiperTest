package com.chiper.test.ui.peliculas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chiper.test.R
import com.chiper.test.application.MyApp
import com.chiper.test.application.Resource
import com.chiper.test.data.model.Result
import com.chiper.test.data.room.MovieViewModel
import com.chiper.test.data.room.MovieViewModelFactory
import com.chiper.test.data.room.Movies
import com.chiper.test.data.source.DataSource
import com.chiper.test.databinding.FragmentPeliculasBinding
import com.chiper.test.presentation.PeliculasViewModel
import com.chiper.test.presentation.PeliculasViewModelFactory
import com.chiper.test.remote.RepositoryMovieImpl


class PeliculasFragment : Fragment(), AdapterMovies.onMovieClickListener, AdapterMoviesRoom.onMovieClickListenerRoom {

    private var _binding: FragmentPeliculasBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MyApp.instance.repository)
    }
    private val peliculaViewModel by viewModels<PeliculasViewModel> {
        PeliculasViewModelFactory(
        RepositoryMovieImpl(DataSource())
    )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeliculasBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding.swipeContainer.setColorSchemeResources(
//            android.R.color.holo_blue_bright,
//            android.R.color.holo_green_light,
//            android.R.color.holo_orange_light,
//            android.R.color.holo_red_light
//        )

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        //OnRefreshListener executes when layout is pull down to perform a refresh
        //binding.swipeContainer.setOnRefreshListener {onRefresh()}
        binding.btnRefresh.setOnClickListener { onRefresh() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupRecyclerView(){
        binding.rvMovieNowPlaying.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMovieNowPlaying.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }
    fun setupObservers(){
        peliculaViewModel.movieNowPlaying().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    binding.progressBar.visibility = View.GONE
                    binding.rvMovieNowPlaying.adapter = AdapterMovies(requireContext(),  it.data.data.results, this)
                    movieViewModel.delete()
                    saveMovies(it.data.data.results)
                }
                is Resource.Failure ->{
                    binding.progressBar.visibility = View.GONE
                    movieViewModel.allMovies.observe(this) { movie ->
                        Log.i("Movies", "------EL tamaño de la lista es: " + movie.size)
                        binding.rvMovieNowPlaying.adapter = AdapterMoviesRoom(requireContext(),  movie, this)
                    }
                    Toast.makeText(requireContext(), "Error extracion de datos ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun saveMovies(lista: MutableList<Result>){
        //var listMovies: MutableList<Movies> = mutableListOf<Movies>()
        for (i in lista.indices) {
            var movie = Movies(
                lista[i].title,
                lista[i].overview,
                lista[i].posterPath.toString(),
                lista[i].voteAverage,
                lista[i].releaseDate,
            )
            //listMovies.add(movie)
            movieViewModel.insert(movie)
        }
    }

    override fun onMovieClick(result: Result, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("result", result)
        findNavController().navigate(R.id.action_navigation_peliculas_to_peliculaDetalleFragment, bundle)
    }

    override fun onMovieClickRoom(movies: Movies, position: Int) {
        val bundle = Bundle()

        var result = Result(
            false,
            "",
            listOf(1, 2, 3, 4, 5),
            0,
            "",
            "",
            movies.overview,
            0.0,
            movies.posterPath,
            movies.releaseDate,
            movies.title,
            false,
            movies.voteAverage,
            0
        )
        binding.btnRefresh.visibility = View.GONE
        bundle.putParcelable("result", result)
        findNavController().navigate(R.id.action_navigation_peliculas_to_peliculaDetalleFragment, bundle)
    }

    fun onRefresh() {
        Log.i("Movie", "----Entra arefrescar")
        binding.rvMovieNowPlaying.adapter = AdapterMovies(requireContext(), mutableListOf(), this)
        //binding.swiperLayout.isRefreshing = false;
        setupRecyclerView()
        setupObservers()
    }

    override fun onResume() {
        binding.btnRefresh.visibility = View.VISIBLE
        super.onResume()
    }
}