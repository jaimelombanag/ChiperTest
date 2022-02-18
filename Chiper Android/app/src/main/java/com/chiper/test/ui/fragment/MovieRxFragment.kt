package com.chiper.test.ui.fragment

import android.app.AlertDialog
import android.content.ClipData
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.*
import androidx.paging.rxjava2.mapAsync
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.chiper.test.R
import com.chiper.test.application.MyApp
import com.chiper.test.data.model.Result
import com.chiper.test.databinding.FragmentMovieListBinding
import com.chiper.test.ui.peliculas.AdapterMovies
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

class MovieRxFragment : Fragment() {


    private val mDisposable = CompositeDisposable()

    private lateinit var mBinding: FragmentMovieListBinding
    private lateinit var mViewModel: GetMoviesRxViewModel
    private lateinit var mAdapter: MoviesRxAdapter
    private lateinit var viewModelRoom: ViewModelRoom

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("Movies", "==============entra al fragment que es==============")

        mBinding = FragmentMovieListBinding.inflate(inflater, container, false)

        val view = mBinding.root

        activity?.title = getString(R.string.rx_with_paging_source)

        mViewModel = ViewModelProvider(this, Injection.provideRxViewModel(view.context)).get(
            GetMoviesRxViewModel::class.java)

        viewModelRoom = ViewModelProvider(this).get(ViewModelRoom::class.java)


        mAdapter = MoviesRxAdapter()

        mBinding.list.layoutManager = GridLayoutManager(view.context, 2)
        mBinding.list.adapter = mAdapter

        mAdapter.addLoadStateListener { loadState ->

            Log.i("Movies", "==========loadState  ${loadState.source.append}")
            Log.i("Movies", "==========loadState  ${loadState.source.prepend}")
            Log.i("Movies", "==========loadState  ${loadState.append}")
            Log.i("Movies", "==========loadState  ${loadState.prepend}")
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error


            if(!loadState.source.prepend.endOfPaginationReached || !loadState.prepend.endOfPaginationReached){
                GlobalScope.launch {
                    viewModelRoom.items.collectLatest {
                        mAdapter.submitData(lifecycle, it)
                    }
                 }
            }

//            errorState?.let {
//                AlertDialog.Builder(view.context)
//                    .setTitle(R.string.error)
//                    .setMessage(it.error.localizedMessage)
//                    .setNegativeButton(R.string.cancel) { dialog, _ ->
//                        dialog.dismiss()
//                    }
//                    .setPositiveButton(R.string.retry) { _, _ ->
//                        mAdapter.retry()
//                    }
//                    .show()
//            }
        }




        mDisposable.add(mViewModel.getFavoriteMovies().subscribe {
            Log.i("Movies", "===================  ${it}")
            mAdapter.submitData(lifecycle, it)
        })

        return view
    }



    override fun onDestroyView() {
        mDisposable.dispose()

        super.onDestroyView()
    }

}