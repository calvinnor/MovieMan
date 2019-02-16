package com.calvinnor.movie.discover.ui

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.calvinnor.core.extensions.ScaleType
import com.calvinnor.core.extensions.inflate
import com.calvinnor.core.extensions.setImage
import com.calvinnor.movie.R
import com.calvinnor.movie.details.ui.MovieDetailsFragmentArgs
import com.calvinnor.movie.discover.model.MovieUiModel
import kotlinx.android.synthetic.main.item_movie.view.*

class DiscoverMoviesAdapter : RecyclerView.Adapter<DiscoverMoviesAdapter.MovieViewHolder>() {

    private val dataItems: MutableList<MovieUiModel> = mutableListOf()

    fun setItems(newItems: List<MovieUiModel>) {
        dataItems.run {
            clear(); addAll(newItems)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        parent.inflate(R.layout.item_movie)
    )

    override fun getItemCount() = dataItems.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(dataItems[position])

    class MovieViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

        fun bind(uiModel: MovieUiModel) = with(itemView) {
            ivBackdrop.setImage(uiModel.backdropImage, scaleType = ScaleType.CENTER_CROP)
            tvTitle.text = uiModel.title

            clRoot.setOnClickListener {

                findNavController().navigate(
                    R.id.navigateToMovieDetails,
                    MovieDetailsFragmentArgs(movieId = uiModel.id).toBundle())
            }
        }
    }
}