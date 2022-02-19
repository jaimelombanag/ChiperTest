package com.chiper.test.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.chiper.test.viewholder.LoadingGridStateViewHolder


class LoadingGridStateAdapter: LoadStateAdapter<LoadingGridStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadingGridStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingGridStateViewHolder {
        return LoadingGridStateViewHolder.create(parent)
    }
}