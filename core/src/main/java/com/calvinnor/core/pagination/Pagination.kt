package com.calvinnor.core.pagination

/**
 * The Pagination Request-Result framework.
 */
object Pagination {

    /**
     * A Pagination Request from sources.
     * @param offset The number of elements shown on screen.
     */
    data class Request(val offset: Long)

    /**
     * A Pagination Result for the UI.
     * @property T The type of elements that the adapter deals with.
     */
    sealed class Result<out T> {

        /**
         * Clear all the current displayed elements.
         */
        data class ClearAll<T>(val dummyParam: String = "") : Result<T>()

        /**
         * The source has no data to provide.
         * Ideally, the Adapter should clear it's flags at this point.
         */
        data class NoData<T>(val existingElements: List<T>) : Result<T>()

        /**
         * Replace all the elements with this new set.
         * @param newElements The set of elements to show.
         */
        data class Replace<T>(val newElements: List<T>) : Result<T>()

        /**
         * Append these elements below the original list.
         * @param allElements The complete set of items.
         * @param newPage The new set of elements (page) to append on screen.
         */
        data class Append<T>(val allElements: List<T>, val newPage: List<T>) : Result<T>()

    }
}
