package com.calvinnor.core.pagination

/**
 * Interface for Paginated Adapters to make requests.
 * These should be fulfilled by Sources.
 */
interface PaginationListener {

    fun onNewRequest(request: Pagination.Request)

}
