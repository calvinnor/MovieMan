package com.calvinnor.core.pagination

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calvinnor.core.utils.DiffUtilCallback

/**
 * The [BottomPaginationAdapter] class provides an implementation to manage bottom data
 * insertion into a [RecyclerView] easy by handling all of the logic within.
 *
 * Handles showing the Loading element when pagination is requested.
 *
 * @param <DataItem> A class type that can used by the data adapter.
 */
abstract class BottomPaginationAdapter<DataItem : PaginatedItem>(
    private val listener: PaginationListener

) : RecyclerView.Adapter<PaginationViewHolder>() {

    protected val dataItems: MutableList<DataItem> = mutableListOf()

    private var onBottomRequested = false

    /**
     * Set a result page on this adapter.
     *
     * @param result The result from data sources. Also includes the type of O/p to perform.
     */
    fun setResult(result: Pagination.Result<DataItem>) {

        // Notify that we've removed the Loader
        notifyItemRemoved(itemCount - 1)

        when (result) {

            is Pagination.Result.NoData -> onNoData(result.existingElements)

            is Pagination.Result.ClearAll -> clearAllItems()

            is Pagination.Result.Replace -> replaceAll(result.newElements)

            is Pagination.Result.Append -> {
                when {
                    dataItems.isEmpty() -> replaceAll(result.allElements)
                    onBottomRequested -> addItemsAtBottom(result.newPage)

                    else -> { // Let DiffUtil handle this
                        diffResult(result).dispatchUpdatesTo(this)
                        dataItems.clear(); dataItems.addAll(result.allElements)
                    }
                }
            }
        }

        clearState()
    }

    /**
     * Override this method to provide custom View Types.
     */
    open fun getItemType(position: Int) = super.getItemViewType(position)

    /**
     * Override this method to create a ViewHolder
     */
    abstract fun onCreateVH(parent: ViewGroup, viewType: Int): PaginationViewHolder

    /**
     * Override this method to bind a ViewHolder
     */
    abstract fun onBindVH(holder: PaginationViewHolder, position: Int)

    /**
     * Override this method to provide a comparator for IDs
     */
    abstract fun areItemsSame(oldItem: DataItem, newItem: DataItem): Boolean

    // Returning +1 to show the Loader
    final override fun getItemCount() = dataItems.count() + 1

    final override fun getItemViewType(position: Int): Int {
        return if (position == dataItems.size) ITEM_TYPE_LOADER
        else getItemType(position)
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginationViewHolder {
        return if (viewType == ITEM_TYPE_LOADER) create(parent)
        else onCreateVH(parent, viewType)
    }

    final override fun onBindViewHolder(holder: PaginationViewHolder, position: Int) {
        if (holder is PaginationLoadingViewHolder) return
        onBindVH(holder, position)
        handlePagination(position)
    }

    /**
     * Clear all persisted states on this adapter.
     */
    private fun clearState() {
        onBottomRequested = false
    }

    private fun onNoData(existingItems: List<DataItem>) {
        if (dataItems.isEmpty()) {
            dataItems.addAll(existingItems)
            notifyDataSetChanged()
        }
    }

    private fun replaceAll(newItems: List<DataItem>) {
        dataItems.clear(); dataItems.addAll(newItems); notifyDataSetChanged()
    }

    private fun clearAllItems() {
        dataItems.clear(); notifyDataSetChanged()
    }

    /**
     * Appends the provided list at the bottom of the [RecyclerView]
     *
     * @param newList The list containing new elements for the [RecyclerView]
     */
    private fun addItemsAtBottom(newList: List<DataItem>) {
        val currentSize = dataItems.size
        dataItems.addAll(newList)
        notifyItemRangeInserted(currentSize, dataItems.size)
    }

    private fun handlePagination(position: Int) {
        if (onBottomRequested) return

        if (position + ADVANCE_ITEM_CALLBACK >= dataItems.count() - 1) {
            onBottomRequested = true
            onBottomReached(dataItems[dataItems.count() - 1], dataItems.count())
        }
    }

    private fun diffResult(result: Pagination.Result.Append<DataItem>) = object :
        DiffUtilCallback<DataItem>(oldList = dataItems, newList = result.allElements) {

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = areItemsSame(
            dataItems[oldItemPosition],
            result.allElements[newItemPosition]
        )

    }.getResult()

    /**
     * To be called when the last item of the [RecyclerView]'s data adapter is bounded to
     * the view.
     */
    open fun onBottomReached(lastItem: DataItem, offset: Int) {
        listener.onNewRequest(Pagination.Request(offset = offset.toLong()))
    }

    companion object {
        private const val ADVANCE_ITEM_CALLBACK = 3

        private const val ITEM_TYPE_LOADER = 1001
    }
}
