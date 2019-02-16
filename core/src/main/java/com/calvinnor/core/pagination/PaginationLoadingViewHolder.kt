package com.calvinnor.core.pagination

import android.view.View
import android.view.ViewGroup
import com.calvinnor.core.R
import com.calvinnor.core.extensions.inflate

/**
 * Custom ViewHolder for showing a Loader.
 */
class PaginationLoadingViewHolder(rootView: View) : PaginationViewHolder(rootView)

fun create(parent: ViewGroup) = PaginationLoadingViewHolder(parent.inflate(R.layout.item_loading))
