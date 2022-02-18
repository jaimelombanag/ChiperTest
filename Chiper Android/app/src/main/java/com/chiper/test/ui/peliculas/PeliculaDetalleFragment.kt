package com.chiper.test.ui.peliculas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chiper.test.R
import com.chiper.test.application.Constants
import com.chiper.test.data.model.Movie
import com.chiper.test.data.model.Result
import com.chiper.test.databinding.FragmentPeliculaDetalleBinding
import com.chiper.test.ui.fragment.Movies


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PeliculaDetalleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var result: Movies.Movie
    private var _binding: FragmentPeliculaDetalleBinding? = null
    private val binding get() = _binding!!
    private var favorite = false

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
        _binding = FragmentPeliculaDetalleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().let {
            result = it.getParcelable("result")!!
            Glide.with(requireContext()).load("${Constants.IMG_MOVIE_DB}${result.poster}").centerCrop().into(binding.imgPosterMovie)
            binding.tvTitleMovie.text = "${result.title}"
            binding.tvRateMovie.text = "Calificación: ${result.voteAverage}"
            binding.tvDateMovie.text = "Fecha: ${result.releaseDate}"
            binding.tvDescripcionDetalle.text = "${result.overview}"
        }

        _binding!!.favotiteBtn.setOnClickListener {
            if(favorite){
                _binding!!.favotiteBtn.setBackgroundResource(R.drawable.ic_favorite)
                favorite = false
            }else{
                favorite = true
                _binding!!.favotiteBtn.setBackgroundResource(R.drawable.ic_favorite_shadow)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PeliculaDetalleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}