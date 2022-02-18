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
        mBinding.list.adapter = mAdapter.withLoadStateFooter(
            footer = LoadingGridStateAdapter()
        )
        mAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                AlertDialog.Builder(view.context)
                    .setTitle(R.string.error)
                    .setMessage(it.error.localizedMessage)
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(R.string.retry) { _, _ ->
                        mAdapter.retry()
                    }
                    .show()
            }
        }

        GlobalScope.launch {

            val db = Room.databaseBuilder(
                MyApp.context,
                MoviesDatabase::class.java, "movies"
            ).build()
                .moviesDao()

            val items = Pager(
                PagingConfig(
                    pageSize = 2,
                    enablePlaceholders = true,
                    maxSize = 150
                )
            ){
                db.getAlphabetizedWords()
            }.flow
            Log.i("Movies", "================   ${items.toString()}")
            items.collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
            Log.i("Movies", "================   ${db.getMovies().size}")


//            viewModelRoom.items.collectLatest {
//                Log.i("Movies", "================   $it")
//
//            items.collectLatest {
//                mAdapter.submitData(lifecycle, it)
//            }



//            }
        }


//        mDisposable.add(mViewModel.getFavoriteMovies().subscribe {
//            mAdapter.submitData(lifecycle, it)
//        })

        return view
    }

    class UiThreadExecutor: Executor {
        private val handler = Handler (Looper.getMainLooper ())
        override fun execute (command: Runnable) {
            handler.post (command)
        }
    }

    class ListDataSource (private val items: List<Movies.Movie>): PageKeyedDataSource<Int, Movies.Movie>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movies.Movie>) {
            callback.onResult (items, 0, items.size)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movies.Movie>) {

        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movies.Movie>) {

        }
    }

    override fun onDestroyView() {
        mDisposable.dispose()

        super.onDestroyView()
    }

}