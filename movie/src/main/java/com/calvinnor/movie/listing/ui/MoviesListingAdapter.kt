package com.calvinnor.movie.listing.ui

import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.calvinnor.core.extensions.*
import com.calvinnor.core.pagination.BottomPaginationAdapter
import com.calvinnor.core.pagination.PaginationListener
import com.calvinnor.core.pagination.PaginationViewHolder
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.commons.util.getBackdropImageTransitionName
import com.calvinnor.movie.commons.util.getBackgroundTransitionName
import com.calvinnor.movie.databinding.ItemMovieBinding

class MoviesListingAdapter(private val interactions: MoviesListingInteractions) :
    BottomPaginationAdapter<MovieUiModel>(interactions) {

    override fun onCreateVH(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMovieBinding.inflate(parent.layoutInflater(), parent, false)
    )

    override fun onBindVH(holder: PaginationViewHolder, position: Int) {
        if (holder is MovieViewHolder) holder.bind(dataItems[position], interactions)
    }

    override fun areItemsSame(oldItem: MovieUiModel, newItem: MovieUiModel) =
        oldItem.id == newItem.id

    class MovieViewHolder(private val viewBinding: ItemMovieBinding) :
        PaginationViewHolder(viewBinding.root) {

        fun bind(uiModel: MovieUiModel, interactions: MoviesListingInteractions) = with(itemView) {
            val movieId = uiModel.id
            val backgroundTransitionName = getBackgroundTransitionName(movieId)
            val backdropTransitionName = getBackdropImageTransitionName(movieId)

            viewBinding.ivBackdrop.apply {
                transitionName = backdropTransitionName
                setImage(
                    uiModel.backdropImage,
                    scaleType = ScaleType.CENTER_CROP,
                    onFailure = { viewBinding.ivBackdrop.defaultImage() },
                    fadeIn = false
                )
            }

            viewBinding.tvTitle.text = uiModel.title
            viewBinding.tvRelease.setTextOrGone(uiModel.releaseDate)

            viewBinding.clRoot.apply {
                transitionName = backgroundTransitionName
                setOnClickListener {
                    interactions.onMovieSelected(
                        uiModel, FragmentNavigatorExtras(
                            viewBinding.ivBackdrop to backdropTransitionName,
                            viewBinding.clRoot to backgroundTransitionName
                        )
                    )
                }
            }
        }
    }

    interface MoviesListingInteractions : PaginationListener {
        fun onMovieSelected(model: MovieUiModel, navigatorExtras: FragmentNavigator.Extras)
    }
}
